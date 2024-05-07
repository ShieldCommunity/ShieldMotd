package com.xism4.shieldmotd.utils;

import io.netty.util.concurrent.FastThreadLocal;
import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

public class FastRandom {

    private static final FastThreadLocal<XoRoShiRo128PlusRandom> FAST_RANDOM = new FastThreadLocal<>() {

        protected XoRoShiRo128PlusRandom initialValue() {
            return new XoRoShiRo128PlusRandom();
        }

    };

    public static XoRoShiRo128PlusRandom getFastRandom() {
        return FAST_RANDOM.get();
    }

}