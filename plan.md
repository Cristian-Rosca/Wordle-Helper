## Planning & Schedule

Date  | Tasks
------------- | -------------
**Sunday (20/02)** | _Whiteboard group planning against Requirements:_ <br><br> POJOs, Controller Layer, Service Layer, DAO Layer, Tables <br><br>
**Monday (21/02)** | Create ER Diagram <br><br> _Version 1:_ <br><br> • (Re)build version 1 of wordle solver (mob programming) <br> • Write tests for methods in version 1 (pair programming) <br><br>
**Tuesday (22/02)**  | _Version 2:_ <br><br> • DAO Layer to access DB  <br> • Method to import from CSV to words table <br> • Integrate with wordle database <br><br> Integration of an API (Controller), to send HTTP requests to progress game of Wordle <br><br> Testing of all methods in Helper Mode <br><br>
**Wednesday (23/02)** | _Work on Competitive Mode:_ <br><br> • Have post request to allow users to create User Object (POJO), and add this to database <br> • Create SQL tables for games played, users, and actual answers <br> • Take the actual answers from the Wordle website, and match them to the date on the txt (csv) file <br> • Re-use the function to import from txt (csv) file <br> • Create new controller class for users <br> • Create users package <br> • Create user DAO and Data Access Service <br> • Creater user service <br> • Add methods - e.g., playFullGame (returns number of guesses for the computer) <br> • In DataAcessService, include method which uses Join <br> • Add custom exception <br><br>
**Thursday (24/02)** | _To Do:_ <br><br> • Finish testing <br> • Clean up code <br> • Plan presentation <br><br> Presentation dry-run <br><br>
**Friday (25/02)** | Client presentations <br><br>