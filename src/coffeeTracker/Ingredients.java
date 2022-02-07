package coffeeTracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/*
 * List coffee/water/(ice) g's
 * List generic other ingredients
 * Calculate important ratios
 * EVENTUALLY show related lap data i.e bloom time
 * 
 * Potentially change additional ingredient system to change "Ingredient #1" fields into Text once user enters the name for visual effect.
 * Maybe can click again to change back to text field?
 */

public class Ingredients {
	
	VBox ingredients;
	VBox addIng;
	
	Ingredients() {
		
		ingredients = new VBox();
		addIng = new VBox();
		
		ingredients.setSpacing(CoffeeTracker.GAP/5);
		
		ingredients.setMinHeight(300); // Fixing dimensions
		ingredients.setPrefHeight(300);
		ingredients.setMaxHeight(300);
		ingredients.setMinWidth(300);
		ingredients.setPrefWidth(300);
		ingredients.setMaxWidth(300);
		
		addIng.setSpacing(CoffeeTracker.GAP/5);
		
		addIng.setMinHeight(300); // Fixing dimensions
		addIng.setPrefHeight(300);
		addIng.setMaxHeight(300);
		addIng.setMinWidth(300);
		addIng.setPrefWidth(300);
		addIng.setMaxWidth(300);
		
		TextField coffeeIn = new TextField(); // Input fields 
		TextField waterIn = new TextField();
		TextField iceIn = new TextField();
		
		coffeeIn.setMaxSize(80, 30); // alignments for input boxes, lines up with combo box
		coffeeIn.setTranslateX(115);
		waterIn.setMaxSize(80, 30);
		waterIn.setTranslateX(115);
		iceIn.setMaxSize(80, 30);
		iceIn.setTranslateX(115);
		
		Label coffeeLabel = new Label("Coffee (g)\t"); // Labels for input fields, tab helps keep everything in line
		Label waterLabel = new Label("Water (g)\t");
		Label iceLabel = new Label("Ice (g)\t");	
				
		HBox coffee = new HBox(); // Containers for input and option fields
		HBox water = new HBox();
		HBox ice = new HBox();
		HBox options = new HBox();
		
		options.setSpacing(CoffeeTracker.GAP/5);
				
		CheckBox isIced = new CheckBox("Iced"); // Node for options area
		
		ComboBox<String> other = new ComboBox<String>(); // Node for options area		
		other.getItems().addAll("0", "1", "2", "3");
		other.setPromptText("# of ingredients");
		other.setOnAction(new EventHandler<ActionEvent>() { // Changes number of additional ingredients
			public void handle(ActionEvent event) {
				
				int numIng = Integer.parseInt(other.getValue());
				if(addIng.getChildren().size() != numIng) { // if selection is different
					addIng.getChildren().clear();
					for(int i = 1; i <= numIng; i++) {
						
						HBox temp = new HBox();
						TextField tempLabel = new TextField("Ingredient #" + i);
						TextField tempIn = new TextField();
						
						temp.setSpacing(CoffeeTracker.GAP/10);
						
						tempLabel.setMaxSize(140, 30);
						tempIn.setMaxSize(80, 30);
						tempIn.setTranslateX(52);
						
						tempLabel.setOnMouseClicked(e -> tempLabel.selectAll());	 // text field highlights when you click it					
						tempIn.setOnMouseClicked(e -> tempIn.selectAll());
						
						temp.getChildren().addAll(tempLabel, tempIn);
						addIng.getChildren().add(temp);
						
					}
				}
				
			}
		});
		
		coffee.getChildren().addAll(coffeeLabel, coffeeIn); // adding all nodes to HBoxes
		water.getChildren().addAll(waterLabel, waterIn);
		ice.getChildren().addAll(iceLabel, iceIn);
		options.getChildren().addAll(isIced, other);
		
		isIced.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(isIced.isSelected()) {
					ingredients.getChildren().add(3, ice); // puts ice specifically under options, coffee, and water (in that order)
				}
				else {
					ingredients.getChildren().remove(ice);
				}
			}
		});
		
		ingredients.getChildren().addAll(options, coffee, water, addIng); // adds all HBox nodes to final VBox to return
				
	}
	
	VBox getIngredients() { // used to put Ingredients container onto scene
		
		return ingredients;
		
	}
	
	// maybe include some get methods in future to transfer data over to storage

}
