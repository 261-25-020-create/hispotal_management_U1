package com.hospital.model;

public class Patient extends Person {
    private String dateOfBirth;
    private String gender;
    private int roomNumber;
    private String doctorId;
    private String status;

    public Patient(String patientId, String firstName, String lastName, String dateOfBirth, 
                   String gender, String contactNumber, int roomNumber, String doctorId, String status) {
        super(patientId, firstName, lastName, contactNumber);
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.roomNumber = roomNumber;
        this.doctorId = doctorId;
        this.status = status;
    }

    @Override
    public void showDetails() {
        System.out.printf("Patient ID: %s | Name: %s %s | Room: %d | Doctor ID: %s | Status: %s%n",
                getId(), getFirstName(), getLastName(), roomNumber, doctorId, status);
    }

    // Getters
    public String getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public int getRoomNumber() { return roomNumber; }
    public String getDoctorId() { return doctorId; }
    public String getStatus() { return status; }
}
