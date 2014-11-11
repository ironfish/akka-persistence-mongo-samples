# CQRS/Event Sourcing Smaple Application

## Technology Stack

* Akka-Persistence
    * Uses [Mongo Journal](https://github.com/ironfish/akka-persistence-mongo/) plugin for Akka Persistence implementation.
    * Uses [`PersistentActor`](http://doc.akka.io/docs/akka/current/scala/persistence.html#Event_sourcing) for processing CQRS command-side commands, journaling events, recovery and snapshots.
    * Uses [`PersistentView`](http://doc.akka.io/docs/akka/current/scala/persistence.html#Views) for projecting eventually consistent CQRS query-side data.
* Uses [Scalaz](https://github.com/scalaz/scalaz) for non-breaking error validation of commands.
* Uses [Salat](https://github.com/novus/salat) for case class serialization atop the mongo casbah driver on the query-side aggregation.
* Uses [Embedded Mongo](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo) for test persistence store.
* Uses [ScalaTest](http://www.scalatest.org/) as test toolkit.

## Description

This sample project demonstrates one way to implement an [Akka Persistence](http://doc.akka.io/docs/akka/current/scala/persistence.html) based CQRS Event Sourcing application. It contains a fictitious domain model that represents an employee management lifecycle where the issuing of domain commands, if valid, generate one or more domain events which are then journaled (persisted).

This application does not have a UI component and is not intended to be a complete HR system. Rather it is designed to demonstrate the use of the above technologies in a simple use case.

## Important Terminology

### Event Sourcing

Event sourcing provides a means by which one can capture the real intent of our users. In an Event sourcing system, all data operations are viewed as a sequence of events that are recorded to an append-only store. This pattern can simplify tasks in complex domains by avoiding the requirement to synchronize the data model and the business domain. It can improve performance, scalability, and responsiveness; provide consistency for transactional data, and maintain full audit trails and history that may enable compensating actions.

Event Sourcing is a very powerful (and old) construct which allows for the recovery of the current state at any point in time from origin to now. Typically an event sourcing system will maintain a current state model in-memory for fast access. Such is the case with this example.

#### Commands

Commands are behavioral constructs that request to change the state of an entity. They are imperative by nature and as such represent a request that one can reject. A single Command can also request to change state in aggregate (similar to batching) from which, if valid, can generate many domain events. The construction of Commands follows the present tense `VerbNoun` format, for example:

```scala
HireEmployee
ChangeEmployeeStreet
```

#### Events

Events are a key component of Event Sourcing, thus the name. Events are Indicative in nature and serve as a sign or indication that something has happened. As such, they are immutable and cannot be rejected. They follow a past tense `NounVerb` format, for example:

```scala
EmployeeHired
EmployeeStreetChanged
```

Events should be atomic in nature, and represent for form of state that has transpired against a domain aggregate.


### CQRS

Command Query Responsibility Segregation is an architectural pattern created by Greg Young, based on Bertrand Meyer’s Command and Query Separation principle. Wikipedia defines this principle as:

> It states that every method should either be a command that performs an action, or a query that returns data to the caller, but not both. In other words, asking a question should not change the answer. More formally, methods should return a value only if they are referentially transparent and hence possess no side effects. (Wikipedia)

CQRS is similar to CQS in that it uses the same definition for Commands and Queries, but fundamentally believes that Commands and Queries should distinct objects in the domain. One of the key advantages to CQRS is that because the Commands (writes) are separate from the Queries (reads) it allows for distinct optimization of each of these concerns.

This example implements CQRS in the following way:

* The Command side contains two Scala files, `Employee.scala` & `EmployeeProtocol.scala`.
* The Query side contains two Scala files, `Benefits.scala` & `BenefitsProtocol.scala`.

The command side will process command requests and upon validation, generates and journals the appropriate events. These events are made _eventually consistent_ with the query side by way of an Akka-Persistence `View`.

## Workflow

### Command Side

In this example, the Command side implements a `1-n`, single actor per aggregate type. Aggregates are case classes with the associated `PersistentActor` named `EmployeeProcessor`. The primary aggregate is the `Employee` entity which can be in one of the three following states:

* `ActiveEmployee` - the employee is currently active.
* `InactiveEmployee` - the employee is temporarily deactivated (i.e., leave of absence)
* `TerminatedEmployee` - the employee has been terminated.

The `EmployeeProcessor` is responsible for managing all the lifecycles for all employees, which entails the following responsibilities:

* Process commands, upon validation, generate events.
* Journaling valid events.
* Updating the in-memory model for the current state of a given aggregate. This update may result in the change of the `Employee` type.

### Query Side

The Query side implements a single aggregate the `BenefitDates` aggregate. Modification to the `BenefitDates` aggregate occurs whenever certain events are made _eventually consistent_ from the Command side. Eventual consistency is achieved by using a `PersistentView` named `BenefitsView`, which receives, transforms and persists the events. The events from the Command side that trigger this behavior are:

* `EmployeeHired` - the hiring of an employee
* `EmployeeDeactivated` - whenever an employee id deactivated (i.e., leave of absence).
* `EmployeeActivated` - the activation of a de-active employee.
* `EmployeeTerminated` - the termination of an active employee.
* `EmployeeRehired` - the re-hire of a terminated employee.

## DDD Implementation

There has been a fair amount of discussion around how to design a distributed domain model using Akka. See the following links for information around a design pattern that seems to be materializing:

* [Creating one instance of an Akka actor per entity](https://groups.google.com/forum/#!topic/akka-user/BRh3YNjP0kY)
* [Cluster sharding for akka-persistence](https://groups.google.com/forum/#!msg/akka-dev/ohdT-Et4ZoY/6cB52mnpkAkJ)
* [Using Scala and Akka with Domain-Driven Design](https://vaughnvernon.co/?p=770)

The general approach identified in the links above is one of a single actor per aggregate, `1-1`. While, in the long run, this is a better approach (especially in regards to clustering), I chose not to implement this design for this example.

## Status

- ~~Initial commit with base functionality~~
- ~~Add State changes~~
    - ~~Deactive~~
    - ~~Activate~~
    - ~~Terminate~~
    - ~~Rehire~~
- ~~Add Run Payroll~~
- ~~Implement base tests~~
- ~~Implement CQRS query side w/ views~~
- ~~Implement CQRS tests~~
- ~~Implement get~~
- Implement `AtLeastOnceDelivery`
- Implement `AtLeastOnceDelivery` confirmation test

## Author / Maintainer

- [Duncan DeVore (@ironfish)](https://github.com/ddevore/)

## Contributors

- [Sean Walsh (@SeanWalshEsq)](https://github.com/sean-walsh/)
- [Al Iacovella](https://github.com/aiacovella/)
