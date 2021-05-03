package day4assignment2.entities;

public class Customer implements IEntity {
	private String id;
	private String firstName;
	private String lastName;
	private String yearOfBirth;
	private String nationalityId;

	public Customer(String id, String firstName, String lastName, String yearOfBirth, String nationalityId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.nationalityId = nationalityId;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getYearOfBirth() {
		return this.yearOfBirth;
	}

	public String getNationalityId() {
		return this.nationalityId;
	}

}
