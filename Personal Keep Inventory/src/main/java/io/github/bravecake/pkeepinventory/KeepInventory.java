package io.github.bravecake.pkeepinventory;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class KeepInventory implements CommandExecutor  {
    final static String jsonFile= Main.dataFolder.getPath()+"/"+Main.fileName;
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (!(sender instanceof Player)) { //console shouldn't be able to use these commands
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("ki")&& args.length>0 ) {

            String playerName = ((Player) sender).getName();
            String option = args[0];
            JSONObject playersSettings = getPlayersSettings();
            Integer storedOption=playersSettings.containsKey(playerName)? (int) (long) playersSettings.get(playerName):0;


            if (option.equalsIgnoreCase("items")) {
                    switch (storedOption) {
                        case 0:
                        case 2:
                            playersSettings.put(playerName, storedOption + 1);
                            sender.sendMessage(ChatColor.GREEN+" Keep items has been enabled");
                            break;
                        default:
                            playersSettings.put(playerName, storedOption - 1);
                            sender.sendMessage(ChatColor.GREEN+" Keep items has been disabled");


                    }

            } else if (option.equalsIgnoreCase("xp")) {
                    switch (storedOption) {
                        case 3:
                        case 2:
                            playersSettings.put(playerName, storedOption - 2);
                            sender.sendMessage(ChatColor.GREEN+" Keep xp has been disabled");
                            break;
                        default:
                            playersSettings.put(playerName, storedOption + 2);
                            sender.sendMessage(ChatColor.GREEN+" Keep xp has been enabled");


                    }


            } else if (option.equalsIgnoreCase("status")) {

                    String itemsStatus = storedOption==1||storedOption==3?"Enabled":"Disabled";
                    String xpStatus = storedOption==2||storedOption==3?"Enabled":"Disabled";
                    String message = String.format("%s Keep-Items:%s \n Keep-XP:%s",ChatColor.GREEN,itemsStatus,xpStatus);
                    sender.sendMessage(message);


            }
            savePlayersSettings(playersSettings);
        }



        return true;
    }
    public void savePlayersSettings(JSONObject playersSettings){
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            fileWriter.write(playersSettings.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public JSONObject getPlayersSettings() {
        JSONObject dataObject = null;
        try {
            dataObject = (JSONObject) new JSONParser().parse(new FileReader(jsonFile));

        }
        catch(Exception e)
        {
            System.out.println("An error has occurred while accessing players data in personal keep inventory\n ***");
            e.printStackTrace();
        }
return dataObject;
    }
}
