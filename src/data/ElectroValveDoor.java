package data;

import java.util.ArrayList;

/**
 * Represents the item electro valve for the doors on the plane. It has the list of the doors sensors, and can move them.
 * @author parrie
 *
 */
public class ElectroValveDoor {
	
	/**
	 * List of the gears.
	 */
	private ArrayList<DoorSensor> doors;
	
	/**
	 * Initialize the value of the gears sensor
	 * @param doorSensors list of the gears sensor already initialized
	 */
	public ElectroValveDoor(ArrayList<DoorSensor> doorSensors) {
		doors = doorSensors; 
	}
	
	public int getDoor1State() {
		return doors.get(0).getState();
	}
	
	public int getDoor2State() {
		return doors.get(1).getState();
	}
	
	public int getDoor3State() {
		return doors.get(2).getState();
	}
	
	/**
	 * For each door in the list, change their status to move.
	 */
	protected void doMove() {
		for (DoorSensor aDoor : doors) {
			aDoor.changeToMove();
		}
	}
	
	/**
	 * For each door in the list, change their status to open.
	 */
	protected void doOpen() {
		for (DoorSensor aDoor : doors) {
			aDoor.changeToOpen();
		}
	}
	
	/**
	 * For each door in the list, change their status to close.
	 */
	protected void doClose() {
		for (DoorSensor aDoor : doors) {
			aDoor.changeToClose();
		}
	}
}
