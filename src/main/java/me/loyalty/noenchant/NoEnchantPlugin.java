package me.loyalty.noenchant;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoEnchantPlugin extends JavaPlugin {

    private static NoEnchantPlugin instance;

    @Override
    public void onEnable() {

        // Set plugin instance
        instance = this;

        // Register events
        Bukkit.getPluginManager().registerEvents(new EnchantListener(), this);

        // Console message
        getLogger().info("=================================");
        getLogger().info(" NoEnchantPlugin Enabled!");
        getLogger().info(" All enchantments are disabled.");
        getLogger().info(" PaperMC Version: 1.21.x");
        getLogger().info("=================================");
    }

    @Override
    public void onDisable() {

        // Console message
        getLogger().info("=================================");
        getLogger().info(" NoEnchantPlugin Disabled!");
        getLogger().info("=================================");
    }

    /*
     * Get plugin instance
     */
    public static NoEnchantPlugin getInstance() {
        return instance;
    }
}
