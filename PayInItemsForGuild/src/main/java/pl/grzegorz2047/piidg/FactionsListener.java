package pl.grzegorz2047.piidg;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsCreate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;


public class FactionsListener implements Listener {
    public Main plugin;
    private FileConfiguration config;
    private String notEnoughBlocksMsg;
    private String prefix;
    private List<String> quantities;
    private List<String> requiredBlocks;
    private int numberOfBlocksInConfigs;

    public FactionsListener(Main instance) {
        plugin = instance;
        config = plugin.getConfig();
        notEnoughBlocksMsg = config.getString("notenoughblocks");
        prefix = ChatColor.GOLD + "[FactionsAddon] ";
        quantities = config.getStringList("ilosc");
        requiredBlocks = config.getStringList("bloki");
        numberOfBlocksInConfigs = requiredBlocks.size();
        int quantities = this.quantities.size();
        if (numberOfBlocksInConfigs != quantities) {
            System.out.println("Masz zla konfiguracje! " +
                    "Moga wystepować błędy, bo listy mają różną ilość elementów!" +
                    " Dla każdego bloku trzeba zdefiniowac konkretna ich ilosc na drugiej liscie!");
        }
    }

    @EventHandler//(priority = EventPriority.HIGHEST)
    public void onClanCr(EventFactionsCreate f) {
        MPlayer mPlayer = f.getMPlayer();
        CommandSender sender = f.getSender();
        String senderName = sender.getName();
        Player player = Bukkit.getPlayer(senderName);
        if (mPlayer.hasFaction()) {
            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', config.getString("alreadyhaveaguild")));
            f.setCancelled(true);
        } else {
            PlayerInventory inventory = player.getInventory();
            for (int i = 0; i < numberOfBlocksInConfigs; i++) {
                String materialName = requiredBlocks.get(i);
                int quantity = Integer.parseInt(this.quantities.get(i));
                if (!inventory.contains(Material.getMaterial(materialName), quantity)) {
                    String full = prefix + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', notEnoughBlocksMsg);
                    player.sendMessage(full);
                    f.setCancelled(true);
                    return;
                }
            }
            for (int i = 0; i < numberOfBlocksInConfigs; i++) {
                String materialName = requiredBlocks.get(i);
                int quantity = Integer.parseInt(this.quantities.get(i));
                Material material = Material.getMaterial(materialName);
                removeFromInv(inventory, material, 0, quantity);
            }
            System.out.println("Gildia prawdopodobnie zostala zalozona :)");

        }

    }


    private static void removeFromInv(Inventory inv, Material material, int dmgValue, int amount) {
        if (inv.contains(material)) {
            int remaining = amount;
            ItemStack[] contents = inv.getContents();
            for (ItemStack is : contents) {
                if (is != null) {
                    if (is.getType() == material) {
                        if (is.getDurability() == dmgValue || dmgValue <= 0) {
                            int itemAmount = is.getAmount();
                            if (itemAmount > remaining) {
                                is.setAmount(itemAmount - remaining);
                                remaining = 0;
                            } else {
                                if (remaining > 0) {
                                    remaining -= itemAmount;
                                    is.setType(Material.AIR);
                                }
                            }
                        }
                    }
                }
            }
            inv.setContents(contents);
        }
    }
}

