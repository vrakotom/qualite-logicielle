package data;

import javafx.application.Platform;

/**
 * Thread that retracts the gears in less than 15 seconds.
 * @author parrie
 *
 */
public class ThreadRetractGears extends Thread {
	
	/**
	 * Electro valve used to open doors
	 */
	private ElectroValveDoor openDoorControl;
	/**
	 * Electro valve used to close doors
	 */
	private ElectroValveDoor closeDoorControl;
	/**
	 * Electro valve used to retract gears
	 */
	private ElectroValveGear closeGearControl;
	
	/**
	 * Initiate the entry points to the doors sensor datas
	 * @param openDoorControl Initiate the corresponding attribute
	 * @param closeDoorControl Initiate the corresponding attribute
	 * @param closeGearControl Initiate the corresponding attribute
	 */
	public ThreadRetractGears(ElectroValveDoor openDoorControl, ElectroValveDoor closeDoorControl, ElectroValveGear closeGearControl) 
	{
		this.openDoorControl=openDoorControl;
		this.closeDoorControl=closeDoorControl;
		this.closeGearControl=closeGearControl;
	}
	
	public void run() {
		
		//The Status of one door is the status of all doors (is it true?)
		int doorsState = openDoorControl.getDoor1State();
		int gearsState = closeGearControl.getGear1State();
		
		if(doorsState == 0)
		{
			Platform.runLater(() -> openDoorControl.doMove());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			Platform.runLater( () -> openDoorControl.doOpen());
			doorsState = openDoorControl.getDoor1State();
		}
		
		//Doors are moving
		if(doorsState == 1)
		{
			Platform.runLater( () -> openDoorControl.doOpen());
			doorsState = openDoorControl.getDoor1State();
		}
		
		if(gearsState == 2)
		{
			Platform.runLater( () -> closeGearControl.doMove());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			Platform.runLater( () -> closeGearControl.doRetracted());
			gearsState = closeGearControl.getGear1State();
		}
		
		if(gearsState == 1)
		{
			Platform.runLater( () -> closeGearControl.doRetracted());
			gearsState = closeGearControl.getGear1State();
		}
		
		if(doorsState == 2)
		{
			Platform.runLater( () -> closeDoorControl.doMove());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			Platform.runLater( () -> closeDoorControl.doClose());
			doorsState = closeDoorControl.getDoor1State();
		}
		
		if(doorsState == 1)
		{
			Platform.runLater( () -> closeDoorControl.doClose());
			doorsState = closeDoorControl.getDoor1State();
		}
		
	}
}
