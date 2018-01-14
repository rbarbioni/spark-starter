# Spark-Starter-Project
Spark Framewok Demo and Start Project

### HOW-TO-BUILD

```
./gradlew build
```

### HOW-TO-TEST

```
./gradlew test
```

### HOW-TO-RUN

After build execute command:
```
java -jar build/libs/spark-starter-1.0-SNAPSHOT.jar
```

### REST API

GET     -> [http://localhost:4567/product](http://localhost:4567/product)

GET     -> [http://localhost:4567/product/:id](http://localhost:4567/product/:id)

POST    -> [http://localhost:4567/product](http://localhost:4567/product)
```
{
	"name": "playstation",
	"price": 1000
}
```

PUT     -> [http://localhost:4567/product/:id](http://localhost:4567/product/:id)

```
{
	"name": "playstation",
	"price": 1000
}
```

DELETE  -> [http://localhost:4567/product/:id](http://localhost:4567/product/:id)