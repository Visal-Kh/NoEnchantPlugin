package me.loyalty.noenchant;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantListener implements Listener {

    private final NamespacedKey customItemKey;

    public EnchantListener(JavaPlugin plugin) {
        // បង្កើត Key សម្រាប់ចាក់សោ Item
        this.customItemKey = new NamespacedKey(plugin, "is_protected");
    }

    // មុខងារត្រួតពិនិត្យថា តើ Item នេះត្រូវបានការពារដែរឬទេ
    private boolean isProtected(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().has(customItemKey, PersistentDataType.BYTE);
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        if (isProtected(event.getItem())) return; 
        event.setCancelled(true);
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        if (isProtected(result)) return;
        
        if (result != null) {
            result.getEnchantments().keySet().forEach(result::removeEnchantment);
            if (result.getItemMeta() instanceof EnchantmentStorageMeta) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) result.getItemMeta();
                meta.getStoredEnchants().keySet().forEach(meta::removeStoredEnchant);
                result.setItemMeta(meta);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType().isAir()) return;

        // ប្រសិនបើជា Item ការពារ គឺឈប់ដំណើរការភ្លាម (រក្សាទុក Enchant)
        if (isProtected(item)) return;

        // លុប Enchant ចំពោះ Item ធម្មតា
        item.getEnchantments().keySet().forEach(item::removeEnchantment);

        if (item.getItemMeta() instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.getStoredEnchants().keySet().forEach(meta::removeStoredEnchant);
            item.setItemMeta(meta);
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (event.getCaught() instanceof org.bukkit.entity.Item) {
            ItemStack item = ((org.bukkit.entity.Item) event.getCaught()).getItemStack();
            if (isProtected(item)) return;
            item.getEnchantments().keySet().forEach(item::removeEnchantment);
        }
    }
}
