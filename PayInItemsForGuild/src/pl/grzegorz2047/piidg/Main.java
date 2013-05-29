package pl.grzegorz2047.piidg;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.p000ison.dev.simpleclans2.api.SCCore;
import com.p000ison.dev.simpleclans2.api.clan.ClanManager;
import com.p000ison.dev.simpleclans2.api.clanplayer.ClanPlayerManager;

public class Main extends JavaPlugin implements Listener{//Potrzeba chyba MySQL do tego...
 

	private SCCore core;
	@Override
	public void onEnable() {
		ConsoleCommandSender konsola = this.getServer().getConsoleSender();
		//Send the console something!
		saveDefaultConfig();
		konsola.sendMessage(ChatColor.BLUE + "----------------------------------------------------------");
		konsola.sendMessage(ChatColor.RED +"[" +this.getName()+"]" +ChatColor.GOLD + " stworzony by przez Grzegorza2047     ");
		konsola.sendMessage(ChatColor.BLUE+ "----------------------------------------------------------");
	    if(!hookSimpleClans()){
	    	konsola.sendMessage(ChatColor.GOLD + "["+ this.getName()+ "] " + ChatColor.RED + "Nie znaleziono SimpleClans!");
	    	konsola.sendMessage(ChatColor.BLUE+ "----------------------------------------------------------");
	    }
	    else{
	    	this.getServer().getPluginManager().registerEvents(new SimpleClansListener(this), this);
	    	konsola.sendMessage(ChatColor.RED +"[" +this.getName()+"]" +ChatColor.GOLD + " zostal przyczepiony SimpleClans  ");
	    	konsola.sendMessage(ChatColor.BLUE+ "----------------------------------------------------------");
	    }
	    this.getServer().getPluginManager().registerEvents(new FactionsListener(this), this);
	}
	
	
	private boolean hookSimpleClans(){
        try {
            for (Plugin plugin : getServer().getPluginManager().getPlugins()) {
                if (plugin instanceof SCCore) {
                    this.core = (SCCore) plugin;
                    return true;
                }
            }
        } catch (NoClassDefFoundError e) {
            return false;
        }
        return false;
	}


	public ClanPlayerManager getClanPlayerManager(){
        return this.core.getClanPlayerManager();
	}

	public ClanManager getClanManager(){
        return this.core.getClanManager();
	}

}

