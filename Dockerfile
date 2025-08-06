FROM amazoncorretto:24-alpine-jdk
WORKDIR /app
ADD ./target/msvc-product-0.0.1-SNAPSHOT.jar msvc-product.jar
ENTRYPOINT ["java", "-jar", "msvc-product.jar"]