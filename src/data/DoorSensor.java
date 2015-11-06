package data;

import java.util.Observable;

/**
 * Represents the door sensor item on the plan.
 * It can only be modified through changeTo functions
 * @author parrie
 *
 */
public class DoorSensor extends Observable{
	
	/**
	 * State of the door 
	 * 0 is closed
	 * 1 is moving
	 * 2 is open
	*/
	private int state;
	
	/**
	 * Constructor initializing the door to close 
	 */
	public DoorSensor() {
		state = 0;
	}
	
	/**
	 * Returns the status of the door
	 * @return Status of the door
	 */
	public int getState() {
		return state;
	}

	/**
	 * If the door is close or open, it moves. Else, it doesn't do anything. Observer is notify once the value is changed
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
	 * If the door is moving, it closes. Else, it doesn't do anything. Observer is notify once the value is changed
	 */
	protected void changeToClose() {
		clearChanged();
		if(state == 1)
		{
			state = 0;
			setChanged();
		}
		notifyObservers(state);
	}
	
	/**
	 * If the door is moving, it opens. Else, it doesn't do anything. Observer is notify once the value is changed
	 */
	protected void changeToOpen() {
		clearChanged();
		if(state == 1)
		{
			state = 2;
			setChanged();
		}
		notifyObservers(state);
	}
}
