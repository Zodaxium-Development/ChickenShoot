package com.zg.chickenshoot.game;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;

public class Game1Mech {
	public Entity SpawnChicken(Location loc){
		Chicken chicken = loc.getWorld().spawn(loc, Chicken.class);
		chicken.setCustomName("FakeChicken");
		chicken.setCustomNameVisible(false);
		return chicken;
	}
	public int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	
	

}
