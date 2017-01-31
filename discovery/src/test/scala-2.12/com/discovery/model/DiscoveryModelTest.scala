package com.discovery.model

import com.discovery.model.Discovery.{Name, ServiceRegistry, Url}
import org.scalatest.{FreeSpec, Matchers}

import scala.util.{Failure, Success, Try}

/**
  * Created by dev-williame on 1/24/17.
  */
class DiscoveryModelTest extends FreeSpec with Matchers {

  "given service registry store it" - {
    val register = Discovery.register(ServiceRegistry(Name("test"), Seq(Url("http://test.com"))))
    register.run(new DiscoveryRepository[ServiceRegistry, Name] {
      override def get(id: Name): Try[Option[ServiceRegistry]] = Success(None)
      override def store(a: ServiceRegistry): Try[ServiceRegistry] = Success(a)
    }) should be(Success(ServiceRegistry(Name("test"), Seq(Url("http://test.com")))))
  }

  "given service registry store with urls then add new url" - {
    val register = Discovery.register(ServiceRegistry(Name("test"), Seq(Url("http://test.com"))))
    register.run(new DiscoveryRepository[ServiceRegistry, Name] {
      override def get(id: Name): Try[Option[ServiceRegistry]] = Success(Some(ServiceRegistry(id, Seq(Url("http://google.com")))))
      override def store(a: ServiceRegistry): Try[ServiceRegistry] = Success(a)
    }) should be(Success(ServiceRegistry(Name("test"), Seq(Url("http://google.com"), Url("http://test.com")))))
  }

  "given service registry with failure then return error" - {
    val register = Discovery.register(ServiceRegistry(Name("test"), Seq(Url("http.//localhost"))))
    val exception = new Exception("connection error")
    register.run(new DiscoveryRepository[ServiceRegistry, Name] {
      override def get(id: Name): Try[Option[ServiceRegistry]] = Failure(exception)
      override def store(a: ServiceRegistry): Try[ServiceRegistry] = ???
    }) should be(Failure(exception))
  }
}
