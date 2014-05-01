package com.zg.chickenshoot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Score;
import org.bukkit.util.Vector;

public class ChickenListener implements Listener {
	Main plugin;
	public ChickenListener(Main plugin){
	    Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	    this.plugin = plugin;
	    }
	@EventHandler
	public void onPlayerPassLine(PlayerMoveEvent e){
		Player player = e.getPlayer();
		if(player.getLocation().getX() <= 1 && plugin.inGame.contains(player.getName())){
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[Chicken Shoot] &dDon't pass the line!"));
			player.setVelocity(new Vector(0.6, 0.2, 0));
		}
	}
	
	@EventHandler
	public void onChickenDie(EntityDeathEvent e){
		if(e.getEntity().getCustomName() == "FakeChicken"){
			e.getDrops().clear();
		}
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e){
		if(e.getCause() == DamageCause.LAVA || e.getCause() == DamageCause.FIRE){
			if(e.getEntity() instanceof Chicken){
				Chicken chick = (Chicken) e.getEntity();
				if(chick.getCustomName() == "FakeChicken"){
					e.setCancelled(true);
					chick.remove();
				}
			}
		}
		
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Chicken){
			Chicken chicken = (Chicken) e.getEntity();
			if(e.getDamager() instanceof Arrow){
				Arrow arrow = (Arrow) e.getDamager();
				if(arrow.getShooter() instanceof Player){
					Player player = (Player) arrow.getShooter();
					if(plugin.inGame.contains(player.getName())){
						chicken.getWorld().playEffect(chicken.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK.getId());
						chicken.remove();
						arrow.remove();
						
						if(plugin.inGame.contains(player.getName())){
							plugin.getConfig().set("Game1.Players." + player.getName(), plugin.getConfig().getInt("Game1.Players." + player.getName()) + 1);
							plugin.saveConfig();
							player.sendMessage("§a[Chicken Shoot] §dChicken killed!");
							Score scoree = plugin.score.getScore(Bukkit.getOfflinePlayer(player.getName()));
							scoree.setScore(plugin.getConfig().getInt("Game1.Players." + player.getName()));
							int KillScore = plugin.getConfig().getInt("Game1Score.WinScore");
							if(plugin.getConfig().getInt("Game1.Players." + player.getName()) >= KillScore){
								Bukkit.getScheduler().cancelAllTasks();
								for(Player p : Bukkit.getOnlinePlayers()){
									if(plugin.inGame.contains(p.getName())){
										p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" +  player.getName() + " &dhas won Chicken Shoot!"));
										plugin.inGame.remove(p.getName());
										p.teleport(Bukkit.getWorld("Minigames").getSpawnLocation());
										plugin.getConfig().set("Game1", "No game");
										plugin.saveConfig();
										plugin.board.resetScores(p);
										p.getActivePotionEffects().clear();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
