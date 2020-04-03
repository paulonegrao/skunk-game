## SKUNK Game
# This is a Java version os SKUNK Game using Java servlets and SSE (Server-Side Events) technology to enable clients (web and mobile) to receive automatic updates from a server via HTTP connection. All clients can interact with the app (select/change tokens' properties/positions, roll dices, etc.). Every interaction will be intercepted by the server, broadcasted to the whole group of clients, and reflected/updated on each individual client browser.

# To start the skunkgame app on tomcat:
sh target/bin/webapp

# To run the app on localhost
http://localhost:8080

#Check PID running on port 8080
lsof -i :8080

#kill process
kill -9 <PID>
