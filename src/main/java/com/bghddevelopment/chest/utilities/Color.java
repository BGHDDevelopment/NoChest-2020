package com.bghddevelopment.chest.utilities;

import org.bukkit.ChatColor;

public class Color {

    public static String translate(String value) {
        return ChatColor.translateAlternateColorCodes('&', value);
    }
}
