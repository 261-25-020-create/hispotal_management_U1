package com.hospital.main;

import com.hospital.service.HospitalDataManager;
import com.hospital.model.Patient;
import com.hospital.exception.RecordNotFoundException;

public class Main {
    public static void main(String[] args) {
        // Initialize the manager service
        HospitalDataManager manager = new HospitalDataManager();

        System.out.println("=========================================");
        System.out.println("    HOSPITAL MANAGEMENT SYSTEM STARTUP   ");
        System.out.println("=========================================\n");

        // 1. Multithreading: Concurrently load all 4 CSV datasets
        System.out.println("[+] Loading CSV datasets using Multithreading...");
        manager.loadAllDataConcurrently(
                "1_Patient.csv", 
                "2_Doctor.csv", 
                "3_Appoinment.csv", 
                "4_Room.csv"
        );

        System.out.println("\n-----------------------------------------");

        // 2. Polymorphism: Display all patients loaded into memory
        System.out.println("[+] Displaying Patient Records (Polymorphic Method Call):");
        manager.displayAll();

        System.out.println("-----------------------------------------");

        // 3. Exception Handling: Searching for records with custom exception handling
        System.out.println("[+] Testing Exception Handling:");

        // Test Case A: Valid Search
        try {
            System.out.println("\nSearching for Patient ID 'P001'...");
            Patient p = manager.findById("P001");
            System.out.print("Found: ");
            p.showDetails();
        } catch (RecordNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Test Case B: Invalid Search (Triggers Exception)
        try {
            System.out.println("\nSearching for non-existent Patient ID 'P999'...");
            Patient p = manager.findById("P999");
            p.showDetails();
        } catch (RecordNotFoundException e) {
            System.err.println("Caught Expected Exception -> " + e.getMessage());
        }

        System.out.println("\n=========================================");
        System.out.println("    SYSTEM EXECUTION FINISHED    ");
        System.out.println("=========================================");
    }
}
