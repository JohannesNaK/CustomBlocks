package me.johannesk.customblocks.commands;

import org.bukkit.entity.Player;

public interface PlayerCommand {

     void execute(Player player, String[] args);
     String getCommandID();
}
