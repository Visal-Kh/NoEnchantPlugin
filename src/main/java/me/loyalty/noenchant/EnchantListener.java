package me.loyalty.noenchant;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantListener implements Listener {

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        event.setCancelled(true);
    }
}
