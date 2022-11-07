package io.github.geysersurvival.plugin;

import io.github.geysersurvival.plugin.commands.GiveRoryCommand;
import io.github.geysersurvival.plugin.commands.ToStickCommand;
import io.github.geysersurvival.plugin.commands.WearCommand;
import io.github.geysersurvival.plugin.listeners.ChatListener;
import io.github.geysersurvival.plugin.listeners.RoryMapFirstJoin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Plugin extends JavaPlugin {
    public static JavaPlugin PLUGIN;

    @Override
    public void onEnable() {
        // Plugin startup logic
        PLUGIN = this;

        //Register commands
        this.getCommand("give-rory").setExecutor(new GiveRoryCommand());
        this.getCommand("tostick").setExecutor(new ToStickCommand());
        this.getCommand("wear").setExecutor(new WearCommand());

        //Register tab completer
        this.getCommand("tostick").setTabCompleter((commandSender, command, s, strings) -> {
            List<String> names = new ArrayList<>();
            names.add("bamboo");
            names.add("planks");
            return names;
        });

        //Register other listeners
        this.getServer().getPluginManager().registerEvents(new RoryMapFirstJoin(), this);
        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
