-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 11, 2021 at 04:16 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `approject`
--
CREATE DATABASE IF NOT EXISTS `approject` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `approject`;

-- --------------------------------------------------------

--
-- Table structure for table `assigned_technician`
--

CREATE TABLE `assigned_technician` (
  `id` int(11) NOT NULL,
  `technician_id` int(15) NOT NULL,
  `reported_issue_id` int(15) NOT NULL,
  `live_chat` varchar(5) NOT NULL DEFAULT 'NO'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assigned_technician`
--

INSERT INTO `assigned_technician` (`id`, `technician_id`, `reported_issue_id`, `live_chat`) VALUES
(1, 1, 10, 'NO'),
(2, 1, 2, 'YES'),
(7, 1, 8, 'YES'),
(8, 1, 1, 'YES'),
(9, 1, 14, 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `complaint_categories`
--

CREATE TABLE `complaint_categories` (
  `id` int(11) NOT NULL,
  `category_id` int(15) NOT NULL,
  `category_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complaint_categories`
--

INSERT INTO `complaint_categories` (`id`, `category_id`, `category_name`) VALUES
(1, 1, 'Outage'),
(2, 2, 'Slow Speed'),
(3, 3, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_user_id` int(15) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `home_address` varchar(100) NOT NULL,
  `tele_num` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `customer_user_id`, `first_name`, `last_name`, `email_address`, `home_address`, `tele_num`) VALUES
(1, 123, 'damain', 'patterson', 'damainp4@gmail.com', 'St. Ann', '8765555555');

-- --------------------------------------------------------

--
-- Table structure for table `customer_login`
--

CREATE TABLE `customer_login` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `cus_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_login`
--

INSERT INTO `customer_login` (`id`, `customer_id`, `cus_password`) VALUES
(3, 1, '123');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `employee_type` varchar(100) NOT NULL,
  `employee_user_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  `home_address` varchar(100) NOT NULL,
  `tele_num` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `employee_type`, `employee_user_id`, `first_name`, `last_name`, `email_address`, `home_address`, `tele_num`) VALUES
(1, 'TECHNICIAN', 321, 'benn', 'frack', 'email@yahoo.com', 'address', '187622222'),
(2, 'REPRESENTATIVE', 987, 'brianna', 'perkins', 'brianna@gmail.com', 'somewhere', '1876555555');

-- --------------------------------------------------------

--
-- Table structure for table `employee_login`
--

CREATE TABLE `employee_login` (
  `id` int(11) NOT NULL,
  `employee_id` int(15) NOT NULL,
  `emp_password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_login`
--

INSERT INTO `employee_login` (`id`, `employee_id`, `emp_password`) VALUES
(1, 1, '321'),
(2, 2, '987');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(10);

-- --------------------------------------------------------

--
-- Table structure for table `payments_list`
--

CREATE TABLE `payments_list` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `amount_due` int(11) NOT NULL,
  `due_date` varchar(10) NOT NULL,
  `payment_status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments_list`
--

INSERT INTO `payments_list` (`id`, `customer_id`, `amount_due`, `due_date`, `payment_status`) VALUES
(1, 1, 10, '5/4/2021', 'paid'),
(2, 2, 20, '20', 'owed');

-- --------------------------------------------------------

--
-- Table structure for table `payment_history`
--

CREATE TABLE `payment_history` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `amount_paid` int(11) NOT NULL,
  `date_due` varchar(25) NOT NULL,
  `date_paid` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment_history`
--

INSERT INTO `payment_history` (`id`, `customer_id`, `amount_paid`, `date_due`, `date_paid`) VALUES
(1, 1, 1000, '5/2/2021', '5/2/2021'),
(2, 1, 2000, '5/3/2021', '5/3/2021');

-- --------------------------------------------------------

--
-- Table structure for table `product_services`
--

