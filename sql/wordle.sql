CREATE TABLE original_word_list (
    id SERIAL PRIMARY KEY,
    word VARCHAR(5),
    probability numeric,
    score numeric
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50), UNIQUE
    email VARCHAR(50), UNIQUE
    username VARCHAR(50) UNIQUE 
);

COPY original_word_list (word, probability, score) FROM
'/Users/oleksiysmola/IdeaProjects/Wordle-Helper/initialcalculations.txt' DELIMITER ',' CSV;


--Actual answers table--
CREATE TABLE actual_answers (
    id SERIAL PRIMARY KEY,
    date_of_given_answer date UNIQUE,
    actual_word VARCHAR(5),
    machine_guesses int
);

COPY actual_answers (dateOfGivenAnswer, actualword) FROM
'/Users/suad/Downloads/date-word-answer.txt' DELIMITER ',' CSV;
