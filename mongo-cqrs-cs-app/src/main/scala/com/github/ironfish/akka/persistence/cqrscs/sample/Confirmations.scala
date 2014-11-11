/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <https://github.com/ironfish/>
 */
package com.github.ironfish.akka.persistence.cqrscs.sample

import akka.persistence.SnapshotMetadata

/**
 * Common messages dispatched to the event stream for probing in tests
 */
sealed trait Confirmations
case class MsgConfirmed(deliveryId: Long) extends Confirmations
case class SnapshotConfirmed(snap: SnapshotMetadata) extends Confirmations
