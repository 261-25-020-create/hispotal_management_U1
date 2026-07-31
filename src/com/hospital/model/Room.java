package com.hospital.model;

public class Room {
    private String roomId;
    private int roomNumber;
    private String type;
    private String status;
    private double rent;

    public Room(String roomId, int roomNumber, String type, String status, double rent) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.status = status;
        this.rent = rent;
    }

    // Getters
    public String getRoomId() { return roomId; }
    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public double getRent() { return rent; }
}
