package com.discovery.model

import scalaz.effect.IO

/**
  * Created by dev-williame on 1/24/17.
  */
object DiscoveryModel {

  case class Url(url: String) extends AnyVal
  case class Name(name: String) extends AnyVal
  case class ServiceRegistry(name: Name, urls: Seq[Url])

  type Store = ServiceRegistry => Unit

  def register(registry: ServiceRegistry)(store: Store): IO[Unit] = ???
}
