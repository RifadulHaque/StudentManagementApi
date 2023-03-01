FROM openjdk:11

COPY target/studentmanagementapi.jar studentmanagementapi.jar

ENTRYPOINT ["java", "-jar", "/studentmanagementapi.jar"]