package io.github.geysersurvival.plugin.commands;

import io.github.geysersurvival.plugin.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cPlease specify a message!");
            return true;
        }

        //Check if the player has previously messaged someone
        UUID receiverUUID = MessageUtils.replyToList.getOrDefault(((Player) sender).getUniqueId(), null);
        Player player;

        if (receiverUUID == null) {
            //They haven't, so don't continue
            sender.sendMessage("§cYou do not have anyone to reply to!");
            return true;
        } else {
            player = Bukkit.getPlayer(receiverUUID);

            if (player == null) {
                //They have, but since they left don't proceed
                sender.sendMessage("§cYou do not have anyone to reply to!");
                return true;
            }
        }

        //Get the message from the args
        String message = MessageUtils.commandArgsToMessage(args, 0);

        //Send out the messages
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("§7§o[You] -> %s: §r%s", player.getName(), message)));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("§7§o%s -> [You]: §r%s", sender.getName(), message)));
        return true;
    }
}
