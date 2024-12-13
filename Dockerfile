FROM maven:3.9.9-openjdk-17 as build


COPY src /home/app/src
COPY pom.xml home/app
RUN mvn -f /home/app/pom.xml clean package

FROM tomcat:9.0.93-jdk17-corretto

WORKDIR /usr/local/tomcat

COPY --from=build /home/app/target/TaskTracker.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]




#FROM maven:3.8.3-eclipse-temurin-21-jammy as build
#
#COPY src /home/app/src
#COPY pom.xml home/app
#RUN mvn -f /home/app/pom.xml clean package
#
#
#
#FROM tomcat:9.0.65-jdk21-temurin-jammy
#
#WORKDIR /usr/local/tomcat
#
#COPY --from=build /home/app/target/TaskTracker.war /usr/local/tomcat/webapps/ROOT.war
#
#EXPOSE 8080
#
#CMD ["catalina.sh", "run"]