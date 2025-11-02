# EnergiSense – Sistem Manajemen Energi Terbarukan

## 1. Nama Program  
EnergiSense

---

## 2. Deskripsi Singkat Program  
EnergiSense adalah aplikasi desktop berbasis **Java Swing** yang digunakan untuk mengelola dan memantau data sumber energi terbarukan.  
Aplikasi ini membantu admin maupun pengguna biasa dalam mencatat, memperbarui, serta menilai efisiensi energi dari berbagai sumber seperti tenaga surya, angin, dan biomassa.  

Sistem ini dibangun menggunakan paradigma **Object Oriented Programming (OOP)** dengan konsep modularitas agar mudah dikembangkan dan dipelihara.  
Selain itu, aplikasi ini terhubung ke database **MySQL** menggunakan library `mysql-connector-j`.

---

## 3. Fitur Program  

1. **Login dan Registrasi Pengguna**  
   Form autentikasi untuk pengguna dan admin dengan validasi data melalui database.  

2. **Kelola Data Sumber Energi Terbarukan**  
   Fitur CRUD (Create, Read, Update, Delete) untuk mengelola data.  

3. **Laporan Efisiensi Energi**  
   Menampilkan data efisiensi energi berdasarkan hasil input dan pengelolaan data energi.  

4. **Manajemen Pengguna dan Admin**  
   Admin memiliki hak akses untuk menambah, mengubah, dan menghapus data pengguna.  

5. **Antarmuka GUI Interaktif**  
   Aplikasi menggunakan antarmuka grafis berbasis Java Swing dengan komponen kustom seperti `RoundedButton`.

---

## 4. Penerapan OOP  

| Pilar OOP | Penerapan di EnergiSense |
|------------|---------------------------|
| **Encapsulation** | Class seperti `Pengguna`, `Admin`, dan `Biasa` memiliki atribut `private` dengan `getter` dan `setter` untuk mengontrol akses data. |
| **Inheritance** | Class `Admin` dan `Biasa` mewarisi atribut dan method dari class induk `Pengguna`. |
| **Polymorphism** | Method seperti `login()` dan `tampilkanMenu()` diimplementasikan berbeda antara `Admin` dan `Biasa`. |
| **Abstraction** | Interface `IAuthenticable` mendefinisikan method abstrak `login()` yang diimplementasikan oleh class pengguna. |
| **Interface** | `IAuthenticable.java` digunakan sebagai kontrak fungsi login yang harus diimplementasikan oleh setiap class pengguna. |

---

## 5. Struktur Folder / Package
```java
EnergiSense/
│
├── src/
│ ├── Tampilan/
│ │ ├── FormAdmin.java
│ │ ├── FormBiasa.java
│ │ ├── FormDaftarPengguna.java
│ │ ├── FormLoginPengguna.java
│ │ ├── FormKelolaBiasa.java
│ │ ├── FormKelolaPerangkat.java
│ │ ├── FormKelolaSumberTerbarukan.java
│ │ ├── FormKelolaLaporanEfisiensi.java
│ │ ├── FormLaporanEfisiensiBiasa.java
│ │ ├── FormSumberTerbarukanBiasa.java
│ │ ├── FormPerangkatBiasa.java
│ │ ├── FrontEnd_EnergiSense.java
│ │ └── RoundedButton.java
│ │
│ ├── auth/
│ │ └── IAuthenticable.java
│ │
│ ├── model/
│ │ ├── Admin.java
│ │ ├── Biasa.java
│ │ └── Pengguna.java
│ │
│ ├── db/
│ │ └── Koneksi.java
│ │
│ └── com/mycompany/frontend_energisense/assets/
│ └── energisense_logo.png
│
├── Dependencies/
│ └── mysql-connector-j-9.4.0.jar
│
├── pom.xml
```
## 6. Library atau Framework

<img width="281" height="99" alt="image" src="https://github.com/user-attachments/assets/f31232df-9174-41f1-a9a3-fff3c93877e9" />

