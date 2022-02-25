# Wordle Helper Back-End Group Project

We are The Imitation Game, a team consisting of BNTA Cohort 4 members, [Alex](https://github.com/oleksiysmola), [Cristian](https://github.com/Roscaaa), [Suad](https://github.com/suadali), [Rosalinda](https://github.com/rosaacodes), and [Rachel](https://github.com/rkaurb).

<!-- ![img](wordlehelper.png) -->
<img src="wordlehelper.png" width=60% height=60%>
<!-- What we've created, what inspired it -->

<!-- Include Tech Stack - how client-side was built -->

## Introduction

This collaborative project consists of a Wordle solver application developed based on the concepts of information theory utilised within a YouTube video created and shared on [3Blue1Brown’s channel by Grant Sanderson](https://www.youtube.com/channel/UCYO_jab_esuFRV4b17AJtAw). The purpose of this Wordle solver application is to help the user guess the five letter word of the day in just a few tries!

* [Solving Wordle using information theory](https://www.youtube.com/watch?v=v68zYyaEmEA)

* [Possible Words](https://gist.github.com/Brystephor/c7fde59e673534dcb4d687243195b544)


## Step-by-step instructions for set up

1. Make sure that you have installed [Java](https://jdk.java.net/17/) and [PostgresQL](https://www.postgresql.org/)
   <br><br>
2. Clone this repository:
   ``git clone git@github.com:https://github.com/Roscaaa/Wordle-Helper.git`` and open in your favourite Java IDE (we recommend [IntelliJ](https://www.jetbrains.com/idea/download/#section=mac))
   <br><br>
2. Create a new PostgresQL database called ``wordle`` <br> (If using the terminal, type ``psql`` to launch PostgresQL, and then run ``CREATE DATABASE wordle;``)
   <br><br>
3. Use the ``worlde.sql`` script to populate your 'wordle' database tables. To do this, you can either:
    * copy and paste each command individually, in sequence, using the terminal; _or_
    * execute the entire sql script using Postico or similar database client
      <br>

_**Note** — the DROP TABLES commands at the beginning of the sql script are not necessary if setting up database for the first time (see below):_
```sql
DROP TABLE IF EXISTS original_word_list CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS actual_answers CASCADE;
DROP TABLE IF EXISTS all_games CASCADE;
```

_**Also Note** — For the below commands, make sure to replace with the correct file paths:_

```sql
-- Insert data into original word list table
COPY original_word_list (word, probability, score) FROM
'/[insert file path here]/initialcalculations.txt' DELIMITER ',' CSV;
```
```sql
-- Insert actual answers and respective dates data into actual answers table
COPY actual_answers (date_of_given_answer, actual_word) FROM
'/[insert file path here]/date-word-answer.txt' DELIMITER ',' CSV;
```

**And a final Note** — The actual_answers table may take some time to populate, as it computes the machine scores for all of the ~3,000 actual answers!


## Project Structure

### Entity Relationship (ER) Diagram

![img](erdiagram.png)

### Plain Old Java Objects (POJOs)
In this section, the POJOs used in the project are listed along with their properties and related JSON structure when using POST and PUT request.

HTTP Request  | Type                                                                                               | Function
------------- |----------------------------------------------------------------------------------------------------| -------------
**Word** | • Integer ID <br> • String Word <br> • Double Probability <br> • Double Score                      | N/A
**User**  | • Integer ID <br> • String Name <br> • String Email <br> • String Username                         | {<br>"name": "Suad",<br>"email": "suad@tig.com",<br>"userName": "SusuTheFlowerPot"<br>}
**Answer**  | • Integer ID <br> • LocalDate DateOfAnswer <br> • String AnswerOfDay <br> • Integer MachineResults | {<br>"dateOfAnswer": "2025-01-04",<br>"answerOfDay": "waist",<br>"machineResult": 3<br>}
**Game** | • Integer ID <br> • Integer UserId <br> • Integer AnswerId <br> • Integer UserGuesses              | {<br>"userId": 1,<br>"answerId": 1,<br>"userGuessed": 3<br>}


## How to use Wordle Helper

### Methods
### _Word Service_ &nbsp; (``WordService``)
&nbsp;&nbsp;&nbsp;&nbsp;This service class invokes the data access layer to retrieve
``Word`` objects from the database. In addition, it also contains
methods pertaining to the logic of the Wordle solver itself.
##### ``.GetAllWords()``
&nbsp;&nbsp;&nbsp;&nbsp;Return a list of all entries in the original_word_list table
as ``Word`` objects.
##### ``.GetAllWordsRankedByScore()``
&nbsp;&nbsp;&nbsp;&nbsp;Same as ``.GetAllWords`` except the list of ``Word`` objects is ranked
by the score property.
##### ``.GetAllWordsRankedByScore(Integer numOfWords)``
&nbsp;&nbsp;&nbsp;&nbsp;Same as ``.GetAllWordsRankedByScore`` except the list of ``Word`` objects has a
size given by ``numOfWords``.
##### ``.GetWordById(Integer id)``
&nbsp;&nbsp;&nbsp;&nbsp;Retrieves ``Word`` with given ``id`` from the
original_word_list table.
##### ``.GetWordByName(String nameOfWord)``
&nbsp;&nbsp;&nbsp;&nbsp;Retrieves first ``Word`` whose ``word`` property equals ``nameOfWord`` from the
original_word_list table.
##### ``.WordValidator(String word)``
&nbsp;&nbsp;&nbsp;&nbsp;Returns ``true`` if given string is included in the word column
in the original_word_list table, otherwise an exception is thrown.
##### ``.setUniformProbabilities(List<Word> wordList)``
&nbsp;&nbsp;&nbsp;&nbsp;Returns input list of ``Word`` objects after
setting the probability property of each ``Word`` to
all be equal and sum to unity. As an example a list consisting
of two ``Word`` objects will be returned with probabilities
both set to ``0.5``.
##### ``.GenerateWordPattern(Word word, Word targetWord)``
&nbsp;&nbsp;&nbsp;&nbsp;Returns a ``LinkedHashMap<String, String>`` representing the
pattern obtained by guessing ``word`` assuming ``targetWord``
is the answer.
![alt Example of possible Wordle pattern](patternexample2.png "Title")
As an example, the Wordle pattern depicted here
would be represented by the map:
```json
{
  "f0": "yellow",
  "o1": "yellow",
  "o2": "green",
  "d3": "grey",
  "s4": "green"
}
```
In general, keys are denoted with the letter followed by the
index marking its position in the word. The values take three
values ``"yellow"``, ``"green"`` and ``"grey"`` each representing
the colour of the letter in the Wordle pattern.
##### ``.CheckPatternMatch(Word word, Word targetWord, LinkedHashMap<String, String> pattern)``
&nbsp;&nbsp;&nbsp;&nbsp;Returns ``true`` if ``word`` and ``targetWord`` can reproduce
``pattern`` when passed as arguments of the ``.GenerateWordPattern``
method. Otherwise, ``false`` is returned.
##### ``findMatchingWords(Word guess, List<Word> wordList, LinkedHashMap<String, String> pattern)``
&nbsp;&nbsp;&nbsp;&nbsp;Returns a list of ``Word`` objects consisting of the words in
``wordList`` that return ``true`` when passed as the ``targetWord``
argument of ``.checkPatternMatch``. Here ``guess`` and ``pattern`` serve
as the other arguments.
##### ``.computePatternProbability(Word guess, List<Word> wordList, LinkedHashMap<String, String> pattern)``
&nbsp;&nbsp;&nbsp;&nbsp;For a given ``guess``, compute a ``Double`` whose value represents the
probability that the given ``pattern`` is obtained. This is determined
through the sum of the probabilities of all ``Word`` objects in
``wordList`` that give return ``true`` when entered as the ``targetWord``
argument for ``.checkPatternMatch``.
##### ``.logTwo(Double value)``
&nbsp;&nbsp;&nbsp;&nbsp;Returns the ``Double`` whose value is the log base two of the argument.
##### ``.computeWordScore(Word word, List<Word> wordList)``
&nbsp;&nbsp;&nbsp;&nbsp;Computes the average of the number of times one expects ``wordList``
to be halved in size by guessing ``word``.
##### ``.computeScoreDistribution(List<Word> wordList)``
&nbsp;&nbsp;&nbsp;&nbsp;Applies the ``.computeWordScore`` method to each ``Word`` in ``wordList``
and sets the ``score`` property to the value returned by the method.
The resulting list of ``Word`` objects is then returned.
##### ``getGuessesForAnswer(Answer answer)``
&nbsp;&nbsp;&nbsp;&nbsp;Computes number of guesses taken for the Wordle solver to guess
``answer``. The ``answer`` is then returned with the ``machineResult``
property set to the computed value.


### _AnswerService_ &nbsp; (``AnswerService``)
&nbsp;&nbsp;&nbsp;&nbsp;This service class invokes the data access layer to retrieve and update
``Answer`` objects from the database.

##### ``.getAllAnswers()``
&nbsp;&nbsp;&nbsp;&nbsp;Return a list of all entries in the actual_answers table
as ``Answer`` objects.

##### ``.doesAnswerWithIdExists(Integer id)``
&nbsp;&nbsp;&nbsp;&nbsp;Returns ``true`` if Answer object with the id exists in actual_answers table

##### ``.getAnswerById(Integer id)``
&nbsp;&nbsp;&nbsp;&nbsp;Returns Answer object with the corresponding id from actual_answers table

##### ``.addAnswerToTable(Answer answer)``
&nbsp;&nbsp;&nbsp;&nbsp;Takes ``Answer`` object and adds to actual_answers table

##### ``.deleteAnswerById(Integer id)``
&nbsp;&nbsp;&nbsp;&nbsp;Deletes ``Answer`` object with corresponding id from actual_answers table from argument

##### ``.updateAnswerById(Integer id, Answer answer)``
&nbsp;&nbsp;&nbsp;&nbsp;Updates ``Answer`` object with corresponding id from actual_answers table with ``Answer`` object passed in argument


### HTTP Requests

### _Primary Helper Mode Requests_

HTTP Request  | Type   | Function
------------- |--------| -------------
localhost:8080/helper | GET    | Get all words
localhost:8080/helper/ranked | GET    | Get all words ranked by score
localhost:8080/helper/ranked/{numOfWords} | GET    | Get all words ranked by score and specify how many words returned
localhost:8080/helper/start | GET    | Start the game. Will return best guesses ordered by score
localhost:8080/helper/start/{word} | DELETE | Input your guess for {word} and include the pattern that you got from Wordle in request body as JSON (i.e: which letters were green, yellow, grey)
localhost:8080/helper/endgame | DELETE | Ends game when you got the correct word

### _Secondary Helper Mode Requests_

HTTP Request  | Type | Function
------------- |------| -------------
localhost:8080/helper/wordbyid/{id} | GET  | Get word by word id
localhost:8080/helper/wordbyname/{nameofword} | GET  | Get word by word name

### _Primary Competitive Mode Requests_

HTTP Request  | Type   | Function
------------- |--------| -------------
localhost:8080/competitive/computemachinescores | PUT    | Required to run as part of Competitive Mode setup. This will populate machine guesses in actual_answers table
localhost:8080/competitive/all | GET    | Get all games in database
localhost:8080/competitive/addgame | POST   | Add new game (JSON) to database using Request Body 
localhost:8080/competitive/dailyresults/{date}| GET    | Get all result on given date
localhost:8080/competitive/userresults/{username}/{date}| GET    | Get all result on for username given date
localhost:8080/competitive/userresults/{username}| GET    | Get all result on for username
localhost:8080/competitive/averageresults/{username}| GET    | Get the average of a user's guesses
localhost:8080/competitive/start/{userId}| GET    | Start a game for a user with matching id
localhost:8080/competitive/start/{userid}/{guess}| DELETE | Input the user's guessed in {guess}. Repeat for each guess until game is complete
localhost:8080/competitive/start/{userid}/end| POST   | End game for user and save result to database
localhost:8080/user| GET    | Get all users from database
localhost:8080/user/{userId}| GET    | Get user from database by id
localhost:8080/user| POST   | Add user (JSON)  to database using Request Body

### _Secondary Competitive Mode Requests_

HTTP Request  | Type   | Function
------------- |--------| -------------
localhost:8080/competitive/{id} | PUT    | Update game (JSON) by id through Request Body
localhost:8080/competitive/{id} | DELETE | Delete game by id
localhost:8080/competitive/{id} | GET    | Get game by id
localhost:8080/answers | GET    | Get all answers
localhost:8080/answers/{id} | GET    | Get an answer by answer id
localhost:8080/answers/addanswer | POST   | Add answer (JSON) using Request Body
localhost:8080/answers/{id} | DELETE | Delete answer by id
localhost:8080/answers/update/{id} | PUT    | Update answer (JSON) by id using Request Body 
localhost:8080/user/{userId} | DELETE | Delete user by id
localhost:8080/user/{userId} | PUT    | Update user (JSON) by id using Request Body


## Project Scoping

### Minimum Viable Product (MVP)

**Must-haves:**

_Helper Mode_
* Service logic
* Integration of an API (Controller), to send HTTP requests to progress game of Wordle
* Integration with database containing the words (DAO interface with database implementation)
* SQL file, with instructions to set up database and tables
* Text (csv) file including full list of words, together with probabilities and scores
* A Class which populates the Words table with data from text (csv) file

_Competitive Mode_
* Allow user to create User POJOs
* Create a user in database
* Allow user to input their number of guesses for a certain date
* Allow users to compare their performance vs the computer, for a certain date
* Allow user to retrieve their average guesses
* Play full game method (retrieve computer guess)


### Potential Extensions to MVP

**Nice-to-haves (if time):**

* Leaderboard
* Authentication
* Test Edge Cases

**Stretch objective:**

* Integrate with Wordle - web-scrape number of guesses for user to win (not relying on self-reporting)


## Acknowledgements

A huge thanks to the [BNTA](https://techacademy.brightnetwork.co.uk/) team, and especially to our trainers, [Colin](), [Nelson](), and [Iain]()!






