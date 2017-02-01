package com.discovery.service

import com.discovery.model.DiscoveryModel.{Name, ServiceRegistry}
import com.discovery.repository.DiscoveryRepository

import scala.util.{Success, Try}
import scalaz.Reader

/**
  * Created by dev-williame on 1/24/17.
  */
object DiscoveryService {
  def get(name: Name): Reader[DiscoveryRepository[ServiceRegistry, Name], Try[Option[ServiceRegistry]]] =
    Reader(r => r.get(name))

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


