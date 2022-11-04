package io.github.geysersurvival.plugin.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

import javax.annotation.Nonnull;
import java.util.Random;

public class RoryUtils {
    private static final Random random = new Random();

    public static void giveRoryMap(@Nonnull Player player) {
        //Get the random number for the rory id
        int id = random.nextInt(113);

        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) itemStack.getItemMeta();
        mapMeta.setMapId(id);
        mapMeta.setDisplayName("§6Rory ID: " + (id+1));
        itemStack.setItemMeta(mapMeta);

        player.getInventory().addItem(itemStack);
        player.sendMessage("§aYou were given Rory ID: " + (id+1) + "!");
    }
}
