package me.tomg.torches;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TorchListener implements Listener{
	
	Main pl;
	public TorchListener(Main plugin){
	  pl = plugin;
	  pl.getServer().getPluginManager().registerEvents(this, pl);
	}

	
	@EventHandler
	public void onPlayerItemHoldEvent(PlayerItemHeldEvent e){ //creating the event to check if the player is holding a torch.
		int torch = 0;				//just an integer to make it easier (default value: 0)
		int redstonetorch = 0;		//just an integer to make it easier (default value: 0)
		
		if (!e.getPlayer().hasPermission("holdabletorches.use")) { //checks if the player has permission. if not, then the code stops here. if so. it'll continue.
            return;
    }
				
		if (e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.TORCH){ //if the player is holding a torch, it'll do the following.
			torch = 1;} 		//set the integer "torch" to "1"
			if(torch == 1){ 	//checks if the integer "torch" = 1, if so. it'll do the following.
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1)); 	//gives the player the potion effect for 1'000'000 seconds.
				
			}		
			
				if(!(e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.TORCH)){ //checks if the player isn't holding the torch anymore.
				e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION); //removes the potion effect.
				torch = 0; //sets the torch value to 0 (the default value) again.
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
				
				if(redstonetorch == 1){
				if(!(e.getPlayer().getInventory().getItem(e.getNewSlot()) != null && e.getPlayer().getInventory().getItem(e.getNewSlot()).getType() == Material.REDSTONE_TORCH_ON)){
				redstonetorch = 0;
				e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
			}
		}
	}
}
