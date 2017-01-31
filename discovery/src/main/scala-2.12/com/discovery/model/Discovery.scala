package com.discovery.model

import scala.util.{Success, Try}
import scalaz.Reader

/**
  * Created by dev-williame on 1/24/17.
  */
object Discovery {

  case class Url(url: String) extends AnyVal
  case class Name(name: String) extends AnyVal
  case class ServiceRegistry(name: Name, urls: Seq[Url])

  def register(registry: ServiceRegistry): Reader[DiscoveryRepository[ServiceRegistry, Name], Try[ServiceRegistry]] =
    Reader(r => {
      for {
        values <- r.get(registry.name)
        toStore <- values
          .map(sr => Success(ServiceRegistry(registry.name, sr.urls ++ registry.urls)))
          .getOrElse(Success(registry))
        stored <- r.store(toStore)
      } yield stored
    })
}

trait DiscoveryRepository[A, Id] {
  def get(id: Id): Try[Option[A]]
  def store(a: A): Try[A]
}

