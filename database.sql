CREATE TABLE `class` (
  `idclass` int NOT NULL AUTO_INCREMENT,
  `sid` int DEFAULT NULL,
  `cid` int DEFAULT NULL,
  `done` bit(1) NOT NULL DEFAULT b'0',
  `grade` varchar(45) DEFAULT NULL,
  `quiz` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idclass`),
  KEY `student_id_idx` (`sid`),
  KEY `exam_id_idx` (`cid`),
  CONSTRAINT `exam_id` FOREIGN KEY (`cid`) REFERENCES `exam` (`cid`),
  CONSTRAINT `student_id` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `exam` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `tid` int NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `firstname` text NOT NULL,
  `lastname` text NOT NULL,
  `tid` int DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`sid`),
  KEY `student_username_idx` (`username`),
  KEY `student_ibfk_1_idx` (`tid`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `student_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `teacher` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `firstname` text NOT NULL,
  `lastname` text NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `username_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
