package queue;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartUpScene {	
	//Declare all labels, Textfields, and other UI Elements we will be using
		Label title_label;	
		Label class_name_label;
		Label select_roster_label;
		Label select_roster_hint_label;
		Label num_student_detected_label;
		Label num_ta_detected_label;
		TextField class_name;
		Button select_roster_button;
		Button confirm_set_up_button;
		FileChooser roster_chooser;
		
		Scene start_up_scene;
		
	StartUpScene(Stage stage) {
		//Set Title
		title_label = new Label(Constants.title_label_text);
		title_label.getStyleClass().add("title");
		title_label.setWrapText(true);
		title_label.setTextAlignment(TextAlignment.CENTER);
		
		//Set Class Name
		HBox enter_class_name = new HBox(10);
		enter_class_name.setAlignment(Pos.CENTER);
		class_name_label = new Label(Constants.class_name_label_text);
		class_name = new TextField("ex. CS 2110");
		class_name.setMaxWidth(100);
		class_name.getStyleClass().add("textfield");
		class_name_label.getStyleClass().add("text");
		enter_class_name.getChildren().addAll(class_name_label, class_name);
		
		//Set Roster
		select_roster_label = new Label(Constants.select_roster_label_text);
		select_roster_label.getStyleClass().add("text");
		roster_chooser = new FileChooser();
		roster_chooser.setTitle("Open Resource File");
		select_roster_button = new Button(Constants.select_roster_button_text);
		select_roster_button.getStyleClass().add("button");
		select_roster_button.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Main.setRoster(roster_chooser.showOpenDialog(stage));
				if (Main.getRoster() != null) {
					select_roster_button.setText(Main.getRoster().getName());
					num_student_detected_label.setText(Main.number_of_students_detected + " Students Detected");
					num_ta_detected_label.setText(Main.number_of_tas_detected + " TAs Detected");
				} else {
					select_roster_button.setText("Must Be Valid .csv File");
				}
			}
		});
		HBox roster_select = new HBox(10);
		roster_select.setAlignment(Pos.CENTER);
		roster_select.getChildren().addAll(select_roster_label, select_roster_button);
		select_roster_hint_label = new Label(Constants.select_roster_hint_label_text);
		select_roster_hint_label.getStyleClass().add("text");
		HBox detected_students = new HBox(10);
		detected_students.setAlignment(Pos.CENTER);
		num_student_detected_label = new Label();
		num_student_detected_label.getStyleClass().add("boldtext");
		num_ta_detected_label = new Label();
		num_ta_detected_label.getStyleClass().add("boldtext");
		detected_students.getChildren().addAll(num_student_detected_label, num_ta_detected_label);
		VBox roster_select_root = new VBox(3);
		roster_select_root.setAlignment(Pos.CENTER);

		
		//Confirm Button
		confirm_set_up_button = new Button(Constants.confirm_set_up_button_text);
		confirm_set_up_button.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if ( Main.getRoster() == null ) { throw new InvalidFileException(); }
					if ( Main.number_of_students_detected == 0 ) {throw new Exception("Roster has no students or is not in the correct format");}
					Main.setClassName( class_name.getText() );
					Main.nextScene(false);
				}
				catch ( Exception e ){
					System.out.println(e.getMessage());
					Toast.makeText(stage, e.getMessage(), 3500, 500, 500);
				}
			}
		});
		roster_select_root.getChildren().addAll(roster_select, select_roster_hint_label,detected_students);
		VBox root = new VBox(50);
		root.getChildren().addAll(title_label, enter_class_name, roster_select_root, confirm_set_up_button);
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill( Constants.background_color, new CornerRadii(.5), new Insets(0) )));
		Scene scene = new Scene(root, Constants.starting_application_width, Constants.starting_application_height);
		File f = new File("StartUpStylesheet.css");
		scene.getStylesheets().clear();
		scene.getStylesheets().add("file:/" + f.getAbsolutePath().replace("\\", "/"));
		this.start_up_scene = scene;
	}
	
	Scene getStartUpScene() {return this.start_up_scene;}

}
