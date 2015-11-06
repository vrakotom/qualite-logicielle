package data;

import java.util.ArrayList;

/**
 * Represents the item electro valve for the gears on the plane. It has the list of the gears sensors, and can move them.
 * @author parrie
 *
 */
public class ElectroValveGear {
	
	/**
	 * List of the gears.
	 */
	private ArrayList<GearSensor> gears;
	
	/**
	 * Initialize the value of the gears sensor
	 * @param gearSensors list of the gears sensor already initialized
	 */
	public ElectroValveGear(ArrayList<GearSensor> gearSensors) {
		gears = gearSensors;
	}
	
	public int getGear1State() {
		return gears.get(0).getState();
	}
	
	public int getGear2State() {
		return gears.get(1).getState();
	}
	
	public int getGear3State() {
		return gears.get(2).getState();
	}
	
	/**
	 * For each gear in the list, change their status to move.
	 */
	protected void doMove() {
		for (GearSensor aGear : gears) {
			aGear.changeToMove();
		}
	}
	
	/**
	 * For each gear in the list, change their status to extended.
	 */
	protected void doExtention() {
		for (GearSensor aGear : gears) {
			aGear.changeToExtended();
		}
	}
	
	/**
	 * For each gear in the list, change their status to rectracted.
	 */
	protected void doRetracted() {
		for (GearSensor aGear : gears) {
			aGear.changeToRetracted();
		}
	}
}
