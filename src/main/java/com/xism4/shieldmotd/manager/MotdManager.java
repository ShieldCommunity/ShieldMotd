package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.utils.TextUtils;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MotdManager {

    private final ShieldMotd plugin;
    private final Random random;
    private List<TextComponent> motds;
    private List<TextComponent> legacyMotds;


    public MotdManager(ShieldMotd plugin) {
        this.plugin = plugin;
        this.random = new Random();
        setupMotd();
    }

    public void setupMotd() {
        motds = plugin.getConfigurationManager()
                .getConfig()
                .getStringList("motd.lines")
                .stream()
                .map(TextUtils::toModernComponent)
                .collect(Collectors.toList());
        legacyMotds = plugin.getConfigurationManager()
                .getConfig()
                .getStringList("motd.lines")
                .stream()
                .map(TextUtils::toLegacyComponent)
                .collect(Collectors.toList());
    }

    public BaseComponent getMotd() {
        return motds.get(random.nextInt(motds.size()));

    }

    public BaseComponent getLegacyMotd() {
        return legacyMotds.get(random.nextInt(legacyMotds.size()));
    }

    public void setMotd(ServerPing ping, int protocol) {
        if (protocol < 735) {
            // Legacy client(<= 1.15.2) does not need an HEX Motd
            ping.setDescriptionComponent(getLegacyMotd());
        }
        // Modern client(1.16+)
        ping.setDescriptionComponent(getMotd());
    }

    public boolean oneMoreHandler() {
        return plugin.getConfigurationManager().getConfig().getBoolean("motd.one-more-player");
    }

    public String versionHandler() {
        return plugin.getConfigurationManager().getConfig().getString("motd.version-name");
    }

    public boolean versionHandlerCheck(){
        return plugin.getConfigurationManager().getConfig().getBoolean("motd.version-name.override-name");
    }

    public boolean playerInfoHandlerCheck(){
        return plugin.getConfigurationManager().getConfig().getBoolean("motd.player-info.enabled");
    }

    public String playerInfoHandler(){
        return plugin.getConfigurationManager().getConfig().getString("motd.player-info");
    }

    @Override
    public String toString() {
        return "MotdManager[modernMotds="+motds+"legacyMotds+"+legacyMotds+"]";
    }
}
