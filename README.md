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

1. Halaman Login
   
   ![Gambar WhatsApp 2025-11-02 pukul 18 29 13_987a3a85](https://github.com/user-attachments/assets/5c439a52-5f64-41a9-8492-107636ecfcd5)

Halaman login terdiri dari email dan password, tombol Masuk. Terdapat opsi "Belum punya akun?" untuk pengguna yg belum memiliki akun dan ingin melakukan registrasi, dan tombol Keluar.   

