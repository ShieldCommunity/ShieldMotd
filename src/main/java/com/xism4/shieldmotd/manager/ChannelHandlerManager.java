package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.utils.FastMotdException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;

import java.io.IOException;

public class ChannelHandlerManager {

    public void closeChannel(ChannelHandlerContext channel, String address) {
        if (channel != null && !address.equals("127.0.0.1")) {
            try {
                channel.close();
            } catch (FastMotdException exception) {
                //XD
            }
        }
    }
}
