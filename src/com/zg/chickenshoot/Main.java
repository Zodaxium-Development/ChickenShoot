package com.zg.chickenshoot;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.zg.chickenshoot.command.BackdoorToGame1;
import com.zg.chickenshoot.command.ChickenShootCommand;
import com.zg.chickenshoot.game.Game1Mech;

public class Main extends JavaPlugin {
	public void onEnable(){
		new ChickenListener(this);
		Game1Mech mechs = new Game1Mech();
		new ChickenShootCommand(this, "chickenshoot", mechs);
		new BackdoorToGame1(this, "testing" , mechs);
		}
	public HashSet<String> inLobby = new HashSet<String>();
	public HashSet<String> inGame = new HashSet<String>();
	public ScoreboardManager manager = Bukkit.getScoreboardManager();
	public Scoreboard board = manager.getNewScoreboard();
	public Objective score = board.registerNewObjective("dummy", "dummy");
}
