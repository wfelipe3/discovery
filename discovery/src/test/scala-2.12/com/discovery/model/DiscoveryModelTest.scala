package com.discovery.model

import com.discovery.model.DiscoveryModel.{Name, ServiceRegistry, Url}
import org.scalatest.{FreeSpec, Matchers}

/**
  * Created by dev-williame on 1/24/17.
  */
class DiscoveryModelTest extends FreeSpec with Matchers {

  "given service registry store it" - {
    DiscoveryModel.register(ServiceRegistry(Name("test"), Seq(Url("http://test.com"))))
  }
}
