package com.itsjustmiaouss.nextcommand.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.itsjustmiaouss.nextcommand.Main;

public class SpawnCommand implements CommandExecutor, TabCompleter {
	
	private Main main;

	public SpawnCommand(Main main) {
		this.main = main;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(main.prefixerror + main.getConfig().getString("console-no-player").replaceAll("&", "§"));
			return true;
		}
		
		Player p =(Player)sender;
		
		if(args.length == 0) {
			if(!p.hasPermission("nextcommand.spawn") || !p.hasPermission("nextcommand.*")) {
				p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
				return true;
			}
					String w = main.getSpawnConfig().getString("spawncommand.location.World");
					Double x = main.getSpawnConfig().getDouble("spawncommand.location.X");
					Double y = main.getSpawnConfig().getDouble("spawncommand.location.Y");
					Double z = main.getSpawnConfig().getDouble("spawncommand.location.Z");
					Float yaw = (float) main.getSpawnConfig().getDouble("spawncommand.location.Yaw");
					Float pitch = (float) main.getSpawnConfig().getDouble("spawncommand.location.Pitch");
					if(w == "") {
						p.sendMessage(main.prefixerror + main.getSpawnConfig().getString("spawncommand.no-spawn").replaceAll("&", "§"));
						return true;
					}
					try {
						p.sendMessage(main.prefix + main.getSpawnConfig().getString("spawncommand.teleportation").replaceAll("&", "§"));
						p.teleport(new Location(Bukkit.getWorld(w), x, y, z, yaw, pitch));
					} catch (Exception e) {
						p.sendMessage(main.prefixerror + main.getConfig().getString("exception").replaceAll("&", "§").replace("{ERROR}", e.getMessage()));
					}
		}
		
		if(args.length >= 1) {
			if(!args[0].equalsIgnoreCase("set")) {
				p.sendMessage(main.prefixerror + "§7/spawn set");
				return true;
			}
			if(!p.hasPermission("nextcommand.spawn.set") || !p.hasPermission("nextcommand.*")) {
			p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
			return true;
			}
			
			main.getSpawnConfig().set("spawncommand.location.World", p.getWorld().getName());
			main.getSpawnConfig().set("spawncommand.location.X", p.getLocation().getX());
			main.getSpawnConfig().set("spawncommand.location.Y", p.getLocation().getY());
			main.getSpawnConfig().set("spawncommand.location.Z", p.getLocation().getZ());
			main.getSpawnConfig().set("spawncommand.location.Yaw", p.getLocation().getYaw());
			main.getSpawnConfig().set("spawncommand.location.Pitch", p.getLocation().getPitch());
			try {
				main.getSpawnConfig().save("plugins/" + main.getName() + "/spawn.yml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			World w = p.getWorld();
			w.setSpawnLocation(new Location(Bukkit.getWorld(w.getName()), p.getLocation().getX(), p.getLocation().getY() ,p.getLocation().getZ(), p.getLocation().getYaw() ,p.getLocation().getPitch()));
			p.sendMessage(main.prefix + main.getSpawnConfig().getString("spawncommand.spawn-set").replaceAll("&", "§"));
		}
		
		
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> s1 = Arrays.asList("set");
		List<String> flist = Lists.newArrayList();
		if(args.length == 1) {
			for(String s : s1) {
				if(s.toLowerCase().startsWith(args[0].toLowerCase())) flist.add(s);
			}
			return flist;
		}
		return null;
	}

}
