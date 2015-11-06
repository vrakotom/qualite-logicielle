package data;

import java.util.Observable;

/**
 * Represents the gear sensor item on the plan.
 * It can only be modified through changeTo functions
 * @author parrie
 *
 */
public class GearSensor extends Observable{
	
	/**
	 * State of the gear
	 * 0 is retracted
	 * 1 is moving
	 * 2 is extended
	*/
	private int state;
	
	/**
	 * Constructor initializing the gear to retracted 
	 */
	public GearSensor()
	{
		state = 0;
	}
	
	/**
	 * Returns the status of the gear
	 * @return Status of the gear
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * If the gear is retracted or extended, it moves. Else, it doesn't do anything. Observer is notify once the value is changed
	 */
	protected void changeToMove() {
		clearChanged();
		if(state == 0 || state == 2)
		{
			state = 1;
			setChanged();
		}	
		notifyObservers(state);
	}
	
	/**
	 * If the gear is moving, it retracts. Else, it doesn't do anything. Observer is notify once the value is changed
	 */
	protected void changeToRetracted() {
		clearChanged();
		if(state == 1)
		{
			state = 0;
			setChanged();
		}
		notifyObservers(state);
	}
	
	/**
	 * If the gear is moving, it extends. Else, it doesn't do anything. Observer is notify once the value is changed
	 */
	protected void changeToExtended() {
		clearChanged();
		if(state == 1)
		{
			state = 2;
			setChanged();
		}
		notifyObservers(state);
	}
	
}
