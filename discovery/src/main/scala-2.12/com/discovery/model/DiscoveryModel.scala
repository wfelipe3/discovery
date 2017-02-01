package com.discovery.model

/**
  * Created by dev-williame on 2/1/17.
  */
object DiscoveryModel {
  case class Url(url: String) extends AnyVal
  case class Name(name: String) extends AnyVal
  case class ServiceRegistry(name: Name, urls: Seq[Url])
}
