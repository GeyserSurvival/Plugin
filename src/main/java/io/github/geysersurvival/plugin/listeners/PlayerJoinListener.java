package io.github.geysersurvival.plugin.listeners;

import io.github.geysersurvival.plugin.Plugin;
import io.github.geysersurvival.plugin.utils.RoryUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import javax.annotation.Nonnull;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(@Nonnull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //Disallow the ability for them to use the vanilla messaging/me commands
        PermissionAttachment perms = player.addAttachment(Plugin.PLUGIN);
        perms.setPermission("minecraft.command.msg", false);
        perms.setPermission("minecraft.command.me", false);

        //If this isn't their first time playing, we won't give them a rory :(
        if (player.hasPlayedBefore()) return;

        //Give them a rory :D
        RoryUtils.giveRoryMap(player);
    }
}
