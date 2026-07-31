package com.hospital.service;

import com.hospital.model.*;
import com.hospital.exception.RecordNotFoundException;

import java.io.*;
import java.util.*;

public class HospitalDataManager implements HospitalOperations<Patient> {
    // Thread-safe Collections
    private final List<Patient> patientList = Collections.synchronizedList(new ArrayList<>());
    private final Map<String, Doctor> doctorMap = Collections.synchronizedMap(new HashMap<>());
    private final List<Appointment> appointmentList = Collections.synchronizedList(new ArrayList<>());
    private final List<Room> roomList = Collections.synchronizedList(new ArrayList<>());

    // Implement Interface Methods
    @Override
    public void addRecord(Patient patient) {
        patientList.add(patient);
    }

    @Override
    public Patient findById(String id) throws RecordNotFoundException {
        synchronized (patientList) {
            return patientList.stream()
                    .filter(p -> p.getId().equalsIgnoreCase(id))
                    .findFirst()
                    .orElseThrow(() -> new RecordNotFoundException("Patient with ID '" + id + "' not found."));
        }
    }

    @Override
    public void displayAll() {
        System.out.println("=== PATIENT LIST ===");
        synchronized (patientList) {
            for (Patient p : patientList) {
                p.showDetails(); // Polymorphic call from Person class
            }
        }
    }

    // --- Multithreading CSV Data Loader ---
    public void loadAllDataConcurrently(String patientCsv, String doctorCsv, String appointmentCsv, String roomCsv) {
        Thread patientThread = new Thread(() -> loadPatientsFromCSV(patientCsv));
        Thread doctorThread = new Thread(() -> loadDoctorsFromCSV(doctorCsv));
        Thread appointmentThread = new Thread(() -> loadAppointmentsFromCSV(appointmentCsv));
        Thread roomThread = new Thread(() -> loadRoomsFromCSV(roomCsv));

        // Start threads concurrently
        patientThread.start();
        doctorThread.start();
        appointmentThread.start();
        roomThread.start();

        try {
            // Wait for all threads to finish loading
            patientThread.join();
            doctorThread.join();
            appointmentThread.join();
            roomThread.join();
            System.out.println("All CSV datasets loaded concurrently into collections successfully.");
        } catch (InterruptedException e) {
            System.err.println("Thread processing was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void loadPatientsFromCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip CSV Header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 9) {
                    Patient p = new Patient(
                            tokens[0].trim(), tokens[1].trim(), tokens[2].trim(),
                            tokens[3].trim(), tokens[4].trim(), tokens[5].trim(),
                            Integer.parseInt(tokens[6].trim()), tokens[7].trim(), tokens[8].trim()
                    );
                    patientList.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Patient CSV: " + e.getMessage());
        }
    }

    private void loadDoctorsFromCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip CSV Header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 6) {
                    Doctor d = new Doctor(
                            tokens[0].trim(), tokens[1].trim(), tokens[2].trim(),
                            tokens[3].trim(), tokens[4].trim(), Double.parseDouble(tokens[5].trim())
                    );
                    doctorMap.put(d.getId(), d);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Doctor CSV: " + e.getMessage());
        }
    }

    private void loadAppointmentsFromCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip CSV Header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 6) {
                    Appointment app = new Appointment(
                            tokens[0].trim(), tokens[1].trim(), tokens[2].trim(),
                            tokens[3].trim(), tokens[4].trim(), tokens[5].trim()
                    );
                    appointmentList.add(app);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Appointment CSV: " + e.getMessage());
        }
    }

    private void loadRoomsFromCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip CSV Header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 5) {
                    Room r = new Room(
                            tokens[0].trim(), Integer.parseInt(tokens[1].trim()),
                            tokens[2].trim(), tokens[3].trim(), Double.parseDouble(tokens[4].trim())
                    );
                    roomList.add(r);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Room CSV: " + e.getMessage());
        }
    }

    // Additional Getters for Services
    public List<Patient> getPatientList() { return patientList; }
    public Map<String, Doctor> getDoctorMap() { return doctorMap; }
    public List<Appointment> getAppointmentList() { return appointmentList; }
    public List<Room> getRoomList() { return roomList; }
}
