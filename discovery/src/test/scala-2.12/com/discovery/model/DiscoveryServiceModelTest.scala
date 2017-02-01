package com.discovery.model

import com.discovery.model.DiscoveryModel.{Name, ServiceRegistry, Url}
import com.discovery.repository.DiscoveryRepository
import com.discovery.service.DiscoveryService
import org.scalatest.{FreeSpec, Matchers}

import scala.util.{Failure, Success, Try}

/**
  * Created by dev-williame on 1/24/17.
  */
class DiscoveryServiceModelTest extends FreeSpec with Matchers {

  "given service registry store it" - {
    val register = DiscoveryService.register(ServiceRegistry(Name("test"), Seq(Url("http://test.com"))))
    register.run(new DiscoveryRepository[ServiceRegistry, Name] {
      override def get(id: Name): Try[Option[ServiceRegistry]] = Success(None)
      override def store(a: ServiceRegistry): Try[ServiceRegistry] = Success(a)
    }) should be(Success(ServiceRegistry(Name("test"), Seq(Url("http://test.com")))))
  }

  "given service registry store with urls then add new url" - {
    val register = DiscoveryService.register(ServiceRegistry(Name("test"), Seq(Url("http://test.com"))))
    register.run(new DiscoveryRepository[ServiceRegistry, Name] {
      override def get(id: Name): Try[Option[ServiceRegistry]] = Success(Some(ServiceRegistry(id, Seq(Url("http://google.com")))))
      override def store(a: ServiceRegistry): Try[ServiceRegistry] = Success(a)
    }) should be(Success(ServiceRegistry(Name("test"), Seq(Url("http://google.com"), Url("http://test.com")))))
  }

  "given service registry with failure then return error" - {
    val register = DiscoveryService.register(ServiceRegistry(Name("test"), Seq(Url("http.//localhost"))))
    val exception = new Exception("connection error")
    register.run(new DiscoveryRepository[ServiceRegistry, Name] {
      override def get(id: Name): Try[Option[ServiceRegistry]] = Failure(exception)
      override def store(a: ServiceRegistry): Try[ServiceRegistry] = ???
    }) should be(Failure(exception))
  }

  "search for registry and the registry is not in the repository, then return none" - {
    DiscoveryService.get(Name("test")).run(new DiscoveryRepository[ServiceRegistry, Name] {
      override def get(id: Name): Try[Option[ServiceRegistry]] = Success(None)
      override def store(a: ServiceRegistry): Try[ServiceRegistry] = ???
    }) should be(Success(None))
  }

  "search for registry and the registry is in the repository then return registry" - {
    val mock = new DiscoveryRepository[ServiceRegistry, Name] {
      override def get(id: Name): Try[Option[ServiceRegistry]] = Success(Some(ServiceRegistry(id, Seq(Url("http://localhost")))))
      override def store(a: ServiceRegistry): Try[ServiceRegistry] = ???
    }
    DiscoveryService.get(Name("test")).run(mock) should be(Success(Some(ServiceRegistry(Name("test"), Seq(Url("http://localhost"))))))
  }
}
