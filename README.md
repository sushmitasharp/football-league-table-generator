# Football League Table Generator API
This is a SpringBoot Application for generating football League table dynamically.

## Endpoints
It provides the following REST API endpoints:

#### POST:
+ /league/matches

#### GET:
+ /league/matches
+ /league-table

#### DELETE:
+ /league/matches

### How to generate the league table dynamically using the above provided REST API endpoints:

For generating the League table, first the list of completed matches needs to be added to the repository (in memory storage).The league table will be generated based on the list of completed list of matches present in the repository at a given time.
###### (NOTE: If you do not want to append to the existing list  of matches that are already present in the repository for generating the league table, please clear the repository first)

+ Add the list of completed football matches using :
  *  POST /league/matches

+ To get the list of matches that is currently present in the repository :
  *  GET /league/matches

+ Finally to view the league table that has been generated based on the added list of matches in the previous steps use:
  * GET /league-table

+ To clear the list of matches from the repository, for which the league table is generated use:
  * DELETE /league/matches

#### Rest API Documentation

Once the application is started please go to the link below for more information on the Rest API endpoints:
+ http://localhost:8080/swagger-ui.html#/

## How to run:
#### To run the unit tests
+ mvn clean install

#### To start the application locally
+ cd football-league-table-generator
+ mvn clean spring-boot:run
# football-league-table-generator
