create table students
	(student_id		SERIAL PRIMARY KEY,
	 first_name		Text Not Null,
	 last_name		Text Not Null,
	 email			Text Not Null Unique,
	 enrollment_date	Date
	);