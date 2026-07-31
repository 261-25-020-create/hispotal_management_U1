package com.hospital.service;

import com.hospital.exception.RecordNotFoundException;

public interface HospitalOperations<T> {
    void addRecord(T record);
    T findById(String id) throws RecordNotFoundException;
    void displayAll();
}
