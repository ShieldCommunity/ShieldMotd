package com.xism4.shieldmotd.command;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.manager.ConfigurationManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ShieldMotdCommand extends Command {

    private ShieldMotd core;
    private ConfigurationManager configurationManager;

    public ShieldMotdCommand(ShieldMotd core) {
        super("ShieldMotd");
        this.core = core;
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer player = (ProxiedPlayer) sender;

            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&eShieldMotd simple & lightweight motd plugin by xIsm4"
                ));

            } else if (args[0].equalsIgnoreCase("reload")) {
                core.getConfigurationManager().reload();
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', "&eShieldMotd has been reload successfully"
                ));

            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&cShieldMotd - The command does not exist"
                ));
            }
        }
    }
}
