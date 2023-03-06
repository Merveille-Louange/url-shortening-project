# url-shortening-project

Running the project.

1. Running the angular application
 navigate to the folder shortly-app from your cmd and run
    
   npm i --save.
once the installations is complete, run 

  ng serve to lauch the application on your localhost port 4200

2. Running the Spring boot service.

navigate to the shorten-service folder inside your project folder.

to be able to run and test the spring boot service, you need to install redis DB.
to run redis on a docker container:
run 

docker run --volume=/data --workdir=/data -p 6379:6379 -d redis:latest

once redis is fully started, you can run the project from your IDE by selecting the main class and clicking run or from the terminal by running:

mvn spring-boot:run



Note: in your application.properties file, ensure the following

spring.application.baseurl: is your service base url and 
application.default.redirecturl: is your web application url

if your application is not running on localhost:8080,

Ensure the following change in your angular app:
open src/app/services/wrapper/http.service.ts

change the config for apiBase to your service base url. 
save the file.