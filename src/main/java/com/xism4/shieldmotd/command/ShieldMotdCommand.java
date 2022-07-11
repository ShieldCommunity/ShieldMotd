package com.xism4.shieldmotd.command;

import com.xism4.shieldmotd.ShieldMotd;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ShieldMotdCommand extends Command {

    private ShieldMotd core;

    public ShieldMotdCommand(ShieldMotd core) {
        super("ShieldMotd");
        this.core = core;
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer player = (ProxiedPlayer) sender;

            if (args.length == 0) {
                player.sendMessage(new ComponentBuilder("ShieldMotd simple & lightweight motd plugin by xIsm4")
                    .color(ChatColor.YELLOW).create());
                return;
            }

            if ("reload".equalsIgnoreCase(args[0])) {
                core.getConfigurationManager().reload();
                player.sendMessage(new ComponentBuilder("ShieldMotd has been reload successfully")
                    .color(ChatColor.YELLOW).create());

            } else {
                player.sendMessage(new ComponentBuilder("ShieldMotd - The command does not exist")
                    .color(ChatColor.RED).create());
            }
        }
    }
}
