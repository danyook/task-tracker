FROM tomcat:9.0.65-jdk17-corretto

WORKDIR /usr/local/tomcat

COPY target/TaskTracker.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]