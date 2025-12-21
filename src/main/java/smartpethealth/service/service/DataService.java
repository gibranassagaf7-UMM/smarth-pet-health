package smartpethealth.service.service;

import smartpethealth.service.model.Pet;
import smartpethealth.service.model.HealthRecord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class DataService {

    private List<Pet> pets = new ArrayList<>();
    private List<HealthRecord> records = new ArrayList<>();

    private int petIDCounter = 1;
    private int recordIDCounter = 1;

    private static final String PETS_FILE = "pets.json";
    private static final String RECORDS_FILE = "health_records.json";
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public DataService() {
        loadPets();
        loadRecords();
        if (!pets.isEmpty()) {
            petIDCounter = pets.stream().mapToInt(Pet::getId).max().orElse(0) + 1;
        }
        if (!records.isEmpty()) {
            recordIDCounter = records.stream().mapToInt(HealthRecord::getId).max().orElse(0) + 1;
        }
    }

    public void addPet(String nama, String jenis, String umur, String pemilik) {
        Pet pet = new Pet(petIDCounter++, nama, jenis, umur, pemilik);
        pets.add(pet);
        savePets();
    }

    public List<Pet> getAllPets() {
        return new ArrayList<>(pets);
    }

    public void addHealthRecord(int petID, java.time.LocalDate tanggal,
                                double berat,
                                String kondisi,
                                String catatan) {
        HealthRecord record = new HealthRecord(
                recordIDCounter++,
                petID,
                tanggal,
                berat,
                kondisi,
                catatan
        );
        records.add(record);
        saveRecords();
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

    public void updateHealthRecord(int id,
                                   java.time.LocalDate tanggal,
                                   double berat,
                                   String kondisi,
                                   String catatan) {
        HealthRecord r = getRecordById(id);
        if (r == null) {
            throw new IllegalArgumentException("Record tidak ditemukan");
        }

        if (tanggal != null) r.setTanggal(tanggal);
        if (berat > 0) r.setBerat(berat);
        if (kondisi != null && !kondisi.isEmpty()) r.setKondisi(kondisi);
        r.setCatatan(catatan);
        saveRecords();
    }

    public void deleteHealthRecord(int id) {
        records.removeIf(r -> r.getId() == id);
        saveRecords();
    }

    public void updatePet(int id, String nama, String jenis, String umur, String pemilik) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                p.setNama(nama);
                p.setJenis(jenis);
                p.setUmur(umur);
                p.setPemilik(pemilik);
                savePets();
                break;
            }
        }
    }

    public void deletePet(int id) {
        pets.removeIf(p -> p.getId() == id);
        savePets();
    }

    public Pet getPetByID(int id) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private void savePets() {
        try (FileWriter writer = new FileWriter(PETS_FILE)) {
            gson.toJson(pets, writer);
        } catch (IOException e) {
            System.err.println("Error saving pets: " + e.getMessage());
        }
    }

    private void saveRecords() {
        try (FileWriter writer = new FileWriter(RECORDS_FILE)) {
            gson.toJson(records, writer);
        } catch (IOException e) {
            System.err.println("Error saving records: " + e.getMessage());
        }
    }

    private void loadPets() {
        try (FileReader reader = new FileReader(PETS_FILE)) {
            Type listType = new TypeToken<List<Pet>>(){}.getType();
            List<Pet> loadedPets = gson.fromJson(reader, listType);
            if (loadedPets != null) {
                pets = loadedPets;
            }
        } catch (IOException e) {
            System.out.println("Pets file not found, starting fresh.");
        }
    }


    private void loadRecords() {
        try (FileReader reader = new FileReader(RECORDS_FILE)) {
            Type listType = new TypeToken<List<HealthRecord>>(){}.getType();
            List<HealthRecord> loadedRecords = gson.fromJson(reader, listType);
            if (loadedRecords != null) {
                records = loadedRecords;
            }
        } catch (IOException e) {
            System.out.println("Records file not found, starting fresh.");
        }
    }

    public void exportData(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            DataExport export = new DataExport(pets, records);
            gson.toJson(export, writer);
        } catch (IOException e) {
            System.err.println("Error exporting data: " + e.getMessage());
        }
    }

    private static class DataExport {
        List<Pet> pets;
        List<HealthRecord> records;

        DataExport(List<Pet> pets, List<HealthRecord> records) {
            this.pets = pets;
            this.records = records;
        }
    }

    private static class LocalDateAdapter implements com.google.gson.JsonSerializer<LocalDate>, com.google.gson.JsonDeserializer<LocalDate> {
        @Override
        public com.google.gson.JsonElement serialize(LocalDate src, Type typeOfSrc, com.google.gson.JsonSerializationContext context) {
            return new com.google.gson.JsonPrimitive(src.toString());
        }

        @Override
        public LocalDate deserialize(com.google.gson.JsonElement json, Type typeOfT, com.google.gson.JsonDeserializationContext context) {
            return LocalDate.parse(json.getAsString());
        }
    }
}