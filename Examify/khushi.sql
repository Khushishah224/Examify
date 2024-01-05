-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2024 at 08:11 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `khushi`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `F_Login` (IN `id` INT, IN `pass` VARCHAR(30))   BEGIN
select * from faculty where f_id=id and f_password=pass;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GenerateQuestion` (IN `setName` VARCHAR(10), IN `tablename` VARCHAR(10))   BEGIN
SELECT * from tablename where q_set=setName;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `S_Login` (IN `id` INT(10), IN `p` VARCHAR(30))   BEGIN
select * from student where s_id=id and s_pass=p;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `ds`
--

CREATE TABLE `ds` (
  `q_no` int(11) NOT NULL,
  `question` varchar(5000) NOT NULL,
  `option_A` varchar(1000) NOT NULL,
  `option_B` varchar(1000) NOT NULL,
  `option_C` varchar(1000) NOT NULL,
  `option_D` varchar(1000) NOT NULL,
  `answer` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ds`
--

INSERT INTO `ds` (`q_no`, `question`, `option_A`, `option_B`, `option_C`, `option_D`, `answer`) VALUES
(1, 'What is a data structure?', 'A programming language\r\n', 'A collection of algorithms\r\n', 'A way to store and organize data\r\n', 'A type of computer hardware', 'C'),
(2, 'What are the disadvantages of arrays?', 'Index value of an array can be negative\r\n', 'Elements are sequentially accessed\r\n', 'Data structure like queue or stack cannot be implemented\r\n', 'There are chances of wastage of memory space if elements inserted in an array are lesser than the allocated size', 'D'),
(3, 'Which data structure is used for implementing recursion?', 'Stack\r\n', 'Queue\r\n', 'List\r\n', 'Array', 'A'),
(4, 'The data structure required to check whether an expression contains a balanced parenthesis is?', 'Queue\r\n', 'Stack\r\n', 'Tree\r\n', 'Array', 'B'),
(5, 'Which data structure is needed to convert infix notation to postfix notation?', 'Tree', 'Branch\r\n', 'Stack\r\n', 'Queue', 'C'),
(6, 'What is the value of the postfix expression 6 3 2 4 + – *?', '74\r\n', '-18\r\n', '22\r\n', '40', 'B'),
(7, 'Which of the following points is not true about Linked List data structure when it is compared with an array?', 'Random access is not allowed in a typical implementation of Linked Lists\r\n', 'Access of elements in linked list takes less time than compared to arrays\r\n', 'Arrays have better cache locality that can make them better in terms of performance\r\n', 'It is easy to insert and delete elements in Linked List', 'B'),
(8, 'Which data structure is based on the Last In First Out (LIFO) principle?\r\n', 'Tree\r\n', 'Linked List\r\n', 'Stack\r\n', 'Queue', 'C'),
(9, 'Which of the following application makes use of a circular linked list?', 'Recursive function calls\r\n', 'Undo operation in a text editor\r\n', 'Implement Hash Tables\r\n', 'Allocating CPU to resources', 'D'),
(10, 'Which of the following is not the type of queue?', 'Priority queue\r\n', 'Circular queue\r\n', 'Single ended queue\r\n', 'Ordinary queue', 'C'),
(11, 'What is the need for a circular queue?', 'easier computations\r\n', 'implement LIFO principle in queues\r\n', 'effective usage of memory\r\n', 'to delete elements based on priority', 'C'),
(12, 'Which of the following is the most widely used external memory data structure?', 'B-tree\r\n', 'Red-black tree\r\n', 'AVL tree\r\n', 'Both AVL tree and Red-black tree', 'A'),
(13, 'What is an AVL tree?', 'a tree which is unbalanced and is a height balanced tree\r\n', 'a tree which is balanced and is a height balanced tree\r\n', 'a tree with atmost 3 children\r\n', 'a tree with three children', 'B'),
(14, 'The optimal data structure used to solve Tower of Hanoi is _________', 'Tree\r\n', 'Heap\r\n', 'Priority queue\r\n', 'Stack', ''),
(15, 'Which is the most appropriate data structure for reversing a word?', 'stack\r\n', 'queue\r\n', 'graph\r\n', 'tree', 'A'),
(16, 'What is the advantage of a hash table as a data structure?', 'easy to implement\r\n', 'faster access of data\r\n', 'exhibit good locality of reference\r\n', 'very efficient for less number of entries', 'B'),
(17, 'What is a dequeue?', 'A queue implemented with both singly and doubly linked lists\r\n', 'A queue with insert/delete defined for front side of the queue\r\n', 'A queue with insert/delete defined for both front and rear ends of the queue\r\n', 'A queue implemented with a doubly linked list', 'C'),
(18, 'A data structure in which elements can be inserted or deleted at/from both ends but not in the middle is?', 'Priority queue\r\n', 'Dequeue\r\n', 'Circular queue\r\n', 'Queue', 'B'),
(19, 'In simple chaining, what data structure is appropriate?', 'Doubly linked list\r\n', 'Circular linked list\r\n', 'Singly linked list\r\n', 'Binary trees', 'A'),
(20, 'Which of the following data structures can be used for parentheses matching?', 'n-ary tree\r\n', 'queue\r\n', 'priority queue\r\n', 'stack', 'D'),
(21, 'The prefix form of A-B/ (C * D ^ E) is?', '-A/B*C^DE\r\n', '-A/BC*^DE\r\n', '-ABCD*^DE\r\n', '-/*^ACBDE', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `exam_id` int(10) NOT NULL,
  `s_id` int(11) NOT NULL,
  `java` int(10) NOT NULL DEFAULT 0,
  `DS` int(10) NOT NULL DEFAULT 0,
  `total` int(10) NOT NULL DEFAULT 0,
  `date` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`exam_id`, `s_id`, `java`, `DS`, `total`, `date`) VALUES
