-- -- Insert a test user (if not already present)
-- INSERT INTO users (username, password, email)
-- VALUES ('testuser', 'password', 'testuser@example.com');

-- -- Insert a test course (ensure to use a valid user_id)
-- INSERT INTO courses (title, subject_area, description, user_id)
-- VALUES ('Sample Course', 'Mathematics', 'A sample course for testing purposes', 1); -- Adjust user_id as necessary
--
-- -- Insert a test quiz (ensure course_id exists)
-- INSERT INTO quizzes (title, subject_area, course_id)
-- VALUES ('Sample Quiz', 'Mathematics', 1); -- Adjust course_id as necessary

-- -- Insert a test performance record
-- INSERT INTO performance (user_id, quiz_id, score, duration, date_taken)
-- VALUES (1, 1, 85, 120, CURDATE()); -- Adjust user_id and quiz_id as necessary
--
-- -- Insert a test achievement record
-- INSERT INTO achievements (user_id, title, description, date_awarded)
-- VALUES (1, 'Top Scorer', 'Achieved highest score', CURDATE()); -- Adjust user_id as necessary


-- -- Insert Questions
-- INSERT INTO questions (question_text, correct_answer, quiz_id)
-- VALUES
--     ('What is 2 + 2?', '4', 1), -- Adjust quiz_id as necessary
--     ('What is the capital of France?', 'Paris', 1),
--     ('Which is the largest planet in our solar system?', 'Jupiter', 1);
--
-- -- Insert Answers for the first question
-- INSERT INTO answers (text, correct, question_id)
-- VALUES
--     ('3', false, 1),
--     ('4', true, 1),
--     ('5', false, 1),
--     ('6', false, 1);
--
--
--
-- -- Insert Answers for the second question
-- INSERT INTO answers (text, correct, question_id)
-- VALUES
--     ('Berlin', false, 2),
--     ('Madrid', false, 2),
--     ('Paris', true, 2),
--     ('Rome', false, 2);
--
-- -- Insert Answers for the third question
-- INSERT INTO answers (text, correct, question_id)
-- VALUES
--     ('Earth', false, 3),
--     ('Mars', false, 3),
--     ('Jupiter', true, 3),
--     ('Saturn', false, 3);


-- -- Insert Courses
-- INSERT INTO courses (title, subject_area, description, user_id)
-- VALUES
--     ('Physics 101', 'Physics', 'An introductory course on Physics', 1),
--     ('Chemistry Basics', 'Chemistry', 'Basic concepts in Chemistry', 1),
--     ('Intro to Computer Science', 'Computer Science', 'Fundamentals of Computer Science', 1);
--
-- -- Insert Quizzes for Physics
-- INSERT INTO quizzes (title, subject_area, course_id)
-- VALUES
--     ('Newtonian Mechanics', 'Physics', 2),
--     ('Thermodynamics', 'Physics', 2);
--
-- -- Insert Quizzes for Chemistry
-- INSERT INTO quizzes (title, subject_area, course_id)
-- VALUES
--     ('Chemical Reactions', 'Chemistry', 3),
--     ('Organic Chemistry', 'Chemistry', 3);
--
-- -- Insert Quizzes for Computer Science
-- INSERT INTO quizzes (title, subject_area, course_id)
-- VALUES
--     ('Introduction to Programming', 'Computer Science', 4),
--     ('Data Structures', 'Computer Science', 4);


-- Insert Questions and Answers for 'Newtonian Mechanics' (Physics)
-- INSERT INTO questions (question_text, correct_answer, quiz_id)
-- VALUES
--     ('What is the first law of motion?', 'Inertia', 3),
--     ('What is the unit of force?', 'Newton', 3),
--     ('Who formulated the laws of motion?', 'Newton', 3),
--     ('What does F=ma stand for?', 'Force = mass × acceleration', 3),
--     ('What is the unit of mass?', 'Kilogram', 3);

-- INSERT INTO answers (text, correct, question_id)
-- VALUES
--     ('Inertia', true, 14),
--     ('Gravity', false, 14),
--     ('Friction', false, 14),
--     ('Motion', false, 14),
--
--     ('Newton', true, 15),
--     ('Pascal', false, 15),
--     ('Joule', false, 15),
--     ('Watt', false, 15),
--
--     ('Newton', true, 16),
--     ('Einstein', false, 16),
--     ('Galileo', false, 16),
--     ('Tesla', false, 16),
--
--     ('Force = mass × acceleration', true, 17),
--     ('Force = mass / acceleration', false, 17),
--     ('Force = velocity × time', false, 17),
--     ('Force = energy × time', false, 17),
--
--     ('Kilogram', true, 18),
--     ('Gram', false, 18),
--     ('Ton', false, 18),
--     ('Pound', false, 18);

-- -- Insert Questions and Answers for 'Thermodynamics' (Physics)
-- INSERT INTO questions (question_text, correct_answer, quiz_id)
-- VALUES
--     ('What is the first law of thermodynamics?', 'Energy conservation', 4),
--     ('What is the unit of temperature?', 'Kelvin', 4),
--     ('What does entropy measure?', 'Disorder', 4),
--     ('What is the second law of thermodynamics?', 'Entropy increase', 4),
--     ('What is absolute zero?', '0 K', 4);

INSERT INTO answers (text, correct, question_id)
VALUES
    ('Energy conservation', true, 19),
    ('Heat transfer', false, 19),
    ('Work done', false, 19),
    ('Entropy change', false, 19),

    ('Kelvin', true, 20),
    ('Celsius', false, 20),
    ('Fahrenheit', false, 20),
    ('Joule', false, 20),

    ('Disorder', true, 21),
    ('Energy', false, 21),
    ('Temperature', false, 21),
    ('Pressure', false, 21),

    ('Entropy increase', true, 22),
    ('Energy transfer', false, 22),
    ('Work done', false, 22),
    ('Heat transfer', false, 22),

    ('0 K', true, 23),
    ('0 C', false, 23),
    ('273 K', false, 23),
    ('100 C', false, 23);

