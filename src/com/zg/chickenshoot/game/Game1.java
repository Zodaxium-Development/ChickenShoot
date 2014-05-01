package com.zg.chickenshoot.game;

import java.util.logging.Level;

import com.zg.chickenshoot.Main;

public class Game1 {
	static Main plugin;
	@SuppressWarnings("static-access")
	public Game1(Main plugin){
		this.plugin = plugin;
	}
	private static boolean gameStat;
	public static boolean hasStarted(){
		return gameStat;
	}
	public static boolean startGame(boolean startgame){
		if(gameStat == true){
			plugin.getLogger().warning("Game already started!");
		}
		if(gameStat == false){
			gameStat = true;
			plugin.getLogger().log(Level.INFO, "Game1 has started!");
		}
		return gameStat;
	}

}
