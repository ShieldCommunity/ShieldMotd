package com.xism4.shieldmotd.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class TextUtils {
    private TextUtils() {}

    public static Component toComponent(String text) {
        text = text.replace("\n", "<br>")
            .replace(LegacyComponentSerializer.SECTION_CHAR, LegacyComponentSerializer.AMPERSAND_CHAR);
        Component initial = LegacyComponentSerializer.legacyAmpersand().deserialize(text);
        String deserializedAsMini = MiniMessage.miniMessage().serialize(initial).replace("\\", "");
        
        return MiniMessage.miniMessage().deserialize(deserializedAsMini);
    }

    public static TextComponent toModernComponent(String text) {
        String json = GsonComponentSerializer.gson().serialize(toComponent(text));

        return new TextComponent(ComponentSerializer.parse(json));
    }

    public static TextComponent toLegacyComponent(String text) {
        Component parsed = toComponent(text);
        String json = GsonComponentSerializer.colorDownsamplingGson().serialize(parsed);
        return new TextComponent(ComponentSerializer.parse(json));
    }

}
