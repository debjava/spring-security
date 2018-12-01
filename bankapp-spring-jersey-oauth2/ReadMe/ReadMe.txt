(C) Completed
---------------
This project aims at use of oauth2.0 in Spring Security along with Jersey with all latest versions of 
Spring security, Spring Core, other Spring library, Spring-Jersey and Jersey frameworks

From this project, you will learn
1) Spring Security with OAuth 2.0
2) Restful web service with OAuth 2.0
3) Jersey framework
4) Spring Jersey Integration
5) Maven build with Maven 3.2
6) Tomcat web server with 8.0.5


To get authentication token

GET
http://localhost:8080/bankapp-spring-jersey-oauth2/oauth/token?grant_type=password&client_id=restapp&client_secret=restapp&username=piku&password=piku

Response
--------
{"access_token":"16ce6fc2-f3e1-4842-a2d9-79b5bd26de11","token_type":"bearer","refresh_token":"eb1d2526-f54f-444b-8d3f-b515de5627c0","expires_in":119}


GET
http://localhost:8080/bankapp-spring-jersey-oauth2/api/1/bankingservices/userid?id=123

Header
------
Authorization

bearer 16ce6fc2-f3e1-4842-a2d9-79b5bd26de11

From the above request use the access_token



To Logout
GET
http://localhost:8080/bankapp-spring-jersey-oauth2/logout

Header
------
Authorization

bearer 16ce6fc2-f3e1-4842-a2d9-79b5bd26de11

From the above request use the access_token

To get refresh token

GET

http://localhost:8080/bankapp-spring-jersey-oauth2/oauth/token?grant_type=refresh_token&client_id=restapp&client_secret=restapp&refresh_token=eb1d2526-f54f-444b-8d3f-b515de5627c0

From the above response, use the refresh_token