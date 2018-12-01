(C) Completed
Token based authentication in Spring security
=============================================

1) Works in Tomcat 8.0.5
2) Spring Security
3) Spring Core 4.0.7
4) Jersey

http://localhost:8080/token-based-bank-app/api/1/bankingservices/authenticate
Header

Content-Type

application/x-www-form-urlencoded

Body
----
username=piku&password=piku

GET
http://localhost:8080/token-based-bank-app/api/1/bankingservices/userid?id=123

Header
------
X-Auth-Token

piku:1445585527578:8930995ab65f2c084a1a5fecf3635312

and 

Accept

application/json


To Logout
---------
GET
http://localhost:8080/token-based-bank-app/api/1/bankingservices/logout

Header
------
X-Auth-Token

piku:1434829290357:90399f46fbd41231f4d927e7a5ce9cff

