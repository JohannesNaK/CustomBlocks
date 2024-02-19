package me.johannesk.customblocks.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


import java.util.*;

public final class BlockManager implements Listener {
    private static final Map<ItemStack,CustomBlock> CUSTOM_ITEM_BLOCKS = new HashMap<>();

    public static void addBlockItem(ItemStack itemStack, CustomBlock customBlock){
        if (!(CUSTOM_ITEM_BLOCKS.containsKey(itemStack)))
            CUSTOM_ITEM_BLOCKS.put(itemStack, customBlock);
    }
    @EventHandler
    public void onBlockPlacement(BlockPlaceEvent ev){
        ItemStack potentialBlockItem = ev.getItemInHand();
        if (CustomBlock.isCustomBlockType(potentialBlockItem.getType())) {
           if (CUSTOM_ITEM_BLOCKS.containsKey(potentialBlockItem)){
               CUSTOM_ITEM_BLOCKS.get(potentialBlockItem).updateBlockState(ev.getBlockPlaced());
           }

        }
    }

    @EventHandler
    public void onPhys(BlockPhysicsEvent ev){
        if (CustomBlock.isCustomBlockType(ev.getBlock().getType())){
            ev.setCancelled(true);
        }
    }



}

