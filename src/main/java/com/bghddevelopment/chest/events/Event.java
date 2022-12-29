package com.bghddevelopment.chest.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class Event implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        if(!player.hasPermission("chest.open")){
            if (event.getInventory().getType() == InventoryType.CHEST) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You are not allowed to open this chest!");
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = (Player) event.getPlayer();
        if(!player.hasPermission("chest.break")){
            if(event.getBlock().getType().equals(Material.CHEST));
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You are not allowed to break this chest!");
        }
    }
}
