package com.gugu42.rcmod.shipsys;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

public class ShipSystem {

	public static ArrayList<ShipWaypoint> waypoints = new ArrayList<ShipWaypoint>();

	public ShipSystem() {

	}

	public static void addWaypoint(ShipWaypoint wp) {
		waypoints.add(wp);
	}

	public static ArrayList<ShipWaypoint> getWaypointList() {
		return waypoints;
	}

	public static String getSaveData() {
		String data = "";

		for (ShipWaypoint wp : waypoints) {
			if (wp != null && wp.name != null && wp.toString() != "null 0 0 0 null false") {
				if (!data.contains(wp.toString())) {
					data += wp.toString() + ";";
				}
			}
		}

		//		data.substring(0, data.length() - 1);

		System.out.println("Sent save data : " + data);

		return data;
	}

	public static void fetchSaveData(String data) {
		String[] waypoints = data.split(";");
		for (int i = 0; i < waypoints.length; i++) {
			if (!waypoints[i].contains("null 0 0 0 null false")) {
				ShipWaypoint wp = new ShipWaypoint(waypoints[i]);
				if (!getWaypointsName().contains(wp.name)) {
					addWaypoint(wp);
				} else {
					System.out.println("This waypoint already exists !");
				}
			} else
				System.out.println("Error : Null waypoint");
		}
	}

	/**
	 * This methods returns all waypoints available to the PLAYER. ( Meaning private ones won't show )
	 * 
	 * @param player
	 * @return Waypoint list
	 */
	public static ArrayList<String> getWaypointsName(EntityPlayer player) {
		ArrayList<String> waypointName = new ArrayList<String>();

		for (ShipWaypoint waypoint : waypoints) {
			if (waypoint.isPrivate) {
				if (player.getDisplayName().equals(waypoint.creatorName)) {
					waypointName.add(waypoint.name);
				}
			} else {
				waypointName.add(waypoint.name);
			}
		}

		return waypointName;
	}

	/**
	 * This methods returns all waypoints available. ( Ignoring privacy settings )
	 * 
	 * @return
	 */
	public static ArrayList<String> getWaypointsName() {
		ArrayList<String> waypointName = new ArrayList<String>();

		for (ShipWaypoint waypoint : waypoints) {
			waypointName.add(waypoint.name);
		}

		return waypointName;
	}

	/**
	 * 
	 * @param name
	 *            The name of the waypoint
	 * @return true if the name is taken
	 */
	public static boolean isNameTaken(String name) {
		for (ShipWaypoint wp : waypoints) {
			if (wp != null && wp.name != null && wp.name.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static ShipWaypoint getWaypointByName(String name) {
		for (ShipWaypoint wp : waypoints) {
			if (wp.name.equals(name)) {
				return wp;
			}
		}

		return null;
	}

	public static void removeWaypoint(String name) {
		if (getWaypointByName(name) != null) {
			waypoints.remove(getWaypointByName(name));
		}
	}
	
	public static void renameWaypoint(String oldName, String newName) {
		for (ShipWaypoint wp : waypoints) {
			if (wp.name.equals(oldName)) {
				wp.name = newName;
			}
		}
	}

}
