-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 03, 2025 at 03:36 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistem_monitoring_energi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_pengguna` int(11) NOT NULL,
  `hak_akses` int(11) NOT NULL,
  `jabatan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_pengguna`, `hak_akses`, `jabatan`) VALUES
(1, 10, 'Manajer Sistem Energi'),
(2, 9, 'Supervisor Efisiensi'),
(3, 8, 'Teknisi Lapangan'),
(4, 7, 'Administrator Rumah Tangga'),
(5, 10, 'Manager Industri'),
(6, 8, 'Koordinator Teknis'),
(7, 7, 'Supervisor Listrik'),
(8, 9, 'Manajer Regional'),
(9, 8, 'Admin Kantor Energi'),
(10, 10, 'Chief Engineer');

-- --------------------------------------------------------

--
-- Table structure for table `biasa`
--

CREATE TABLE `biasa` (
  `id_pengguna` int(11) NOT NULL,
  `tanggal_daftar` date NOT NULL,
  `status_aktif` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `biasa`
--

INSERT INTO `biasa` (`id_pengguna`, `tanggal_daftar`, `status_aktif`) VALUES
(11, '2024-10-01', 1),
(12, '2024-10-02', 1),
(13, '2024-10-03', 1),
(14, '2024-10-04', 1),
(15, '2024-10-05', 1),
(16, '2024-10-06', 1),
(17, '2024-10-07', 1),
(18, '2024-10-08', 1),
(19, '2024-10-09', 1),
(20, '2024-10-10', 1),
(21, '2024-10-11', 1),
(22, '2024-10-12', 1),
(23, '2024-10-13', 1),
(24, '2024-10-14', 1),
(25, '2024-10-15', 1),
(26, '2024-10-16', 1),
(27, '2024-10-17', 1),
(28, '2024-10-18', 1),
(29, '2024-10-19', 1),
(30, '2024-10-20', 1),
(31, '2024-10-21', 1),
(32, '2024-10-22', 1),
(33, '2024-10-23', 1),
(34, '2024-10-24', 1),
(35, '2024-10-25', 1),
(36, '2024-10-26', 1),
(37, '2024-10-27', 1),
(38, '2024-10-28', 1),
(39, '2024-10-29', 1),
(40, '2024-10-30', 1),
(41, '2024-10-31', 1),
(42, '2024-11-01', 1),
(43, '2024-11-02', 1),
(44, '2024-11-03', 1),
(45, '2024-11-04', 1),
(46, '2024-11-05', 1),
(47, '2024-11-06', 1),
(48, '2024-11-07', 1),
(49, '2024-11-08', 1),
(50, '2024-11-09', 1),
(53, '2025-11-01', 1),
(54, '2025-11-01', 1),
(55, '2025-11-02', 1);

-- --------------------------------------------------------

--
-- Table structure for table `laporan_efisiensi`
--

CREATE TABLE `laporan_efisiensi` (
  `id_laporan` int(11) NOT NULL,
  `id_pengguna` int(11) NOT NULL,
  `periode` varchar(20) NOT NULL,
  `total_energi` decimal(10,2) DEFAULT NULL,
  `skor_efisiensi` decimal(5,2) DEFAULT NULL,
  `saran_hemat` varchar(255) DEFAULT NULL,
  `admin_id_pengguna` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laporan_efisiensi`
--

