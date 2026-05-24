package me.loyalty.noenchant;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class EnchantListener implements Listener {

    // Block enchanting table
    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        event.setCancelled(true);
    }

    // Remove enchantments from anvils/books
    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();

        if (result != null) {
            result.getEnchantments().keySet().forEach(result::removeEnchantment);

            if (result.getItemMeta() instanceof EnchantmentStorageMeta meta) {
                for (Enchantment enchant : meta.getStoredEnchants().keySet()) {
                    meta.removeStoredEnchant(enchant);
                }
                result.setItemMeta(meta);
            }

            event.setResult(result);
        }
    }

    // Block enchanted books in inventories
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();

        if (item == null) return;

        item.getEnchantments().keySet().forEach(item::removeEnchantment);

        if (item.getItemMeta() instanceof EnchantmentStorageMeta meta) {
            for (Enchantment enchant : meta.getStoredEnchants().keySet()) {
                meta.removeStoredEnchant(enchant);
            }
            item.setItemMeta(meta);
        }
    }

    // Remove enchanted fishing loot
    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (event.getCaught() instanceof org.bukkit.entity.Item itemEntity) {
            ItemStack item = itemEntity.getItemStack();

            item.getEnchantments().keySet().forEach(item::removeEnchantment);
        }
    }
}