1. **Import java.awt.*;**
   berfungsi untuk mengimpor seluruh kelas dari paket java.awt (Abstract Window Toolkit).   Paket ini digunakan untuk membangun user interface dasar seperti tombol, label, warna, layout, dan elemen visual lainnya. Contoh komponennya meliputi: Button, Label, TextField, Panel, Color, Font, dll.
2.  **import javax.swing.*;**
 berfungsi mengimpor seluruh kelas dari paket javax.swing, yang merupakan framework GUI modern di Java. Swing digunakan untuk membuat tampilan program yang lebih interaktif dan fleksibel dibanding AWT. pada program Energisense menggunakan Swing untuk menampilkan Dashboard, Form Login, Data Perangkat, dan Laporan Efisiensi Energi.
3.  **import model.Admin;**
   berfungsi mengimpor kelas Admin dari paket model. Kelas ini merupakan bagian dari struktur OOP proyek Energisense yang menerapkan konsep inheritance dari kelas Pengguna.

<img width="428" height="165" alt="image" src="https://github.com/user-attachments/assets/048eb4c6-ea5b-4270-9c4b-a7d20ac9b63e" />

1. **import db.Koneksi;**
   berfungsi mengimpor kelas Koneksi dari package db.
2. **import java.sql.Connection;**
   berfungsi membuka koneksi ke database
3. **import java.sql.PreparedStatement;**
   digunakan untuk query dinamis, terutama untuk INSERT, UPDATE, DELETE agar lebih aman dari SQL Injection.
4. **import java.sql.Statement;**
   untuk menjalankan query statis seperti SELECT * FROM pengguna.
   
<img width="523" height="201" alt="image" src="https://github.com/user-attachments/assets/0cf4db07-b62f-4c9b-9273-4069419e2d3d" />

1. **import java.awt.event.*;**
   Untuk mengimpor kelas-kelas yang digunakan untuk menangani interaksi pengguna (event handling), seperti klik tombol atau input teks.
2. **import javax.swing.table.DefaultTableModel;**
   Mengimpor kelas DefaultTableModel yang digunakan untuk mengatur data dalam JTable. Kelas ini berperan penting saat menampilkan data dari database (misalnya data pengguna atau perangkat).
3. **import java.sql.*;**
   Mengimpor semua kelas dari paket java.sql agar program bisa menjalankan query SQL ke database.

<img width="366" height="208" alt="image" src="https://github.com/user-attachments/assets/d7ed31d0-ebf3-4f23-8868-ad5e18f9dc34" />

1. **import java.sql.ResultSet;**
   berfungsi untuk menyimpan hasil query SELECT dari database
2. **import model.Biasa;**
   yaitu kelas turunan dari Pengguna, dengan kemampuan tambahan seperti mengelola data pengguna dan memverifikasi laporan efisiensi.
3. **import model.Pengguna;**
   yaitu kelas turunan dari Pengguna yang mewakili pengguna umum dengan akses terbatas hanya untuk melihat data dan rekomendasi efisiensi.

<img width="307" height="74" alt="image" src="https://github.com/user-attachments/assets/ddeafd0a-1fa2-46f2-9619-4aeff4b82c00" />

1. **import java.sql.Connection;**
   Mengimpor kelas Connection, yang digunakan untuk membuat dan memelihara koneksi antara program Java dan database (misalnya MySQL). Objek Connection inilah yang menjadi jalan agar program bisa menjalankan query SQL seperti INSERT, SELECT, UPDATE, atau DELETE.
2. **import java.sql.DriverManager;**
   DriverManager berfungsi untuk mendaftarkan dan mengelola driver database. Dengan DriverManager, kamu bisa memanggil database driver seperti com.mysql.cj.jdbc.Driver dan membuat koneksi melalui URL JDBC.
3. **import java.sql.SQLException;**
   library ini digunakan untuk menangani kesalahan (error) yang terjadi saat proses koneksi atau eksekusi SQL.

<img width="226" height="30" alt="image" src="https://github.com/user-attachments/assets/e912d4f3-299e-419c-9357-1d546b00ee50" />

