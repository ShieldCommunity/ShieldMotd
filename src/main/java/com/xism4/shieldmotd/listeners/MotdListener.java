package com.xism4.shieldmotd.listeners;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.manager.ByteBuff;
import com.xism4.shieldmotd.manager.ConfigurationHandler;
import com.xism4.shieldmotd.manager.IChannelWrapper;
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
    public void proxyHandler(ProxyPingEvent event, IChannelWrapper channelWrapper) {

        ServerPing pingHandler = event.getResponse();

        if (pingHandler == null
                && ConfigurationHandler.IMP.MOTD.CLOSE_ON_INVALID_PING) {
            channelWrapper.close();
            core.getLogger().warning(
                    "Invalid ping response"
            );
            return;
        }

        core.getMotdManager().setMotd(pingHandler,
                event.getResponse().getVersion().getProtocol());
    }
}
