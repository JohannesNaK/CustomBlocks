package me.johannesk.customblocks;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import me.johannesk.customblocks.blocks.BlockManager;
import me.johannesk.customblocks.commands.GiveBlock;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main INSTANCE;
    @Override
    public void onEnable() {
         this.getCommand("customblock").setExecutor(new GiveBlock());
         //BlockManager is responsible for detecting custom blocks upon placement.
         this.getServer().getPluginManager().registerEvents(new BlockManager(), this);
         INSTANCE = this;
    }

    public static  Main getInstance(){
        return INSTANCE;
    }
    @Override
    public void onDisable() {

    }
}
