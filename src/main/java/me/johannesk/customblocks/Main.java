package me.johannesk.customblocks;


import me.johannesk.customblocks.blocks.BlockManager;
import me.johannesk.customblocks.commands.CommandManager;
import me.johannesk.customblocks.commands.GiveBlockCommand;
import me.johannesk.customblocks.commands.GiveItemSelectorCommand;
import me.johannesk.customblocks.commands.PlayerCommand;
import me.johannesk.customblocks.items.ItemSelector;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    private static Main INSTANCE;
    @Override
    public void onEnable() {
         INSTANCE = this;
         GiveBlockCommand giveBlock = new GiveBlockCommand();
         GiveItemSelectorCommand giveItemSelector = new GiveItemSelectorCommand();
         Map<String, PlayerCommand> commands = new HashMap<>();
         commands.put(giveBlock.getCommandID(),giveBlock);
         commands.put(giveItemSelector.getCommandID(), giveItemSelector);
         this.getCommand("customblock").setExecutor(new CommandManager(commands));
         //BlockManager is responsible for detecting custom blocks upon placement.
         this.getServer().getPluginManager().registerEvents(new BlockManager(), this);
         this.getServer().getPluginManager().registerEvents(new ItemSelector(giveItemSelector.getSelector()), this);

    }

    public static  Main getInstance(){
        return INSTANCE;
    }
    @Override
    public void onDisable() {

    }
}
