# Smart Pet Health

**Smart Pet Health** adalah aplikasi desktop berbasis **Java Swing** untuk manajemen kesehatan hewan peliharaan. Aplikasi ini memungkinkan pengguna untuk menambah, mengedit, menghapus, dan melihat riwayat kesehatan pet, dengan data disimpan dalam format **JSON**.

---

## Fitur

- **Dashboard**: Tampilan utama dengan statistik total pet, tips kesehatan, dan navigasi ke fitur lain.  
- **Manajemen Pet**: Tambah, edit, hapus, dan lihat daftar hewan peliharaan.  
- **Riwayat Kesehatan**: Tambah, edit, hapus, dan lihat record kesehatan per pet (tanggal, berat, kondisi, catatan).  
- **Pencarian**: Cari pet berdasarkan nama atau jenis, dengan navigasi langsung ke riwayat kesehatan.  
- **Laporan**: Generate laporan kesehatan semua pet.  
- **Pengaturan**: Backup data ke file JSON.    

---

## Instalasi

### Prasyarat

- Java JDK 11 atau lebih tinggi  
- Maven 3.6+ (untuk build dan test)  
- IntelliJ IDEA atau IDE Java lainnya (opsional, tapi direkomendasikan)  

### Langkah Instalasi

1. Clone repository ini:
```bash
git clone https://github.com/yourusername/smart-pet-health.git
cd smart-pet-health
```

Penggunaan

1. Jalankan aplikasi, akan muncul Dashboard.

2. Klik tombol untuk navigasi:
   - Daftar Hewan: Lihat dan kelola pet.
   - Tambah Hewan: Tambah pet baru (validasi: nama, jenis, umur angka, pemilik wajib).
   - Riwayat Kesehatan: Lihat record kesehatan pet (klik pet di daftar untuk akses).
   - Pencarian: Cari pet, klik hasil untuk lihat riwayat.
   - Laporan: Generate laporan teks.
   - Pengaturan: Backup data.

Data otomatis disimpan di file pets.json dan health_records.json di direktori project.

src/
├── main/java/smartpethealth/
│   ├── Main.java                 # Entry point aplikasi
│   ├── model/
│   │   ├── Pet.java              # Model untuk Pet
│   │   └── HealthRecord.java     # Model untuk HealthRecord
│   ├── service/
│   │   └── DataService.java      # Service untuk CRUD dan file handling
│   └── ui/
│       ├── MainFrame.java        # Frame utama dengan CardLayout
│       ├── DashboardPanel.java   # Panel Dashboard
│       ├── PetListPanel.java     # Panel daftar pet
│       ├── AddPetPanel.java      # Panel tambah pet
│       ├── EditPetPanel.java     # Panel edit pet
│       ├── HealthRecordListPanel.java # Panel riwayat kesehatan
│       ├── AddHealthRecordPanel.java  # Panel tambah record
│       ├── EditHealthRecordPanel.java # Panel edit record
│       ├── SearchPanel.java      # Panel pencarian
│       ├── ReportsPanel.java     # Panel laporan
│       ├── SettingsPanel.java    # Panel pengaturan
│       ├── HelpPanel.java        # Panel bantuan
│       └── SummaryPanel.java     # Panel ringkasan
└── test/java/smartpethealth/service/
    └── DataServiceTest.java      # Unit tests untuk CRUD

pom.xml                           # Maven configuration
README.md                         # Dokumentasi ini

Teknologi yang Digunakan
- Java Swing: Untuk UI desktop
- Maven: Build dan dependency management
- JSON: Penyimpanan data pets dan health records
