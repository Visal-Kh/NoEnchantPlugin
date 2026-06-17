package me.loyalty.noenchant;

import org.bukkit.plugin.java.JavaPlugin;

public class NoEnchantPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EnchantListener(), this);

        getLogger().info("NoEnchantPlugin Enabled!");
    }

@Override
public void onEnable() {
    getServer().getPluginManager().registerEvents(new EnchantListener(this), this);
    // បន្ថែមបន្ទាត់នេះដើម្បី Register command
    getCommand("protectitem").setExecutor(this);
    getLogger().info("NoEnchantPlugin Enabled!");
}
