package io.github.bravecake.afk;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("afk").setExecutor(new AfkCommand());
        getServer().getPluginManager().registerEvents(new AfkListner(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
