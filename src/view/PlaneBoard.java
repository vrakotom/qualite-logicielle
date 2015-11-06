package view;

import controler.CommandPanelController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Graphical interface of the application
 * @author parrie
 *
 */
public class PlaneBoard extends Parent{
	
	/**
	 * Controller of the application
	 */
	private CommandPanelController controller;
	
	private ToggleButton gearControl;
	private Rectangle red;
	private Rectangle yellow;
	private Rectangle green;
	private Label news;
	
	/**
	 * Initialize all the components of the interface.
	 * @param controller Controller of the application, to refer to to handle actions
	 */
	public PlaneBoard(CommandPanelController controller)
	{
		super();
		this.controller=controller;
		gearControl = new ToggleButton("Extend trains");
		red = new Rectangle(50, 50);
		red.setFill(Color.RED);
		green = new Rectangle(50,50);
		green.setFill(Color.BLACK);
		yellow = new Rectangle(50,50);
		yellow.setFill(Color.BLACK);
		news = new Label("Initialized plane");
	}
	
	/**
	 * Constructs the interface and binds the actions to the components
	 */
	public void launch() {
		
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		
		Label status = new Label("Gear Status");
		
		bp.setTop(news);
		
		bp.setCenter(gp);
		gp.add(status, 1, 1);
		gp.add(red, 1, 2);
		gp.add(yellow, 1, 3);
		gp.add(green, 1, 4);
		
		bp.setBottom(gearControl);
		
		gearControl.setOnAction((e) -> {
			controller.simulateAction(getGearControl());
		});
		
		this.getChildren().add(bp);
	}

	public Rectangle getRed() {
		return red;
	}

	public Rectangle getYellow() {
		return yellow;
	}

	public Rectangle getGreen() {
		return green;
	}

	public Label getNews() {
		return news;
	}

	public ToggleButton getGearControl() {
		return gearControl;
	}
}
