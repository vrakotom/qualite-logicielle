package data;

import java.util.ArrayList;

import controler.CommandPanelController;

/**
 * Represents the general electro valve item on the plane. It owns 2 door electro valve, one to open, the other to close, and 2 gear electro valve. 
 * @author parrie
 *
 */
public class GeneralElectroValve {

	/**
	 * One door electro valve to open
	 */
	private ElectroValveDoor openDoorControl;
	/**
	 * One door electro valve to close
	 */
	private ElectroValveDoor closeDoorControl;
	/**
	 * One gear electro valve to open
	 */
	private ElectroValveGear openGearControl;
	/**
	 * One gear electro valve to close
	 */
	private ElectroValveGear closeGearControl;
	
	/**
	 * This thread is used to launch the series of action and waiting times between those actions.
	 */
	private Thread action;
	
	/**
	 * Initiate all the doors sensor and gear sensor, then initiate the electro valves.
	 * @param observer The controller is needed due to observer pattern. It is used to initialize the doors which are observable.
	 */
	public GeneralElectroValve(CommandPanelController observer) {

		DoorSensor newDoor;
		ArrayList<DoorSensor> doorsSensors = new ArrayList<DoorSensor>();
		for (int i = 0; i <= 2; i++) {
			newDoor = new DoorSensor();
			newDoor.addObserver(observer);
			doorsSensors.add(newDoor);
		}

		GearSensor newGear;
		ArrayList<GearSensor> gearsSensors = new ArrayList<GearSensor>();
		for (int i = 0; i <= 2; i++) {
			newGear = new GearSensor();
			newGear.addObserver(observer);
			gearsSensors.add(newGear);
		}

		openDoorControl = new ElectroValveDoor(doorsSensors);
		closeDoorControl = new ElectroValveDoor(doorsSensors);
		openGearControl = new ElectroValveGear(gearsSensors);
		closeGearControl = new ElectroValveGear(gearsSensors);
		
		action = new Thread();
	}

	public int[] getGearsStatus() {
		int[] status = new int[3];
		status[0] = openGearControl.getGear1State();
		status[1] = openGearControl.getGear2State();
		status[2] = openGearControl.getGear3State();
		return status;
	}

	public int[] getDoorsStatus() {
		int[] status = new int[3];
		status[0] = openDoorControl.getDoor1State();
		status[1] = openDoorControl.getDoor2State();
		status[2] = openDoorControl.getDoor3State();
		return status;
	}

	/**
	 * Interrupts existing action to launch a thread that will extend gears
	 */
	protected void extendGears() {
		if(action.isAlive())
			action.interrupt();
		action = new ThreadExtendGears(openDoorControl, closeDoorControl, openGearControl);
		action.start();
	}

	/**
	 * Interrupts existing action to launch a thread that will retract gears
	 */
	protected void retractGears() {
		if(action.isAlive())
			action.interrupt();
		action = new ThreadRetractGears(openDoorControl, closeDoorControl, closeGearControl);
		action.start();
	}
}
