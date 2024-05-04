package com.xism4.shieldmotd.config;

import com.xism4.shieldmotd.libs.net.elytrium.serializer.NameStyle;
import com.xism4.shieldmotd.libs.net.elytrium.serializer.SerializerConfig;
import com.xism4.shieldmotd.libs.net.elytrium.serializer.annotations.*;

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


