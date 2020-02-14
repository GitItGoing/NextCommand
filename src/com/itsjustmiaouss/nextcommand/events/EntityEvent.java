package com.itsjustmiaouss.nextcommand.events;

import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.itsjustmiaouss.nextcommand.Main;

public class EntityEvent implements Listener {
	
	private Main main;

	public EntityEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		World w = e.getEntity().getWorld();
		if(e.getEntity() instanceof TNTPrimed) {
			if(main.getConfig().getBoolean("explosion.tnt-explosion") == true) return;
				e.setCancelled(true);
				w.createExplosion(e.getEntity().getLocation(), 0);
		}
		if(e.getEntity() instanceof Creeper) {
			if(main.getConfig().getBoolean("explosion.creeper-explosion") == true) return;
			e.setCancelled(true);
			w.createExplosion(e.getEntity().getLocation(), 0);
		}
		if(e.getEntity() instanceof Minecart) {
			if(main.getConfig().getBoolean("explosion.minecart-explosion") == true) return;
			e.setCancelled(true);
			w.createExplosion(e.getEntity().getLocation(), 0);
		}
		if(e.getEntity() instanceof Wither) {
			if(main.getConfig().getBoolean("explosion.wither-explosion") == true) return;
			e.setCancelled(true);
			w.createExplosion(e.getEntity().getLocation(), 0);
		}
		if(e.getEntity() instanceof WitherSkull) {
			if(main.getConfig().getBoolean("explosion.wither-explosion") == true) return;
			e.setCancelled(true);
			w.createExplosion(e.getEntity().getLocation(), 0);
		}
	}

}
