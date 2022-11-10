package io.github.geysersurvival.plugin.commands;

import io.github.geysersurvival.plugin.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public class MeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cPlease specify the message to send!");
            return true;
        }

        Bukkit.broadcastMessage("* " + sender.getName() + " " + ChatColor.translateAlternateColorCodes('&', MessageUtils.commandArgsToMessage(args, 0)));
        return true;
    }
}
