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

    public void addPet(String nama, String jenis, int umur, String pemilik){
        Pet pet = new Pet(petIDCounter++, nama, jenis, umur, pemilik);
        pets.add(pet);
    }

    public List<Pet> getAllPets(){
        return pets;
    }

    public void addHealthRecord(int petID, HealthRecord record){
        records.add(record);
    }

    public List<HealthRecord> getAllRecords(){
        return records;
    }
}
