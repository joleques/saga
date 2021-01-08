FROM openjdk:11-jre-slim

WORKDIR /app
COPY ./build/libs .
EXPOSE 8081

CMD ["sh", "-c", "java -jar -Dspring.profiles.active=production pedido.jar" ]