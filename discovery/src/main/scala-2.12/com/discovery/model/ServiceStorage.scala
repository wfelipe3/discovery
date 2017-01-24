package com.discovery.model

import scalaz.effect.IO

/**
  * Created by dev-williame on 1/24/17.
  */
object ServiceStorage {

  case class Url(url: String) extends AnyVal
  case class Name(name: String) extends AnyVal
  case class ServiceRegistry(name: Name, url: Url)

  def save(registry: ServiceRegistry)(store: ServiceRegistry => Unit): IO[Unit] =
    IO {
      store(registry)
    }
}