CREATE TABLE `product_services` (
  `id` int(11) NOT NULL,
  `product_id` int(15) NOT NULL,
  `product_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_services`
--

INSERT INTO `product_services` (`id`, `product_id`, `product_name`) VALUES
(1, 1, 'Broadband'),
(2, 2, 'Telephone'),
(4, 3, 'Cable');

-- --------------------------------------------------------

--
-- Table structure for table `reported_issue`
--

CREATE TABLE `reported_issue` (
  `id` int(15) NOT NULL,
  `userid` int(15) NOT NULL,
  `product_service_id` int(15) NOT NULL,
  `complaint_category_id` int(15) NOT NULL,
  `complaint` varchar(250) NOT NULL,
  `completed` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reported_issue`
--

INSERT INTO `reported_issue` (`id`, `userid`, `product_service_id`, `complaint_category_id`, `complaint`, `completed`) VALUES
(1, 1, 1, 1, 'first issue', 'YES'),
(2, 1, 1, 1, 'another one', 'YES'),
(3, 1, 1, 1, 'another one', 'YES'),
(4, 1, 1, 1, 'testing the report section', 'YES'),
(5, 1, 2, 2, 'hello there something is wrong with my device', 'YES'),
(9, 1, 1, 1, 'hello there... just a report', 'YES'),
(10, 1, 1, 2, 'just another report', 'YES'),
(11, 1, 2, 1, 'hello there', 'YES'),
(12, 1, 1, 1, 'hello this is a broadband complaint', 'NO'),
(13, 1, 1, 1, 'hello there this is a complaint', 'YES'),
(14, 1, 1, 1, 'new issue blah blah blah', 'NO');

-- --------------------------------------------------------

--
-- Table structure for table `response`
--

CREATE TABLE `response` (
  `id` int(11) NOT NULL,
  `complaint_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `responder_id` int(11) NOT NULL,
  `response` varchar(250) NOT NULL,
  `response_date` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `response`
--

INSERT INTO `response` (`id`, `complaint_id`, `customer_id`, `responder_id`, `response`, `response_date`) VALUES
(1, 4, 1, 1, 'this is a response from a employee', '1/4/2020'),
(2, 2, 1, 2, 'this is another response from another employee', '31/3/2020'),
(3, 4, 1, 1, 'this is suppose to be the last response for benn frack', '3/4/2020'),
(4, 4, 1, 2, 'blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah', '3/4/2021'),
(5, 5, 1, 1, 'this is a response made from the technician\r\n\r\nProposed Vist Date: 06/04/2021', '05/04/2021'),
(8, 9, 1, 1, 'testing technician response with new date of visit format and selection of available for live chat feature...\n\nProposed Vist Date: 16/04/2021', '05/04/2021'),
(9, 1, 1, 2, 'this is a response from a representative to the \'first issue\' complaint', '08/04/2021'),
(10, 1, 1, 2, 'another response from representative', '08/04/2021'),
(11, 10, 1, 2, 'making a response', '10/04/2021'),
(12, 1, 1, 1, 'response from tech\n\nProposed Vist Date: 17/04/2021', '10/04/2021'),
(13, 14, 1, 1, 'response from tech\n\nProposed Vist Date: 17/04/2021', '10/04/2021');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assigned_technician`
--
ALTER TABLE `assigned_technician`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `complaint_categories`
--
ALTER TABLE `complaint_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_login`
--
ALTER TABLE `customer_login`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_login_idfk` (`customer_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee_login`
--
ALTER TABLE `employee_login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payments_list`
--
ALTER TABLE `payments_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment_history`
--
ALTER TABLE `payment_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_services`
--
ALTER TABLE `product_services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reported_issue`
--
ALTER TABLE `reported_issue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userid_fk` (`userid`),
  ADD KEY `complaint_category_id_fk` (`complaint_category_id`),
  ADD KEY `product_service_id_fk` (`product_service_id`);

--
-- Indexes for table `response`
--
ALTER TABLE `response`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id_fk` (`customer_id`),
  ADD KEY `complaint_id_fk` (`complaint_id`),
  ADD KEY `responder_id_fk` (`responder_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assigned_technician`
--
ALTER TABLE `assigned_technician`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `complaint_categories`
--
ALTER TABLE `complaint_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customer_login`
--
ALTER TABLE `customer_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `employee_login`
--
ALTER TABLE `employee_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `payments_list`
--
ALTER TABLE `payments_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `payment_history`
--
ALTER TABLE `payment_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product_services`
--
ALTER TABLE `product_services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reported_issue`
--
ALTER TABLE `reported_issue`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `response`
--
ALTER TABLE `response`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_login`
--
ALTER TABLE `customer_login`
  ADD CONSTRAINT `customer_login_idfk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

--
-- Constraints for table `reported_issue`
--
ALTER TABLE `reported_issue`
  ADD CONSTRAINT `complaint_category_id_fk` FOREIGN KEY (`complaint_category_id`) REFERENCES `complaint_categories` (`id`),
  ADD CONSTRAINT `product_service_id_fk` FOREIGN KEY (`product_service_id`) REFERENCES `product_services` (`id`),
  ADD CONSTRAINT `userid_fk` FOREIGN KEY (`userid`) REFERENCES `customer` (`id`);

--
-- Constraints for table `response`
--
ALTER TABLE `response`
  ADD CONSTRAINT `complaint_id_fk` FOREIGN KEY (`complaint_id`) REFERENCES `reported_issue` (`id`),
  ADD CONSTRAINT `customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `responder_id_fk` FOREIGN KEY (`responder_id`) REFERENCES `employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
