package com.zg.chickenshoot.game;

import org.bukkit.Location;
import org.bukkit.World;

public class ChickenSpawnLocation implements Runnable {
	Game1Mech mechs;
	
	private World w;
	private int x1;
	private int y1;
	private int z1;
	private int z2;
	public ChickenSpawnLocation(Game1Mech mechs, World world, int x, int y, int zmin, int zmax){
		super();
		this.mechs = mechs;
		w=world;
		x1=x;
		y1=y;
		z1=zmin;
		z2=zmax;
		}
	@Override
	public void run() {
		int zloc = mechs.randInt(z1, z2);
		mechs.SpawnChicken(new Location(w, x1 + 0.5, y1 + 0.5, zloc + 0.5));
		}
	}