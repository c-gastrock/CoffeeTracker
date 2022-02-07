package coffeeTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/* Main features to do:
 * Timer [BASIC FUNCTIONALITY DONE, VERY INEFFICIENT]
 * Water/Ice/Coffee Ratio/grams [ENTRY DONE, NEEDS RETRIEVAL]
 * Coffee used
 * Notes/Issues [ENTRY DONE, NEEDS RETRIEVAL]
 * Store data somehow, maybe in spreadsheet (learn Google Sheets API?)
 * Learn basic CSS
 * 
 * Color hexcodes:
 * #566573
 * #2c3e50
 * #1c2833
 * #212f3d start screen background
 * #616a6b
 * #2e4053
 */

public class CoffeeTracker extends Application {
	
	final static double GAP = 50;
	File info;

	@Override
	public void start(Stage primary) {
                
        TabPane tabs = new TabPane(); // Makes non-closable tabs for coffee tools and a note-pad area
        GridPane toolGrid = new GridPane();
        Timer timer = new Timer();
        Ingredients ingredients = new Ingredients();
        NewNotes newNotes = new NewNotes();
        info = new File("src\\resources\\info.txt"); // creates text file to store and retrieve coffee data from use.
                      
        HBox bottomHalf = new HBox(); // container for ingredients and notes
        bottomHalf.setSpacing(GAP/5);
        bottomHalf.getChildren().addAll(ingredients.getIngredients(), newNotes.getNewNotes());
        
        toolGrid.setMinSize(200, 200);
        toolGrid.setAlignment(Pos.CENTER);
        
        toolGrid.add(timer.getTimer(), 0, 0, 2, 1);
        toolGrid.add(bottomHalf, 0, 1);
        
        toolGrid.setVgap(GAP);
        //toolGrid.setStyle("-fx-background-color: #212F3D");
        
        Tab tab1 = new Tab("Tool"); // Tab 1 will contain the main program
        tab1.setClosable(false);
        tab1.setContent(toolGrid);
        
        Tab tab2 = new Tab("Notes"); // Tab 2 will contain previous coffee notes from previous runs
        tab2.setClosable(false);
        
        tabs.getTabs().add(tab1);
        tabs.getTabs().add(tab2);
        
        VBox tabBox = new VBox(tabs);
        
        Scene mainScene = new Scene(tabBox,750,750);
        
        mainScene.getStylesheets().add("/resources/styles.css"); // adding style sheet to main tool. Had to clean and build (Project -> Clean)
        
        primary.setScene(mainScene);
        primary.centerOnScreen();
        primary.setTitle("CoffeeTracker");
        primary.setResizable(false);
        primary.show();

	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	

}
