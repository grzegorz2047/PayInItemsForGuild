package pl.grzegorz2047.piidg;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {//Potrzeba chyba MySQL do tego... nie


//    private SCCore core;

    @Override
    public void onEnable() {
        Server server = this.getServer();
        ConsoleCommandSender console = server.getConsoleSender();
        //Send the console something!
        saveDefaultConfig();
        console.sendMessage(ChatColor.BLUE + "----------------------------------------------------------");
        console.sendMessage(ChatColor.RED + "[" + this.getName() + "]" + ChatColor.GOLD + " stworzony by przez Grzegorza2047     ");
        console.sendMessage(ChatColor.BLUE + "----------------------------------------------------------");
	   /* if(!hookSimpleClans()){
	    	console.sendMessage(ChatColor.GOLD + "["+ this.getName()+ "] " + ChatColor.RED + "Nie znaleziono SimpleClans!");
	    	console.sendMessage(ChatColor.BLUE+ "----------------------------------------------------------");
	    }
	    else{
	    	this.getServer().getPluginManager().registerEvents(new SimpleClansListener(this), this);
	    	console.sendMessage(ChatColor.RED +"[" +this.getName()+"]" +ChatColor.GOLD + " zostal przyczepiony SimpleClans  ");
	    	console.sendMessage(ChatColor.BLUE+ "----------------------------------------------------------");
	    }*/
        PluginManager pluginManager = server.getPluginManager();
        pluginManager.registerEvents(new FactionsListener(this), this);
    }
	
	/*
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

*/
	/*public ClanPlayerManager getClanPlayerManager(){
        return this.core.getClanPlayerManager();
	}

	public ClanManager getClanManager()	{
        return this.core.getClanManager();
	}*/

}

