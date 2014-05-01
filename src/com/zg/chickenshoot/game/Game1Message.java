package com.zg.chickenshoot.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

import com.zg.chickenshoot.Main;

public class Game1Message extends BukkitRunnable {
	private ItemStack bow = new ItemStack(Material.BOW);
	Main plugin;
	private final String message;
	Game1Mech mechs;
	public Game1Message(Main plugin, String m, Game1Mech mechs){
		super();
		message = m;
		this.plugin = plugin;
		this.mechs = mechs;
	}
	@Override
	public void run(){
		for(Player player : Bukkit.getOnlinePlayers()){
			if(plugin.inLobby.contains(player.getName())){
				player.sendMessage(message);
			}
		}
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()){
					if(plugin.inLobby.contains(p.getName())){
						p.teleport((Bukkit.getWorld("ChickenShoot").getSpawnLocation()));
						bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
						p.getInventory().clear();
						p.getInventory().addItem(bow);
						p.getInventory().addItem(new ItemStack(Material.ARROW));
						plugin.inLobby.remove(p.getName());
						plugin.inGame.add(p.getName());
						p.sendMessage("§a§lThe game has started!");
						Score scoree = plugin.score.getScore(Bukkit.getOfflinePlayer(p.getName()));
						scoree.setScore(0);
						plugin.score.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aChicken Shoot"));
						plugin.score.setDisplaySlot(DisplaySlot.SIDEBAR);
						p.setScoreboard(plugin.board);
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999999, 999999999));
						
					}
				}
				/*
				 * This is a little complicated. Because I don't know what way the arena is gonna face, I can't make a certain method
				 * id the arena runs along the X-axis, you would enter the method like this:
				 * new ChickenSpawnLocation(x-min, x-max, y, z);
				 * If it was running on the Z-axis, you would do:
				 * new ChickenSpawnLocation(x, y, z-min, z-max);
				 */
				
				//TODO MUST CHANGE!
				Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new ChickenSpawnLocation(mechs, Bukkit.getWorld("ChickenShoot"), -19, 76, 26, 46), 10L, 10L);
				
			}
			
		}, 600L);
		
	
	}

}
