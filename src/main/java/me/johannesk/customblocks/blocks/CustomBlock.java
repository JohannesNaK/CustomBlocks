package me.johannesk.customblocks.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Mushroom;

import java.util.HashMap;
import java.util.Map;

public class CustomBlock {
    private final Map<BlockFace,Boolean> directions = new HashMap<>();

    public CustomBlock(String[] values){
        if (values.length != 6)
            throw new IllegalArgumentException("Invalid parameters! Expected 6");
         //Setting directional values
        directions.put(BlockFace.DOWN, Boolean.parseBoolean(values[0]));
        directions.put(BlockFace.EAST, Boolean.parseBoolean(values[1]));
        directions.put(BlockFace.NORTH, Boolean.parseBoolean(values[2]));
        directions.put(BlockFace.SOUTH, Boolean.parseBoolean(values[3]));
        directions.put(BlockFace.UP, Boolean.parseBoolean(values[4]));
        directions.put(BlockFace.WEST,Boolean.parseBoolean(values[5]));
    }
    public void updateBlockState(Block block){
            BlockData blockData = block.getBlockData();
            //Needed to set block's directions
            MultipleFacing facing = (MultipleFacing) blockData;
            for (BlockFace face : directions.keySet())
                facing.setFace(face,directions.get(face));
            block.setBlockData(facing);
    }
}
