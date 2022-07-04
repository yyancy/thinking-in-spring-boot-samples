## Transaction Model

### Local Transaction Model
Local Transaction refers to the fact that transaction management is handled by the underlying Database(DBMS) or in the case of JMS the underlying messaging provider. From a developer perspective wo do manage  *connections* rather *transactions*.

#### pitfalls
* there is plenty of room for developer error
* it cannot exist concurrently when coordinating multiple resouces using an XA global transaction. eg a database and JMS destination.

### The Programmatic Transaction Model
The Programmatic transaction model do manages *transacions*, not *connections*.

#### pitfalls
* must be very careful with exception handling. The developer must ensure that the transaction is always terminated in the method that started the transaction.
* The transaction cannot propagate to another transaction, which would start a new transaction.

#### scenarios
* client-initiated transactions
* localized JTA transactions
* long-running transactions

### The Declarative Transaction Model
With the Declarative Transaction Model the container mangages the transaction, meaning that the developer does not have to write Java code to start or commit a transaction.

#### Transaction Attributes(Propagation)
* Required  If there is an existing transaction context the container will use it; otherwise it will start a new transaction.
* Mandatory  Unlike `Required` attribute, this attribute will never start a new transaction. If a transaction has not been started prior to a method invocation, the container will throw a `TransaxctionRequiredException`.
* RequiredNew  the container should always start a new transaction when the bean method is invoked. If a transaction has already been started prior to the mothod invocation that transaction is suspended and a new transaction is started. When the new transaction is terminated, the original transacion is resumed.
* Supports  Tells the container that the method doesn't need a transaction, but if one is present it will use it. Using `Supports` will cause the conatiner to use the existing transaction context and look at database log, thereby including any updates made during that transaction.
* NotSupported  tells the container that the method being invoked does not use a transaction. If a transaction has already been started it will be suspended until the mothod completes.
* Never  tells the container that the method being invoked cannot be invoked with a transaction.

#### Transaction Isolation Level
* TransactionReadUncommitted
* TransactionReadCommitted
* TransactionRepeatableRead
* TransactionSerializalbe

### XA Transaction Processing
The XA interface froms the communication bridge between the Transaction Manager and the various Resource Managers. XA also supports the two-phase commit protocol.

The XA Interface should only be used if you are coordinating multiple resources within the same transaction context.

The XA include some components: the two-phase commit process(2PC), heuristic exceptions and the use of XA Drivers.

#### Two-Phase Commit
The two-phase commit protocol is the mechanism used by XA to coordinate multiple resources during a golbal transation.
The two-phase commit protocol consists of two phases:
Phase One(the Prepare Phase) and Phase two(the Commit Phase)

