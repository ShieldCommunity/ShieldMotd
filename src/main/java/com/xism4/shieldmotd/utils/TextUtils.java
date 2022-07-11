package com.xism4.shieldmotd.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class TextUtils {
    private TextUtils() {}

    public static String toLegacy(String text) {
        //TODO: pending implementation of minimessage system and hex legacy system (idk)
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static BaseComponent[] toBaseComponent(String text) {
        return TextComponent.fromLegacyText(text);
    }

    public static TextComponent toTextComponent(String text) {
        return new TextComponent(toBaseComponent(text));
    }

}
