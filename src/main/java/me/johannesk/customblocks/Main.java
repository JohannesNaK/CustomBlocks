package me.johannesk.customblocks;

import me.johannesk.customblocks.blocks.BlockManager;
import me.johannesk.customblocks.commands.GiveBlock;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
         this.getCommand("customblock").setExecutor(new GiveBlock());
         //BlockManager is responsible for detecting custom blocks upon placement.
         this.getServer().getPluginManager().registerEvents(new BlockManager(), this);
    }

    @Override
    public void onDisable() {

    }
}
