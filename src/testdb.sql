-- -- Insert a test user (if not already present)
-- INSERT INTO users (username, password, email)
-- VALUES ('testuser', 'password', 'testuser@example.com');

-- -- Insert a test course (ensure to use a valid user_id)
-- INSERT INTO courses (title, subjectArea, description, user_id)
-- VALUES ('Sample Course', 'Mathematics', 'A sample course for testing purposes', 1); -- Adjust user_id as necessary
--
-- -- Insert a test quiz (ensure course_id exists)
-- INSERT INTO quizzes (title, subjectArea, course_id)
-- VALUES ('Sample Quiz', 'Mathematics', 1); -- Adjust course_id as necessary

-- -- Insert a test performance record
-- INSERT INTO performance (user_id, quiz_id, score, duration, dateTaken)
-- VALUES (1, 1, 85, 120, CURDATE()); -- Adjust user_id and quiz_id as necessary
--
-- -- Insert a test achievement record
-- INSERT INTO achievements (user_id, title, description, dateAwarded)
-- VALUES (1, 'Top Scorer', 'Achieved highest score', CURDATE()); -- Adjust user_id as necessary
