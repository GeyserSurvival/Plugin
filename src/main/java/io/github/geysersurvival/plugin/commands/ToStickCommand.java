package io.github.geysersurvival.plugin.commands;

import io.github.geysersurvival.plugin.Plugin;
import io.github.geysersurvival.plugin.utils.ToStickItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import javax.annotation.Nonnull;

public class ToStickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cPlease specify what to turn into a stick!");
            return true;
        }

        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("§cYou must be a player to use this command!");
            return true;
        }

        Bukkit.getScheduler().runTaskAsynchronously(Plugin.PLUGIN, () -> {
            ToStickItems items;
            switch (args[0].toLowerCase()) {
                case "bamboo":
                    items = ToStickItems.BAMBOO;
                    break;
                case "planks":
                    items = ToStickItems.PLANKS;
                    break;
                default:
                    sender.sendMessage("§cInvalid item specified: " + args[0].toLowerCase());
                    return;
            }

            //Counter for how many of a specific item a player has
            int itemCount = 0;
            Player player = (Player) sender;
            PlayerInventory inventory = player.getInventory();

            {
                boolean isValid = false;
                for (Material material : items.getMaterials()) {
                    if (inventory.contains(material)) {
                        isValid = true;
                        break;
                    }
                }

                //Complain to the player if they don't have the resource
                if (!isValid) {
                    player.sendMessage("§cYou do not have any " + items.name().toLowerCase() + " to convert into sticks!");
                    return;
                }
            }

            //Count up how many of a specific item the player has
            for (ItemStack itemStack : inventory.getStorageContents()) {
                if (itemStack == null) {
                    continue;
                }

                //Add the amount for every eligible material
                for (Material material : items.getMaterials()) {
                    if (itemStack.getType().equals(material)) {
                        itemCount += itemStack.getAmount();
                    }
                }

            }

            //The number of sticks to give
            int stickCount = 0;

            //How many items we should take from the player
            int itemsToTake = 0;

            while (itemCount >= items.getInput()) {
                itemCount -= items.getInput();
                itemsToTake += items.getInput();
                stickCount+= items.getOutput();
            }

            //If we aren't even taking enough for a stick return
            if (itemsToTake < items.getInput()) {
                player.sendMessage("§cYou do not have enough " + items.name().toLowerCase() + " to convert into sticks!");
                return;
            }

            for (int i = 0; i < inventory.getStorageContents().length; i++) {
                ItemStack itemStack = inventory.getStorageContents()[i];

                if (itemStack == null) {
                    continue;
                }

                for (Material material : items.getMaterials()) {
                    if (itemStack.getType().equals(material)) {
                        int amount = itemStack.getAmount();

                        //Bamboo needs two items for a stick, so skip if it's just one
                        if (items.equals(ToStickItems.BAMBOO) && amount == 1) {
                            continue;
                        }

                        //Remove the item if the number of items we're taking is bigger than the amount of items in the slot
                        if (itemsToTake >= amount) {
                            itemsToTake-=amount;
                            inventory.clear(i);
                        } else {
                            inventory.getItem(i).setAmount(amount-itemsToTake);
                        }
                    }
                }
            }

            inventory.addItem(new ItemStack(Material.STICK, stickCount));
        });

        return true;
    }
}
