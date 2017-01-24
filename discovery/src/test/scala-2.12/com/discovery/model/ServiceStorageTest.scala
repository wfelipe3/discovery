package com.discovery.model

import com.discovery.model.ServiceStorage.{Name, ServiceRegistry, Url}
import org.scalatest.{FreeSpec, Matchers}

/**
  * Created by dev-williame on 1/24/17.
  */
class ServiceStorageTest extends FreeSpec with Matchers {

  "given service registry store it" - {
    val store = (s: ServiceRegistry) => {
      s should be(ServiceRegistry(Name("test"), Url("http://localhost:8080")))
      ()
    }
    val x = ServiceStorage.save(ServiceRegistry(Name("test"), Url("http://localhost:8080")))(store)
    x.unsafePerformIO()
  }
}
