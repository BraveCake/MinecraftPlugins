package io.github.bravecake.mysysteminfo;

import com.sun.management.OperatingSystemMXBean;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.management.ManagementFactory;

public class SystemInfo implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("systeminfo")) {
            if (sender.isOp()) {

                OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                sender.sendMessage("------ System Information ------");
                sender.sendMessage("Operating System: " + System.getProperty("os.name"));
                sender.sendMessage("Architecture: " + System.getProperty("os.arch"));
                sender.sendMessage("Java Version: " + System.getProperty("java.version"));
                sender.sendMessage("Total Processors: " + bean.getAvailableProcessors());
                sender.sendMessage("Available Processors: " + Runtime.getRuntime().availableProcessors());
                sender.sendMessage("Total Used Memory: " + Runtime.getRuntime().totalMemory() / (1024 * 1024) + "MB");
                sender.sendMessage("Free Memory: " + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB");
                sender.sendMessage("Max Memory: " + Runtime.getRuntime().maxMemory() / (1024 * 1024) + "MB");
                return true;
            } else {
                sender.sendMessage("You must be OP to use this command.");
                return false;
            }
        }
        return false;
    }
}
