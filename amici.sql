-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Dic 06, 2016 alle 12:26
-- Versione del server: 10.1.13-MariaDB
-- Versione PHP: 7.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `amici`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `amici`
--

CREATE TABLE `amici` (
  `id` int(11) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `dataNascita` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `amici`
--

INSERT INTO `amici` (`id`, `cognome`, `nome`, `dataNascita`) VALUES
(1, 'gastaldo', 'samuele', '1996-07-03'),
(2, 'nonsei', 'mioamico', '1990-03-05'),
(4, 'basso', 'matteo', '1998-09-06'),
(5, 'nardi', 'davide', '1998-05-12'),
(6, 'zhou', 'ziqiang', '1998-09-30');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `amici`
--
ALTER TABLE `amici`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `amici`
--
ALTER TABLE `amici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
