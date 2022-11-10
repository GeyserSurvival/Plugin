package io.github.geysersurvival.plugin;

import io.github.geysersurvival.plugin.commands.*;
import io.github.geysersurvival.plugin.listeners.ChatListener;
import io.github.geysersurvival.plugin.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;

public final class Plugin extends JavaPlugin {
    public static JavaPlugin PLUGIN;

    @Override
    public void onEnable() {
        // Plugin startup logic
        PLUGIN = this;

        //Register commands
        this.getCommand("give-rory").setExecutor(new GiveRoryCommand());
        this.getCommand("me").setExecutor(new MeCommand());
        this.getCommand("message").setExecutor(new MessageCommand());
        this.getCommand("reply").setExecutor(new ReplyCommand());
        this.getCommand("tostick").setExecutor(new ToStickCommand());
        this.getCommand("wear").setExecutor(new WearCommand());

        //Register tab completer for commands
        this.getCommand("message").setTabCompleter((sender, command, s, args) -> args.length == 1 ? null : Collections.emptyList());
        this.getCommand("me").setTabCompleter((sender, command, s, args) -> Collections.emptyList());
        this.getCommand("reply").setTabCompleter((sender, command, s, args) -> Collections.emptyList());
        this.getCommand("tostick").setTabCompleter((sender, command, s, args) -> args.length == 1 ? Arrays.asList("bamboo", "planks") : null);

        //Register other listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
