
DROP DATABASE IF EXISTS gradingDB;
CREATE DATABASE gradingDB;
USE gradingDB;

CREATE TABLE students(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(40) NOT NULL,
password VARCHAR(40) NOT NULL
);


INSERT INTO students(id, name,password)
VALUES(1000, "Mammon Ahmad","1111");

insert into students(name,password) 
values("Amar Qasem","2222"),
("Ali Mohmmad","3333"),
("Yamen Hassan","4444");

CREATE TABLE courses(
id INT PRIMARY KEY,
name VARCHAR(40) NOT NULL
);

INSERT INTO courses(id, name)
VALUES(100, "english"),
(200, "math"),
(300, "JAVA");

CREATE TABLE enrollment(
student_id INT,
course_id INT,
grade INT,
FOREIGN KEY (student_id) REFERENCES students(id),
FOREIGN KEY (course_id) REFERENCES courses(id)
);

INSERT INTO enrollment(student_id,course_id,grade)
VALUES(1000, 100,98),
(1000, 200,94),
(1000, 300,99),
(1001, 100,80),
(1001, 200,90),
(1002, 300,70),
(1002, 100,50),
(1003, 100,88),
(1003, 200,74),
(1003, 300,69);



/*
select students.name,courses.name as course,enrollment.grade 
from students,courses,enrollment
where enrollment.student_id=students.id and enrollment.course_id=courses.id and enrollment.course_id=200;

select courses.name as course,,enrollment.grade 
from students,courses,enrollment
where enrollment.student_id=students.id and enrollment.course_id=courses.id and students.id=1000;

select students.name as student_name,courses.name as course,enrollment.grade 
from students,courses,enrollment
where enrollment.student_id=students.id and enrollment.course_id=courses.id and enrollment.course_id=200 and students.id=1002;

*/

