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

    // កូដសម្រាប់ការពារការ Enchant ក្នុងតុ (រក្សាទុក)
    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        event.setCancelled(true);
    }

    // កូដសម្រាប់អណ្ដៅ (Anvil)
    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        if (result != null && result.hasItemMeta() && result.getItemMeta().hasDisplayName() && result.getItemMeta().getDisplayName().contains("Riel")) {
            return; // រំលង (មិនលុប Enchant) បើជាលុយរៀល
        }
        // ... កូដដើមដែលលុប Enchant ...
    }

    // កូដសំខាន់៖ ពេលចុចអូស Item (InventoryClick)
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType().isAir()) return;

        // --- ត្រង់នេះគឺជាកន្លែងដែលយើងការពារ Item លុយរៀល ---
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            if (item.getItemMeta().getDisplayName().contains("Riel")) {
                return; // បើជាលុយរៀល គឺឈប់ដំណើរការភ្លាម (Enchant នៅដដែល)
            }
        }
        // --- បញ្ចប់ការការពារ ---

        // បន្តដំណើរការលុប Enchant ចំពោះតែ Item ធម្មតា
        item.getEnchantments().keySet().forEach(item::removeEnchantment);

        if (item.getItemMeta() instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            for (Enchantment enchant : meta.getStoredEnchants().keySet()) {
                meta.removeStoredEnchant(enchant);
            }
            item.setItemMeta(meta);
        }
    }
}
