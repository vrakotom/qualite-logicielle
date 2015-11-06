package controler;

import java.util.Observable;
import java.util.Observer;

import data.DoorSensor;
import data.GearSensor;
import data.Handle;
import view.PlaneBoard;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Initiate the view and the whole model.
 * 
 * @author parrie
 *
 */
public class CommandPanelController extends Application implements Observer{
	
	/**
	 * command is the entry point to the whole model datas.
	 */
	private Handle command;
	
	/**
	 * view is the graphical interface of the application
	 */
	private PlaneBoard view;
	
	/**
	 * Constructor using superclass constructor
	 */
	public CommandPanelController(){
		super();
	}
	
	/**
	 * Implementation of the superclass method start, ordering to start using JavaFX on this class
	 */
	public void start()
	{	
		Application.launch(CommandPanelController.class, "");
	}
	
	/**
	 * Implementation of superclass method, initializing both model and view
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Plane simulation");
        Group root = new Group();
        Scene scene = new Scene(root, 640, 480, Color.WHITE);
        
        view = new PlaneBoard(this);
        view.launch();
        
        command = new Handle(this);
        
        root.getChildren().add(view);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Gets news from the door sensors. If their state moves, the view is modified.
	 */
	@Override
	public void update(Observable o, Object arg) {
		int event = (Integer) arg;
		//Notification from DoorSensor
		if(o instanceof DoorSensor) {
			//No treatement on lights, panel is not design to display doors informations
			switch (event) {
			case 0:
				view.getNews().setText("Doors are closed");
				break;
				
			case 1:
				view.getNews().setText("Doors are moving");
				break;
				
			case 2:
				view.getNews().setText("Doors are opened");
				break;

			default:
				break;
			}
			
		}
		
		//Notification from GearSensor
		if(o instanceof GearSensor) {
			switch (event) {
			case 0:
				view.getRed().setFill(Color.RED);
				view.getYellow().setFill(Color.BLACK);
				view.getGreen().setFill(Color.BLACK);
				break;
				
			case 1:
				view.getRed().setFill(Color.BLACK);
				view.getYellow().setFill(Color.YELLOW);
				view.getGreen().setFill(Color.BLACK);
				break;
				
			case 2:
				view.getRed().setFill(Color.BLACK);
				view.getYellow().setFill(Color.BLACK);
				view.getGreen().setFill(Color.GREEN);
				break;

			default:
				break;
			}
		}
	}
	
	/**
	 * Function called by the view in order to start retraction or extention actions
	 * 
	 * @param toggle Is used in ordered to know which action to play, and to change its text
	 */
	public void simulateAction(ToggleButton toggle) {
		command.handleMoves();
		
		//Toggle button manipulations
		if(toggle.isSelected())
			toggle.setText("Retract gears");
		else 
			toggle.setText("Extend gears");
	}
	
	
}
