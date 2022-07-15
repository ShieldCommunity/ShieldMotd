package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.ShieldMotd;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.logging.Level;

public class ConfigurationManager {

    private final ShieldMotd plugin;
    private Configuration config;
    private final Path path;
    private final Path dataFolder;
    private final String fileName;

    public ConfigurationManager(ShieldMotd plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.dataFolder = plugin.getDataFolder().toPath();
        this.path = dataFolder.resolve(fileName);
        createFile();
    }

    public void makeConfig() {
        if (Files.notExists(dataFolder)) {
            try {
                Files.createDirectory(dataFolder);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "An error ocurred loading the plugin data folder", e);
                return;
            }
        }

        if (Files.notExists(path)) {
            try (InputStream in = plugin.getResourceAsStream(fileName)) {
                Files.copy(Objects.requireNonNull(in, "The internal configuration cannot be null"), this.path);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "An error ocurred initializating configuration", e);
            }
        }
    }

    private void createFile() {
        makeConfig();
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .save(config, path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfig() {
        return config;
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

