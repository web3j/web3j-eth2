<img src="https://avatars1.githubusercontent.com/u/16343502" alt="OpenAPI" width="200"/>

Beacon Node API Client
======================

Client library for the [Eth2 Beacon Node API](https://ethereum.github.io/eth2.0-APIs/).

## Dependencies

* [JAX-RS 2.1](https://github.com/eclipse-ee4j/jaxrs-api/tree/2.1-Maintenance) (Java RESTful Web Services)
* [Jackson Annotations 2.11](https://github.com/FasterXML/jackson-annotations)
* [Jersey Client 2.32](https://github.com/eclipse-ee4j/jersey)

## Test and build

This library is tested against different Beacon Node API nodes to ensure that it works on all of them.
At the moment the tests run against [Lighthouse](https://github.com/sigp/lighthouse) and
[Teku](https://github.com/Consensys/teku/). 

Other Beacon Node implementations providing Docker images such as 
[Nimbus](https://github.com/status-im/nimbus-eth2) and [Prysm](https://github.com/prysmaticlabs/prysm) will be included 
in the future.

### Requirements

To test and build the project, you'll need [Docker](https://www.docker.com/products/docker-desktop) running.

Unless you pull the images manually, you will also need to define these variables in order to pull the Docker images 
from the [Docker Hub](https://hub.docker.com/) registry:

- `registry.username`
- `registry.password`

Check the [Docker client API](https://github.com/docker-java/docker-java/blob/master/docs/getting_started.md#instantiating-a-dockerclientconfig)
for more information on configuration options.

### Build command

Run this command on a console:

```shell
./gradlew build
```
