package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.ShieldMotd;

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

    public String getMotd() {
        //TODO: return single motd and processed with random selection
        return "JA";
    }

    public boolean isLimitBadMotds() {
        return limitBadMotds;
    }

    public boolean isDenyProtocol() {
        return denyProtocols;
    }
}
