FROM node:24-alpine AS build-website
WORKDIR /src/website/
COPY website/ .
RUN npm install
RUN npm run-script build

FROM gradle:8-jdk AS build-api
WORKDIR /src/api/
COPY api/ .
COPY --from=build-website /src/website/build/ /src/api/src/main/resources/static/
RUN gradle bootJar

FROM bellsoft/liberica-runtime-container:jdk-21-cds-slim-glibc
COPY --from=build-api /src/api/build/libs/*.jar /hidden-numbers.jar
EXPOSE 8080
CMD [ "java", "-jar", "/hidden-numbers.jar" ]