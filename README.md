# chatGPT_API
Java Microservice for Querying the ChatGPT API
This project is a Java microservice that queries the OpenAI ChatGPT API and retrieves the answer to a question provided by the user.
Prerequisites
•	Java 17
•	Maven
•	Spring boot 3

for running the project :

clone this project via this link : 
extracte the zip file on your machine
import the project in an Spring boot IDE like esclipse or intellij 

before to run this project you should :
 - create an account on chatGPT, generate an API KEY and copy it.
 - go to application.properties file and paste this key  in the variable openai.api_key
 - run the mysql server in Xampp application on your machine
 - then go to  http://localhost/phpmyadmin and create a database with this name "chatgpt".
 
 now you can run the microservice, go to postmen application
 tape this route: localhost:8080/completion and you can give a question in JSON format in the request body:
 
 example of a request :
 {
"prompt": "What is gluten sensitivity?",
}

after you can verify the file CSV, if the question;answer has been added?
 
 
 
 
