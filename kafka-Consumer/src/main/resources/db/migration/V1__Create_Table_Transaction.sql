CREATE TABLE `transaction` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `initial_time` varchar (100),
  `end_time` varchar (100),
  `time_lapsed` varchar (100),
  `level` int ,
  `repetitions` int ,
  `method` varchar (100)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
