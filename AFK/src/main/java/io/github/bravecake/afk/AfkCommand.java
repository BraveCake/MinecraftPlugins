package io.github.bravecake.afk;

import org.apache.maven.artifact.repository.metadata.Metadata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class AfkCommand implements CommandExecutor {
@Override
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player)) { //console shouldn't be able to use these commands
        sender.sendMessage("This command can only be executed by a player.");
        return true;
    }
    Player player = (Player) sender;
    Plugin plugin = Bukkit.getPluginManager().getPlugin("AFK");
    List<MetadataValue> afk = player.getMetadata("afk");
    boolean isAfk = afk.size()==0?false: afk.get(0).asBoolean();
    if (isAfk)
    {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "effect"+ " clear "+player.getName());
        player.sendMessage(ChatColor.GREEN+"You are no longer AFK");
        Bukkit.broadcastMessage(ChatColor.GRAY+player.getName()+" is no longer AFK");
        player.setMetadata("afk",new FixedMetadataValue(plugin, false));

        return true;
    }
    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 255, false, false));
    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 200, false, false));
    player.sendMessage(ChatColor.GREEN+"You are now AFK use /afk command when you come back");
    player.setMetadata("afk",new FixedMetadataValue(plugin, true));
    Bukkit.broadcastMessage(ChatColor.GRAY+player.getName()+" AFK");
    return true;


}


}
