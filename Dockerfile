FROM openjdk
VOLUME /tmp

ADD target/springBootstrap.jar .

RUN sh -c 'touch /springRestBootstrap.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","springRestBootstrap.jar"]