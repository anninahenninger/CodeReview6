SELECT * FROM student WHERE student.classID = 2;

SELECT * FROM student JOIN classes USING (classID) WHERE classes.className = "1a"; 