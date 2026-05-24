package me.loyalty.noenchant;

import org.bukkit.plugin.java.JavaPlugin;

public class NoEnchantPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EnchantListener(), this);

        getLogger().info("NoEnchantPlugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoEnchantPlugin Disabled!");
    }
}
