package com.xism4.shieldmotd.listeners;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.config.ShieldMotdConfig;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MotdListener implements Listener {

    private final ShieldMotd core;

    public MotdListener(ShieldMotd core) {
        this.core = core;
    }

    @EventHandler(priority = 64)
    public void proxyHandler(ProxyPingEvent event) {

        ServerPing pingHandler = event.getResponse();

        if (pingHandler == null) {
            event.getConnection().disconnect();
            return;
        }

        ServerPing.Protocol protocolHandler = pingHandler.getVersion();

        if (ShieldMotdConfig.IMP.VERSION.ENABLED) {
            protocolHandler.setName(ShieldMotdConfig.IMP.VERSION.NAME);
        }

        if (ShieldMotdConfig.IMP.PLAYER_INFO.ENABLED) {
            ServerPing.Players player = pingHandler.getPlayers();
            player.setSample(new ServerPing.PlayerInfo[] {
                    new ServerPing.PlayerInfo(ShieldMotdConfig.IMP.PLAYER_INFO.INFO, protocolHandler.getName())
            });
        }

        core.getMotdManager().setMotd(pingHandler, protocolHandler.getProtocol());
        //core.getLogger().info("Protocol response " + event.getResponse().getVersion().getProtocol() + " for " + event.getConnection().getName()); why even log this info?
    }

}
