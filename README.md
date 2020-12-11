<img src="Web3j-Eth2.png" alt="Web3j Ethereum 2.0" width="200"/>

Web3j - Ethereum 2.0
====================

This project contains Ethereum 2.0 related modules.

At the moment it only contains the Beacon Node API client.

## Beacon Node API Client

Web3j client library for the [Eth2 Beacon Node API](https://ethereum.github.io/eth2.0-APIs/).

### Dependencies

* [JAX-RS 2.1](https://github.com/eclipse-ee4j/jaxrs-api/tree/2.1-Maintenance) (Java RESTful Web Services)
* [Jackson Annotations 2.11](https://github.com/FasterXML/jackson-annotations)
* [Jersey Client 2.32](https://github.com/eclipse-ee4j/jersey)

### Quickstart

You can add the library to your project using either Gradle or Maven.

#### Maven

```xml
<dependency>
  <groupId>org.web3j.eth2</groupId>
  <artifactId>beacon-node-api</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### Gradle

```groovy
implementation 'org.web3j.eth2:beacon-node-api:1.0.0'
```

Create a service pointing to your Beacon Chain node:

```java
var service = new BeaconNodeService("http://public-mainnet-node.ethereum.org/api");
```

Build a client instance using the client factory:

```java
var client = BeaconNodeClientFactory.build(service);
```

That's it! You can start using the client to call the node endpoints, for instance:

```java
client.getBeacon().getBlocks().findById(NamedBlockId.HEAD);
```

### Test and build

Run this command on a console:

```shell
./gradlew build
```

#### Integration tests

This library is tested against different Beacon Node API nodes to ensure that it works on all of them.
At the moment the tests run against [Lighthouse](https://github.com/sigp/lighthouse) and
[Teku](https://github.com/Consensys/teku/).

Other Beacon Node implementations providing Docker images such as
[Nimbus](https://github.com/status-im/nimbus-eth2) and [Prysm](https://github.com/prysmaticlabs/prysm) will be included
in the future.

To run the integration tests, you'll need [Docker](https://www.docker.com/products/docker-desktop) running.
Unless you pull the images manually, you will also need to define these variables in order to pull the Docker images
from the [Docker Hub](https://hub.docker.com/) registry:

- `registry.username`
- `registry.password`

Check the [Docker client API](https://github.com/docker-java/docker-java/blob/master/docs/getting_started.md#instantiating-a-dockerclientconfig)
for more information on configuration options.

Run this command on a console:

```shell
./gradlew integrationTest
```
