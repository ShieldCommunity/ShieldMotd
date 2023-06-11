package com.xism4.shieldmotd.command;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.manager.ConfigurationHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

import java.io.File;

public class ShieldMotdCommand extends Command {

    private final ShieldMotd core;

    public ShieldMotdCommand(ShieldMotd core) {
        super("shieldmotd", "shieldmotd.command");
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new ComponentBuilder("ShieldMotd - simple & lightweight motd plugin by xIsm4")
                    .color(ChatColor.YELLOW).create());
            return;
        }

        if ("reload".equalsIgnoreCase(args[0])) {
            ConfigurationHandler.IMP.reload(new File("resources", "config.yml"));
            core.getMotdManager().setupMotd();
            sender.sendMessage(new ComponentBuilder("ShieldMotd - has been reload successfully")
                    .color(ChatColor.YELLOW).create());

        } else {
            sender.sendMessage(new ComponentBuilder("ShieldMotd - The command does not exist")
                    .color(ChatColor.RED).create());
        }
    }
}
