package pl.grzegorz2047.piidg;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.massivecraft.factions.event.FactionCreateEvent;

 

public class FactionsListener implements Listener {
	public Main plugin;
	
	public FactionsListener(Main instance) {
		plugin = instance;
	}

	@EventHandler//(priority = EventPriority.HIGHEST)
	public void onClanCr(FactionCreateEvent f){

		//System.out.println("Bumcfyksz");
		if(f.getFPlayer().hasFaction()){
			f.getFPlayer().sendMessage(ChatColor.GOLD +"[FactionsAddon] "+ ChatColor.RED+"Posiadasz juz gildie!");
			f.setCancelled(true);
		}
		else{
			Player player = (Player) f.getFPlayer().getPlayer();
			boolean EnoughBlocks=false;
			List<String> liczba = plugin.getConfig().getStringList("Ilosc");
			List<String> Bloki = plugin.getConfig().getStringList("bloki");
			for(int i=0;i<Bloki.size();i++){
				if(player.getInventory().contains(Integer.parseInt(Bloki.get(i)),Integer.parseInt(liczba.get(i))) ){
					
				}
				else{
					String wiadomosc = plugin.getConfig().getString("notenoughblocks");
					player.sendMessage(ChatColor.GOLD +"[FactionsAddon] "+ ChatColor.RED+ wiadomosc);
					EnoughBlocks=false;
					f.setCancelled(true);
					return;
				}
			}
			EnoughBlocks=true;
			if(EnoughBlocks){
				for(int i=0;i<Bloki.size();i++){
					removeFromInv(player.getInventory(),Material.getMaterial(Integer.parseInt(Bloki.get(i))),0,Integer.parseInt(liczba.get(i) ) );

				}
				System.out.println("Gildia prawdopodobnie zostala zalozona :)");
			}
		}
		
	}
	
	
	public static void removeFromInv(Inventory inv, Material mat, int dmgValue, int amount){
		if(inv.contains(mat)){
			int remaining = amount;
			ItemStack[] contents = inv.getContents();
			for (ItemStack is : contents){
				if (is != null){
					if (is.getType() == mat){
						if (is.getDurability() == dmgValue || dmgValue <= 0){
							if(is.getAmount() > remaining){
								is.setAmount(is.getAmount() - remaining);
								remaining = 0;
							}
							else if(is.getAmount() <= remaining){
								if (remaining > 0){
									remaining -= is.getAmount();
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

