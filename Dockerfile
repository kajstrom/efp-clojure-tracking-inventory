FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/efp-clojure-tracking-inventory.jar /efp-clojure-tracking-inventory/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/efp-clojure-tracking-inventory/app.jar"]
