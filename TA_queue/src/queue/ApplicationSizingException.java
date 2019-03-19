package queue;

public class ApplicationSizingException extends Exception {

	private static final long serialVersionUID = -7842170333967047863L;

	ApplicationSizingException(int attempted_width, int attempted_hight){
		super("The Queuing System must be between 900x900 and 1920x1080, "+ attempted_width +"x" + attempted_hight + "is not valid" );
	}
}
