package smartpethealth.service;

import smartpethealth.model.HealthRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HealthRecordService {

    private final List<HealthRecord> records = new ArrayList<>();
    private int nextId = 1;

    public void addRecord(int petId,
                          LocalDate tanggal,
                          double berat,
                          String kondisi,
                          String catatan) {

        if (tanggal == null) {
            throw new IllegalArgumentException("Tanggal tidak boleh kosong");
        }
        if (berat <= 0) {
            throw new IllegalArgumentException("Berat harus lebih dari 0");
        }
        if (kondisi == null || kondisi.isEmpty()) {
            throw new IllegalArgumentException("Kondisi tidak boleh kosong");
        }

        HealthRecord record = new HealthRecord(
                nextId++,
                petId,
                tanggal,
                berat,
                kondisi,
                catatan
        );

        records.add(record);
    }

    public List<HealthRecord> getRecordsByPet(int petId) {
        List<HealthRecord> result = new ArrayList<>();

        for (HealthRecord r : records) {
            if (r.getPetId() == petId) {
                result.add(r);
            }
        }

        return result;
    }

    public HealthRecord getRecordById(int id) {
        for (HealthRecord r : records) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public void updateRecord(int id,
                             LocalDate tanggal,
                             double berat,
                             String kondisi,
                             String catatan) {

        HealthRecord record = getRecordById(id);
        if (record == null) {
            throw new IllegalArgumentException("Health record tidak ditemukan");
        }

        if (tanggal != null) {
            record.setTanggal(tanggal);
        }
        if (berat > 0) {
            record.setBerat(berat);
        }
        if (kondisi != null && !kondisi.isEmpty()) {
            record.setKondisi(kondisi);
        }

        record.setCatatan(catatan);
    }
}

