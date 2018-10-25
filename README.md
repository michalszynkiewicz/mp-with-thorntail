# MicroProfile with Thorntail

A demo app showing how to use the following MicroProfile features in Thorntail:
* OpenApi
* Metrics
* Rest Client
* Config
* OpenTracing

The demo consists of two services, ads that serves advertisements and quotes that serves quotes with one advertisement on the top.

The ads service is already ready for all the steps of the demo. 
The specific steps that correspond to the [commits](https://github.com/michalszynkiewicz/mp-with-thorntail/commits/master), are made on the quotes app

To run an application run the following in an application directory:
```
mvn clean package && java -jar target/*-thorntail.jar
```

You can access the openapi information on `localhost:8080/openapi`, metrics at `localhost:8080/metrics` and to run Jaeger to collect the spans, you can use:
```
docker run -d --name jaeger   -e COLLECTOR_ZIPKIN_HTTP_PORT=9411   -p 5775:5775/udp   -p 6831:6831/udp   -p 6832:6832/udp   -p 5778:5778   -p 16686:16686   -p 14268:14268   -p 9411:9411   jaegertracing/all-in-one:1.6
```

[Click here to download slides for the accompanying presentation](https://github.com/michalszynkiewicz/mp-with-thorntail/raw/master/EclipseCon%20Thorntail%20-%20A%20Micro%20Implementation%20of%20Eclipse%20MicroProfile.pdf)
