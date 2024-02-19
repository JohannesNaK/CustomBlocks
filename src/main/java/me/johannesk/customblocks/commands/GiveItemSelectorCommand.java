package me.johannesk.customblocks.commands;

import me.johannesk.customblocks.blocks.CustomBlock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GiveItemSelectorCommand implements PlayerCommand {

    private static final String COMMAND_ID = "selector";
    private final ItemStack selector;
    public GiveItemSelectorCommand(){
        selector = new ItemStack(Material.BONE);
        ItemMeta meta = selector.getItemMeta();
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        dataContainer.set(CustomBlock.BLOCK_NAMESPACE_KEY, PersistentDataType.STRING,"selector");
        selector.setItemMeta(meta);
    }

    public ItemStack getSelector(){
        return selector.clone();
    }

    @Override
    public void execute(Player player, String[] args) {
        player.getInventory().addItem(selector.clone());
        player.sendMessage(CommandManager.SUCCESS_PREFIX + "block selector added to inventory!");
    }
    public String getCommandID(){
        return COMMAND_ID;
    }

}
