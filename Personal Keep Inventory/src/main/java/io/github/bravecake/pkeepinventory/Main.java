package io.github.bravecake.pkeepinventory;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Main extends JavaPlugin {
 final static String fileName = "PKI-Data.json";
 static File dataFolder;
    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Personal Keep Inventory plugin has started!");
        this.getDataFolder().mkdirs();
        dataFolder= getDataFolder();

        createJsonFileIfNotExists();
        getCommand("ki").setExecutor(new KeepInventory());
        getServer().getPluginManager().registerEvents(new DeathListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void createJsonFileIfNotExists() {
        // The name of the JSON file you want to create


        // Check if the file already exists
        java.io.File file = new java.io.File(getDataFolder(), fileName);
        if (!file.exists()) {
            try {
                // Create the file
                boolean creationSucceeded=file.createNewFile();
                getLogger().info("Created " + fileName);
                if(creationSucceeded) //initialize the file if it is empty
                {
                    FileWriter myWriter = new FileWriter(dataFolder.getPath()+'/'+fileName);
                    myWriter.write("{}");
                    myWriter.close();
                }
            } catch (IOException e) {
                getLogger().warning("Failed to create " + fileName);
                e.printStackTrace();
            }
        }
    }
}
