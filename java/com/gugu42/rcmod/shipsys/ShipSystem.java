package com.gugu42.rcmod.shipsys;

import java.util.ArrayList;
import java.util.List;

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
			if (wp != null && wp.name != null
					&& wp.toString() != "null 0 0 0 null false") {
				data += wp.toString() + ";";
			}
		}

		//		data.substring(0, data.length() - 1);

		System.out.println("Sent save data : " + data);

		return data;
	}

	public static void fetchSaveData(String data) {
		String[] waypoints = data.split(";");
		for (int i = 0; i < waypoints.length; i++) {
			if (!waypoints[i].contains("null 0 0 0 null false"))
				addWaypoint(new ShipWaypoint(waypoints[i]));
			else
				System.out.println("Error : Null waypoint");
		}
	}

	public static ArrayList getWaypointsName() {
		ArrayList waypointName = new ArrayList<String>();

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

}
