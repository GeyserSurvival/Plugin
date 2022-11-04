package io.github.geysersurvival.plugin.listeners;

import io.github.geysersurvival.plugin.utils.RoryUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.annotation.Nonnull;

public class RoryMapFirstJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(@Nonnull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //If this isn't their first time playing, we won't give them a rory :(
        if (player.hasPlayedBefore()) return;

        //Give them a rory
        RoryUtils.giveRoryMap(player);
    }
}
