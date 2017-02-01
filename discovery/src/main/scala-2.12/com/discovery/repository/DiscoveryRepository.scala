package com.discovery.repository

import com.discovery.model.DiscoveryModel.{Name, ServiceRegistry}

import scala.util.{Success, Try}

/**
  * Created by dev-williame on 2/1/17.
  */
object DiscoveryRepository {
  object InMemoryRepository extends DiscoveryRepository[ServiceRegistry, Name] {
    var values: Map[Name, ServiceRegistry] = Map.empty
    override def get(id: Name): Try[Option[ServiceRegistry]] = Success(values.get(id))
    override def store(a: ServiceRegistry): Try[ServiceRegistry] = {
      values = values + (a.name -> a)
      Success(a)
    }
  }
}

trait DiscoveryRepository[A, Id] {
  def get(id: Id): Try[Option[A]]
  def store(a: A): Try[A]
}
