# <i>New York Food Stores</i>


### <u>GitHub repository</u>

* [New York Food Stores on GitHub](https://github.com/Damnjan998/NewYorkFoodStores)


### <u>Technologies</u>
* [Java 17]()
* [ElasticSearch]()
* [JUnit5]()
* [MapStruct]()
* [Lombok]()
* [OpenCsv]()
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data ElasticSearch](https://docs.spring.io/spring-boot/docs/3.0.3/reference/htmlsingle/)


### <u>Introduction</u>

NewYorkFoodStores is an API that handles 3 endpoints.</br>
GET Endpoint for fetching list of the closest food stores for given latitude and longitude.</br>
GET Endpoint for fetching list of stores with a given partial entity name or address.</br>
POST Endpoint for populating ElasticSearch db with data given in CSV file.


### <u>Curl Commands</u>

#### Get curl command for finding stores by entity name or address name containing
curl 'http://localhost:8080/api/store/name_address?condition=grant' </br>

#### Get curl command for finding the closest stores for given latitude and longitude
curl 'http://localhost:8080/api/store/geo_distance?lat=-72&lon=40&distance=100&unit=km' </br>

#### Post curl command for populating ElasticSearch
curl 'http://localhost:8080/api/store/'


### <u>Instructions</u>
First of all, you have to generate your own password for ES if you don't have one. Then you have to open application.yml file
and set it to password field. After starting the app you have to execute POST request to populate and create your DB.
For the other two endpoints you have query params. Both of them have page and size query param for pagination where default value for page 
is 1 and for size is 20. </br></br> Get request for finding the closest store you have to provide latitude, longitude, distance and unit.
Latitude must be between -90 and 90, while longitude must be between -180 and 180. Distance must be greater than 0 and unit
can be Inches("in"), Feet("ft"), Yards("yd"), Miles("mi"), NauticMiles("nmi"), Kilometers("km"), Meters("m"), Centimeters("cm"), Millimeters("mm"). </br> </br>
For endpoint where you get list of stores that has given condition in entity name or address, there is query param called 
condition, and it must be present. </br></br>


## Author
#### Damnjan Askovic