INSERT INTO `laporan_efisiensi` (`id_laporan`, `id_pengguna`, `periode`, `total_energi`, `skor_efisiensi`, `saran_hemat`, `admin_id_pengguna`) VALUES
(1, 11, '2024-10', 45.20, 89.10, 'Kurangi penggunaan AC saat siang hari.', 1),
(2, 12, '2024-10', 33.80, 82.40, 'Gunakan lampu hemat energi.', 2),
(3, 13, '2024-10', 27.60, 88.30, 'Matikan perangkat saat tidak digunakan.', 3),
(4, 14, '2024-10', 50.30, 75.50, 'Batasi penggunaan alat masak listrik.', 4),
(5, 15, '2024-10', 42.10, 91.20, 'Cek beban perangkat besar secara berkala.', 5),
(6, 16, '2024-10', 31.40, 84.10, 'Gunakan peralatan berlabel efisien.', 6),
(7, 17, '2024-10', 43.40, 79.60, 'Hindari konsumsi daya tinggi malam hari.', 7),
(8, 18, '2024-10', 39.20, 77.90, 'Manfaatkan energi surya secara maksimal.', 8),
(9, 19, '2024-10', 29.90, 93.00, 'Matikan lampu saat tidak diperlukan.', 9),
(10, 20, '2024-10', 48.10, 85.20, 'Gunakan timer otomatis untuk AC.', 10),
(11, 21, '2024-10', 36.70, 88.60, 'Gunakan peralatan berdaya rendah.', 1),
(12, 22, '2024-10', 40.50, 74.30, 'Kurangi penggunaan listrik di jam sibuk.', 2),
(13, 23, '2024-10', 33.20, 86.00, 'Atur suhu pendingin ruangan efisien.', 3),
(14, 24, '2024-10', 37.60, 90.10, 'Gunakan sensor cahaya otomatis.', 4),
(15, 25, '2024-10', 32.00, 79.90, 'Matikan komputer saat tidak digunakan.', 5),
(16, 26, '2024-10', 41.10, 82.50, 'Cek konsumsi energi peralatan rumah.', 6),
(17, 27, '2024-10', 46.80, 87.20, 'Lakukan pemeliharaan peralatan rutin.', 7),
(18, 28, '2024-10', 38.30, 80.00, 'Gunakan listrik saat tarif rendah.', 8),
(19, 29, '2024-10', 34.50, 90.70, 'Ganti lampu ke LED hemat energi.', 9),
(20, 30, '2024-10', 30.70, 89.40, 'Gunakan mode hemat daya di TV.', 10),
(21, 31, '2024-10', 43.50, 84.30, 'Matikan perangkat saat keluar rumah.', 1),
(22, 32, '2024-10', 35.60, 77.60, 'Gunakan perangkat efisiensi tinggi.', 2),
(23, 33, '2024-10', 44.10, 80.20, 'Kurangi beban daya puncak sore hari.', 3),
(24, 34, '2024-10', 48.90, 71.40, 'Gunakan timer pencahayaan otomatis.', 4),
(25, 35, '2024-10', 39.70, 86.10, 'Kurangi beban alat industri siang hari.', 5),
(26, 36, '2024-10', 50.00, 88.90, 'Manfaatkan sumber energi alternatif.', 6),
(27, 37, '2024-10', 29.20, 90.00, 'Gunakan peralatan listrik efisien.', 7),
(28, 38, '2024-10', 34.00, 85.50, 'Batasi pemakaian alat berat.', 8),
(29, 39, '2024-10', 41.50, 92.10, 'Gunakan daya sesuai kebutuhan.', 9),
(30, 40, '2024-10', 37.10, 78.80, 'Periksa sambungan kabel secara rutin.', 10),
(31, 41, '2024-10', 45.20, 80.90, 'Gunakan alat pengukur efisiensi.', 1),
(32, 42, '2024-10', 32.80, 86.70, 'Matikan pendingin ruangan saat kosong.', 2),
(33, 43, '2024-10', 40.40, 89.50, 'Kurangi durasi pemakaian elektronik.', 3),
(34, 44, '2024-10', 36.50, 82.40, 'Gunakan alat hemat energi.', 4),
(35, 45, '2024-10', 47.30, 84.70, 'Matikan lampu luar saat pagi.', 5),
(36, 46, '2024-10', 42.20, 87.10, 'Gunakan peralatan berlabel hemat energi.', 6),
(37, 47, '2024-10', 39.00, 83.20, 'Optimalkan distribusi daya.', 7),
(38, 48, '2024-10', 31.60, 91.80, 'Gunakan pencatat daya otomatis.', 8),
(39, 49, '2024-10', 35.00, 79.30, 'Hematkan energi malam hari.', 9),
(40, 50, '2024-10', 33.40, 85.50, 'Gunakan saklar otomatis.', 10),
(42, 11, '2025-10', 60.00, 70.00, 'Coba kurangi penggunaan alat elektronik saat tidak dibutuhkan.', 5),
(43, 11, '2025-11', 75.00, 62.50, 'Gunakan peralatan hemat energi dan matikan perangkat idle.', NULL),
(44, 12, '2025-10', 65.00, 67.50, 'Gunakan peralatan hemat energi dan matikan perangkat idle.', 1),
(45, 11, '2025-11', 80.00, 60.00, 'Gunakan peralatan hemat energi dan matikan perangkat idle.', NULL),
(46, 11, '2025-11', 90.00, 55.00, 'Gunakan peralatan hemat energi dan matikan perangkat idle.', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int(11) NOT NULL,
  `nama_pengguna` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `kata_sandi` varchar(100) NOT NULL,
  `tipe_pengguna` varchar(20) DEFAULT NULL CHECK (`tipe_pengguna` in ('rumah','kantor','industri'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_pengguna`, `email`, `kata_sandi`, `tipe_pengguna`) VALUES
(1, 'Rizky Aditya', 'rizky.aditya@email.com', 'energi123', 'industri'),
(2, 'Putri Ayu', 'putri.ayu@email.com', 'solar987', 'kantor'),
(3, 'Budi Santoso', 'budi.santoso@email.com', 'eco789', 'rumah'),
(4, 'Anisa Lestari', 'anisa.lestari@email.com', 'green2024', 'rumah'),
(5, 'Fajar Hidayat', 'fajar.hidayat@email.com', 'power555', 'industri'),
(6, 'Yuliana Rahma', 'yuliana.rahma@email.com', 'bright007', 'kantor'),
(7, 'Andi Setiawan', 'andi.setiawan@email.com', 'light2023', 'rumah'),
(8, 'Siti Marlina', 'siti.marlina@email.com', 'save88', 'kantor'),
(9, 'Galang Pratama', 'galang.pratama@email.com', 'eco2025', 'industri'),
(10, 'Nadia Sari', 'nadia.sari@email.com', 'power246', 'kantor'),
(11, 'Rudi Saputra', 'rudi.saputra@email.com', 'eco234', 'rumah'),
(12, 'Desi Amalia', 'desi.amalia@email.com', 'energy999', 'kantor'),
(13, 'Yoga Firmansyah', 'yoga.firmansyah@email.com', 'sun456', 'rumah'),
(14, 'Lina Agustina', 'lina.agustina@email.com', 'green369', 'rumah'),
(15, 'Taufik Rahman', 'taufik.rahman@email.com', 'bright654', 'kantor'),
(16, 'Eka Prasetyo', 'eka.prasetyo@email.com', 'save001', 'rumah'),
(17, 'Citra Dewi', 'citra.dewi@email.com', 'clean432', 'kantor'),
(18, 'Rian Kurniawan', 'rian.kurniawan@email.com', 'eco753', 'industri'),
(19, 'Mega Safitri', 'mega.safitri@email.com', 'energy147', 'rumah'),
(20, 'Wahyu Nugroho', 'wahyu.nugroho@email.com', 'solar369', 'kantor'),
(21, 'Riska Amelia', 'riska.amelia@email.com', 'light963', 'rumah'),
(22, 'Hendra Gunawan', 'hendra.gunawan@email.com', 'power852', 'industri'),
(23, 'Naufal Hakim', 'naufal.hakim@email.com', 'eco951', 'kantor'),
(24, 'Ayu Kartika', 'ayu.kartika@email.com', 'green147', 'rumah'),
(25, 'Bayu Pradana', 'bayu.pradana@email.com', 'bright159', 'kantor'),
(26, 'Lukman Hakim', 'lukman.hakim@email.com', 'save258', 'rumah'),
(27, 'Nurul Aini', 'nurul.aini@email.com', 'energy789', 'industri'),
(28, 'Edo Wijaya', 'edo.wijaya@email.com', 'clean357', 'kantor'),
(29, 'Rahmawati', 'rahmawati@email.com', 'power753', 'rumah'),
(30, 'Ali Mustofa', 'ali.mustofa@email.com', 'eco951', 'rumah'),
(31, 'Maya Cahyani', 'maya.cahyani@email.com', 'bright123', 'rumah'),
(32, 'Reza Alfian', 'reza.alfian@email.com', 'green777', 'kantor'),
(33, 'Dewi Kurniasih', 'dewi.kurniasih@email.com', 'save369', 'rumah'),
(34, 'Fani Anggraini', 'fani.anggraini@email.com', 'energy555', 'kantor'),
(35, 'Rio Saputra', 'rio.saputra@email.com', 'eco159', 'industri'),
(36, 'Ratna Wulandari', 'ratna.wulandari@email.com', 'light654', 'rumah'),
(37, 'Yusuf Ramadhan', 'yusuf.ramadhan@email.com', 'clean753', 'kantor'),
(38, 'Dina Oktaviani', 'dina.oktaviani@email.com', 'power852', 'rumah'),
(39, 'Ahmad Ridwan', 'ahmad.ridwan@email.com', 'solar951', 'rumah'),
(40, 'Silvia Nur', 'silvia.nur@email.com', 'energy456', 'rumah'),
(41, 'Zainal Arifin', 'zainal.arifin@email.com', 'bright159', 'rumah'),
(42, 'Nina Puspita', 'nina.puspita@email.com', 'eco258', 'kantor'),
(43, 'Dwi Putra', 'dwi.putra@email.com', 'green357', 'rumah'),
(44, 'Tiara Anggun', 'tiara.anggun@email.com', 'save951', 'kantor'),
(45, 'Yogi Prakoso', 'yogi.prakoso@email.com', 'clean147', 'rumah'),
(46, 'Farah Indah', 'farah.indah@email.com', 'power321', 'rumah'),
(47, 'Agung Firmansyah', 'agung.firmansyah@email.com', 'light654', 'kantor'),
(48, 'Winda Safira', 'winda.safira@email.com', 'eco753', 'rumah'),
(49, 'Iqbal Hidayat', 'iqbal.hidayat@email.com', 'energy951', 'rumah'),
(50, 'Nur Azizah', 'nur.azizah@email.com', 'bright258', 'kantor'),
(53, 'Rezky Ramadhan', 'rezky.betulan04@email.com', '12345678', 'industri'),
(54, 'Daffa husein', 'daffa.biasa@email.com', '12345678', 'rumah'),
(55, 'sayid rafi', 'sayid.rafibiasa@email.com', '123456', 'rumah');

-- --------------------------------------------------------

--
-- Table structure for table `penggunaan_energi`
--

CREATE TABLE `penggunaan_energi` (
  `id_penggunaan` int(11) NOT NULL,
  `id_perangkat` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `jam` int(11) DEFAULT NULL CHECK (`jam` between 0 and 23),
  `energi_kwh` decimal(10,2) NOT NULL,
  `biaya` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penggunaan_energi`
--

INSERT INTO `penggunaan_energi` (`id_penggunaan`, `id_perangkat`, `tanggal`, `jam`, `energi_kwh`, `biaya`) VALUES
(1, 1, '2024-10-01', 8, 2.50, 3750.00),
(2, 2, '2024-10-01', 10, 0.80, 1200.00),
(3, 3, '2024-10-01', 12, 0.20, 300.00),
(4, 4, '2024-10-02', 13, 0.70, 1050.00),
(5, 5, '2024-10-02', 9, 1.10, 1650.00),
(6, 6, '2024-10-03', 10, 0.50, 750.00),
(7, 7, '2024-10-03', 14, 0.90, 1350.00),
(8, 8, '2024-10-03', 11, 1.20, 1800.00),
(9, 9, '2024-10-04', 12, 0.40, 600.00),
(10, 10, '2024-10-04', 13, 0.10, 150.00),
(11, 11, '2024-10-05', 8, 0.60, 900.00),
(12, 12, '2024-10-05', 9, 3.00, 4500.00),
(13, 13, '2024-10-05', 10, 0.40, 600.00),
(14, 14, '2024-10-06', 11, 0.80, 1200.00),
(15, 15, '2024-10-06', 8, 0.30, 450.00),
(16, 16, '2024-10-06', 12, 0.70, 1050.00),
(17, 17, '2024-10-07', 10, 0.20, 300.00),
(18, 18, '2024-10-07', 13, 2.10, 3150.00),
(19, 19, '2024-10-07', 15, 0.10, 150.00),
(20, 20, '2024-10-08', 9, 0.30, 450.00),
(21, 21, '2024-10-08', 10, 1.20, 1800.00),
(22, 22, '2024-10-08', 11, 0.40, 600.00),
(23, 23, '2024-10-09', 12, 0.50, 750.00),
(24, 24, '2024-10-09', 13, 0.80, 1200.00),
(25, 25, '2024-10-09', 14, 2.20, 3300.00),
(26, 26, '2024-10-10', 8, 0.60, 900.00),
(27, 27, '2024-10-10', 9, 0.90, 1350.00),
(28, 28, '2024-10-11', 10, 1.50, 2250.00),
(29, 29, '2024-10-11', 11, 0.40, 600.00),
(30, 30, '2024-10-12', 12, 0.30, 450.00),
(31, 31, '2024-10-12', 8, 0.90, 1350.00),
(32, 32, '2024-10-12', 9, 2.00, 3000.00),
(33, 33, '2024-10-13', 10, 0.30, 450.00),
(34, 34, '2024-10-13', 11, 0.50, 750.00),
(35, 35, '2024-10-13', 12, 2.50, 3750.00),
(36, 36, '2024-10-14', 13, 1.80, 2700.00),
(37, 37, '2024-10-14', 14, 0.70, 1050.00),
(38, 38, '2024-10-15', 15, 0.20, 300.00),
(39, 39, '2024-10-15', 16, 0.10, 150.00),
(40, 40, '2024-10-15', 17, 0.90, 1350.00);

-- --------------------------------------------------------

--
-- Table structure for table `perangkat`
--

CREATE TABLE `perangkat` (
  `id_perangkat` int(11) NOT NULL,
  `id_pengguna` int(11) NOT NULL,
  `nama_perangkat` varchar(100) NOT NULL,
  `daya_watt` decimal(10,2) NOT NULL,
  `status_perangkat` varchar(10) DEFAULT NULL CHECK (`status_perangkat` in ('ON','OFF')),
  `admin_id_pengguna` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `perangkat`
--

INSERT INTO `perangkat` (`id_perangkat`, `id_pengguna`, `nama_perangkat`, `daya_watt`, `status_perangkat`, `admin_id_pengguna`) VALUES
(1, 11, 'Kulkas LG 2 Pintu', 180.50, 'ON', 1),
(2, 12, 'AC Panasonic 1 PK', 750.00, 'OFF', 2),
(3, 13, 'Lampu LED Philips', 12.00, 'ON', 3),
(4, 14, 'Mesin Cuci Sharp', 500.00, 'OFF', 4),
(5, 15, 'Komputer Kantor Lenovo', 150.00, 'ON', 5),
(6, 16, 'TV Samsung 42 Inch', 100.00, 'ON', 6),
(7, 17, 'Pompa Air Shimizu', 370.00, 'OFF', 7),
(8, 18, 'Oven Listrik Cosmos', 800.00, 'OFF', 8),
(9, 19, 'Setrika Philips', 300.00, 'OFF', 9),
(10, 20, 'Lampu Hemat Energi', 10.00, 'ON', 10),
(11, 21, 'Kipas Angin Maspion', 45.00, 'ON', 1),
(12, 22, 'Server Data Center', 1200.00, 'ON', 2),
(13, 23, 'Proyektor Epson', 200.00, 'OFF', 3),
(14, 24, 'Mesin Kopi Nescafe', 900.00, 'OFF', 4),
(15, 25, 'Dispenser Miyako', 250.00, 'ON', 5),
(16, 26, 'Komputer Kantor Asus', 180.00, 'ON', 6),
(17, 27, 'Lampu Jalan Tenaga Surya', 60.00, 'ON', 7),
(18, 28, 'Motor Listrik Gesits', 2200.00, 'OFF', 8),
(19, 29, 'Lampu Outdoor', 15.00, 'ON', 9),
(20, 30, 'Kulkas Sharp 1 Pintu', 140.00, 'ON', 10),
(21, 31, 'AC Daikin 0.5 PK', 450.00, 'OFF', 1),
(22, 32, 'TV LED Polytron', 90.00, 'ON', 2),
(23, 33, 'Speaker Aktif', 70.00, 'ON', 3),
(24, 34, 'Laptop HP Pavilion', 100.00, 'ON', 4),
(25, 35, 'Kompor Induksi', 1200.00, 'OFF', 5),
(26, 36, 'Setrika Philips Mini', 250.00, 'OFF', 6),
(27, 37, 'Vacuum Cleaner Electrolux', 350.00, 'OFF', 7),
(28, 38, 'Lampu Kantor', 18.00, 'ON', 8),
(29, 39, 'Pompa Kolam', 500.00, 'OFF', 9),
(30, 40, 'Kipas Dinding', 50.00, 'ON', 10),
(31, 41, 'Rice Cooker Philips', 400.00, 'ON', 1),
(32, 42, 'Komputer Server Dell', 900.00, 'ON', 2),
(33, 43, 'TV Toshiba', 120.00, 'OFF', 3),
(34, 44, 'Proyektor BenQ', 180.00, 'ON', 4),
(35, 45, 'Lampu Neon 20W', 20.00, 'ON', 5),
(36, 46, 'Komputer Gaming MSI', 750.00, 'ON', 6),
(37, 47, 'Kipas Meja', 35.00, 'OFF', 7),
(38, 48, 'Dispenser Sharp', 240.00, 'ON', 8),
(39, 49, 'Lampu Belajar', 15.00, 'ON', 9),
(40, 50, 'TV Panasonic 32 Inch', 110.00, 'ON', 10);

-- --------------------------------------------------------

--
-- Table structure for table `sumber_terbarukan`
--

CREATE TABLE `sumber_terbarukan` (
  `id_sumber` int(11) NOT NULL,
  `id_pengguna` int(11) NOT NULL,
  `jenis_sumber` varchar(50) NOT NULL,
  `kapasitas_kw` decimal(10,2) DEFAULT NULL,
  `kontribusi_kwh` decimal(10,2) DEFAULT NULL,
  `tanggal_catat` date DEFAULT NULL,
  `admin_id_pengguna` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sumber_terbarukan`
--

INSERT INTO `sumber_terbarukan` (`id_sumber`, `id_pengguna`, `jenis_sumber`, `kapasitas_kw`, `kontribusi_kwh`, `tanggal_catat`, `admin_id_pengguna`) VALUES
(1, 11, 'Panel Surya', 3.20, 120.40, '2024-10-02', 1),
(2, 12, 'Panel Surya', 2.80, 95.60, '2024-10-03', 2),
(3, 13, 'Turbin Angin Mini', 4.50, 150.80, '2024-10-03', 3),
(4, 14, 'Panel Surya', 3.00, 110.20, '2024-10-04', 4),
(5, 15, 'Panel Surya', 5.00, 210.50, '2024-10-04', 5),
(6, 16, 'Panel Surya', 2.60, 90.10, '2024-10-05', 6),
(7, 17, 'Panel Surya', 4.00, 130.70, '2024-10-05', 7),
(8, 18, 'Turbin Angin Mini', 6.00, 250.40, '2024-10-06', 8),
(9, 19, 'Panel Surya', 3.50, 140.30, '2024-10-07', 9),
(10, 20, 'Panel Surya', 4.20, 165.10, '2024-10-07', 10),
(11, 21, 'Panel Surya', 3.10, 125.00, '2024-10-08', 1),
(12, 22, 'Turbin Angin Mini', 7.00, 290.80, '2024-10-08', 2),
(13, 23, 'Panel Surya', 4.50, 180.20, '2024-10-09', 3),
(14, 24, 'Panel Surya', 2.90, 100.50, '2024-10-09', 4),
(15, 25, 'Panel Surya', 5.50, 210.60, '2024-10-10', 5),
(16, 26, 'Turbin Angin Mini', 6.00, 250.00, '2024-10-10', 6),
(17, 27, 'Panel Surya', 3.70, 140.90, '2024-10-11', 7),
(18, 28, 'Panel Surya', 3.00, 115.70, '2024-10-11', 8),
(19, 29, 'Panel Surya', 4.30, 175.20, '2024-10-12', 9),
(20, 30, 'Panel Surya', 3.60, 145.50, '2024-10-12', 10),
(21, 31, 'Panel Surya', 3.20, 125.30, '2024-10-13', 1),
(22, 32, 'Panel Surya', 2.80, 95.60, '2024-10-13', 2),
(23, 33, 'Turbin Angin Mini', 4.80, 160.20, '2024-10-14', 3),
(24, 34, 'Panel Surya', 3.30, 120.00, '2024-10-14', 4),
(25, 35, 'Panel Surya', 5.50, 220.40, '2024-10-15', 5),
(26, 36, 'Panel Surya', 2.90, 105.10, '2024-10-15', 6),
(27, 37, 'Panel Surya', 4.10, 165.20, '2024-10-16', 7),
(28, 38, 'Turbin Angin Mini', 6.50, 260.10, '2024-10-16', 8),
(29, 39, 'Panel Surya', 3.40, 135.90, '2024-10-17', 9),
(30, 40, 'Panel Surya', 3.90, 155.00, '2024-10-17', 10),
(31, 41, 'Panel Surya', 2.70, 95.80, '2024-10-18', 1),
(32, 42, 'Panel Surya', 4.00, 150.00, '2024-10-18', 2),
(33, 43, 'Panel Surya', 3.50, 140.50, '2024-10-19', 3),
(34, 44, 'Panel Surya', 3.10, 120.20, '2024-10-19', 4),
(35, 45, 'Turbin Angin Mini', 6.20, 245.30, '2024-10-20', 5),
(36, 46, 'Panel Surya', 3.00, 115.00, '2024-10-20', 6),
(37, 47, 'Panel Surya', 4.50, 175.40, '2024-10-21', 7),
(38, 48, 'Panel Surya', 3.80, 155.60, '2024-10-21', 8),
(39, 49, 'Panel Surya', 2.90, 100.50, '2024-10-22', 9),
(40, 50, 'Panel Surya', 3.60, 145.50, '2024-10-22', 10),
(41, 11, 'Panel Surya', 6.00, 120.00, '2025-09-10', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `biasa`
--
ALTER TABLE `biasa`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `laporan_efisiensi`
--
ALTER TABLE `laporan_efisiensi`
  ADD PRIMARY KEY (`id_laporan`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `admin_id_pengguna` (`admin_id_pengguna`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `penggunaan_energi`
--
ALTER TABLE `penggunaan_energi`
  ADD PRIMARY KEY (`id_penggunaan`),
  ADD KEY `id_perangkat` (`id_perangkat`);

--
-- Indexes for table `perangkat`
--
ALTER TABLE `perangkat`
  ADD PRIMARY KEY (`id_perangkat`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `admin_id_pengguna` (`admin_id_pengguna`);

--
-- Indexes for table `sumber_terbarukan`
--
ALTER TABLE `sumber_terbarukan`
  ADD PRIMARY KEY (`id_sumber`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `admin_id_pengguna` (`admin_id_pengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `laporan_efisiensi`
--
ALTER TABLE `laporan_efisiensi`
  MODIFY `id_laporan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `perangkat`
--
ALTER TABLE `perangkat`
  MODIFY `id_perangkat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `sumber_terbarukan`
--
ALTER TABLE `sumber_terbarukan`
  MODIFY `id_sumber` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `biasa`
--
ALTER TABLE `biasa`
  ADD CONSTRAINT `biasa_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `laporan_efisiensi`
--
ALTER TABLE `laporan_efisiensi`
  ADD CONSTRAINT `laporan_efisiensi_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `laporan_efisiensi_ibfk_2` FOREIGN KEY (`admin_id_pengguna`) REFERENCES `admin` (`id_pengguna`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `penggunaan_energi`
--
ALTER TABLE `penggunaan_energi`
  ADD CONSTRAINT `penggunaan_energi_ibfk_1` FOREIGN KEY (`id_perangkat`) REFERENCES `perangkat` (`id_perangkat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `perangkat`
--
ALTER TABLE `perangkat`
  ADD CONSTRAINT `perangkat_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `perangkat_ibfk_2` FOREIGN KEY (`admin_id_pengguna`) REFERENCES `admin` (`id_pengguna`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `sumber_terbarukan`
--
ALTER TABLE `sumber_terbarukan`
  ADD CONSTRAINT `sumber_terbarukan_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sumber_terbarukan_ibfk_2` FOREIGN KEY (`admin_id_pengguna`) REFERENCES `admin` (`id_pengguna`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
