package io.github.bravecake.mysysteminfo;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("systeminfo").setExecutor(new SystemInfo());
        getLogger().info("SystemInfoPlugin has been enabled!");


    }

    @Override
    public void onDisable() {
        getLogger().info("SystemInfoPlugin has been disabled!");

        // Plugin shutdown logic
    }
}
