# Akka Persistence Sample Applications

[![Build Status](https://travis-ci.org/ironfish/akka-persistence-mongo-samples.png?branch=master)](https://travis-ci.org/ironfish/akka-persistence-mongo-samples)

## Overview

This repository acts as a playground to explore different ways to implement [Akka Persistence](http://doc.akka.io/docs/akka/current/scala/persistence.html) based applications. In addition to Akka Persistence, other topics such as [CQRS](http://msdn.microsoft.com/en-us/library/dn568103.aspx) (Command Query Responsibility Segregation), Command Sourcing, [Event Sourcing](http://doc.akka.io/docs/akka/snapshot/scala/persistence.html#Event_sourcing), and Distributed Domain Models are explored as well.

## Disclaimer
Please **note** while all sample code compiles and tests pass, it is intended for example purposes only, and in no way should be construed as production ready code.

## Table of Contents

1. [Coming Soon] Cluster Sharded CQRS/ES with Distributed Domain Model - This application will implement an `actor-as-aggregate` model allowing for the distribution of `actor aggregates` across several nodes in a cluster.

2. [CQRS/Event Sourcing Sample Application](https://github.com/ironfish/akka-persistence-mongo-samples/tree/master/mongo-cqrs-es-app) implements [CQRS](http://msdn.microsoft.com/en-us/library/dn568103.aspx)(Command Query Responsibility Segregation) with Akka-Persistence [Event Sourcing](http://doc.akka.io/docs/akka/current/scala/persistence.html#Event_sourcing).

3. [Command Sourcing Sample Application](https://github.com/ironfish/akka-persistence-mongo-samples/tree/master/mongo-cqrs-cs-app) implements the `command sourcing` pattern. This pattern is typically used when local consistency requirements can be relaxed for high throughput use-cases. **Note** In order to implement the pattern known as *"command sourcing..."*, in this example, the journal acts as a `write-ahead-log` for whatever persisted messages it receives.

## Author / Maintainer

* [Duncan DeVore (@ironfish)](https://github.com/ironfish/)

## Contributors

* [Sean Walsh (@SeanWalshEsq)](https://github.com/sean-walsh/)
* [Al Iacovella](https://github.com/aiacovella/)