-- -- Insert Questions and Answers for 'Chemical Reactions' (Chemistry)
-- INSERT INTO questions (question_text, correct_answer, quiz_id)
-- VALUES
--     ('What is a chemical reaction?', 'A process that transforms substances', 5),
--     ('What is the pH of water?', '7', 5),
--     ('What is the chemical formula for water?', 'H2O', 5),
--     ('Who discovered the electron?', 'Thomson', 5),
--     ('What is the unit of atomic mass?', 'AMU', 5);

INSERT INTO answers (text, correct, question_id)
VALUES
    ('A process that transforms substances', true, 24),
    ('A physical transformation', false, 24),
    ('A nuclear reaction', false, 24),
    ('A biological process', false, 24),

    ('7', true, 25),
    ('1', false, 25),
    ('10', false, 25),
    ('14', false, 25),

    ('H2O', true, 26),
    ('CO2', false, 26),
    ('O2', false, 26),
    ('NaCl', false, 26),

    ('Thomson', true, 27),
    ('Rutherford', false, 27),
    ('Bohr', false, 27),
    ('Curie', false, 27),

    ('AMU', true, 28),
    ('Gram', false, 28),
    ('Mole', false, 28),
    ('Kilogram', false, 28);

-- -- Insert Questions and Answers for 'Organic Chemistry' (Chemistry)
-- INSERT INTO questions (question_text, correct_answer, quiz_id)
-- VALUES
--     ('What is an alkane?', 'A saturated hydrocarbon', 6),
--     ('What is the functional group of alcohols?', 'OH', 6),
--     ('What is the simplest alkene?', 'Ethene', 6),
--     ('What is the functional group of carboxylic acids?', 'COOH', 6),
--     ('What is a polymer?', 'A large molecule made of repeating units', 6);

INSERT INTO answers (text, correct, question_id)
VALUES
    ('A saturated hydrocarbon', true, 29),
    ('An unsaturated hydrocarbon', false, 29),
    ('An aromatic hydrocarbon', false, 29),
    ('An alkyne', false, 29),

    ('OH', true, 30),
    ('COOH', false, 30),
    ('NH2', false, 30),
    ('SH', false, 30),

    ('Ethene', true, 31),
    ('Methane', false, 31),
    ('Propane', false, 31),
    ('Butane', false, 31),

    ('COOH', true, 32),
    ('OH', false, 32),
    ('NH2', false, 32),
    ('SH', false, 32),

    ('A large molecule made of repeating units', true, 33),
    ('A small molecule', false, 33),
    ('A complex mixture', false, 33),
    ('An inorganic molecule', false, 33);

-- -- Insert Questions and Answers for 'Introduction to Programming' (Computer Science)
-- INSERT INTO questions (question_text, correct_answer, quiz_id)
-- VALUES
--     ('What is a variable?', 'A storage location for data', 7),
--     ('What is a loop?', 'A control structure that repeats a block of code', 7),
--     ('What is an array?', 'A collection of elements of the same type', 7),
--     ('What is a function?', 'A block of code that performs a task', 7),
--     ('What is a conditional statement?', 'A statement that executes code based on a condition', 7);

INSERT INTO answers (text, correct, question_id)
VALUES
    ('A storage location for data', true, 34),
    ('A fixed value', false, 34),
    ('A function', false, 34),
    ('An object', false, 34),

    ('A control structure that repeats a block of code', true, 35),
    ('A function call', false, 35),
    ('A conditional check', false, 35),
    ('A data type', false, 35),

    ('A collection of elements of the same type', true, 36),
    ('A single element', false, 36),
    ('A function', false, 36),
    ('A loop', false, 36),

    ('A block of code that performs a task', true, 37),
    ('A variable', false, 37),
    ('A data type', false, 37),
    ('A loop', false, 37),

    ('A statement that executes code based on a condition', true, 38),
    ('A loop', false, 38),
    ('A variable', false, 38),
    ('An array', false, 38);

-- -- Insert Questions and Answers for 'Data Structures' (Computer Science)
-- INSERT INTO questions (question_text, correct_answer, quiz_id)
-- VALUES
--     ('What is a linked list?', 'A sequence of elements where each element points to the next', 8),
--     ('What is a stack?', 'A collection where elements are added and removed from one end', 8),
--     ('What is a queue?', 'A collection where elements are added at one end and removed from the other', 8),
--     ('What is a tree?', 'A hierarchical structure of nodes', 8),
--     ('What is a graph?', 'A collection of nodes connected by edges', 8);

INSERT INTO answers (text, correct, question_id)
VALUES
    ('A sequence of elements where each element points to the next', true, 39),
    ('A collection of elements with a random order', false, 39),
    ('A matrix', false, 39),
    ('A set', false, 39),

    ('A collection where elements are added and removed from one end', true, 40),
    ('A collection where elements are added at one end and removed from the other', false, 40),
    ('A random access memory', false, 40),
    ('A linked list', false, 40),

    ('A collection where elements are added at one end and removed from the other', true, 41),
    ('A collection where elements are added and removed from one end', false, 41),
    ('A tree', false, 41),
    ('A graph', false, 41),

    ('A hierarchical structure of nodes', true, 42),
    ('A sequence of elements', false, 42),
    ('A stack', false, 42),
    ('A queue', false, 42),

    ('A collection of nodes connected by edges', true, 43),
    ('A tree', false, 43),
    ('A list', false, 43),
    ('A queue', false, 43);
