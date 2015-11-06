package data;

import javafx.application.Platform;

/**
 * Thread that extracts the gears in less than 15 seconds.
 * @author parrie
 *
 */
public class ThreadExtendGears extends Thread {
	
	/**
	 * Electro valve used to open doors
	 */
	private ElectroValveDoor openDoorControl;
	/**
	 * Electro valve used to close doors
	 */
	private ElectroValveDoor closeDoorControl;
	/**
	 * Electro valve used to extract gears
	 */
	private ElectroValveGear openGearControl;

	/**
	 * Initiate the entry points to the doors sensor datas
	 * @param openDoorControl Initiate the corresponding attribute
	 * @param closeDoorControl Initiate the corresponding attribute
	 * @param openGearControl Initiate the corresponding attribute
	 */
	public ThreadExtendGears(ElectroValveDoor openDoorControl, ElectroValveDoor closeDoorControl, ElectroValveGear openGearControl) {
		this.openDoorControl = openDoorControl;
		this.closeDoorControl = closeDoorControl;
		this.openGearControl = openGearControl;
	}
	
	public void run()
	{
		//The Status of one door is the status of all doors (is it true?)
		int doorsState = openDoorControl.getDoor1State();
		int gearsState = openGearControl.getGear1State();
		
		//Doors are closed
		if(doorsState == 0)
		{
			Platform.runLater(() -> openDoorControl.doMove());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			Platform.runLater( () -> openDoorControl.doOpen());
			//Actualize DoorState
			doorsState = openDoorControl.getDoor1State();
		}
		
		//Doors are moving
		if(doorsState == 1)
		{
			Platform.runLater( () -> openDoorControl.doOpen());
			doorsState = openDoorControl.getDoor1State();
		}
	
		//Gears are retracted
		if(gearsState == 0)
		{
			Platform.runLater( () -> openGearControl.doMove());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			Platform.runLater( () -> openGearControl.doExtention());
			gearsState = openGearControl.getGear1State();
		}
		
		//Gears are moving
		if(gearsState == 1)
		{
			Platform.runLater( () -> openGearControl.doExtention());
			gearsState = openGearControl.getGear1State();
		}
		
		//Doors are opened
		if(doorsState == 2)
		{
			Platform.runLater( () -> closeDoorControl.doMove());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			Platform.runLater( () -> closeDoorControl.doClose());
			doorsState = closeDoorControl.getDoor1State();
		}
		
		//Doors are moving
		if(doorsState == 1)
		{
			Platform.runLater( () -> closeDoorControl.doClose());
			doorsState = closeDoorControl.getDoor1State();
		}
	}
}
