package me.tomg.torches;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription(); //getting the plugin.yml
	    Bukkit.getServer().getLogger().info(pdfFile.getName() + "Version: " + pdfFile.getVersion() + " Has been Enabled!"); //sending a message in the console that the plugin is enabled correctly and which version it's running on.
	    Bukkit.getServer().getLogger().info("Plugin made by: " + pdfFile.getAuthors()); //sending the author's into the console.
	    new TorchListener(this); //letting the plugin know that there's another class
	}
	/*
	 * Following code is from https://github.com/StashCat/cCore/blob/master/src/tk/stashcat/cCore.java
	 * No idea what it exactly does. But it has something with ChatColors. StashCat said it was usefull so
	 * thats why I added it.
	 */
	
	public String getPrefix(Plugin p){
    	String prefix = "[" + ChatColor.GREEN + ChatColor.BOLD + p.getDescription().getName() + ChatColor.RESET + "] ";
    	return prefix;
    }
    
    public void sendMsg(CommandSender s, boolean usePrefix, String msg, Plugin p){
		msg = colorize(msg);
		if (usePrefix)
			s.sendMessage(getPrefix(p) + msg);
		else
			s.sendMessage(msg);
	}
    
    public int digitize(String amount){
    	int integer;
    	try{
			integer = Integer.parseInt(amount);
			return integer;
			} catch (NumberFormatException e) {
				return -1;
			}
    }
    
    public String colorize(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
    
    public String colorize(String msg, char character){
    	return ChatColor.translateAlternateColorCodes(character, msg);
    }
    
    public void addListElement(Plugin p, String key, String... element) {
        List<String> list = p.getConfig().getStringList(key);
        list.addAll(Arrays.asList(element));
        p.getConfig().set(key, list);
        p.saveConfig();
    }
}
