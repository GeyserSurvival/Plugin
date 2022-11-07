package io.github.geysersurvival.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.annotation.Nonnull;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(@Nonnull AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        //Send chat message as system chat + translate color codes
        Bukkit.broadcastMessage(event.getPlayer().getName() + ": " + ChatColor.translateAlternateColorCodes('&', event.getMessage()));

    }
}
