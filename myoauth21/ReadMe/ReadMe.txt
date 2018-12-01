http://localhost:8080/myoauth21/oauth/token?grant_type=password&client_id=my-trusted-client-with-secret&client_secret=somesecret&username=user&password=password

POST

Response
{
  "access_token": "12476b17-f821-4fb3-9ef6-530b18ea477d",
  "token_type": "bearer",
  "refresh_token": "d04a6e8c-f278-4ad3-a050-5ab63a164c81",
  "expires_in": 43199
}

Use the above access_token value
http://localhost:8080/myoauth21/test/ateam?access_token=12476b17-f821-4fb3-9ef6-530b18ea477d

GET

or

http://localhost:8080/myoauth21/test/ateam

Use as Header below.
Authorization

bearer 12476b17-f821-4fb3-9ef6-530b18ea477d

To Logout
http://localhost:8080/myoauth21/test/logout?access_token=ae69ea11-7e83-4e1c-b2b9-cac728e4b3d0

http://localhost:8080/myoauth21/logout

Authorization

bearer 12476b17-f821-4fb3-9ef6-530b18ea477d