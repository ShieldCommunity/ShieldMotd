package com.xism4.shieldmotd.listeners;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.manager.ChannelHandlerManager;
import io.netty.channel.ChannelHandlerContext;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.protocol.PacketWrapper;
import net.md_5.bungee.protocol.packet.Handshake;

import java.util.logging.Level;

public class LoginHandlerListener implements Listener {

    private final ShieldMotd core;
    private PacketWrapper packet;
    private Handshake handshake;

    public LoginHandlerListener(ShieldMotd core) {
        this.core = core;
    }

    @EventHandler
    public void loginHandler(LoginEvent event) {
        ChannelHandlerManager channelHandlerManager = core.getChannelHandlerManager();
        String address = event.getConnection().getAddress().getAddress().getHostAddress();
        final PendingConnection connection = event.getConnection();

        if (connection == null || connection.getUniqueId() == null || packet == null || handshake == null) {
            channelHandlerManager.closeChannel((ChannelHandlerContext) this, address);
            event.setCancelled(true);

            core.getLogger().log(Level.INFO,
                    "The connection -> {0} thrown multiple weird exceptions");
        }
    }
}
