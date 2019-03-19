package queue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

public class QueueElement extends HBox {

	Label space_holder;
	Label name_label;
	String name;
	
	
	QueueElement(String name){
		super(10);
		this.name = name;
		space_holder = new Label("");
		space_holder.setMinWidth(50);
		name_label = new Label(name);
		name_label.getStyleClass().add("title");
		name_label.setMinWidth(300);
		name_label.setAlignment(Pos.CENTER);
		name_label.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().addAll(space_holder, name_label);
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
		this.setMinWidth(400);
		this.setMaxWidth(400);
	}
	
	void taEditMode() {
		
	}
	
	void studentEditMode() {
		
	}
	
	void endEditMode() {
		
	}
}
