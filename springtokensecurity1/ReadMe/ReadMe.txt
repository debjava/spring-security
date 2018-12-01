(C) Completed

POST
http://localhost:8080/springtokensecurity1/rest/user/authenticate

Body
username=piku&password=piku

Response
{"token":"piku:1434829080094:25794ffe9e1736b46aa1c225a151f857"}

GET

http://localhost:8080/springtokensecurity1/rest/user

Header
------
X-Auth-Token

piku:1434829290357:90399f46fbd41231f4d927e7a5ce9cff

To Logout
http://localhost:8080/springtokensecurity1/rest/user/logout

Header
------
X-Auth-Token

piku:1434829290357:90399f46fbd41231f4d927e7a5ce9cff