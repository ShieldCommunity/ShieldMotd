package com.xism4.shieldmotd.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class TextUtils {

    public static String toLegacy(String text) {
        //TODO: pending implementation of minimessage system and hex legacy system (idk)
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static TextComponent toComponent(String text) {
        return new TextComponent(toLegacy(text));
    }

}
