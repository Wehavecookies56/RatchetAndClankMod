package com.gugu42.rcmod.shipsys;

import java.util.ArrayList;

public class ShipSystem {

	public ArrayList<ShipWaypoint> waypoints = new ArrayList<ShipWaypoint>();	
	
	
	public ShipSystem(){
		
	}
	
	
	public void addWaypoint(ShipWaypoint wp){
		waypoints.add(wp);
	}
	
	
}
