FROM eclipse-temurin:21-jdk as builder
WORKDIR /app
COPY ./api .
RUN ./gradlew clean assemble

FROM eclipse-temurin:21-jre
COPY --from=builder /app/build/libs/dashboard*.jar ./dashboard-api.jar
CMD java ${JAVA_OPTS} -jar dashboard-api.jar
EXPOSE 8081
