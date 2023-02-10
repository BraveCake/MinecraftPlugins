package me.unlimitedenchantment.handlers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public class AnvilListener implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        // Slot1 & Slot2 in the anvil
        AnvilInventory inventory = event.getInventory();
        ItemStack slot1 = inventory.getItem(0);
        ItemStack slot2 = inventory.getItem(1);
        if(slot1==null || slot2==null)
            return;  //nothing to enchant (yet)
        // Check if the item is enchantable by using Arrow_Damage (could be any enchantment)
        Enchantment test = Enchantment.ARROW_DAMAGE;
        if (test.canEnchantItem(slot1))
        {
           if( slot2.getType() == Material.ENCHANTED_BOOK )
           {
               //Clone slot1 item then apply the book enchantments on it finally set it as the final result
               ItemStack endProduct = slot1.clone();
               Map<Enchantment, Integer> enchantments = ((EnchantmentStorageMeta) slot2.getItemMeta()).getStoredEnchants();
               int cost = slot1.getEnchantments().size()*2;
               for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                  endProduct.addUnsafeEnchantment(entry.getKey(),entry.getValue());
                  inventory.setRepairCost(cost);
               }
               event.setResult(endProduct);
                    }
        }

    }
}
