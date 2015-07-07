
### Start Felix Container
```
cd target/felix-framework-5.0.1
java -jar bin/felix.jar
```

### Install Bundles
```
felix:install http://central.maven.org/maven2/com/google/code/findbugs/annotations/3.0.0/annotations-3.0.0.jar
felix:install http://central.maven.org/maven2/org/slf4j/slf4j-simple/1.7.12/slf4j-simple-1.7.12.jar
felix:install http://central.maven.org/maven2/org/slf4j/slf4j-api/1.7.12/slf4j-api-1.7.12.jar
felix:install http://central.maven.org/maven2/com/sun/xml/bind/jaxb-impl/2.2.11/jaxb-impl-2.2.11.jar
felix:install http://central.maven.org/maven2/com/helger/ph-commons/5.7.1/ph-commons-5.7.1.jar
felix:install http://central.maven.org/maven2/com/helger/ph-ubl20/3.3.0/ph-ubl20-3.3.0.jar
felix:install http://central.maven.org/maven2/com/helger/ph-ubl21/3.3.0/ph-ubl21-3.3.0.jar
felix:install http://central.maven.org/maven2/com/helger/ph-ubl/3.3.1/ph-ubl-3.3.1.jar
felix:install file:../ph-ubl-client-1.0-SNAPSHOT.jar
felix:start 5 7 8 9 10 11 12 13
```

### Usage
```
phubl:marshal
phubl:unmarshal
```