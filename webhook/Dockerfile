# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR al contenedor
COPY target/webhook-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que se ejecuta la aplicación
EXPOSE 8085

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
