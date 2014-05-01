package com.zg.chickenshoot.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.zg.chickenshoot.Main;
import com.zg.chickenshoot.game.Game1Mech;
import com.zg.chickenshoot.game.Game1Message;

public class BackdoorToGame1 implements CommandExecutor {
	
	Main plugin;
	Game1Mech mechs;
	public BackdoorToGame1(Main plugin, String cmd, Game1Mech mechs){
        plugin.getCommand(cmd).setExecutor(this);
        this.plugin = plugin;
        this.mechs = mechs;
    }
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("testing")){
			Bukkit.getScheduler().runTaskLater(plugin, new Game1Message(plugin, "§a[Chicken Shoot] §dA game is starting in 30 seconds!", mechs), 20);
		}
		return false;
	}

}
