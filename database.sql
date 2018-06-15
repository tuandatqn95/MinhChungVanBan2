-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2017 at 10:30 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `minhchungvanban`
--

-- --------------------------------------------------------

--
-- Table structure for table `botieuchuan`
--

CREATE TABLE `botieuchuan` (
  `ID` int(11) NOT NULL,
  `MaBoTC` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `TenBoTC` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` longtext COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `botieuchuan`
--

INSERT INTO `botieuchuan` (`ID`, `MaBoTC`, `TenBoTC`, `MoTa`) VALUES
(1, 'AUN-VN', 'Chất lượng giáo dục', 'mo ta'),
(3, 'TEST', 'TEST BTC', 'mÃ´ táº£');

-- --------------------------------------------------------

--
-- Table structure for table `loaitaikhoan`
--

CREATE TABLE `loaitaikhoan` (
  `ID` int(10) NOT NULL,
  `TenLoaiTK` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `loaitaikhoan`
--

INSERT INTO `loaitaikhoan` (`ID`, `TenLoaiTK`) VALUES
(1, 'Người xem minh chứng'),
(2, 'Người đăng minh chứng'),
(3, 'Super Admin');

-- --------------------------------------------------------

--
-- Table structure for table `minhchung`
--

CREATE TABLE `minhchung` (
  `ID` int(10) NOT NULL,
  `IDTieuChi` int(10) NOT NULL,
  `MaMC` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenMC` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` longtext COLLATE utf8_unicode_ci,
  `SoHieu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayBanHanh` date NOT NULL,
  `IDNBH` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `minhchung`
--

INSERT INTO `minhchung` (`ID`, `IDTieuChi`, `MaMC`, `TenMC`, `MoTa`, `SoHieu`, `NgayBanHanh`, `IDNBH`) VALUES
(3, 3, 'H1.1.1.1', 'Kế hoạch chiến lược phát triển trung hạn giai đoạn 2011-2015', '', '12/2010', '2017-11-21', 1);

-- --------------------------------------------------------

--
-- Table structure for table `noibanhanh`
--

CREATE TABLE `noibanhanh` (
  `ID` int(11) NOT NULL,
  `MaNBH` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenNBH` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `noibanhanh`
--

INSERT INTO `noibanhanh` (`ID`, `MaNBH`, `TenNBH`) VALUES
(1, 'PCTSV', 'Phòng Công Tác Sinh Viên'),
(2, 'PDT', 'Phòng Đào Tạo');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `ID` int(11) NOT NULL,
  `Email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `IDLoaiTK` int(10) NOT NULL,
  `HoTen` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `Nu` bit(1) NOT NULL,
  `NgaySinh` date NOT NULL,
  `DiaChi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NoiCongTac` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ChucVu` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoDT` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AnhDaiDien` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`ID`, `Email`, `MatKhau`, `IDLoaiTK`, `HoTen`, `Nu`, `NgaySinh`, `DiaChi`, `NoiCongTac`, `ChucVu`, `SoDT`, `AnhDaiDien`) VALUES
