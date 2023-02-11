package me.klmlocalchat.enhancedchat;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable()
    {
        System.out.println("KLM chat plugin has started!");
        EnhancedWhisper messenger = new EnhancedWhisper(); //object to use our class that will act as a messenger
        getCommand("w").setExecutor(messenger);
        getCommand("re").setExecutor(messenger);
        getCommand("loc").setExecutor(new LocalChat());






    }

}
