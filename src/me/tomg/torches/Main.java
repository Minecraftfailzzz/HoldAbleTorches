package me.tomg.torches;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener{
	
	public ArrayList<Player> torch = new ArrayList<Player>();
	public ArrayList<Player> redstonetorch = new ArrayList<Player>();
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription(); //getting the plugin.yml
	    Bukkit.getServer().getLogger().info(pdfFile.getName() + "Version: " + pdfFile.getVersion() + " Has been Enabled!"); //sending a message in the console that the plugin is enabled correctly and which version it's running on.
	    Bukkit.getServer().getLogger().info("Plugin made by: " + pdfFile.getAuthors()); //sending the author's into the console.
	    Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerItemHoldEvent(PlayerItemHeldEvent e){ //creating the event to check if the player is holding a torch.
		
		if (!(e.getPlayer().hasPermission("holdabletorches.use"))) { //checks if the player has permission. if not, then the code stops here. if so. it'll continue.
            return;
    }
				
		if (e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.TORCH){ //if the player is holding a torch, it'll do the following.
			torch.add(e.getPlayer());
			}
			if(torch.contains(e.getPlayer())){
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1)); 	//gives the player the potion effect for 1'000'000 seconds.
				
			}		
			
				if(!(e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.TORCH)){ //checks if the player isn't holding the torch anymore.
				e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION); //removes the potion effect.
				torch.remove(e.getPlayer());
				}
				/*
				 * Below it does the same all over again but then for the Redstone Torch instead of the normal torch.
				 * I don't feel like copying all the comments from above again so thats why I'm writing this.
				 */
				
				if (e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.REDSTONE_TORCH_ON){
					redstonetorch.add(e.getPlayer());
					}
				
					if(redstonetorch.contains(e.getPlayer())){
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0));
					}
				
				if(redstonetorch.contains(e.getPlayer())){
				if(!(e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.REDSTONE_TORCH_ON)){
				redstonetorch.remove(e.getPlayer());
				e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
			}
		}
	}
}
