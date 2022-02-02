package lab5;

public class Person {

	private String givenName;
	private String surname;
	private int phoneNumber;
	
	public Person(String name, String name2, int number) {
		givenName = name.trim();
		surname = name2.trim();
		phoneNumber = number;
	}
	
	public String getSurname() {

		return surname;
}
	
	public String getFullName() {
		String Fullname = givenName + " " + surname;
		return Fullname;
	}
	
	public int getPhoneNumber() {
		
		return phoneNumber; 
		
	}
	
}
