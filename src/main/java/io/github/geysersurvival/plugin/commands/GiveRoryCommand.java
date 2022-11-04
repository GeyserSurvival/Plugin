package io.github.geysersurvival.plugin.commands;

import io.github.geysersurvival.plugin.utils.RoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GiveRoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player;
        if (args.length == 0) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage("§cPlease specify a player to give the map to!");
                return true;
            }

            player = (Player) sender;
        } else {
            player = Bukkit.getPlayer(args[0]);

            if (player == null) {
                sender.sendMessage("§cPlease specify a player that is currently online!");
                return true;
            }
        }

        RoryUtils.giveRoryMap(player);
        sender.sendMessage("§aA Rory was given to " + player.getName());
        return true;
    }
}
