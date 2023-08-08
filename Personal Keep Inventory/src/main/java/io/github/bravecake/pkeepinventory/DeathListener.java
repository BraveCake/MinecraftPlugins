package io.github.bravecake.pkeepinventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

public class DeathListener implements Listener{

    private JSONObject playersSettings;
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        playersSettings= KeepInventory.getPlayersSettings();
        Player player = event.getEntity();
        String playerName = player.getName();
        Integer storedOption = playersSettings.containsKey(playerName)? (int) (long) playersSettings.get(playerName):0;
            // Store the player's items and XP temporarily
            if(storedOption==3||storedOption==1) {
                event.setKeepInventory(true);
                event.getDrops().clear();
            }
            if(storedOption==3||storedOption==2)
                event.setKeepLevel(true);

    }


}
