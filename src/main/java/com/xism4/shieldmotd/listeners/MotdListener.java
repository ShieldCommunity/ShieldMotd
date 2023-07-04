package com.xism4.shieldmotd.listeners;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.manager.MotdManager;
import io.netty.channel.ChannelHandlerContext;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
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

        final MotdManager motdManager = core.getMotdManager();
        ServerPing pingHandler = event.getResponse();

        if (pingHandler == null) {
               event.getConnection().disconnect(new TextComponent(
                       "Invalid ping?")
               );
            core.getLogger().warning("Invalid ping response");
            return;
        }

        ServerPing.Players player = pingHandler.getPlayers();
        ServerPing.Protocol protocolHandler = pingHandler.getVersion();

        if(motdManager.versionHandlerCheck()) {
            protocolHandler.setName(motdManager.versionHandler());
        }

        if(motdManager.hidePlayerHandler()) {
                    pingHandler.getPlayers().setOnline(0);
        }

        if(motdManager.playerInfoHandlerCheck()) {
            player.setSample(new ServerPing.PlayerInfo[]{
                    new ServerPing.PlayerInfo(motdManager.playerInfoHandler(),
                            protocolHandler.getName())
            });

        }

        core.getMotdManager().setMotd(pingHandler, event.getResponse().getVersion().getProtocol());
    }

}
