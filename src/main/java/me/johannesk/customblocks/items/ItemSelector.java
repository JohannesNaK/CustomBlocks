package me.johannesk.customblocks.items;

import me.johannesk.customblocks.Main;
import me.johannesk.customblocks.blocks.CustomBlock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;

public class ItemSelector implements Listener {
    private final ItemStack selector;
    public ItemSelector(ItemStack selector){
        this.selector = selector.clone();
    }
    @EventHandler
    public void onClick(PlayerInteractEvent ev){
        if (ev.getAction()  == Action.RIGHT_CLICK_BLOCK) {
                PlayerInventory inventory = ev.getPlayer().getInventory();
                ItemStack item = inventory.getItemInMainHand();
                PersistentDataContainer pde = item.getItemMeta().getPersistentDataContainer();
                String data = pde.get(CustomBlock.BLOCK_NAMESPACE_KEY, PersistentDataType.STRING);
                if (data != null) {
                    if (data.equals("selector")){
                        if (CustomBlock.isCustomBlockType(ev.getClickedBlock().getType())){
                            //Faster to just create a new CustomBlock than checking if one already exists
                            CustomBlock customBlock = new CustomBlock(ev.getClickedBlock());
                            inventory.addItem(customBlock.getBlockItem());
                        }
                    }
                }
        }
    }

}
