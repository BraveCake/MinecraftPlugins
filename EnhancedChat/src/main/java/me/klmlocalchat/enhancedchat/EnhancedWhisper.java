package me.klmlocalchat.enhancedchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;

public class EnhancedWhisper implements CommandExecutor {
    private final HashMap<String, Player> lastWhisperer = new HashMap<>();
    private boolean sendPrivateMessage(Player sender,Player receiver,String message)
    {
        if(receiver == null)
            return true;
        lastWhisperer.put(receiver.getName(), (Player) sender);
        sender.sendMessage(ChatColor.GREEN + "[SMS] " + ChatColor.WHITE + sender.getName() + ": " +message);
        receiver.sendMessage(ChatColor.GREEN + "[SMS] " + ChatColor.WHITE + sender.getName() + ": " + message);
        return true;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { //console shouldn't be able to use these commands
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        //handles whisper command
        if (cmd.getName().equalsIgnoreCase("w")) {
            Player receiver = Bukkit.getPlayer(args[0]);
            if (receiver.getName() == sender.getName()) {
                sender.sendMessage("You cannot send a message to yourself, stoopid dogu >-<");
                return true;
            }
            sendPrivateMessage((Player) sender, receiver, String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
            return true;

        }

        //handles reply command
        if (cmd.getName().equalsIgnoreCase("re")) {
            Player receiver = lastWhisperer.get(sender.getName());
            sendPrivateMessage((Player) sender, receiver, String.join(" ", args));
            return true;


        }
        return true;
    }
}
