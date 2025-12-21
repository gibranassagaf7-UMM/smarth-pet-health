Smart Pet Health
Smart Pet Health adalah aplikasi desktop sederhana untuk mengelola data kesehatan hewan peliharaan (pets). Aplikasi ini dibangun menggunakan Java Swing dan memungkinkan pengguna untuk menambah, melihat, mengedit, dan menghapus data hewan serta riwayat kesehatan mereka.

Fitur Utama
Dashboard: Halaman utama dengan statistik sederhana (total hewan) dan navigasi cepat.
Manajemen Hewan (Pets):
Tambah hewan baru (nama, jenis, umur).
Lihat daftar hewan dalam bentuk tabel.
Edit data hewan.
Hapus hewan (dengan konfirmasi).
Riwayat Kesehatan (Health Records):
Tambah record kesehatan (tanggal, berat, kondisi, catatan).
Lihat riwayat kesehatan per hewan dalam tabel (sortable, read-only).
Edit record kesehatan.
Hapus record kesehatan (dengan konfirmasi).
UI Modern: Desain dengan tema pastel purple, font Segoe UI, dan elemen interaktif seperti hover effect pada button.
Validasi Input: Cek field kosong, format tanggal (DD MM YYYY), dan angka untuk berat.
Auto-Refresh: Halaman otomatis refresh setelah operasi CRUD.
Persyaratan Sistem
Java: JDK 8 atau lebih tinggi (disarankan JDK 11+ untuk performa terbaik).
IDE: Eclipse, IntelliJ IDEA, atau NetBeans (opsional, tapi direkomendasikan untuk development).
OS: Windows, macOS, atau Linux (aplikasi cross-platform).
Instalasi
Clone Repository:


Copy code
git clone https://github.com/yourusername/smart-pet-health.git
cd smart-pet-health
Compile Kode:

Pastikan JDK terinstall dan JAVA_HOME sudah diset.
Compile semua file Java:

Copy code
javac -d bin -cp . src/smartpethealth/**/*.java
Jalankan Aplikasi:

Jalankan MainFrame sebagai entry point:

Copy code
java -cp bin smartpethealth.ui.MainFrame
Atau, jika menggunakan IDE:

Import proyek sebagai Java Project.
Jalankan MainFrame.java sebagai main class.
Cara Menjalankan
Jalankan aplikasi seperti di atas.
Di Dashboard, klik "Lihat Daftar Hewan" untuk melihat pets atau "Tambah Pet" untuk menambah baru.
Pilih hewan dari daftar untuk melihat riwayat kesehatan.
Gunakan button "Tambah", "Edit", atau "Delete" untuk mengelola data.
Navigasi kembali menggunakan button "Back".
Struktur Kode

Copy code
smart-pet-health/
├── src/
│   └── smartpethealth/
│       ├── model/
│       │   ├── HealthRecord.java  # Model untuk record kesehatan
│       │   └── Pet.java           # Model untuk hewan
│       ├── service/
│       │   └── DataService.java   # Service untuk CRUD data (in-memory)
│       └── ui/
│           ├── MainFrame.java     # Frame utama dengan CardLayout
│           ├── DashboardPanel.java # Panel dashboard
│           ├── PetListPanel.java  # Panel daftar hewan
│           ├── AddPetPanel.java   # Panel tambah hewan
│           ├── EditPetPanel.java  # Panel edit hewan
│           ├── HealthRecordListPanel.java # Panel riwayat kesehatan
│           ├── AddHealthRecordPanel.java  # Panel tambah record kesehatan
│           └── EditHealthRecordPanel.java # Panel edit record kesehatan
├── bin/                           # Folder output compile
├── README.md                      # File ini
└── LICENSE                        # Lisensi (jika ada)
Teknologi yang Digunakan
Java Swing: Untuk UI desktop.
AWT: Untuk layout dan event handling.
In-Memory Storage: Data disimpan di memori (tidak persistent; bisa diperluas ke database seperti SQLite).
Font: Segoe UI untuk konsistensi modern.