(1, 1, 10, 0, 10, '2023-10-08'),
(2, 1, 14, 7, 21, '2023-10-09'),
(3, 2, 7, 4, 11, '2023-10-09'),
(4, 4, 16, 7, 23, '2023-10-09'),
(5, 3, 11, 9, 20, '2023-10-09');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `f_id` int(11) NOT NULL,
  `f_password` varchar(10) NOT NULL,
  `f_name` varchar(50) NOT NULL,
  `f_phone_no` bigint(20) NOT NULL,
  `f_que_set` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`f_id`, `f_password`, `f_name`, `f_phone_no`, `f_que_set`) VALUES
(101, 'abc', 'PAT', 9876534290, 'P'),
(102, 'defg', 'IAM', 8976543028, 'I'),
(103, 'hijk', 'NPB', 9087654338, 'N'),
(104, 'lmno', 'JPM', 8079865432, 'J');

-- --------------------------------------------------------

--
-- Table structure for table `java`
--

CREATE TABLE `java` (
  `q_no` int(11) NOT NULL,
  `question` varchar(5000) NOT NULL,
  `option_A` varchar(1000) NOT NULL,
  `option_B` varchar(1000) NOT NULL,
  `option_C` varchar(1000) NOT NULL,
  `option_D` varchar(1000) NOT NULL,
  `answer` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `java`
--

INSERT INTO `java` (`q_no`, `question`, `option_A`, `option_B`, `option_C`, `option_D`, `answer`) VALUES
(1, 'Number of primitive data types in Java are?', '6', '7', '8', '9', 'C'),
(2, 'Automatic type conversion is possible in which of the possible cases?', 'float to int', 'int to long', 'double to float', 'none of the above', 'B'),
(3, 'What is the extension of java code files?\r\n', '.js', '.txt', '.class', '.java', 'D'),
(4, 'What is not the use of “this” keyword in Java?\r\n', 'Referring to the instance variable when a local variable has the same name', 'Passing itself to the method of the same class\r\n', 'Passing itself to another method', 'Calling another constructor in constructor chaining', 'B'),
(5, 'What is the extension of compiled java classes?', '.txt\r\n', '.js\r\n', '.class\r\n', '.java', 'C'),
(6, 'Which statement is true about Java?', 'Java is a sequence-dependent programming language', 'Java is a code dependent programming language', 'Java is a platform-dependent programming language', 'Java is a platform-independent programming language', 'D'),
(7, 'Which component is used to compile, debug and execute the java programs?', 'JRE', 'JIT', 'JDK', 'JVM', 'C'),
(8, ' Which one of the following is not a Java feature?', 'Object-oriented', ' Use of pointers', 'Portable', ' Dynamic and Extensible', 'B'),
(9, ' Which of these cannot be used for a variable name in Java? ', 'identifier & keyword', ' identifier', ' keyword', 'none of the mentioned', 'C'),
(10, 'What is the extension of java code files?', '.js', '.txt', '.class', '.java', 'D'),
(11, 'Which exception is thrown when java is out of memory?', 'MemoryError\r\n', 'OutOfMemoryError\r\n', 'MemoryOutOfBoundsException\r\n', 'MemoryFullException', 'B'),
(12, ' Which of the following is not an OOPS concept in Java?', 'Polymorphism', 'Inheritance', 'Compilation', 'Encapsulation', 'C'),
(13, 'Which of these keywords is used to define interfaces in Java?\r\n', 'intf\r\n', 'Intf\r\n', 'interface\r\n', 'Interfaces', 'C'),
(14, 'Which of the following is a superclass of every class in Java?', 'ArrayList\r\n', 'Abstract class\r\n', 'Object class\r\n', 'String', 'C'),
(15, 'Which of these packages contains the exception Stack Overflow in Java?\r\n', 'java.io\r\n', 'java.system\r\n', 'java.lang\r\n', 'java.util', 'C'),
(16, 'Which of these statements is incorrect about Thread?\r\n', 'start() method is used to begin execution of the thread\r\n', 'run() method is used to begin execution of a thread before start() method in special cases\r\n', 'A thread can be formed by implementing Runnable interface only\r\n', 'A thread can be formed by a class that extends Thread class', 'B'),
(17, 'Which of these keywords are used for the block to be examined for exceptions?\r\n', 'check\r\n', 'throw\r\n', 'catch\r\n', 'try', 'D'),
(18, 'Which one of the following is not an access modifier?\r\n', 'Protected\r\n', 'Void\r\n', 'Public\r\n', 'Private', 'B'),
(19, 'What is the numerical range of a char data type in Java?\r\n', '0 to 256\r\n', '-128 to 127\r\n', '0 to 65535\r\n', '0 to 32767', 'C'),
(20, 'Which of this keyword can be used in a subclass to call the constructor of superclass?\r\n', 'super\r\n', 'this\r\n', 'extent\r\n', 'extends', 'A'),
(21, 'What is the process of defining a method in a subclass having same name & type signature as a method in its superclass?\r\n', 'Method overloading\r\n', 'Method overriding\r\n', 'Method hiding\r\n', 'None of the mentioned', 'B');

-- --------------------------------------------------------

--
-- Table structure for table `questionpaper`
--

CREATE TABLE `questionpaper` (
  `qp_id` int(10) NOT NULL,
  `f_id` int(10) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `paperfile` varchar(100) NOT NULL,
  `solution_of_paper` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `questionpaper`
--

INSERT INTO `questionpaper` (`qp_id`, `f_id`, `subject`, `paperfile`, `solution_of_paper`) VALUES
(1, 101, 'java', 'Auto_Test1.txt', 'Auto_Solution_Test1.txt'),
(2, 101, 'DS', 'Auto_Test1_DS.txt', 'Auto_Solution_Test1_DS.txt'),
(3, 101, 'java', 'Auto_Test2_java.txt', 'Auto_Solution_Test2_java.txt'),
(4, 101, 'DS', 'Test2_DS.txt', 'Solution_Test2_DS.txt'),
(5, 102, 'DS', 'Auto_Test3_DS.txt', 'Auto_Solution_Test3_DS.txt'),
(6, 103, 'JAVA', 'Auto_NPB_Paper.txt', 'Auto_Solution_NPB_Paper.txt');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `s_id` int(11) NOT NULL,
  `s_pass` varchar(30) NOT NULL,
  `s_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`s_id`, `s_pass`, `s_name`) VALUES
(1, 'abc', 'Shah Khushi Jayeshkumar'),
(2, 'sangita_shah', 'Shah Sangita Jayeshkumar'),
(3, 'sakshi_23', 'Mori Sakshi Riteshbhai'),
(4, 'madhu_11', 'Makwana Madhvi Hiteshkumar');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ds`
--
ALTER TABLE `ds`
  ADD PRIMARY KEY (`q_no`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`exam_id`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`f_id`);

--
-- Indexes for table `java`
--
ALTER TABLE `java`
  ADD PRIMARY KEY (`q_no`);

--
-- Indexes for table `questionpaper`
--
ALTER TABLE `questionpaper`
  ADD PRIMARY KEY (`qp_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`s_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ds`
--
ALTER TABLE `ds`
  MODIFY `q_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `exam_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `faculty`
--
ALTER TABLE `faculty`
  MODIFY `f_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT for table `java`
--
ALTER TABLE `java`
  MODIFY `q_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `questionpaper`
--
ALTER TABLE `questionpaper`
  MODIFY `qp_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
