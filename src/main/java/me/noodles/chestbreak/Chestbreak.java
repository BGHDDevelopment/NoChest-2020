package me.noodles.chestbreak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Chestbreak extends JavaPlugin implements Listener {

    public static Chestbreak plugin;
    private UpdateChecker checker;
	
	
    public void onEnable() {
    	Chestbreak.plugin = this;
        final PluginDescriptionFile VarUtilType = this.getDescription();
        this.getLogger().info("NoChest V" + VarUtilType.getVersion() + " starting...");
        this.saveDefaultConfig();
        this.reloadConfig();
        registerEvents(this, this);
        registerEvents((Plugin)this, new UpdateJoinEvent());
        this.getLogger().info("NoChest V" + VarUtilType.getVersion() + " started!");
        this.setEnabled(true);
        this.getLogger().info("NoChest V" + VarUtilType.getVersion() + " checking for updates...");
        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
                getServer().getConsoleSender().sendMessage("------------------------");
                getServer().getConsoleSender().sendMessage("No Chest is outdated!");
                getServer().getConsoleSender().sendMessage("Newest version: " + this.checker.getLatestVersion());
                getServer().getConsoleSender().sendMessage("Your version: " + Chestbreak.plugin.getDescription().getVersion());
                getServer().getConsoleSender().sendMessage("Please Update Here: https://www.spigotmc.org/resources/46563");
                getServer().getConsoleSender().sendMessage("------------------------");
            }
            else {
                getServer().getConsoleSender().sendMessage("------------------------");
                getServer().getConsoleSender().sendMessage("No Chest is up to date!");
                getServer().getConsoleSender().sendMessage("------------------------");            }
        }
    }
	
	
    @EventHandler
	public void onInventoryOpen(InventoryOpenEvent e) {
		Player player = (Player) e.getPlayer();
		if(!player.hasPermission("chest.open")){
		   if (e.getInventory().getType() == InventoryType.CHEST) {
		      e.setCancelled(true);
	            player.sendMessage(ChatColor.RED + "You are not allowed to open this chest!");
		   }
		}
	}
	
    @EventHandler
    public void onBreak(BlockBreakEvent e){
    	Player player = (Player) e.getPlayer();
    	if(!player.hasPermission("chest.break")){
        if(e.getBlock().getType().equals(Material.CHEST));
        e.setCancelled(true);
        player.sendMessage(ChatColor.RED + "You are not allowed to break this chest!");
    }
 }

        	
    
	    public static void registerEvents(final Plugin plugin, final Listener... listeners) {
	        for (final Listener listener : listeners) {
	            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
	        }
	    }
	    
	    @SuppressWarnings({ "unchecked", "rawtypes"})
		public static Chestbreak getPlugin() {
	        return (Chestbreak)getPlugin((Class) Chestbreak.class);
	    }
	
	
}
