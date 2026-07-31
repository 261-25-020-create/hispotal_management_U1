package com.hospital.model;

public class Doctor extends Person {
    private String specialization;
    private double salary;

    public Doctor(String doctorId, String firstName, String lastName, 
                  String specialization, String contactNumber, double salary) {
        super(doctorId, firstName, lastName, contactNumber);
        this.specialization = specialization;
        this.salary = salary;
    }

    @Override
    public void showDetails() {
        System.out.printf("Doctor ID: %s | Dr. %s %s | Specialization: %s | Contact: %s%n",
                getId(), getFirstName(), getLastName(), specialization, getContactNumber());
    }

    // Getters
    public String getSpecialization() { return specialization; }
    public double getSalary() { return salary; }
}
