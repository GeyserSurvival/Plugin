package io.github.geysersurvival.plugin.commands;

import io.github.geysersurvival.plugin.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class MessageCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@Nonnull CommandSender sender,@Nonnull  Command command,@Nonnull String s, @Nonnull String[] args) {
        if (args.length < 2) {
            //Nag the player to include more args
            if (args.length == 0) sender.sendMessage("§cPlease specify a player!");
            if (args.length == 1) sender.sendMessage("§cPlease specify the message to send!");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("§cThe player specified was not found!");
            return true;
        }

        //Get the message from the args, we start at 1 due to the first value being the player name
        String message = MessageUtils.commandArgsToMessage(args, 1);

        //Add both the player and receiver's uuid's to the hash map for the /reply command
        MessageUtils.replyToList.putIfAbsent(((Player) sender).getUniqueId(), player.getUniqueId());

        //Send out the messages
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("§7§o[You] -> %s: §r%s", player.getName(), message)));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("§7§o%s -> [You]: §r%s", sender.getName(), message)));
        return true;
    }
}
