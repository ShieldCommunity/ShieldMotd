package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.utils.TextUtils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ConfigurationManager {

    private final ShieldMotd plugin;
    private Configuration config;
    private List<BaseComponent> motds;
    private final Path path;
    private final String fileName;

    public ConfigurationManager(ShieldMotd plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.path = plugin.getDataFolder().toPath().resolve(fileName);
        createFile();
    }

    public void makeConfig() {
        Path dataFolder = plugin.getDataFolder().toPath();
        if (Files.notExists(dataFolder)) {
            try {
                Files.createDirectory(dataFolder);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "An error ocurred initializating configuration", e);
                return;
            }
        }

        if (Files.notExists(path)) {
            try (InputStream in = plugin.getResourceAsStream(fileName)) {
                Files.copy(in, this.path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile() {
        makeConfig();
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(path.toFile());
            loadMotds();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .save(config, path.toFile());
            loadMotds();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(path.toFile());
            loadMotds();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMotds() {
        ///fixme: Temporary fix for the motd bug, will be removed in the future @Jonakls
        motds = config.getStringList("motd.lines")
            .stream()
            .map(TextUtils::toBungeeComponent)
            .collect(Collectors.toList());
    }

    public Configuration getConfig() {
        return config;
    }

    public List<BaseComponent> getMotds() {
        return this.motds;
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public String getStringList(String path) {
        return String.valueOf(config.getStringList(path));
    }
}

