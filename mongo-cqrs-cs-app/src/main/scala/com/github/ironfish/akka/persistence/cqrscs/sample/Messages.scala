/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <https://github.com/ironfish/>
 */
package com.github.ironfish.akka.persistence.cqrscs.sample

/**
 * common messages used in testing
 */
case class Msg(deliveryId: Long, payload: Any)
case class Confirm(deliveryId: Long)
