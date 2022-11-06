package io.github.geysersurvival.plugin;

import io.github.geysersurvival.plugin.commands.GiveRoryCommand;
import io.github.geysersurvival.plugin.commands.WearCommand;
import io.github.geysersurvival.plugin.listeners.RoryMapFirstJoin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Register listener and command for the rory map
        this.getCommand("give-rory").setExecutor(new GiveRoryCommand());
        this.getServer().getPluginManager().registerEvents(new RoryMapFirstJoin(), this);

        this.getCommand("wear").setExecutor(new WearCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
