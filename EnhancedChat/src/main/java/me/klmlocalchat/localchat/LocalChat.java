package me.klmlocalchat.localchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocalChat implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;
        String message = String.join(" ", args);
        double radius = 300.0;
        int playersCount =0;
        for (Player online  : Bukkit.getOnlinePlayers()) {
            if (online.getWorld() == player.getWorld() && online.getLocation().distance(player.getLocation()) <= radius) {
                ++playersCount;
            }
        }
        for (Player online  : Bukkit.getOnlinePlayers()) {
            if (online.getWorld() == player.getWorld() && online.getLocation().distance(player.getLocation()) <= radius) {

                online.sendMessage(ChatColor.GOLD + "[Local(" + playersCount + ")] " + ChatColor.WHITE + player.getName() + ": " + message);
            }
        }
        return true;
    }
    }
