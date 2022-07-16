package com.xism4.shieldmotd.enums;

import com.xism4.shieldmotd.ShieldMotd;

public class StatusHandlerEnum {

    private final ShieldMotd core;

    public StatusHandlerEnum(ShieldMotd core) {
        this.core = core;
    }

    private enum State {

        HANDSHAKE, EVENT, STATUS, PING, USERNAME, ENCRYPT, FINISHING;
    }
}
