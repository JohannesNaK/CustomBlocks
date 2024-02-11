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
    private static final Set<Material> ALLOWED_MATERIALS = new HashSet<>();
    public GiveBlock(){
        ALLOWED_MATERIALS.add(Material.RED_MUSHROOM_BLOCK);
        ALLOWED_MATERIALS.add(Material.BROWN_MUSHROOM_BLOCK);
        ALLOWED_MATERIALS.add(Material.MUSHROOM_STEM);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player)  sender;
            if (player.hasPermission("customblocks.*")) {
                if (args.length == 6) {
                        ItemStack item = player.getInventory().getItemInMainHand().clone();
                        if (ALLOWED_MATERIALS.contains(item.getType())) {
                            //Creates new custom block to store provided values
                            CustomBlock block = new CustomBlock(args);
                            ItemMeta meta = item.getItemMeta();
                            //Easier for user to see what type of block it is by setting display name
                            // equal to arguments. The display name is NOT used to check if it is a valid custom block.
                            meta.setDisplayName(args.toString());
                            item.setItemMeta(meta);
                            player.getInventory().addItem(item);
                            //Adds cstom block to the block manager
                            BlockManager.addBlockItem(item, block);
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
