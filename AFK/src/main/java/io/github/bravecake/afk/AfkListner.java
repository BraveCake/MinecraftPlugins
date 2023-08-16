package io.github.bravecake.afk;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class AfkListner implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof  Player)){
            return;
        }
        Player player = (Player)event.getEntity();
        List<MetadataValue> afk = player.getMetadata("afk");
        boolean isAfk = afk.size()==0?false: afk.get(0).asBoolean();
        if(isAfk) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        List<MetadataValue> afk = player.getMetadata("afk");
        boolean isAfk = afk.size()==0?false: afk.get(0).asBoolean();
        if(isAfk) {
            event.setCancelled(true);
        }
    }

}
