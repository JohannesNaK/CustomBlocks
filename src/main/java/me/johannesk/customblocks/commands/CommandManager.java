package me.johannesk.customblocks.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {
    public static final String ERROR_PREFIX = ChatColor.YELLOW + "CustomBlocks >> " + ChatColor.RED;
    public static final String SUCCESS_PREFIX = ChatColor.YELLOW + "CustomBlocks >> " + ChatColor.GREEN;
    private Map<String,PlayerCommand> commands;
    public CommandManager(Map<String,PlayerCommand> commands){
       this.commands = commands;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player)  sender;
            if (player.hasPermission("customblocks.*")) {
                if (commands.containsKey(args[0].toLowerCase()))
                    commands.get(args[0]).execute(player,args);
                else
                    player.sendMessage(ERROR_PREFIX + "Unknown command!" +
                            "\nAvailable commands: /customblock selector\n /customblock block");
            } else
                player.sendMessage(ERROR_PREFIX + "Insufficient permissions!");
        }
        return true;
    }
}
