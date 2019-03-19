package queue;

import java.io.IOException;

public class InvalidFileException extends IOException {

	private static final long serialVersionUID = 325932937861848310L;

	InvalidFileException () {
		super("Roster File Is Not A Valid CSV");
	}
}
