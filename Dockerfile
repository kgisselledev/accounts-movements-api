FROM openjdk:17
COPY "./target/accounts-0.0.1-SNAPSHOT.jar" "accounts.jar"
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "accounts.jar"]
