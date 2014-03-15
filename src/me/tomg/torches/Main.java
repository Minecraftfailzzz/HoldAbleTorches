package me.tomg.torches;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener{
	
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription(); //getting the plugin.yml
	    Bukkit.getServer().getLogger().info(pdfFile.getName() + " Version: " + pdfFile.getVersion() + " Has been Enabled!"); //sending a message in the console that the plugin is enabled correctly and which version it's running on.
	    PluginManager pm = Bukkit.getPluginManager(); //registering the events for holding the torches
		pm.registerEvents(this, this); //registering the events for holding the torches
	}
	
	
	@EventHandler
	public void onPlayerItemHoldEvent(PlayerItemHeldEvent e){ //creating the event to check if the player is holding a torch.
		int torch = 0;				//just an integer to make it easier (default value: 0)
		int redstonetorch = 0;		//just an integer to make it easier (default value: 0)
				
		if (e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.TORCH){ //if the player is holding a torch, it'll do the following.
			torch = 1;} 		//set the integer "torch" to "1"
			if(torch == 1){ 	//checks if the integer "torch" = 1, if so. it'll do the following.
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1)); 	//gives the player the potion effect for 1'000'000 seconds.
				
			}				
				
				if(torch == 1) return; //checks if torch = 1 again. if so it'll do the following.
				if(!(e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.TORCH)){ //checks if the player isn't holding the torch anymore.
				torch = 0; //sets the torch value to 0 (the default value) again.
				e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION); //removes the potion effect.
			}
				/*
				 * Below it does the same all over again but then for the Redstone Torch instead of the normal torch.
				 * I don't feel like copying all the comments from above again so thats why I'm writing this.
				 */
				
				if (e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.REDSTONE_TORCH_ON){
					redstonetorch = 1;}
				
					if(redstonetorch == 1){
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0));
					}
				
				if(redstonetorch == 1) return;
				if(!(e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.REDSTONE_TORCH_ON)){
				redstonetorch = 0;
				e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
	}
}
