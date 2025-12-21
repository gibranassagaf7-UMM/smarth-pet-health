# Smart Pet Health

**Smart Pet Health** adalah aplikasi desktop sederhana untuk mengelola data kesehatan hewan peliharaan (pets). Aplikasi ini dibangun menggunakan **Java Swing** dan memungkinkan pengguna untuk menambah, melihat, mengedit, dan menghapus data hewan serta riwayat kesehatan mereka.

---

## Fitur Utama

- **Dashboard**
  - Halaman utama dengan statistik sederhana (total hewan) dan navigasi cepat.
- **Manajemen Hewan (Pets)**
  - Tambah hewan baru (nama, jenis, umur).
  - Lihat daftar hewan dalam bentuk tabel.
  - Edit data hewan.
  - Hapus hewan (dengan konfirmasi).
- **Riwayat Kesehatan (Health Records)**
  - Tambah record kesehatan (tanggal, berat, kondisi, catatan).
  - Lihat riwayat kesehatan per hewan dalam tabel (sortable, read-only).
  - Edit record kesehatan.
  - Hapus record kesehatan (dengan konfirmasi).
- **UI Modern**
  - Desain dengan tema pastel purple, font Segoe UI, dan elemen interaktif seperti hover effect pada button.
- **Validasi Input**
  - Cek field kosong, format tanggal (DD MM YYYY), dan angka untuk berat.
- **Auto-Refresh**
  - Halaman otomatis refresh setelah operasi CRUD.

---

## Persyaratan Sistem

- **Java**: JDK 8 atau lebih tinggi (disarankan JDK 11+ untuk performa terbaik).  
- **IDE**: Eclipse, IntelliJ IDEA, atau NetBeans (opsional, direkomendasikan untuk development).  
- **OS**: Windows, macOS, atau Linux (cross-platform).  

---

## Instalasi

### Clone Repository
```bash
git clone https://github.com/yourusername/smart-pet-health.git
cd smart-pet-health
