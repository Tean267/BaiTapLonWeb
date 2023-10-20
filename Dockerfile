FROM openjdk:17

COPY target/0ffice_Manager-1.0.war 0ffice_Manager-1.0.war 

ENTRYPOINT ["java","-jar","/0ffice_Manager-1.0.war"]