package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.ShieldMotd;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.Random;

public class MotdManager {

    private final ShieldMotd plugin;
    private Random random;
    private boolean limitBadMotds;
    private boolean denyProtocols;

    public MotdManager(ShieldMotd plugin) {
        //TODO: implement system for motd here, create individual system for make a correct structure for motd
        this.plugin = plugin;
    }

    public void setupMotd() {
        //TODO: setup all motds list from config file
    }

    public void makeMotd() {
        //TODO: make one motd list and split lines with \n value from config file
    }

    public BaseComponent[] getMotd() {
        //TODO: return single motd and processed with random selection
        
        return BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("a"));
    }

    public boolean isLimitBadMotds() {
        return limitBadMotds;
    }

    public boolean isDenyProtocol() {
        return denyProtocols;
    }
}
