# RestaurantReservations

## Installation Steps

* Extract files in a any folder 
* Edit src/main/resources/application.properties file and put your database configuration
* Edit Flyway (database migration tool) configuration in the end of pom.xml file and put your database configuration
* Make sure database configuration in application.properties and pom.xml files are the same
* Run Migration Command: mvn flyway:migrate
* Make Sure the backend url is the same one in the frontend
* Admin User credentials => email : admin@admin.com , password: admin
* Run The project

Thats it
Cheers
