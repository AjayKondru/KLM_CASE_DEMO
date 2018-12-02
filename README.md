KLM_CASE_DEMO
KLM_CASE_DEMO is REST API which provides data to draw graph of Close rate over time and bar chart showing 
data for close over a period.



Prerequisites:
need to have stock details of Ford Company in following path 
C:/Users/Ajay/Downloads/f.csv

STEPS TO EXECUTE:
Execute KMLDemo class to launch the API
enter following URI to access restful webservices
http://localhost:8080/getCloseOverPeriod?year=1972
http://localhost:8080/getAverageClose?year=1972&month=6&day=7

Year is Mandatory parameter
Month and day are optional parameters.

Technologies Used:
Spring Boot
Spring Restful
Maven
Eclipse
Tomcat Server
H2 DB

Running the tests :
Execute klmControllerTest to Run Junits developed for API.

Authors:
AJAY KONDRU
