package pl.grzegorz2047.piidg;


import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SimpleClansListener implements Listener{

	public Main plugin;
	Player gracz = null;
	
	public SimpleClansListener(Main instance) {
		plugin = instance;
	}

		


/*

	@EventHandler 
	public void onPlayerCr(ClanCreateEvent c){

		Set<ClanPlayer> player = c.getClan().getAllMembers();// I cannot get player who wrote /clan create tag nameofcan
		
		for(ClanPlayer p : player){
			gracz = p.toPlayer();
		}
			boolean EnoughBlocks=false;
			List<String> liczba = plugin.getConfig().getStringList("Ilosc");
			List<String> Bloki = plugin.getConfig().getStringList("bloki");
			for(int i=0;i<Bloki.size();i++){
				if(gracz.getInventory().contains(Integer.parseInt(Bloki.get(i)),Integer.parseInt(liczba.get(i))) ){
					
				}
				else{
					String wiadomosc = plugin.getConfig().getString("notenoughblocks");
					gracz.sendMessage(ChatColor.GOLD +"[SimpleClansAddon] "+ ChatColor.RED+ wiadomosc);
					EnoughBlocks=false;
					c.setCancelled(true);
					return;
				}
			}
			EnoughBlocks=true;
			if(EnoughBlocks){
				for(int i=0;i<Bloki.size();i++){
					removeFromInv(gracz.getInventory(),Material.getMaterial(Integer.parseInt(Bloki.get(i))),0,Integer.parseInt(liczba.get(i) ) );
				}
				System.out.println("Gildia prawdopodobnie zostala zalozona :)");
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
	}*/
	
}
