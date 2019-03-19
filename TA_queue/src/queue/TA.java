package queue;

import java.io.File;

public class TA extends Student {
	File picture;
	
	TA(int gtid, String name, String email, String major, String section) {
		super(gtid,name,email,major,section);
	}
	
	public void setTAPicture(File picture) { this.picture = picture; }
	public File getTAPicture() { return this.picture; };
	
}
