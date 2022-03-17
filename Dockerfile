FROM openjdk:17
EXPOSE 8080
RUN mkdir /app
COPY build/libs/*.jar /app/FullStackMovieDatabase.jar

ENTRYPOINT ["java","-Xmx2g","-jar","/app/FullStackMovieDatabase.jar"]