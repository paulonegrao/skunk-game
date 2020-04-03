# SKUNK Game

## Description
This is a Java version os SKUNK Game using Java servlets and SSE (Server-Side Events) technology to enable clients (web and mobile) to receive automatic updates from a server via HTTP connection. All clients can interact with the app (select/change tokens' properties/positions, roll dices, etc.). Every interaction will be intercepted by the server, broadcasted to the whole group of clients, and reflected/updated on each individual client browser.

![skunkgame](https://github.com/paulonegrao/skunk-game/blob/master/src/main/webapp/images/skunkgame.png?raw=true)

## Main components
The app (SkunkGame) has the following components:
1. SkunkGame.java: class that controls the main components/rules of the game and also handles all connections and messages between the server and its multiple clients (browsers);
1. Player.java: class that represents each individual player with methods to perform the player/tokens movements in the game;
1. Dice.java: class that simulates all the necessary dice for the game;
1. DiceRotation: class that controls all the possible dice rotations/results;
1. Score.java: class that handles the individual and total scores of the game;
1. index.jsp: a unique JSP to receive the movements/instructions from the Player.java  class and render the graphic interface presentation accordingly.

## SSE technology
The application is a "many clients to server" configuration, hosted in a Heroku.com dyno, and It is using SSE (Server Sent Events) to be able to perform a "broadcasting transaction", making all the clients(browsers) be simultaneously updated in real time.

## Accessing a Heroku Demo of the game
To access the app: http://skunkgame.herokuapp.com/  
Please, it has to be "http" ok? If you try https it will not work for this prototype version (I a not accepting SSL connections also to avoid the overhead of encryption). 
I didn't have time to insert instructions during the game (sorry), but it is resembling the game like performed in class, so it is very intuitive.

Any questions/suggestions will be always welcome. 
Thank you,
Cheers!

## To start the SKUNKGAME app on tomcat:
sh target/bin/webapp

## To run the app on localhost
http://localhost:8080

## To Check PID running on port 8080
lsof -i :8080

## To kill process
kill -9 `<PID>`
