package me.johannesk.customblocks.blocks;

import me.johannesk.customblocks.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Mushroom;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;
import java.util.*;

public class CustomBlock {
    public static final Set<Material> CUSTOM_BLOCK_MATERIALS = new HashSet<>(Arrays.asList(Material.RED_MUSHROOM_BLOCK, Material.BROWN_MUSHROOM_BLOCK, Material.MUSHROOM_STEM));
    private static final NamespacedKey BLOCK_NAMESPACE_KEY = new NamespacedKey(Main.getInstance(),"custom_blocks");
    private final Map<BlockFace,Boolean> directions = new HashMap<>();
    private final ItemStack blockItem;
    public CustomBlock(String[] values, ItemStack item){
        if (values.length != 6)
            throw new IllegalArgumentException("Invalid parameters! Expected 6");
         //Setting directional values
        directions.put(BlockFace.DOWN, Boolean.parseBoolean(values[0]));
        directions.put(BlockFace.EAST, Boolean.parseBoolean(values[1]));
        directions.put(BlockFace.NORTH, Boolean.parseBoolean(values[2]));
        directions.put(BlockFace.SOUTH, Boolean.parseBoolean(values[3]));
        directions.put(BlockFace.UP, Boolean.parseBoolean(values[4]));
        directions.put(BlockFace.WEST,Boolean.parseBoolean(values[5]));
        blockItem = item.clone();
        //Set items PDE
        ItemMeta meta = blockItem.getItemMeta();
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        dataContainer.set(BLOCK_NAMESPACE_KEY, PersistentDataType.STRING, Arrays.asList(values).toString());
        blockItem.setItemMeta(meta);
        blockItem.setAmount(64);
    }
    public boolean isCustomBlockItem(ItemStack item){
        return blockItem.isSimilar(item);
    }
    public ItemStack getBlockItem(){
        return blockItem.clone();
    }
    public void updateBlockState(Block block){
            BlockData blockData = block.getBlockData();
            //Needed to set block's directions
            MultipleFacing facing = (MultipleFacing) blockData;
            for (BlockFace face : directions.keySet())
                facing.setFace(face,directions.get(face));
            block.setBlockData(facing);
    }
    public static boolean isCustomBlockType(Material blockType){
        return  CUSTOM_BLOCK_MATERIALS.contains(blockType);
    }
}
