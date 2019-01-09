#!/bin/sh

getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Esperando pelo servidor Eureka na porta $(getPort $EUREKASERVER_PORT)"
echo "********************************************************"
while ! `nc -z eurekaserver  $(getPort $EUREKASERVER_PORT)`; do sleep 3; done
echo "******* Eureka Server iniciou"

echo "********************************************************"
echo "Esperando config server na porta $(getPort $CONFIGSERVER_PORT)"
echo "********************************************************"
while ! `nc -z configserver $(getPort $CONFIGSERVER_PORT)`; do sleep 3; done
echo "*******  Configuration Server iniciou"

echo "********************************************************"
echo "Iniciando Zuul Service with $CONFIGSERVER_URI"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKA_DEFAULT_ZONE   \
     -Dspring.profiles.active=$PROFILE                             \
     -jar /usr/local/@project.artifactId@/@project.build.finalName@.jar