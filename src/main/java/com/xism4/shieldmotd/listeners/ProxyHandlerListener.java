package com.xism4.shieldmotd.listeners;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.manager.ChannelHandlerManager;
import com.xism4.shieldmotd.manager.ConfigurationManager;
import com.xism4.shieldmotd.utils.FastMotdException;
import io.netty.channel.ChannelHandlerContext;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.protocol.BadPacketException;
import net.md_5.bungee.protocol.OverflowPacketException;

import static com.xism4.shieldmotd.utils.TextUtils.toComponent;

public class ProxyHandlerListener implements Listener {

    private final ShieldMotd core;
    private static long lastInitialException;
    private Throwable cause;

    public ProxyHandlerListener(ShieldMotd core) {
        this.core = core;
    }

    @EventHandler(priority = 64)
    public void proxyHandler(ProxyPingEvent event) {

        ConfigurationManager configurationManager = core.getConfigurationManager();
        ChannelHandlerManager channelHandlerManager = core.getChannelHandlerManager();
        ServerPing pingHandler = event.getResponse();
        String address = event.getConnection().getAddress().getAddress().getHostAddress();

        if (pingHandler == null) {
            channelHandlerManager.closeChannel((ChannelHandlerContext) this, address);
            core.getLogger().warning("An handshake was null and cancelled");
            return;
        }

        if (cause instanceof FastMotdException || cause instanceof BadPacketException || cause instanceof OverflowPacketException) {
            channelHandlerManager.closeChannel((ChannelHandlerContext) this, address);
            core.getLogger().log(Level.INFO,
                    "The connection -> {0} rate-limited bad-joins, we suggest blacklist", address);
            return;
        }

        //TODO: Temporary fix for the motd bug, will be removed in the future @Jonakls
        List<String> motd = configurationManager.getConfig().getStringList("motd.lines");
        Random random = new Random();
        String motdLine = motd.get(random.nextInt(motd.size()));

        pingHandler.setDescriptionComponent(toComponent(motdLine));


    }
}
