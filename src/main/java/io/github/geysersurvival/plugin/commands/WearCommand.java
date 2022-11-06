package io.github.geysersurvival.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Locale;

public class WearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length != 0) {
            sender.sendMessage("§cToo many arguments!");
            return true;
        }

        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("§cYou must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();

        //Store the items from the inventory that we want to switch
        ItemStack hand = inventory.getItemInMainHand();
        ItemStack head = inventory.getHelmet();

        //Switch the items
        inventory.setItemInMainHand(head);
        inventory.setHelmet(hand);

        sender.sendMessage("§aYou're now wearing a " + hand.getType().name().toLowerCase(Locale.ROOT) + "!");
        return true;
    }
}
