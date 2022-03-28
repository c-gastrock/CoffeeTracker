package coffeeTracker;

import javafx.geometry.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

/*
 * Timer with basic functionality
 * TODO: reduce CPU and memory usage (don't use super infinite loop, make it check few times per second.
 * 		 add notes to each lap
 * 		 add some padding between the lines
 */

public class Timer extends AnimationTimer{
	
	Label timeDisplay;
	VBox device; // actual timer object to return to show on scene
	long startTime;
	long stopTime;
	boolean started;
	VBox lapsDisplay;
	
	Timer() {
		
		started = false;
		
		device = new VBox(); // container for clock face + controls
		device.setMinHeight(300);
		device.setPrefHeight(300);
		device.setMaxHeight(300);
		device.setMinWidth(600);
		device.setPrefWidth(600);
		device.setMaxWidth(600);
		HBox btnRow = new HBox(); // row for the timer controls
		
		timeDisplay = new Label("0:00");
		timeDisplay.setId("clock"); // css id selector for clockface
		
		device.setAlignment(Pos.CENTER);
		device.getChildren().add(timeDisplay);
		
		btnRow.setAlignment(Pos.CENTER);
		btnRow.setSpacing(5);
		btnRow.setPadding(new Insets(10));
		
		Button btnSta = new Button("Start");
		Button btnLap = new Button("Lap");
		Button btnSto = new Button("Stop");
		Button btnRes = new Button("Reset");
		
		btnSta.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				start();
			}
		});
		btnLap.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				lap();
			}
		});
		btnSto.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				stop();
			}
		});
		btnRes.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				reset();
			}
		});
		
		btnRow.getChildren().addAll(btnSta, btnLap, btnSto, btnRes);
		
		lapsDisplay = new VBox();
		lapsDisplay.setSpacing(5);
		
		ScrollPane lapPane = new ScrollPane();
		lapPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		lapPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		lapPane.setPrefViewportHeight(150);
		lapPane.setPrefViewportWidth(400);
		lapPane.setMaxSize(400, 150);
		lapPane.setContent(lapsDisplay);
		lapPane.setPadding(new Insets(25));

		lapPane.setId("timer-laps");
		
		device.getChildren().add(btnRow);
		device.getChildren().add(lapPane);
		
	}
	
	VBox getTimer() { // to move timer container onto scene
		
		return device;
		
	}
	
	public void handle(long now) {
		
    	long currTime, mins, secs;
    	String toDisplay = "";
		currTime = (System.currentTimeMillis() - startTime);
		mins = currTime/60000;
		secs = (currTime/1000) % 60;
		toDisplay = String.valueOf(mins) + ":" + String.valueOf(secs); // might be an easier way to do this, oh well
		if(secs < 10) { toDisplay = toDisplay.substring(0, toDisplay.indexOf(':') + 1) + "0" + toDisplay.substring(toDisplay.indexOf(':') + 1);} // adds extra 0 to make clock face pretty
		timeDisplay.setText(toDisplay); // sets display to string value of current timer time (to 1 second)
				
	}
	
	@Override
	public void start() { // starts timer
		
		if(!started) {
			startTime = System.currentTimeMillis();
			stopTime = startTime;
			started = true;
		}
		else {
			startTime += System.currentTimeMillis() - stopTime;
		}
		
		super.start();
		
	}
	
	void lap() { // records current timer time (use LL?) might not need LL if I'm not going to do anything with it, unsure
		
		String recorded = timeDisplay.getText();
		HBox entry = new HBox();
		Label lapsTime = new Label();
		TextField lapsField = new TextField("Details");
		
		entry.setSpacing(10);
		
		lapsField.setOnMouseClicked(e -> lapsField.selectAll());
		lapsField.setPrefWidth(250);
				
		lapsTime.setText(recorded);
		
		entry.getChildren().addAll(lapsTime, lapsField);
		lapsDisplay.getChildren().add(entry);
		
	}
	
	@Override
	public void stop() { // stops timer
		
		stopTime = System.currentTimeMillis();
		super.stop();
		
	}
	
	void reset() { // resets timer
		
		stop();
		timeDisplay.setText("0:00");
		lapsDisplay.getChildren().clear();
		started = false;
		
	}

}
