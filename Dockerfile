FROM adoptopenjdk/openjdk8

COPY build/libs/inTime-0.0.5.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]

# Build the image
# docker build -t jdk8-spring-image .
# docker run -ti --rm jdk8-spring-image /bin/bash

# Create (and run) the container
# docker run -d -v /Users/alexandre/Sites/new_sid:/var/www/html -p 8080:80 --name new_sid --hostname server_on_docker jdk8-spring-image
# or
# docker-compose up -d

# Stop the container
# docker-compose down

