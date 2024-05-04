package com.xism4.shieldmotd.config;

import com.xism4.shieldmotd.libs.net.elytrium.serializer.SerializerConfig;
import com.xism4.shieldmotd.libs.net.elytrium.serializer.language.object.YamlSerializable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SafeYamlSerializable extends YamlSerializable {
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    private Logger logger = Logger.getLogger("BungeeCord");

    public SafeYamlSerializable(Path path, SerializerConfig config) {
        super(path, config);
    }

    public SafeYamlSerializable(SerializerConfig config) {
        super(config);
    }

    public boolean load(BufferedReader reader) {
        try {
            return super.load(reader);
        } catch (Throwable e) {
            this.logger.log(Level.SEVERE, "Failed to load config " + getClass().getSimpleName(), e);
            return false;
        }
    }

    public void save(BufferedWriter writer) {
        try {
            super.save(writer);
        } catch (Throwable e) {
            this.logger.log(Level.SEVERE, "Failed to save config " + getClass().getSimpleName(), e);
        }
    }
}
