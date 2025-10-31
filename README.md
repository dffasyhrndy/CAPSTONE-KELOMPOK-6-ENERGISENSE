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
