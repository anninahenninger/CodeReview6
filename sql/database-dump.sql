-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 15. Jun 2019 um 11:27
-- Server-Version: 10.1.38-MariaDB
-- PHP-Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `cr6`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `classes`
--

CREATE TABLE `classes` (
  `classID` int(11) NOT NULL,
  `className` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `classes`
--

INSERT INTO `classes` (`classID`, `className`) VALUES
(1, '1a'),
(2, '1b'),
(3, '2a'),
(4, '2b'),
(5, '3a'),
(6, '4a');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student`
--

CREATE TABLE `student` (
  `studentID` int(11) NOT NULL,
  `studentName` varchar(55) DEFAULT NULL,
  `studentSurname` varchar(55) DEFAULT NULL,
  `studentEmail` varchar(55) DEFAULT NULL,
  `classID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `student`
--

INSERT INTO `student` (`studentID`, `studentName`, `studentSurname`, `studentEmail`, `classID`) VALUES
(1, 'Barbara', 'Reibling', 'bare@st.school.at', 1),
(2, 'Daniela', 'Hasler', 'daha@st.school.at', 2),
(3, 'Nina', 'Selden', 'nise@st.school.at', 3),
(4, 'Marion', 'Zeginigg', 'maze@st.school.at', 2),
(5, 'Sabrina', 'Kugi', 'saku@st.school.at', 1),
(6, 'Stefan', 'Riegler', 'stri@st.school.at', 4),
(7, 'Stefan', 'Pöltl', 'stpö@st.school.at', 5),
(8, 'Klaus', 'Hartinger', 'klha1st.school.at', 6),
(9, 'Reinhard', 'Ferner', 'refe@st.school.at', 5),
(10, 'Hubert', 'Komar', 'huko@st.school.at', 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teacher`
--

CREATE TABLE `teacher` (
  `teacherID` int(11) NOT NULL,
  `teacherName` varchar(55) DEFAULT NULL,
  `teacherSurname` varchar(55) DEFAULT NULL,
  `teacherEmail` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `teacher`
--

INSERT INTO `teacher` (`teacherID`, `teacherName`, `teacherSurname`, `teacherEmail`) VALUES
(1, 'Fritz', 'Kurz', 'fk@school.at'),
(2, 'Claudia', 'Kolinsky', 'ck@school.at'),
(3, 'Josef', 'Wastian', 'jw@school.at'),
(4, 'Marie', 'Buison', 'mb@school.at');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teachers_classes`
--

CREATE TABLE `teachers_classes` (
  `teacherID` int(11) NOT NULL,
  `classID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `teachers_classes`
--

INSERT INTO `teachers_classes` (`teacherID`, `classID`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 5),
(2, 2),
(2, 6),
(2, 4),
(3, 4),
(3, 5),
(3, 1),
(4, 2),
(4, 5),
(4, 3),
(4, 4),
(4, 6);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`classID`);

--
-- Indizes für die Tabelle `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentID`),
  ADD KEY `classID` (`classID`);

--
-- Indizes für die Tabelle `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherID`);

--
-- Indizes für die Tabelle `teachers_classes`
--
ALTER TABLE `teachers_classes`
  ADD KEY `classID` (`classID`),
  ADD KEY `teacherID` (`teacherID`) USING BTREE;

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `classes`
--
ALTER TABLE `classes`
  MODIFY `classID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `student`
--
ALTER TABLE `student`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`classID`) REFERENCES `classes` (`classID`);

--
-- Constraints der Tabelle `teachers_classes`
--
ALTER TABLE `teachers_classes`
  ADD CONSTRAINT `teachers_classes_ibfk_1` FOREIGN KEY (`classID`) REFERENCES `classes` (`classID`),
  ADD CONSTRAINT `teachers_classes_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`teacherID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
