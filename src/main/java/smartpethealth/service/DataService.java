package smartpethealth.service;

import smartpethealth.model.Pet;
import smartpethealth.model.HealthRecord;

import java.util.List;
import java.util.ArrayList;

public class DataService {

    private List<Pet> pets = new ArrayList<>();
    private List<HealthRecord> records = new ArrayList<>();

    private int petIDCounter = 1;
    private int recordIDCounter = 1;

    public void addPet(String nama, String jenis, String umur, String pemilik){
        Pet pet = new Pet(petIDCounter++, nama, jenis, umur, pemilik);
        pets.add(pet);
    }

    public List<Pet> getAllPets(){
        return pets;
    }

    public void addHealthRecord(int petID, java.time.LocalDate tanggal,
                                double berat,
                                String kondisi,
                                String catatan){


        if (tanggal == null) {
            throw new IllegalArgumentException("Tanggal wajib diisi");
        }
        if (berat <= 0) {
            throw new IllegalArgumentException("Berat harus > 0");
        }

        HealthRecord record = new HealthRecord(
                recordIDCounter++,
                petID,
                tanggal,
                berat,
                kondisi,
                catatan
        );
        records.add(record);
    }

    public List<HealthRecord> getAllRecords(){
        return records;
    }


    public List<HealthRecord> getRecordsByPet(int petId){
        List<HealthRecord> result = new ArrayList<>();
        for (HealthRecord r : records){
            if (r.getPetId() == petId){
                result.add(r);
            }
        }
        return result;
    }

    public HealthRecord getRecordById(int id){
        for (HealthRecord r : records){
            if (r.getId() == id){
                return r;
            }
        }
        return null;
    }

    public void updateHealthRecord(int id,
                                   java.time.LocalDate tanggal,
                                   double berat,
                                   String kondisi,
                                   String catatan){

        HealthRecord r = getRecordById(id);
        if (r == null){
            throw new IllegalArgumentException("Record tidak ditemukan");
        }

        if (tanggal != null) r.setTanggal(tanggal);
        if (berat > 0) r.setBerat(berat);
        if (kondisi != null && !kondisi.isEmpty()) r.setKondisi(kondisi);
        r.setCatatan(catatan);
    }

    public void deleteHealthRecord(int id){
        records.removeIf(r -> r.getId() == id);
    }

    public void updatePet(int id, String nama, String jenis, String umur, String pemilik){
        for (Pet p : pets){
            if (p.getId() == id){
                p.setNama(nama);
                p.setJenis(jenis);
                p.setUmur(umur);
                p.setPemilik(pemilik);
                break;
            }
        }
    }

    public void deletePet(int id){
        pets.removeIf(p -> p.getId() == id);
    }

    public Pet getPetByID(int id){
        for (Pet p : pets){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
}
