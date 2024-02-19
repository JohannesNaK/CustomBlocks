package me.johannesk.customblocks.blocks;

import me.johannesk.customblocks.Main;
import org.bukkit.Bukkit;
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
    public static final NamespacedKey BLOCK_NAMESPACE_KEY = new NamespacedKey(Main.getInstance(),"custom_blocks");
    private static final List<BlockFace> BLOCK_FACE_DIRECTIONS = Arrays.asList(BlockFace.DOWN,BlockFace.EAST, BlockFace.SOUTH, BlockFace.UP,BlockFace.WEST);

    private final Map<BlockFace,Boolean> directions = new HashMap<>();

    private final ItemStack blockItem;
    public CustomBlock(Block block){
        if (!(CUSTOM_BLOCK_MATERIALS.contains(block.getType())))
            throw new IllegalArgumentException("Invalid block type!");
        MultipleFacing facing = (MultipleFacing) block.getBlockData();
        //Getting current directions
        for (BlockFace face :  facing.getFaces())
            directions.put(face,face.isCartesian());
        //Setting missing directions. MC does not store all blockface directions on a block. This is required.
        for (BlockFace face : BLOCK_FACE_DIRECTIONS) {
            if (!(facing.hasFace(face)))
                directions.put(face,false);
        }
        //Create  block item
        blockItem = new ItemStack(block.getType(),64);
        //Set items PDE
        ItemMeta meta = blockItem.getItemMeta();
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        dataContainer.set(BLOCK_NAMESPACE_KEY, PersistentDataType.STRING, Arrays.asList(directions.values()).toString());
        blockItem.setItemMeta(meta);
        BlockManager.addBlockItem(blockItem, this);
    }
    public CustomBlock(List<String> values, ItemStack item){
        if (values.size() != 6)
            throw new IllegalArgumentException("Invalid parameters! Expected 6");
         //Setting directional values
        for (int i = 0; i < BLOCK_FACE_DIRECTIONS.size(); i++)
            directions.put(BLOCK_FACE_DIRECTIONS.get(i),Boolean.parseBoolean(values.get(i)));
        blockItem = item.clone();
        //Set items PDE
        ItemMeta meta = blockItem.getItemMeta();
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        dataContainer.set(BLOCK_NAMESPACE_KEY, PersistentDataType.STRING, Arrays.asList(values).toString());
        blockItem.setItemMeta(meta);
        blockItem.setAmount(64);
        BlockManager.addBlockItem(blockItem, this);
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
