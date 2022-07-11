package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.utils.TextUtils;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MotdManager {

    private final ShieldMotd plugin;
    private final Random random;
    private List<TextComponent> motds;
    private List<TextComponent> legacyMotds;
    private boolean limitBadMotds;
    private boolean denyProtocols;

    public MotdManager(ShieldMotd plugin) {
        this.plugin = plugin;
        this.random = new Random();
    }

    public void setupMotd() {
        ///fixme: Temporary fix for the motd bug, will be removed in the future @Jonakls
        motds = plugin.getConfigurationManager()
            .getConfig()
            .getStringList("motd.lines")
            .stream()
            .map(TextUtils::toBungeeComponent)
            .collect(Collectors.toList());
        legacyMotds = plugin.getConfigurationManager()
            .getConfig()
            .getStringList("motd.lines")
            .stream()
            .map(TextUtils::toLegacyBungeeComponent)
            .collect(Collectors.toList());
    }

    public TextComponent getMotd() {
        return motds.get(random.nextInt(motds.size()));
    }

    public TextComponent getLegacyMotd() {
        return legacyMotds.get(random.nextInt(legacyMotds.size()));
    }

    public void setMotd(ServerPing ping, int protocol) {
        if (protocol < 735) {
            // Legacy client(<= 1.15.2) does not need an HEX Motd
            ping.setDescriptionComponent(getLegacyMotd());
        } else {
            // Modern client(1.16+)
            ping.setDescriptionComponent(getMotd());
        }
    }

    public boolean isLimitBadMotds() {
        return limitBadMotds;
    }

    public boolean isDenyProtocol() {
        return denyProtocols;
    }
}
