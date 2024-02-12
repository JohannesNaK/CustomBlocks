package me.johannesk.customblocks.commands;

import me.johannesk.customblocks.blocks.BlockManager;
import me.johannesk.customblocks.blocks.CustomBlock;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GiveBlock implements CommandExecutor {

    private static final String ERROR_PREFIX = ChatColor.RED + "CustomBlocks> " + ChatColor.YELLOW;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player)  sender;
            if (player.hasPermission("customblocks.*")) {
                if (args.length == 6) {
                        ItemStack item = player.getInventory().getItemInMainHand();
                        if (CustomBlock.isCustomBlockType(item.getType())) {
                            //Creates new custom block to store provided values
                            //Creates PDE for item as well.
                            CustomBlock customBlock = new CustomBlock(args, item);
                            //Retrieve item with modified PDE
                            ItemStack customBlockItem = customBlock.getBlockItem();
                            player.getInventory().addItem(customBlockItem);
                            //Adds custom block to the block manager.
                            BlockManager.addBlockItem(customBlockItem, customBlock);
                        } else
                            player.sendMessage(ERROR_PREFIX + "Invalid material! Can only use red, brown and stem mushroom blocks!");
                  } else {
                    player.sendMessage(ERROR_PREFIX + "invalid arguments!" );
                    player.sendMessage(ChatColor.GREEN + "Correct usage is: \n /customblock <down> <east> <north> <south> <up> <west>");
                }
             } else
                 player.sendMessage(ERROR_PREFIX + "Insufficient permissions!");
        }
        return true;
    }
}