(1, 'tuandatqn95@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 3, 'Nguyễn Tuấn Đạt', b'0', '1995-02-12', 'Thủ Đưc', 'Khoa CNTT', 'N/A', '0988944543', 'avatar-1606916_960_720.png'),
(2, 'tuandat122@gmail.com', '12345', 1, 'Tuấn Đạt', b'0', '2011-11-11', 'Lê văn chí', 'null', 'Sinh Viên', '09865325', 'avatar-1606916_960_720.png');

-- --------------------------------------------------------

--
-- Table structure for table `taptin`
--

CREATE TABLE `taptin` (
  `ID` int(10) NOT NULL,
  `IDMinhChung` int(10) NOT NULL,
  `FilePath` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `FileType` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `thongbao`
--

CREATE TABLE `thongbao` (
  `ID` int(10) NOT NULL,
  `TieuDeTB` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `NoiDungTB` longtext COLLATE utf8_unicode_ci,
  `ThGianDangTai` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thongbao`
--

INSERT INTO `thongbao` (`ID`, `TieuDeTB`, `NoiDungTB`, `ThGianDangTai`) VALUES
(1, 'Thông báo số 1', 'Hiển thị kết quả cho id=\"text Editor\" editor bootstrap\r\nTìm kiếm thay thế cho id=\"txtEditor\" editor bốttrap\r\nKết quả tìm kiếm\r\nResponsive WYSIWYG Text Editor with jQuery and Bootstrap ...\r\nhttps://www.jqueryscript.net › jQuery Text Plugins\r\nDịch trang này\r\n31 thg 5, 2014 - LineControl Editor is a jQuery plugin to append an WYSIWYG rich text editor to a textarea or any other container elements like DIV.', '2017-11-21'),
(2, 'aaaaa', 'tseưqewqeqwqweqwewqe', '2017-11-21'),
(3, 'aaaaa', 'bbbbbbbbbbbbbb', '2017-11-21');

-- --------------------------------------------------------

--
-- Table structure for table `tieuchi`
--

CREATE TABLE `tieuchi` (
  `ID` int(10) NOT NULL,
  `IDTieuChuan` int(10) NOT NULL,
  `MaTieuChi` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenTieuChi` longtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tieuchi`
--

INSERT INTO `tieuchi` (`ID`, `IDTieuChuan`, `MaTieuChi`, `TenTieuChi`) VALUES
(1, 10, 'TC1.1', 'Tiêu chí 1.1'),
(3, 9, 'TC1.2', 'Tiêu chí 1.2');

-- --------------------------------------------------------

--
-- Table structure for table `tieuchuan`
--

CREATE TABLE `tieuchuan` (
  `ID` int(11) NOT NULL,
  `IDBoTieuChuan` int(11) NOT NULL,
  `MaTieuChuan` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenTieuChuan` longtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tieuchuan`
--

INSERT INTO `tieuchuan` (`ID`, `IDBoTieuChuan`, `MaTieuChuan`, `TenTieuChuan`) VALUES
(9, 1, 'TC1', 'Tiêu chuẩn 1'),
(10, 1, 'TC2', 'Tiêu chuẩn 2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `botieuchuan`
--
ALTER TABLE `botieuchuan`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `loaitaikhoan`
--
ALTER TABLE `loaitaikhoan`
  ADD PRIMARY KEY (`ID`) USING BTREE;

--
-- Indexes for table `minhchung`
--
ALTER TABLE `minhchung`
  ADD PRIMARY KEY (`ID`) USING BTREE,
  ADD KEY `IDNBH` (`IDNBH`),
  ADD KEY `IDTieuChi` (`IDTieuChi`);

--
-- Indexes for table `noibanhanh`
--
ALTER TABLE `noibanhanh`
  ADD PRIMARY KEY (`ID`) USING BTREE;

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`ID`) USING BTREE,
  ADD KEY `IDLoaiTK` (`IDLoaiTK`);

--
-- Indexes for table `taptin`
--
ALTER TABLE `taptin`
  ADD PRIMARY KEY (`ID`) USING BTREE,
  ADD KEY `IDMinhChung` (`IDMinhChung`);

--
-- Indexes for table `thongbao`
--
ALTER TABLE `thongbao`
  ADD PRIMARY KEY (`ID`) USING BTREE;

--
-- Indexes for table `tieuchi`
--
ALTER TABLE `tieuchi`
  ADD PRIMARY KEY (`ID`) USING BTREE,
  ADD KEY `IDTieuChuan` (`IDTieuChuan`);

--
-- Indexes for table `tieuchuan`
--
ALTER TABLE `tieuchuan`
  ADD PRIMARY KEY (`ID`) USING BTREE,
  ADD KEY `IDBoTieuChuan` (`IDBoTieuChuan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `botieuchuan`
--
ALTER TABLE `botieuchuan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `loaitaikhoan`
--
ALTER TABLE `loaitaikhoan`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `minhchung`
--
ALTER TABLE `minhchung`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `noibanhanh`
--
ALTER TABLE `noibanhanh`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `taptin`
--
ALTER TABLE `taptin`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `thongbao`
--
ALTER TABLE `thongbao`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tieuchi`
--
ALTER TABLE `tieuchi`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tieuchuan`
--
ALTER TABLE `tieuchuan`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `minhchung`
--
ALTER TABLE `minhchung`
  ADD CONSTRAINT `minhchung_ibfk_2` FOREIGN KEY (`IDNBH`) REFERENCES `noibanhanh` (`ID`),
  ADD CONSTRAINT `minhchung_ibfk_3` FOREIGN KEY (`IDTieuChi`) REFERENCES `tieuchi` (`ID`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`IDLoaiTK`) REFERENCES `loaitaikhoan` (`ID`);

--
-- Constraints for table `taptin`
--
ALTER TABLE `taptin`
  ADD CONSTRAINT `taptin_ibfk_1` FOREIGN KEY (`IDMinhChung`) REFERENCES `minhchung` (`ID`);

--
-- Constraints for table `tieuchi`
--
ALTER TABLE `tieuchi`
  ADD CONSTRAINT `tieuchi_ibfk_1` FOREIGN KEY (`IDTieuChuan`) REFERENCES `tieuchuan` (`ID`);

--
-- Constraints for table `tieuchuan`
--
ALTER TABLE `tieuchuan`
  ADD CONSTRAINT `tieuchuan_ibfk_1` FOREIGN KEY (`IDBoTieuChuan`) REFERENCES `botieuchuan` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
