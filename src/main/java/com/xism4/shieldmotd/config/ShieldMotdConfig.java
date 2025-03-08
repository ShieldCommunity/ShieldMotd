package com.xism4.shieldmotd.config;

import net.elytrium.serializer.NameStyle;
import net.elytrium.serializer.SerializerConfig;
import net.elytrium.serializer.annotations.Comment;
import net.elytrium.serializer.annotations.CommentValue;
import net.elytrium.serializer.annotations.NewLine;
import net.elytrium.serializer.annotations.Transient;

import java.nio.file.Paths;
import java.util.*;

public class ShieldMotdConfig extends SafeYamlSerializable {

    @Transient
    private static final SerializerConfig CONFIG = (new SerializerConfig.Builder())
            .setCommentValueIndent(1)
            .setFieldNameStyle(NameStyle.MACRO_CASE)
            .setNodeNameStyle(NameStyle.KEBAB_CASE)
            .build();

    public ShieldMotdConfig() {
        super(Paths.get("plugins/ShieldMotd/config.yml"), CONFIG);
        this.MOTD = new MOTD();
        this.PLAYER_INFO = new PLAYER_INFO();
        this.VERSION = new VERSION();
    }

    @Transient
    public static final ShieldMotdConfig IMP = new ShieldMotdConfig();

    @Comment(@CommentValue("ShieldMotd by xIsm4 & ShieldCommunity Team - www.shieldcommunity.net"))

    @NewLine
    public MOTD MOTD;

    @Comment(@CommentValue("Main MOTD settings"))
    public static class MOTD {
        @Comment({
                @CommentValue("Randomized MOTD rows"),
                @CommentValue("You can use multiple formats such as MiniMessage, Legacy or RGB")
        })
        public List<String> LINES = new LinkedList<>();
        {
            LINES.add("<gradient:red:blue>ShieldMotd</gradient> <yellow>Lightweight Motd<reset><br> <gradient:#F53803:#FCE043>Full HEX color Support</gradient>");
            LINES.add("<gradient:red:blue>ShieldMotd</gradient> <newline><aqua>Full motd from single line");
        }

        @Comment({
                @CommentValue("Motds for specific protocol versions (https://minecraft.fandom.com/wiki/Protocol_version)"),
                @CommentValue("Example: 763-766 means that this motd will be shown for players who plays on 1.20+")
        })
        public Map<String, List<String>> PROTOCOL_LINES = new HashMap<>();
        {
            PROTOCOL_LINES.put("763-766", List.of("<gradient:red:blue>ShieldMotd</gradient> <blue>This motd is for MC version 1.20"));
        }

    }

    @NewLine
    public PLAYER_INFO PLAYER_INFO;
    @Comment(@CommentValue("Settings for custom player information"))
    public static class PLAYER_INFO {
        public boolean ENABLED = true;
        public String INFO = " <gradient:red:blue>ShieldMotd</gradient>";

    }

    @NewLine
    public VERSION VERSION;
    @Comment(@CommentValue("Settings for the name that MOTD send when receives a ping request"))
    public static class VERSION {
        public boolean ENABLED = true;
        public String NAME = "ShieldMotd";
    }
}


