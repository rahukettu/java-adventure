#Adventure game

An adventure game made with SpringBoot and Maven. Made without UI, works with HTTP GET and POST requests.
After building the project, the game runs at http://localhost:8080 and you can make requests with curl, Postman, etc.

##Requests
```
/       GET  gameplay instructions
/start  GET  starts geme
/name   POST player name input (name?name=[name])
/weapon POST player weapon input (weapon?weapon=[weapon])
/play   GET  gameplay (repeat this request to play)
```
