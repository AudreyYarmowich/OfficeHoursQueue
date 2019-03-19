package queue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class TaElement extends VBox {
	Label name_label;
	ImageView picture;
	
	
	TaElement(String name){
		super(10);
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
						inputstream = new FileInputStream("TA_queue\\src\\queue\\images\\missing_ta_picture.png");
					} catch (FileNotFoundException oops) {
						System.out.println("well this is embarising");
					}
				}
			}
		} 
		
		if (inputstream != null) {
			picture = new ImageView(new Image(inputstream)); 
			picture.setFitWidth(140);
			picture.setFitHeight(140);
			this.getChildren().add(picture);
		}
		name_label = new Label(name);
		name_label.getStyleClass().add("title");
		name_label.setMinWidth(190);
		name_label.setMaxWidth(190);
		name_label.setAlignment(Pos.CENTER);
		name_label.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().add( name_label );
		this.setBackground(new Background(new BackgroundFill( Constants.element_color, new CornerRadii(.5), new Insets(0) )));
		this.setBorder(new Border(new BorderStroke(Constants.element_outline_color, BorderStrokeStyle.SOLID, new CornerRadii(.5), new BorderWidths(1) )));
		this.setAlignment(Pos.CENTER);
		this.setMinHeight(190);
		this.setMaxHeight(190);
		this.setMaxWidth(190);
		this.setMinWidth(190);
	}
}
