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
