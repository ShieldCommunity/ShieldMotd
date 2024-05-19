package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.config.ShieldMotdConfig;
import com.xism4.shieldmotd.utils.FastRandom;
import com.xism4.shieldmotd.utils.TextUtils;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MotdManager {

    private Random random;
    private List<TextComponent> motds;
    private List<TextComponent> legacyMotds;
    private Map<Integer, List<TextComponent>> protocolMotds = new Int2ObjectOpenHashMap<>();

    public MotdManager() {
        random = FastRandom.getFastRandom();
        this.random = FastRandom.getFastRandom();
        setupMotd();
    }

    public void setupMotd() {
        List<String> lines = ShieldMotdConfig.IMP.MOTD.LINES;

        motds = lines.stream()
                .map(TextUtils::toModernComponent)
                .toList();

        legacyMotds = lines.stream()
                .map(TextUtils::toLegacyComponent)
                .toList();

        if (ShieldMotdConfig.IMP.MOTD.PROTOCOL_LINES.keySet().isEmpty()) {
            return;
        }
        for (String protocol : ShieldMotdConfig.IMP.MOTD.PROTOCOL_LINES.keySet()) {
            if (protocol == null || protocol.isEmpty()) {
                continue;
            }
            protocol = protocol.trim();
            List<String> protocolLines = ShieldMotdConfig.IMP.MOTD.PROTOCOL_LINES.get(protocol);
            String[] splitRange = protocol.split("-");
            try {
                if (splitRange.length == 2) {
                    int firstValue = Integer.parseInt(splitRange[0]);
                    int secondValue = Integer.parseInt(splitRange[1]);
                    int lowerBound = Math.min(firstValue, secondValue);
                    int upperBound = Math.max(firstValue, secondValue);
                    for (int i = lowerBound; i <= upperBound; i++) {
                        setProtocolMotds(i, protocolLines);
                    }
                } else {
                    int protocolNumber = Integer.parseInt(protocol);
                    setProtocolMotds(protocolNumber, protocolLines);
                }
            } catch (NumberFormatException  ex) {
                ProxyServer.getInstance().getLogger().warning("Invalid protocol format: " + protocol);
            }
        }
    }

    private void setProtocolMotds(int protocolNumber, List<String> protocolLines) {
        if (protocolNumber < 735) {
            protocolMotds.put(protocolNumber, protocolLines.stream()
                    .map(TextUtils::toLegacyComponent)
                    .toList());
        } else {
            protocolMotds.put(protocolNumber, protocolLines.stream()
                    .map(TextUtils::toModernComponent)
                    .toList());
        }
    }

    public BaseComponent getRandomMotd(boolean isLegacy) {
        if (isLegacy) {
            return legacyMotds.get(random.nextInt(legacyMotds.size()));
        }
        return motds.get(random.nextInt(motds.size()));
    }

    public void setMotd(ServerPing ping, int protocol) {
        if (protocolMotds.containsKey(protocol)) {
            ping.setDescriptionComponent(protocolMotds.get(protocol).get(random.nextInt(protocolMotds.get(protocol).size())));
            return;
        }
        ping.setDescriptionComponent(getRandomMotd(protocol < 735));
    }

    @Override
    public String toString() {
        return "MotdManager[modernMotds="+motds+"legacyMotds+"+legacyMotds+"protocolMotds="+protocolMotds+"]";
    }
}
