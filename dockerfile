FROM openjdk:11-jdk
   COPY target/ms.siemens.com-0.0.1.jar ms.siemens.com-0.0.1.jar
   ENTRYPOINT ["java" ,"-jar", "/ms.siemens.com-0.0.1.jar"]