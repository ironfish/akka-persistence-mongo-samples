# Command Sourcing Sample Application

## Technology Stack

* Akka-Persistence
    * Uses [Mongo Journal](https://github.com/ironfish/akka-persistence-mongo/) plugin for Akka Persistence implementation.
    * Uses [`PersistentActor`](http://doc.akka.io/docs/akka/current/scala/persistence.html#Event_sourcing) for processing CQRS command-side commands, journaling commands, recovery and snapshots.
+ Uses [ScalaStm](http://nbronson.github.io/scala-stm/) for managing runtime state.
* Uses [Scalaz](https://github.com/scalaz/scalaz) for non-breaking error validation of commands.
* Uses [Salat](https://github.com/novus/salat) for case class serialization atop the mongo casbah driver on the query-side aggregation.
* Uses [Embedded Mongo](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo) for test persistence store.
* Uses [ScalaTest](http://www.scalatest.org/) as test toolkit.

## Description

This sample project implements the Command Sourcing pattern using Akka Persistence. It is a re-write of the original sample based on the [Eventsourced](https://github.com/eligosource/eventsourced) library originally written by Martin Krasser.

This example shows some of the ins and outs of an employee domain and a read side (see CQRS) construct for the separate concern of HR (human resources) tracking the overall employment length of employees.

Command Sourcing was successfully used in our production systems and effectively meant the journaling of all message received by the processor. Since this application is Command Sourced, `PersistentViews` are not used. As a result, the read side construct is more of a home grown type of thing and uses basic Akka functionality.

The Command Sourcing pattern is typically used when local consistency requirements can be relaxed for high throughput use-cases. **Note** In order to implement the pattern known as *"Command Sourcing"*, in this example, the journal acts as a `write-ahead-log` for whatever persisted messages it receives.

## Status

* Initial commit with base functionality.

## Author / Maintainer

* [Sean Walsh (@SeanWalshEsq)](https://github.com/sean-walsh/)

## Contributors

* [Duncan DeVore (@ironfish)](https://github.com/ddevore/)
* [Al Iacovella](https://github.com/aiacovella/)
