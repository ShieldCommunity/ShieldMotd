package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.ShieldMotd;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigurationManager {

    private final ShieldMotd plugin;
    private Configuration config;
    private final File file;
    private final String fileName;

    public ConfigurationManager(ShieldMotd plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        createFile();
    }

    public void makeConfig() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
            plugin.getLogger().info("Configuration manager folder ->: " + plugin.getDataFolder().mkdir());
        }

        if (!file.exists()) {
            try (InputStream in = plugin.getResourceAsStream(fileName)) {
                Files.copy(in, this.file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile() {
        makeConfig();
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        File loadedFiles = new File(plugin.getDataFolder(), fileName);
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(loadedFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

