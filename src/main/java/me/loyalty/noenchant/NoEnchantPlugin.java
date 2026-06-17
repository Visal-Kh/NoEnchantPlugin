package me.loyalty.noenchant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class NoEnchantPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        // ចុះឈ្មោះ Listener
        getServer().getPluginManager().registerEvents(new EnchantListener(this), this);
        // ចុះឈ្មោះ Command
        getCommand("protectitem").setExecutor(this);
        getLogger().info("NoEnchantPlugin Enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("protectitem")) {
            if (!(sender instanceof Player)) return true;
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item != null && item.getType() != org.bukkit.Material.AIR) {
                ItemMeta meta = item.getItemMeta();
                NamespacedKey key = new NamespacedKey(this, "is_protected");
                meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
                item.setItemMeta(meta);
                player.sendMessage("§a[ជោគជ័យ] Item នេះត្រូវបានការពារពីការលុប Enchant ហើយ!");
            }
            return true;
        }
        return false;
    }
}
