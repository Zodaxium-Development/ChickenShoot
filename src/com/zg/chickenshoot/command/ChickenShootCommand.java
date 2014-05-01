package com.zg.chickenshoot.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zg.chickenshoot.Main;
import com.zg.chickenshoot.game.Game1;
import com.zg.chickenshoot.game.Game1Mech;
import com.zg.chickenshoot.game.Game1Message;

public class ChickenShootCommand implements CommandExecutor {
	
	Main plugin;
	Game1Mech mechs;
	public ChickenShootCommand(Main plugin, String cmd, Game1Mech mechs){
        plugin.getCommand(cmd).setExecutor(this);
        this.plugin = plugin;
        this.mechs = mechs;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String fullCmd, String[] arg){
		if(sender instanceof Player){
			Player player = (Player) sender;
			String name = player.getName();
			if(cmd.getName().equalsIgnoreCase("ChickenShoot")){
				if(arg.length == 0){
					player.sendMessage("§a§m                                                    "); //52 spots. That is the full chat line.
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage("§d§lType §a§l/ChickenShoot help§d§l for a list of Chicken Shoot commands.");
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage("§a§m                                                    ");
					return false;
				}
				if(arg[0].equalsIgnoreCase("help")){
					player.sendMessage("§a----------------------");
					player.sendMessage("§d/cs join");
					player.sendMessage("§a----------------------");
				}
				if(arg[0].equalsIgnoreCase("join")){
					if(plugin.inLobby.contains(name)){
						player.sendMessage("§4[Chicken Shooter Error] §cYou are already in the lobby.");
						return false;
					}
					if(!plugin.inLobby.contains(name)){
						if(!Game1.hasStarted()){
							plugin.inLobby.add(name);
							player.sendMessage("§a[Chicken Shoot] §dYou have joined Game1");
							if(plugin.inLobby.size() == 3){
								Bukkit.getScheduler().runTaskLater(plugin, new Game1Message(plugin, "§a[Chicken Shoot] §dA game is starting in 30 seconds!", mechs), 20);
							}
						} else {
							player.sendMessage("§4[Chicken Shooter Error] §cGame already started.");
						}
					}
					
				}
				
			}
		}
		return false;
	}

}
