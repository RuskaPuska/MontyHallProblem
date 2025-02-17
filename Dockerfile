FROM tomcat:10.1.26-jre21-temurin-noble
COPY target/MontyHallProblem.war /usr/local/tomcat/webapps/ROOT.war