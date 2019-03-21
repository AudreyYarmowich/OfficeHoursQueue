package queue;

import javafx.scene.paint.Color;

final class Constants {
	static String title_label_text = "Welcome To The Georgia Tech Computer Science Office Hours Queueing System";
	static String dimension_label_text = "Please Enter Your Monitors Dimensions:";
	static String select_roster_label_text = "Please Select Your Class Roster .csv";
	static String class_name_label_text = "Please Enter Your Class Name";
	static String select_roster_hint_label_text = "This is the xlsx.xlsx file you downloaded from Canvas exported as a csv";
	static String select_roster_button_text = "Select Class Roster .csv";
	static String confirm_set_up_button_text = "Confirm";
	
	static String main_title_text = " Office Hours Queue";
	static String tas_on_duty_label_text = "TA’s On Duty:";
	static String join_queue_structure_label_text = "To Join The Queue Swipe Your Buzzcard or Type In Your Name Below:";
	static String main_queue_label_text = "Student Queue:";
	
	static String no_ta_picture_found = "TA_queue\\src\\queue\\images\\missing_ta_picture.png";
	static String remove_icon = "TA_queue\\src\\queue\\images\\remove_icon.png";
	static String up_icon = "TA_queue\\src\\queue\\images\\up.png";
	static String down_icon = "TA_queue\\src\\queue\\images\\down.png";
	
	static int starting_application_width = 500;
	static int starting_application_height = 500;
	static int application_width = 1920;
	static int application_height = 1080;
	static int min_application_width = 900;
	static int min_application_height = 900;
	static int max_application_width = 1921;
	static int max_application_height = 1081;
	static int default_class_size = 500;
	
	static double main_scene_title_size = .0741;
	static double tas_on_duty_structure_size = .1852;
	static double join_queue_structure_size = .1389;
	static double main_queue_structure_size = .6018;
	
	static Color background_color = new Color(.565, .643, .682, 1);
	static Color element_color = new Color(.69, .745, .773, 1);
	static Color element_outline_color = new Color(.149, .196, .22, 1);
	static Color text_color = new Color(.216, .278, .31, 1);
	/*
	 * static Color background_color = new Color(144, 164, 174, 1);
	 * static Color element_color = new Color(176, 190, 197, 1);
	 * static Color element_outline_color = new Color(38, 50, 56, 1);
	 * static Color text_color = new Color(55, 71, 79, 1);
	 */
}
