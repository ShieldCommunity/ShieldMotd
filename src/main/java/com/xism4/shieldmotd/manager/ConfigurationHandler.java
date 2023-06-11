package com.xism4.shieldmotd.manager;

import com.xism4.shieldmotd.elytrium.YamlConfig;

import java.util.Arrays;
import java.util.List;

public class ConfigurationHandler extends YamlConfig {

    @Ignore
    public static final ConfigurationHandler IMP = new ConfigurationHandler();

    @Create
    public MOTD MOTD;

    @Comment("ShieldMotd configuration")
    public static class MOTD {
        @Comment(
                "Should the MOTD close a invalid ping for some reason?"
        )
        public boolean CLOSE_ON_INVALID_PING = true;

        @Comment({
                "Don't use '\\n', Use %nl%",
                "Before using HEX colors, use the '&' character. Example: &#9c9dff"
        })

        @Comment("Standart MOTD message printed on ServerList")
        public String MOTD_LINES = "What Ever";

        @Comment("What name should we print on the proxy")
        public String NAME_BRAND = "ShieldMotd";
    }

}
