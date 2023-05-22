#Base Image
FROM maven:latest AS build

WORKDIR /ecommerce

#Copy pom file
COPY pom.xml pom.xml

#Install dependency
RUN mvn dependency:go-offline

#Copy all file
COPY src/ ./src

# Build the application using maven
RUN mvn clean package

#COPY mysql-connector-j-8.0.32.jar /app/lib

CMD ["java", "-jar", "target/ecommerce-1.0-SNAPSHOT.jar"]

