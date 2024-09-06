FROM gradle:8.2.1-jdk17 AS BUILDER

ENV APP_HOME=/btgpactual-challenge
WORKDIR ${APP_HOME}

COPY build.gradle settings.gradle gradlew ${APP_HOME}
COPY . .

RUN gradle clean bootJar --no-daemon

FROM openjdk:17-alpine as RUNTIME

ENV APP_HOME=/btgpactual-challenge
WORKDIR ${APP_HOME}

COPY --from=BUILDER ${APP_HOME}/build/libs/btgpactual-challenge-0.0.1.jar ${APP_HOME}

CMD ["java", "-jar", "btgpactual-challenge-0.0.1.jar"]