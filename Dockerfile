FROM openjdk:11
COPY ./teste/desafio-conta-bancaria.jar /desafio-conta-bancaria.jar
ENTRYPOINT java -jar /desafio-conta-bancaria.jar