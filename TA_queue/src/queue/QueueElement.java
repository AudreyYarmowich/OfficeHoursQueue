package queue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class QueueElement extends HBox {

	Label space_holder;
	Label name_label;
	String name;
	boolean is_in_edit_mode = false;
	
	
	QueueElement(String name){
		super(3);
		this.name = name;
		this.setAlignment(Pos.CENTER);
		//space_holder = new Label("");
		//space_holder.setMinWidth(50);
		name_label = new Label(name);
		name_label.getStyleClass().add("title");
		name_label.setMinWidth(365);
		name_label.setMaxWidth(365);
		name_label.setAlignment(Pos.CENTER);
		name_label.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().addAll(name_label);
		this.setBackground(
			new Background(
				new BackgroundFill( 
						Constants.element_color,
						new CornerRadii(.5),
						new Insets(0) 
				)
			)
		);
		this.setBorder(
			new Border(
				new BorderStroke(
					Constants.element_outline_color,
					BorderStrokeStyle.SOLID,
					new CornerRadii(.5),
					new BorderWidths(1) 
				)
			)
		);
		this.setMinWidth(500);
		this.setMinHeight(24);
		this.setMaxWidth(500);
	}
	
	void taEditMode() {
		if (!is_in_edit_mode) {
			try {
				is_in_edit_mode = true;
				double space_holder_width = 60;
				boolean top = false;
				Label space_holder = new Label();
				Button up_button = new Button();
				Button down_button = new Button();
				Button remove_button = new Button();
				up_button.setGraphic(new ImageView( new Image(new FileInputStream(Constants.up_icon))));
				down_button.setGraphic(new ImageView( new Image(new FileInputStream(Constants.down_icon))));
				remove_button.setGraphic(new ImageView( new Image(new FileInputStream(Constants.remove_icon))));
				up_button.setMinSize(19,19);
				down_button.setMinSize(19,19);
				remove_button.setMinSize(19,19);
				up_button.setMaxSize(19,19);
				down_button.setMaxSize(19,19);
				remove_button.setMaxSize(19,19);
				up_button.getStyleClass().add("removebutton");
				down_button.getStyleClass().add("removebutton");
				remove_button.getStyleClass().add("removebutton");
				remove_button.setOnAction( new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						VBox student_queue = ((VBox)((Node)event.getSource()).getParent().getParent());
						QueueElement curr = ((QueueElement)((Node)event.getSource()).getParent());
						Main.students_in_queue.remove(curr.name);
						student_queue.getChildren().remove(curr);
						System.out.println("remove student");
						update_queue(student_queue.getChildren());
					}
				});
				up_button.setOnAction( new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						VBox student_queue = ((VBox)((Node)event.getSource()).getParent().getParent());
						QueueElement curr = ((QueueElement)((Node)event.getSource()).getParent());
						int index = student_queue.getChildren().indexOf(curr);
						student_queue.getChildren().remove(curr);
						student_queue.getChildren().add(index - 1, curr);
						System.out.println("move student from " + index +" to " + (index -1));
						update_queue(student_queue.getChildren());
					}
				});
				down_button.setOnAction( new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						VBox student_queue = ((VBox)((Node)event.getSource()).getParent().getParent());
						QueueElement curr = ((QueueElement)((Node)event.getSource()).getParent());
						int index = student_queue.getChildren().indexOf(curr);
						student_queue.getChildren().remove(curr);
						student_queue.getChildren().add(index + 1, curr);
						System.out.println("move student from " + index +" to " + (index +1));
						update_queue(student_queue.getChildren());
					}
				});
				Label missing_button = new Label();
				missing_button.setMinWidth(19);
				if (((VBox)this.getParent()).getChildren().indexOf(this) != 0) {
					top = true;
					this.getChildren().add(up_button);
				} else {
					this.getChildren().add(missing_button);
				}
				if (((VBox)this.getParent()).getChildren().indexOf(this) != ((VBox)this.getParent()).getChildren().size() - 1) {
					this.getChildren().add(down_button);
				} else {
					this.getChildren().add(missing_button);
				}
				this.getChildren().add(remove_button);
				space_holder.setMinWidth(space_holder_width);
				this.getChildren().add(0, space_holder);
			} catch (FileNotFoundException e) {
				
			}
		}
	}
	
	void studentEditMode() {
		if (!is_in_edit_mode) {
			try {
				double space_holder_width = 19;
				is_in_edit_mode = true;
				Label space_holder = new Label();
				Button down_button = new Button();
				Button remove_button = new Button();
				down_button.setGraphic(new ImageView( new Image(new FileInputStream(Constants.down_icon))));
				remove_button.setGraphic(new ImageView( new Image(new FileInputStream(Constants.remove_icon))));
				down_button.setMinSize(19,19);
				remove_button.setMinSize(19,19);
				down_button.setMaxSize(19,19);
				remove_button.setMaxSize(19,19);
				down_button.getStyleClass().add("removebutton");
				remove_button.getStyleClass().add("removebutton");
				remove_button.setOnAction( new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						VBox student_queue = ((VBox)((Node)event.getSource()).getParent().getParent());
						QueueElement curr = ((QueueElement)((Node)event.getSource()).getParent());
						Main.students_in_queue.remove(curr.name);
						student_queue.getChildren().remove(curr);
						System.out.println("remove student");
						s_update_queue(student_queue.getChildren());
					}
				});
				down_button.setOnAction( new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						VBox student_queue = ((VBox)((Node)event.getSource()).getParent().getParent());
						QueueElement curr = ((QueueElement)((Node)event.getSource()).getParent());
						int index = student_queue.getChildren().indexOf(curr);
						student_queue.getChildren().remove(curr);
						student_queue.getChildren().add(index + 1, curr);
						System.out.println("move student from " + index +" to " + (index +1));
						s_update_queue(student_queue.getChildren());
					}
				});
				this.getChildren().add(0, space_holder);
				if (((VBox)this.getParent()).getChildren().indexOf(this) != ((VBox)this.getParent()).getChildren().size() - 1) {
					this.getChildren().add(down_button);
					space_holder_width = space_holder_width + down_button.getWidth() + 3;
				}
				this.getChildren().add(down_button);
				this.getChildren().add(remove_button);
			} catch (FileNotFoundException e) {
				
			}
		}
	}
	
	void endEditMode() {
		if (is_in_edit_mode) {
			this.getChildren().clear();
			this.getChildren().add(name_label);
			is_in_edit_mode = false;
		}
	}
	
	static void update_queue(ObservableList<Node> parent) {
		for( Node e: parent ) {
			((QueueElement)e).endEditMode();
		}
		for( Node e: parent ) {
			((QueueElement)e).taEditMode();;
		}
	}
	static void s_update_queue(ObservableList<Node> parent) {
		for( Node e: parent ) {
			((QueueElement)e).endEditMode();
		}
		for( Node e: parent ) {
			((QueueElement)e).studentEditMode();;
		}
	}
}
