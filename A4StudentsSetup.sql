CREATE TABLE students 
	(student_id			SERIAL,
	 first_name			TEXT	NOT NULL,
	 last_name			TEXT	NOT NULL,
	 email				TEXT 	UNIQUE NOT NULL,
	 enrollment_date	DATE,
	 PRIMARY KEY (student_id)
	);
	
insert into students (first_name, last_name, email, enrollment_date) values
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02')