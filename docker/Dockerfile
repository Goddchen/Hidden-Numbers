FROM node:9 as build-website
WORKDIR /src/website/
COPY website/ .
RUN npm install
RUN npm run-script build

FROM gradle:6.2.2-jdk as build-api
WORKDIR /src/api/
COPY api/ .
COPY --from=build-website /src/website/build/ /src/api/src/main/resources/static/
RUN gradle bootJar

FROM openjdk:15-alpine
COPY --from=build-api /src/api/build/libs/*.jar /hidden-numbers.jar
EXPOSE 8080
CMD [ "java", "-jar", "/hidden-numbers.jar" ]