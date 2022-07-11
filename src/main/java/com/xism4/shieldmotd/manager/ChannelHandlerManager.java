package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.utils.FastMotdException;
import io.netty.channel.ChannelHandlerContext;

public class ChannelHandlerManager {

    public void closeChannel(ChannelHandlerContext channel, String address) {
        if (channel != null && !address.equals("127.0.0.1")) {
            try {
                channel.close();
            } catch (FastMotdException exception) {
                //ignored
            }
        }
    }
}
