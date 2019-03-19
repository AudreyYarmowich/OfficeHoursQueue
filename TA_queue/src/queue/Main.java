package queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	static Image flavicon;
	static protected StartUpScene start_up_scene;
	static protected MainScene main_scene;
	protected static Stage stage;
	protected static Main main;
	
    static HashMap<Integer, Student> student_map; 
    static List<String> students_in_queue;
    static List<String> tas_on_duty;
	
	//Declare all other elements we will be using
	private static String class_name;
	static File roster;
	static int current_student = 0; //This will be the Student ID of the student/TA who last swiped their buzzcard twice to enter and edit mode
	static ApplicationMode mode = ApplicationMode.START_UP; //Starts in Startup mode to get window size, class name, and roster info
	static int number_of_students_detected;
	static int number_of_tas_detected;
	
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
	
	public static void main(String[] args) {
		students_in_queue = new ArrayList<String>();
		tas_on_duty = new ArrayList<String>();
		System.out.println("Start");
		launch(args);
	}

	@Override
	public void start(Stage new_stage) throws Exception {
		main = this;
		Main.stage = new_stage;
		File f = new File("TA_queue\\src\\queue\\images\\flavicon.png");
		flavicon = new Image(f.toURI().toString());
		Main.stage.getIcons().add(flavicon);
		start_up_scene = new StartUpScene(Main.stage);
		Main.stage.setScene(start_up_scene.getStartUpScene());
		Main.stage.setTitle("TA Queue System");
		Main.stage.show();
	}
	
	public static void nextScene(boolean ta) {
		if (mode == ApplicationMode.START_UP) {
			mode = ApplicationMode.DISPLAY;
			main_scene = new MainScene();
			stage.setScene(main_scene.getMainScene());
			stage.setTitle(class_name + " Queue System");
			stage.setFullScreen(true);
			stage.show();
		} else if ( mode == ApplicationMode.DISPLAY && ta ) {
			mode = ApplicationMode.TA;
		} else if ( mode == ApplicationMode.DISPLAY && !ta ) {
			mode = ApplicationMode.STUDENT;
		} else if ( mode == ApplicationMode.STUDENT || mode == ApplicationMode.TA) {
			mode = ApplicationMode.DISPLAY;
		}
	}
	
	static File getRoster() {return roster;}
	
	static String getClassName() {return class_name;}
	
	static void setClassName(String name) {class_name = name.replaceFirst("ex. ", "");}
	
	static boolean setRoster(File file) {
		if (file == null ) {
			roster = null;
		} else if (!file.getName().endsWith(".csv")) {
			roster = null;
		} else {
			try {
				Scanner scanner = new Scanner(file);
				student_map = new HashMap<Integer, Student>(Constants.default_class_size);
				roster = file;
				int counter = 0;
				while (scanner.hasNext() || counter < 5) {
					counter++;
		            List<String> line = parseLine(scanner.nextLine());
		            try {
		            	if (line.get(5).equals("Student")) {
		            		student_map.put(Integer.valueOf(line.get(2)), new Student(Integer.valueOf(line.get(2)),line.get(0).replaceAll("(?<= ).*( =?)", ""), line.get(1), line.get(4), line.get(6)));
		            		number_of_students_detected++;
		            	}
		            	else if (line.get(5).equals("Ta")) {
		            		student_map.put(Integer.valueOf(line.get(2)), new TA(Integer.valueOf(line.get(2)),line.get(0).replaceAll("(?<= ).*( =?)", ""), line.get(1), line.get(4), line.get(6)));
		            		number_of_tas_detected++;
		            	}
		            } catch (Exception e) {
		            	
		            }
		        }
		        scanner.close();
				return true;
			} catch (FileNotFoundException e) {
				roster = null;
				return false;
			}
		}
		return false;
	}
	
	  public static List<String> parseLine(String cvsLine) {
	        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
	    }

	    public static List<String> parseLine(String cvsLine, char separators) {
	        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
	    }

	    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

	        List<String> result = new ArrayList<>();

	        //if empty, return!
	        if (cvsLine == null && cvsLine.isEmpty()) {
	            return result;
	        }

	        if (customQuote == ' ') {
	            customQuote = DEFAULT_QUOTE;
	        }

	        if (separators == ' ') {
	            separators = DEFAULT_SEPARATOR;
	        }

	        StringBuffer curVal = new StringBuffer();
	        boolean inQuotes = false;
	        boolean startCollectChar = false;
	        boolean doubleQuotesInColumn = false;

	        char[] chars = cvsLine.toCharArray();

	        for (char ch : chars) {

	            if (inQuotes) {
	                startCollectChar = true;
	                if (ch == customQuote) {
	                    inQuotes = false;
	                    doubleQuotesInColumn = false;
	                } else {

	                    //Fixed : allow "" in custom quote enclosed
	                    if (ch == '\"') {
	                        if (!doubleQuotesInColumn) {
	                            curVal.append(ch);
	                            doubleQuotesInColumn = true;
	                        }
	                    } else {
	                        curVal.append(ch);
	                    }

	                }
	            } else {
	                if (ch == customQuote) {

	                    inQuotes = true;

	                    //Fixed : allow "" in empty quote enclosed
	                    if (chars[0] != '"' && customQuote == '\"') {
	                        curVal.append('"');
	                    }

	                    //double quotes in column will hit this!
	                    if (startCollectChar) {
	                        curVal.append('"');
	                    }

	                } else if (ch == separators) {

	                    result.add(curVal.toString());

	                    curVal = new StringBuffer();
	                    startCollectChar = false;

	                } else if (ch == '\r') {
	                    //ignore LF characters
	                    continue;
	                } else if (ch == '\n') {
	                    //the end, break!
	                    break;
	                } else {
	                    curVal.append(ch);
	                }
	            }

	        }

	        result.add(curVal.toString());

	        return result;
	    }

}
