package smartpethealth.service.model;

import java.time.LocalDate;

public class HealthRecord {

    private int id;
    private int petId;
    private LocalDate tanggal;
    private double berat;
    private String kondisi;
    private String catatan;

    public HealthRecord(int id, int petId, LocalDate tanggal,
                        double berat, String kondisi, String catatan) {
        this.id = id;
        this.petId = petId;
        this.tanggal = tanggal;
        this.berat = berat;
        this.kondisi = kondisi;
        this.catatan = catatan;
    }

    public int getId() {
        return id;
    }

    public int getPetId() {
        return petId;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public double getBerat() {
        return berat;
    }

    public String getKondisi() {
        return kondisi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    @Override
    public String toString() {
        return "HealthRecord{" +
                "id=" + id +
                ", petId=" + petId +
                ", tanggal=" + tanggal +
                ", berat=" + berat +
                ", kondisi='" + kondisi + '\'' +
                ", catatan='" + catatan + '\'' +
                '}';
    }
}