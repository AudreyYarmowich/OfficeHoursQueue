package queue;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;

public class MainScene {

	Scene main_scene;
	Label title;
	Label tas_on_duty_label;
	Label join_queue_structure_label;
	TextField join_queue_structure_textfield;
	Label main_queue_label;
	String raw_buzzcard_input = "";
	HBox title_structure;
	HBox tas_on_duty_structure;
	VBox student_queue_structure;
	Button exit_button;
	
	MainScene(){
		VBox root = new VBox();
		Scene scene = new Scene(root, Constants.application_width, Constants.application_height);
		this.main_scene = scene;
		
		title_structure = new HBox(20);
		title_structure.setAlignment(Pos.CENTER);
		title = new Label(Main.getClassName() + Constants.main_title_text);
		title.setTextAlignment(TextAlignment.CENTER);
		title.getStyleClass().add("bigtitle");	
		title.setMinHeight(this.main_scene.getHeight() * Constants.main_scene_title_size);
		title.setMaxHeight(this.main_scene.getHeight() * Constants.main_scene_title_size);
		title_structure.getChildren().add(title);
		
		tas_on_duty_structure = new HBox(20);
		tas_on_duty_structure.setMinHeight(this.main_scene.getHeight() * Constants.tas_on_duty_structure_size);
		tas_on_duty_structure.setMaxHeight(this.main_scene.getHeight() * Constants.tas_on_duty_structure_size);
		tas_on_duty_structure.setAlignment(Pos.CENTER);
		tas_on_duty_label = new Label(Constants.tas_on_duty_label_text);
		tas_on_duty_label.getStyleClass().add("title");
		tas_on_duty_structure.getChildren().add(tas_on_duty_label);
		
		
		VBox join_queue_structure = new VBox(20);
		join_queue_structure.setMinHeight(this.main_scene.getHeight() * Constants.join_queue_structure_size);
		join_queue_structure.setMaxHeight(this.main_scene.getHeight() * Constants.join_queue_structure_size);
		join_queue_structure.setAlignment(Pos.CENTER);
		join_queue_structure_label = new Label(Constants.join_queue_structure_label_text);
		join_queue_structure_label.getStyleClass().add("title");
		join_queue_structure_textfield = new TextField();
		join_queue_structure_textfield.getStyleClass().add("maintextfield");
		join_queue_structure_textfield.setMaxWidth(300);
		join_queue_structure_textfield.textProperty().addListener((observable, oldvalue, newvalue)->{
			if (newvalue.matches(".*[\\d?;=,].*")) {
				raw_buzzcard_input = raw_buzzcard_input+newvalue;
				join_queue_structure_textfield.setText("");
			}
		});
		
		
		join_queue_structure.getChildren().addAll(join_queue_structure_label, join_queue_structure_textfield);
		
		
		HBox main_queue_structure = new HBox(20);
		main_queue_structure.setMinHeight(this.main_scene.getHeight() * Constants.main_queue_structure_size);
		main_queue_structure.setMaxHeight(this.main_scene.getHeight() * Constants.main_queue_structure_size);
		main_queue_structure.setAlignment(Pos.CENTER);
		main_queue_label = new Label(Constants.main_queue_label_text);
		main_queue_label.getStyleClass().add("title");
		VBox student_queue_label_structure = new VBox(20);
		student_queue_label_structure.setMinWidth(150);
		student_queue_label_structure.setMaxWidth(150);
		Label space_holder = new Label();
		space_holder.setMinHeight(20);
		Label space_holder2 = new Label();
		space_holder2.setMinWidth(150);
		
		student_queue_label_structure.getChildren().addAll(space_holder, main_queue_label);
		student_queue_structure = new VBox(20);
		student_queue_structure.setMaxWidth(400);
		main_queue_structure.getChildren().addAll(student_queue_label_structure, student_queue_structure,space_holder2);
		
		root.getChildren().addAll(title_structure, lineUnder(title_structure), tas_on_duty_structure, lineUnder(tas_on_duty_structure),
				join_queue_structure, lineUnder(join_queue_structure), space_holder, main_queue_structure);
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill( Constants.background_color, new CornerRadii(.5), new Insets(0) )));
		File f = new File("TA_queue\\src\\queue\\stylesheets\\StartUpStylesheet.css");
		scene.getStylesheets().clear();
		scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode()==KeyCode.ENTER) {
				if (join_queue_structure_textfield.getText().equals("")) {
					Main.current_student = Integer.valueOf(raw_buzzcard_input.substring(6, 15));
					if (Main.student_map.get(Main.current_student) instanceof TA) {
						if (Main.tas_on_duty.contains(Main.student_map.get(Main.current_student).getName())) {
							if (Main.mode == ApplicationMode.DISPLAY) {
								for(Node t: tas_on_duty_structure.getChildren()) {
									if (t instanceof TaElement) {
										((TaElement) t).editMode();
									}
								}
								for(Node t: student_queue_structure.getChildren()) {
									if (t instanceof QueueElement) {
										((QueueElement) t).taEditMode();
									}
								}
								Main.nextScene(true);
							} else if (Main.mode == ApplicationMode.TA) {
								exit_button.fire();
							}
							this.editMode();
						} else {
							Main.tas_on_duty.add(Main.student_map.get(Main.current_student).getName());
							TaElement t = new TaElement(Main.student_map.get(Main.current_student).getName());
							tas_on_duty_structure.getChildren().add(t);
							if (Main.mode == ApplicationMode.TA) {
								t.editMode();
							}
						}
					} else {
						if (Main.students_in_queue.contains(Main.student_map.get(Main.current_student).getName())) {
							for(Node t: student_queue_structure.getChildren()) {
								if (t instanceof QueueElement && ((QueueElement)t).name.equals(Main.student_map.get(Main.current_student).getName())) {
									((QueueElement) t).studentEditMode();
								}
							}
							Main.nextScene(false);
							this.editMode();
						} else {
							student_queue_structure.getChildren().add(new QueueElement(Main.student_map.get(Main.current_student).getName()));
							if (Main.mode == ApplicationMode.TA) {
								QueueElement.update_queue(student_queue_structure.getChildren());
							} else if (Main.mode == ApplicationMode.STUDENT) {
								QueueElement.s_update_queue(student_queue_structure.getChildren());
							}
						}
					}
				} else {
					student_queue_structure.getChildren().add(new QueueElement(join_queue_structure_textfield.getText()));
					join_queue_structure_textfield.setText("");
				}
			}
		});
	}
	
	void editMode() {
		exit_button = new Button("Exit Edit Mode");
		Label space_holder = new Label();
		space_holder.setMinWidth(exit_button.getWidth());
		exit_button.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("debug" + Main.mode);
				if (Main.mode == ApplicationMode.TA) {
					for(Node t: tas_on_duty_structure.getChildren()) {
						if (t instanceof TaElement) {
							((TaElement) t).endEditMode();
						}
					}
					for(Node t: student_queue_structure.getChildren()) {
						if (t instanceof QueueElement) {
							((QueueElement) t).endEditMode();
						}
					}
					Main.nextScene(false);
				} else {
					for(Node t: student_queue_structure.getChildren()) {
						if (t instanceof QueueElement && ((QueueElement)t).name.equals(Main.student_map.get(Main.current_student).getName())) {
							((QueueElement) t).endEditMode();
						}
					}
					Main.nextScene(false);
				}
				System.out.println(((HBox)((Node)event.getSource()).getParent()).getChildren().remove(0));
				System.out.println(((HBox)((Node)event.getSource()).getParent()).getChildren().remove((Node)event.getSource()));	
				
			}
		});
		
		title_structure.getChildren().add(0,space_holder);
		title_structure.getChildren().add(exit_button);
	}
	
	Scene getMainScene() {return main_scene;}

	Line lineUnder (Node node) {
		return new Line(node.getLayoutX(), node.getLayoutY(), this.main_scene.getWidth(), node.getLayoutY());
	}
}
