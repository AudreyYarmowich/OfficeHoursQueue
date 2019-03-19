package queue;

public class Student {
	private int gtid;
	private String name;
	private String email;
	private String major;
	private String section;
	
	Student(int gtid, String name, String email, String major, String section) {
		this.setGtid(gtid);
		this.setName(name);
		this.setEmail(email);
		this.setMajor(major);
		this.setSection(section);
	}

	public int getGtid() {
		return gtid;
	}

	public void setGtid(int gtid) {
		this.gtid = gtid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	
}
