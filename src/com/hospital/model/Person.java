package com.hospital.model;

public abstract class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public Person(String id, String firstName, String lastName, String contactNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public abstract void showDetails();

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getContactNumber() { return contactNumber; }
}
