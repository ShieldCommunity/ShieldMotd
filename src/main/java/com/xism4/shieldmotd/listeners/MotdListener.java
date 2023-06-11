package com.xism4.shieldmotd.listeners;

import com.xism4.shieldmotd.ShieldMotd;
import io.netty.channel.ChannelHandlerContext;
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
    public void proxyHandler(ProxyPingEvent event, ChannelHandlerContext ctx) {

        ServerPing pingHandler = event.getResponse();

        if (pingHandler == null) {
            ctx.channel().close();
            core.getLogger().warning("Invalid ping response");
            return;
        }

        core.getMotdManager().setMotd(pingHandler, event.getResponse().getVersion().getProtocol());
    }
}
