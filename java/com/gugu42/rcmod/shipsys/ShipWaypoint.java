package com.gugu42.rcmod.shipsys;

public class ShipWaypoint {

	public String name;
	public int posX, posY, posZ;
	public String creatorName;
	public boolean isPrivate;

	public ShipWaypoint(String name, int posX, int posY, int posZ,
			String creatorName, boolean isPrivate) {
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.creatorName = creatorName;
		this.isPrivate = isPrivate;
	}

	public ShipWaypoint(String waypointInfo) {
		String[] infos = waypointInfo.split(" ");
		if (infos.length == 6) {
			this.name = infos[0];
			this.posX = Integer.parseInt(infos[1]);
			this.posY = Integer.parseInt(infos[2]);
			this.posZ = Integer.parseInt(infos[3]);
			this.creatorName = infos[4];
			this.isPrivate = Boolean.parseBoolean(infos[5]);
		} else {
			System.err.println("WAYPOINT STRING ERROR. NOT ENOUGH ARGS. ENGAGING DERP MODE !!! String : " + waypointInfo);
		}
	}
	
	public String toString(){
		return name + " " + posX + " " + posY + " " + posZ + " " + creatorName + " " + isPrivate;
	}
	
	public ShipWaypoint getWaypointFromString(String text)
	{
		return new ShipWaypoint(text);
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public int getPosZ(){
		return this.posZ;
	}
	
	public static ShipWaypoint stringToWaypoint(String data){
		return new ShipWaypoint(data);
	}
	

}
