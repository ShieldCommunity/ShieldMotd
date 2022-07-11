package com.xism4.shieldmotd.utils;

import io.netty.handler.codec.DecoderException;

public class FastMotdException extends DecoderException {

    public FastMotdException(String message) {
        super(message);
    }

    @Override
    public Throwable initCause(Throwable cause) {
        return this;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}