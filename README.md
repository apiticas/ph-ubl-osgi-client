
### Start Felix Container
```
mvn clean install
cd target/felix-framework-5.0.1
java -jar bin/felix.jar
```

### Install Bundles
```
felix:install http://central.maven.org/maven2/com/google/code/findbugs/annotations/3.0.0/annotations-3.0.0.jar
felix:install http://central.maven.org/maven2/org/slf4j/slf4j-simple/1.7.12/slf4j-simple-1.7.12.jar
felix:install http://central.maven.org/maven2/org/slf4j/slf4j-api/1.7.12/slf4j-api-1.7.12.jar
felix:install http://central.maven.org/maven2/com/sun/xml/bind/jaxb-impl/2.2.11/jaxb-impl-2.2.11.jar
felix:install http://repo1.maven.org/maven2/com/helger/ph-commons/6.0.1/ph-commons-6.0.1.jar
felix:install http://repo1.maven.org/maven2/com/helger/ph-ubl-api/4.0.0-RC1/ph-ubl-api-4.0.0-RC1.jar
felix:install http://repo1.maven.org/maven2/com/helger/ph-ubl21/4.0.0-RC1/ph-ubl21-4.0.0-RC1.jar
felix:install file:../ph-ubl-client-1.0-SNAPSHOT.jar
felix:start 5 7 8 9 10 11 12
```

### Usage
```
phubl:marshal
phubl:unmarshal
```