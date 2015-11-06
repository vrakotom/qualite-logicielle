package data;

import controler.CommandPanelController;

/**
 * Represents the handle item on the plane. It uses the general electro valve to launches the actions.
 * @author parrie
 *
 */
public class Handle {
	
	/**
	 * Represents the status of the handle
	 * 0 handle is down
	 * 1 handle is up
	 */
	private int state;
	
	/**
	 * Entry point to the model.
	 */
	private GeneralElectroValve digitalSystem;
	
	/**
	 * Initiate the general electro valve
	 * @param observer It is transmitted to the observable items.
	 */
	public Handle(CommandPanelController observer) {
		state = 0;
		digitalSystem = new GeneralElectroValve(observer);
	}
	
	public int getState() {
		return state;
	}
	
	/**
	 * Deals the actions on the handle. If the handle goes down, the gears are extended. It the handle goes up, the gears are retracted.
	 */
	public void handleMoves() {
		if( state == 0)
		{
			state = 1;
			digitalSystem.extendGears();
		}
		if( state == 1)
		{
			state = 0;
			digitalSystem.retractGears();
		}
	}
	
	public int[] getDoorsStatus() {
		return digitalSystem.getDoorsStatus();
	}
	
	public int[] getGearsStatus() {
		return digitalSystem.getGearsStatus();
	}
}