1. **import java.sql.Date;**
  digunakan untuk merepresentasikan data tanggal (tanpa jam dan menit) yang diambil dari atau dikirim ke database MySQL.

## 6. Cara Menggunakan Program

**1. Halaman Login**
   
   ![Gambar WhatsApp 2025-11-02 pukul 18 29 13_987a3a85](https://github.com/user-attachments/assets/5c439a52-5f64-41a9-8492-107636ecfcd5)

Halaman login terdiri dari email, password, dan tombol Masuk. Terdapat opsi "Belum punya akun?" untuk pengguna yg belum memiliki akun dan ingin melakukan registrasi, dan tombol Keluar.

**2. Menu Utama Admin**
   
   ![Gambar WhatsApp 2025-11-02 pukul 18 29 13_9e7f1538](https://github.com/user-attachments/assets/d277f79e-0087-44cb-a9f4-02cf44cd83b0)

Terdapat 4 pilihan Menu Admin: Kelola Daftar Pengguna Biasa, Kelola Daftar Perangkat, Kelola Daftar Sumber Terbarukan, dan Kelola Daftar Laporan.

**3. Dashboard Admin - Kelola Laporan Efisiensi**

![Gambar WhatsApp 2025-11-02 pukul 18 29 14_8639d013](https://github.com/user-attachments/assets/f163582a-20f1-4a92-bf5c-c961d031cc86)

Admin dapat melihat semua laporan efisiensi pengguna dengan data periode, total energi (kWh), skor efisiensi, dan saran hemat. Terdapat dialog untuk mengedit laporan dengan input periode dan total energi.

**4. Dashboard Admin - Tambah Pengguna Biasa**

   ![Gambar WhatsApp 2025-11-02 pukul 18 29 15_1f627f5c](https://github.com/user-attachments/assets/813045a5-d20c-4eb6-8296-9d0e8839cdc7)

Tabel daftar pengguna dengan dialog untuk menambah pengguna baru (nama, email, kata sandi, dan tipe pengguna).

**5. Dashboard Admin - Edit Pengguna Biasa**
   
![Gambar WhatsApp 2025-11-02 pukul 18 29 15_7f2d03fe](https://github.com/user-attachments/assets/c8edf91f-97e3-4e5d-b496-61d2d1882a33)

Tabel edit data pengguna yang sudah terdaftar dengan opsi mengubah nama, email, kata sandi, dan tipe pengguna.

**6. Dashboard Admin - Konfirmasi Hapus Pengguna**

  ![Gambar WhatsApp 2025-11-02 pukul 18 29 16_bef6a4c1](https://github.com/user-attachments/assets/e6d94b64-09a4-42f6-af75-3c27d5dede98)

Tabel konfirmasi sebelum menghapus pengguna dengan tombol Yes dan No untuk keamanan data.

**7. Dashboard Admin - Data Perangkat (View Only)**
   
   ![Gambar WhatsApp 2025-11-02 pukul 18 29 16_c64facd8](https://github.com/user-attachments/assets/3287a341-bda2-4241-995d-d691013e466f)

Admin dapat melihat semua perangkat pengguna dengan info ID, nama perangkat, daya (W), status, pemilik, email pemilik, dan admin pengawas.

**8. Dashboard Admin - Kelola Sumber Energi Terbarukan**
   
   ![Gambar WhatsApp 2025-11-02 pukul 18 29 16_0141690f](https://github.com/user-attachments/assets/20c75599-b97b-46b9-a8c8-1b723113b533)

Tabel lengkap data sumber energi terbarukan dengan informasi ID, jenis sumber, kapasitas, kontribusi, tanggal catat, pemilik, dan admin pengawasnya.

**9. Dashboard Admin - Tambah Sumber Energi Terbarukan**

    ![Gambar WhatsApp 2025-11-02 pukul 18 29 17_c7bd75f7](https://github.com/user-attachments/assets/8f695955-5fe1-4f95-a10c-1c7ae83a7a0f)

Tabel untuk menambah data sumber energi baru dengan input jenis sumber, kapasitas (kW), kontribusi (kWh), tanggal catat, dan ID pemilik.

**10. Dashboard Admin - Edit Sumber Energi Terbarukan**
    
    ![Gambar WhatsApp 2025-11-02 pukul 18 29 17_88b70fd6](https://github.com/user-attachments/assets/e2ddbe36-8d8d-44c2-8992-f1a29ab5c99f)

Tabel edit data sumber energi terbarukan yang sudah ada dengan opsi mengubah semua field data.

**11. Dashboard Admin - Kelola Laporan Efisiensi**
    
   ![Gambar WhatsApp 2025-11-02 pukul 18 29 17_9a08ecb8](https://github.com/user-attachments/assets/0883f72b-2dc9-4e37-8ec5-9174b41145d0)

Tampilan lengkap tabel laporan efisiensi dengan tombol Buat/Create, Refresh, Edit/Update, dan Hapus/Delete untuk mengelola data.

**12. Dashboard Admin - Tabel Tambah Laporan
**
    ![Gambar WhatsApp 2025-11-02 pukul 18 29 18_638e6604](https://github.com/user-attachments/assets/8d6ef9d9-4d1c-4fe3-bf3c-90776a5cb6fa)

Tabel input laporan efisiensi baru dengan field ID pengguna, periode (YYYY-MM), dan total energi (kWh).

**13. Dashboard Admin - Edit Laporan Efisiensi**

    ![Gambar WhatsApp 2025-11-02 pukul 18 29 18_0b25b185](https://github.com/user-attachments/assets/9a3ff8e2-5530-4f3d-8b7a-d8370d214a40)

Tabel edit laporan efisiensi yang sudah ada dengan input periode (2025-11) dan total energi (90.00 kWh) untuk memperbarui data laporan pengguna.

**14 Form Registrasi pengguna**

![Gambar WhatsApp 2025-11-02 pukul 18 29 18_ac1db04f](https://github.com/user-attachments/assets/1cc99181-ce3f-4952-844d-6f20970a2754)

Halaman pendaftaran akun baru dengan input nama, email, password, dan tipe pengguna (rumah/kantor/industri).

**15. Menu Utama Pengguna Biasa**

![Gambar WhatsApp 2025-11-02 pukul 18 29 19_ce1b0e4f](https://github.com/user-attachments/assets/2ded5e2f-182a-4b7f-982a-a523e007d2ee)

Menu navigasi dengan 4 pilihan: Laporan Efisiensi, Sumber Terbarukan, Perangkat, dan Kembali/Logout.

**16. Dashboard Pengguna - Laporan Efisiensi**

    ![Gambar WhatsApp 2025-11-02 pukul 18 29 19_b394eff3](https://github.com/user-attachments/assets/31101ae6-3767-41c9-8814-d65aa424ddb4)

Pengguna melihat laporan konsumsi energi pribadi dan dapat membuat laporan baru dengan dialog input periode dan total energi.

**17. Dashboard Pengguna - Sumber Terbarukan**

    ![Gambar WhatsApp 2025-11-02 pukul 18 29 20_65dc362b](https://github.com/user-attachments/assets/888a9182-f610-4548-85cc-5fa45858ac51)

Pengguna mengelola data sumber energi terbarukan pribadi dengan tabel input jenis sumber, kapasitas (kW), kontribusi (kWh), dan tanggal catat.

**18. Dashboard Pengguna - Tambah Perangkat**

    ![Gambar WhatsApp 2025-11-02 pukul 18 29 20_9b31fe31](https://github.com/user-attachments/assets/ca0b95f3-13a1-46b0-820c-49dfcefc368a)

Halaman manajemen perangkat dengan dialog untuk menambah perangkat baru (nama perangkat, daya W, dan status ON/OFF).

**19. Dashboard Pengguna - Edit Perangkat**

    ![Gambar WhatsApp 2025-11-02 pukul 18 29 20_fb664401](https://github.com/user-attachments/assets/a560e6de-f382-4b1b-bb2a-a32c91d3d65b)

Tabel edit perangkat elektronik yang sudah ada dengan opsi mengubah nama, daya, dan status perangkat.
