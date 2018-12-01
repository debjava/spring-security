http://www.e-zest.net/blog/rest-authentication-using-oauth-2-0-resource-owner-password-flow-protocol/


http://localhost:8080/demo.rest.springsecurity.oauth2.0.authentication/oauth/token?username=user1&password=user1&client_id=client1&client_secret=client1&grant_type=password


http://localhost:8080/demo.rest.springsecurity.oauth2.0.authentication/resources/MyResource/getMyInfo

To Logout

http://localhost:8080/demo.rest.springsecurity.oauth2.0.authentication/logout

=========================================================================================================
/oauth2-1


POST

http://localhost:8080/oauth2-1/oauth/token?username=user1&password=user1&client_id=client1&client_secret=client1&grant_type=password


GET

http://localhost:8080/oauth2-1/resources/emp/info/123

Header
-------
Authorization

bearer 027a8a54-2c30-4ee0-bdf3-89ab863ceb9e


http://localhost:8080/oauth2-1/logout
Header
-------
Authorization

bearer 027a8a54-2c30-4ee0-bdf3-89ab863ceb9e



