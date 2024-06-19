package com.xism4.shieldmotd.command;

import com.xism4.shieldmotd.ShieldMotd;
import com.xism4.shieldmotd.config.ShieldMotdConfig;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

import java.util.logging.Level;

public class ShieldMotdCommand extends Command {

    private final ShieldMotd core;

    public ShieldMotdCommand(ShieldMotd core) {
        super("shieldmotd", "shieldmotd.command");
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length == 0) {
            sendPluginInfoMessage(sender);
            return;
        }

        if ("reload".equalsIgnoreCase(args[0])) {
            reloadPlugin(sender);
        }
    }

    private void sendPluginInfoMessage(CommandSender sender) {
        sender.sendMessage(new ComponentBuilder("ShieldMotd by xism4").color(ChatColor.YELLOW).create());
    }

    private void reloadPlugin(CommandSender sender) {
        try {
            ShieldMotdConfig.IMP.reload();
            core.getMotdManager().setupMotd();
            sender.sendMessage(new ComponentBuilder(
                    "ShieldMotd - Has been reloaded sucesfully").color(ChatColor.GREEN).create()
            );
        } catch (Exception exception) {
            core.getLogger().log(Level.WARNING, "Exception while reloading the plugin " + exception.getCause());

        }
    }
}