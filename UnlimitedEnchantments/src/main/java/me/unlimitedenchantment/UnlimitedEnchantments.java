package me.unlimitedenchantment;

import me.unlimitedenchantment.handlers.AnvilListener;
import org.bukkit.plugin.java.JavaPlugin;


public final class UnlimitedEnchantments extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        //Event listener for Anvil Enchantments
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
