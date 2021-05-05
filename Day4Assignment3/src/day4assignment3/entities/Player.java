package day4assignment3.entities;

public class Player implements IEntity {
	private String nationlityId;
	private String firstName;
	private String lastName;
	private String yearOfBirth;
	public Player(String nationlityId, String firstName, String lastName, String yearOfBirth) {
		this.nationlityId = nationlityId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
	}
	public String getNationlityId() {
		return nationlityId;
	}
	public void setNationlityId(String nationlityId) {
		this.nationlityId = nationlityId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getFullName() {
		return firstName + " " + lastName;
	}
}
