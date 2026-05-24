package me.loyalty.noenchant;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;
import java.util.Map;

public class EnchantListener implements Listener {

    /*
     * =========================
     * Disable Enchanting Table
     * =========================
     */
    @EventHandler
    public void onEnchant(EnchantItemEvent event) {

        if (event.getEnchanter().hasPermission("noenchant.bypass")) {
            return;
        }

        event.setCancelled(true);

        event.getEnchanter().sendMessage("§cEnchanting is disabled in this server!");
    }

    /*
     * =========================
     * Disable Anvil Enchants
     * =========================
     */
    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {

        ItemStack result = event.getResult();

        if (result == null) {
            return;
        }

        removeEnchantments(result);

        event.setResult(result);
    }

    /*
     * =========================
     * Remove Enchanted Books
     * =========================
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getCurrentItem() == null) {
            return;
        }

        ItemStack item = event.getCurrentItem();

        removeEnchantments(item);

        /*
         * Convert enchanted book
         * into normal book
         */
        if (item.getType() == Material.ENCHANTED_BOOK) {

            ItemStack normalBook = new ItemStack(Material.BOOK);

            event.setCurrentItem(normalBook);
        }
    }

    /*
     * =========================
     * Remove Fishing Enchants
     * =========================
     */
    @EventHandler
    public void onFish(PlayerFishEvent event) {

        if (event.getCaught() instanceof Item itemEntity) {

            ItemStack item = itemEntity.getItemStack();

            removeEnchantments(item);
        }
    }

    /*
     * =========================
     * Remove Villager Enchants
     * =========================
     */
    @EventHandler
    public void onVillagerTrade(VillagerAcquireTradeEvent event) {

        MerchantRecipe recipe = event.getRecipe();

        ItemStack result = recipe.getResult();

        removeEnchantments(result);

        /*
         * Remove enchanted books
         */
        if (result.getType() == Material.ENCHANTED_BOOK) {

            result.setType(Material.BOOK);
        }

        recipe.setResult(result);

        event.setRecipe(recipe);
    }

    /*
     * =========================
     * Utility Method
     * =========================
     */
    private void removeEnchantments(ItemStack item) {

        if (item == null) {
            return;
        }

        /*
         * Remove normal enchants
         */
        Iterator<Map.Entry<Enchantment, Integer>> iterator =
                item.getEnchantments().entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Enchantment, Integer> entry = iterator.next();

            item.removeEnchantment(entry.getKey());
        }

        /*
         * Remove stored enchants
         */
        ItemMeta meta = item.getItemMeta();

        if (meta instanceof EnchantmentStorageMeta storageMeta) {

            for (Enchantment enchantment : storageMeta.getStoredEnchants().keySet()) {

                storageMeta.removeStoredEnchant(enchantment);
            }

            item.setItemMeta(storageMeta);
        }
    }
}
