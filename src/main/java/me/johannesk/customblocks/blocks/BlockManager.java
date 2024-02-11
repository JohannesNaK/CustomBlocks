package me.johannesk.customblocks.blocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class BlockManager implements Listener {
    private static final Map<ItemStack,CustomBlock> CUSTOM_ITEM_BLOCKS = new HashMap<>();


    public static void addBlockItem(ItemStack itemStack, CustomBlock customBlock){
        if (!(CUSTOM_ITEM_BLOCKS.containsKey(itemStack)))
            CUSTOM_ITEM_BLOCKS.put(itemStack, customBlock);
    }
    @EventHandler
    public void onBlockPlacement(BlockPlaceEvent ev){
        if (CUSTOM_ITEM_BLOCKS.containsKey(ev.getItemInHand()))
            CUSTOM_ITEM_BLOCKS.get(ev.getItemInHand()).updateBlockState(ev.getBlockPlaced());
    }
}
