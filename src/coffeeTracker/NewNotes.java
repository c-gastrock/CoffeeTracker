package coffeeTracker;

import javafx.scene.control.*;
import javafx.scene.layout.*;

/*
 * TextField to write down notes. Maybe separate into general notes and potential improvements
 * TODO: Make hitting enter make a bullet-point?
 */

public class NewNotes {

	TextArea genNotes; // general notes
	TextArea impNotes; // improvement notes
	VBox container;
	
	NewNotes(){
		
		container = new VBox();
		genNotes = new TextArea("General notes");
		impNotes = new TextArea("Potential improvements");
		
		genNotes.setOnMouseClicked(e -> genNotes.selectAll()); // highlight box on click
		impNotes.setOnMouseClicked(e -> impNotes.selectAll());
		
		genNotes.setWrapText(true); // formatting
		impNotes.setWrapText(true);
				
		genNotes.setMinHeight(145);
		genNotes.setPrefHeight(145);
		genNotes.setMaxHeight(145);
		genNotes.setMinWidth(300);
		genNotes.setPrefWidth(300);
		genNotes.setMaxWidth(300);
		
		impNotes.setMinHeight(145);
		impNotes.setPrefHeight(145);
		impNotes.setMaxHeight(145);
		impNotes.setMinWidth(300);
		impNotes.setPrefWidth(300);
		impNotes.setMaxWidth(300);
		
		container.setSpacing(CoffeeTracker.GAP/10 + 1);
		container.getChildren().addAll(genNotes, impNotes);
		
	}
	
	VBox getNewNotes() { // used to move container onto scene
		
		return container;
		
	}

	String getGen() {
		
		return genNotes.getText();
		
	}
	
	String getImp() {
		
		return impNotes.getText();
		
	}
	
}
