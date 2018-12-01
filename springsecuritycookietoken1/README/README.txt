POST
http://localhost:8090/securedweb1/api/1/authservices/authenticate

username=piku&password=piku

GET 
http://localhost:8090/securedweb1/api/1/bankingservices/userid?id=123

X-Auth-Token : piku:1495317439680:3a4d1fe6b1ddf8efa83c0d84d0e97325

POST
http://localhost:8090/securedweb1/api/1/authservices/logout
X-Auth-Token : piku:1495317439680:3a4d1fe6b1ddf8efa83c0d84d0e97325

POST
http://localhost:8090/securedweb1/api/1/authservices/login?j_username=d&j_password=d

GET
http://localhost:8090/securedweb1/api/1/bankingservices/userid?id=123

GET
http://localhost:8090/securedweb1/api/1/authservices/logout

For Web Access
===============
http://localhost:8090/securedweb1

http://localhost:8090/securedweb1/api/1/bankingservices/info
