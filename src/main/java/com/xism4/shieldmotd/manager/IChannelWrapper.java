package com.xism4.shieldmotd.manager;

import net.md_5.bungee.protocol.PacketWrapper;

import io.netty.channel.Channel;

/**
 * Represents a wrapper channel for NullCordX api compatibility
 */
public interface IChannelWrapper {

    Channel getHandle();

    void write(Object packet);

    void write(PacketWrapper packet);

    void close();

    void close(Object packet);

    void forceClose();

}