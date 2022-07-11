package com.xism4.shieldmotd.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.chat.TextComponent;

public class TextUtils {
    private TextUtils() {}

    public static Component toComponent(String text) {
        return MiniMessage.miniMessage().deserialize(
            MiniMessage.miniMessage().serialize(
                BungeeComponentSerializer.get().deserialize(
                    TextComponent.fromLegacyText(text))));
    }

    public static TextComponent toBungeeComponent(String text) {
        return new TextComponent(BungeeComponentSerializer.get().serialize(toComponent(text)));
    }

    public static TextComponent toLegacyBungeeComponent(String text) {
        return new TextComponent(BungeeComponentSerializer.legacy().serialize(toComponent(text)));
    }

}
