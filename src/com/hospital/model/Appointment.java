package com.hospital.model;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String appointmentDate;
    private String type;
    private String status;

    public Appointment(String appointmentId, String patientId, String doctorId, 
                       String appointmentDate, String type, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.type = type;
        this.status = status;
    }

    // Getters
    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public String getAppointmentDate() { return appointmentDate; }
    public String getType() { return type; }
    public String getStatus() { return status; }
}
