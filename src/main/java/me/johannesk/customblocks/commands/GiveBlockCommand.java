package me.johannesk.customblocks.commands;

import me.johannesk.customblocks.blocks.CustomBlock;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GiveBlockCommand implements PlayerCommand {
    private static final  String COMMAND_ID = "block";

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 7) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (CustomBlock.isCustomBlockType(item.getType())) {
                //First arg is command id
                List<String> directions = Arrays.asList(args);
                directions.remove(0);
                //Creates new custom block to store provided values
                CustomBlock customBlock = new CustomBlock(directions, item);
                //Retrieve item with modified PDE

                ItemStack customBlockItem = customBlock.getBlockItem();
                player.getInventory().addItem(customBlockItem);
            } else
                player.sendMessage(CommandManager.ERROR_PREFIX+ "Invalid material! Can only use red, brown and stem mushroom blocks!");
        } else {
            player.sendMessage(CommandManager.ERROR_PREFIX + "invalid arguments!" );
            player.sendMessage(ChatColor.GREEN + "Correct usage is: \n /customblock <down> <east> <north> <south> <up> <west>");
        }
    }
    public String getCommandID(){
        return COMMAND_ID;
    }
}
