package queue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class TaElement extends VBox {
	Label name_label;
	ImageView picture;
	HBox top_row;
	String name;
	
	TaElement(String name){
		super(10);
		this.name = name;
		FileInputStream inputstream = null;
		try {
			inputstream = new FileInputStream("TA_queue\\src\\queue\\images\\ta_pictures\\" + name +".png");
		} catch (FileNotFoundException a) {
			try {
				inputstream = new FileInputStream("TA_queue\\src\\queue\\images\\ta_pictures\\" + name +".jpg");
			} catch (FileNotFoundException b) {
				try {
					inputstream = new FileInputStream("TA_queue\\src\\queue\\images\\ta_pictures\\" + name +".jpeg");
				} catch (FileNotFoundException c) {
					try {
						inputstream = new FileInputStream(Constants.no_ta_picture_found);
					} catch (FileNotFoundException oops) {
						System.out.println("well this is embarrassing...no ta picture not found");
					}
				}
			}
		} 
		top_row = new HBox();
		top_row.setAlignment(Pos.CENTER);
		if (inputstream != null) {
			picture = new ImageView(new Image(inputstream)); 
			picture.setFitWidth((Constants.tas_on_duty_structure_size * Main.stage.getHeight()) - (.048* Main.stage.getHeight()));
			picture.setFitHeight((Constants.tas_on_duty_structure_size * Main.stage.getHeight()) - (.048* Main.stage.getHeight()));
			top_row.getChildren().add(picture);
		}
		name_label = new Label(name);
		name_label.getStyleClass().add("title");
		name_label.setMinWidth((Constants.tas_on_duty_structure_size * Main.stage.getHeight()) - 10);
		name_label.setMaxWidth((Constants.tas_on_duty_structure_size * Main.stage.getHeight()) - 10);
		name_label.setAlignment(Pos.CENTER);
		name_label.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().addAll( top_row, name_label );
		this.setBackground(new Background(new BackgroundFill( Constants.element_color, new CornerRadii(.5), new Insets(0) )));
		this.setBorder(new Border(new BorderStroke(Constants.element_outline_color, BorderStrokeStyle.SOLID, new CornerRadii(.5), new BorderWidths(1) )));
		this.setAlignment(Pos.CENTER);
		this.setMinHeight((Constants.tas_on_duty_structure_size * Main.stage.getHeight()) - 10);
		this.setMaxHeight((Constants.tas_on_duty_structure_size * Main.stage.getHeight()) - 10);
		this.setMaxWidth((Constants.tas_on_duty_structure_size * Main.stage.getHeight()) - 10);
		this.setMinWidth((Constants.tas_on_duty_structure_size * Main.stage.getHeight()) - 10);
	}
	
	void editMode() {
		Button remove_button = new Button();
		try {
			VBox right_side = new VBox();
			remove_button.setGraphic(new ImageView( new Image(new FileInputStream(Constants.remove_icon))));
			remove_button.setMinWidth(19);
			remove_button.setMinHeight(19);
			remove_button.getStyleClass().add("removebutton");
			remove_button.setOnAction( new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					HBox ta_queue = ((HBox)((Node)event.getSource()).getParent().getParent().getParent().getParent());
					TaElement curr = ((TaElement)((Node)event.getSource()).getParent().getParent().getParent());
					Main.tas_on_duty.remove(curr.name);
					ta_queue.getChildren().remove(curr);
					System.out.println("remove ta");
				}
			});
			Label place_holder = new Label("");
			place_holder.setMinSize(19, 40);
			right_side.getChildren().addAll(remove_button,place_holder);
			top_row.getChildren().add(0, place_holder);
			top_row.getChildren().add(right_side);	
		} catch (FileNotFoundException e) {
			
		}
	}
}
