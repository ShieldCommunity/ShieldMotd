package com.xism4.shieldmotd.manager;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.io.BufferedReader;

/**
 * Represents a packet as a set of ready-made byte arrays for each game protocol version.
 */
public interface ByteBuff {

    ByteBuf getByteBufByProtocol(int protocol);

    default void writeAndClose(IChannelWrapper channelWrapper, int protocol) {
        ByteBuf buf = getByteBufByProtocol(protocol);
        if (buf == null) {
            channelWrapper.close();
            return;
        }
        channelWrapper.close(new BufReader(buf.retainedDuplicate()));
    }

    default void writePacket(IChannelWrapper channelWrapper, int protocol) {
        writePacket(channelWrapper.getHandle(), protocol);
    }

    default void writePacket(Channel channel, int protocol) {
        ByteBuf buf = getByteBufByProtocol(protocol);
        if (buf == null) {
            return;
        }
        channel.write(new BufReader(buf.retainedDuplicate()), channel.voidPromise());
    }

    void release();

}