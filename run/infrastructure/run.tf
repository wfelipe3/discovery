resource "azurerm_resource_group" "rg" {
  name = "${var.location}-${var.prefix_rs}-rg-${var.channel_g}"
  location = "${var.locations}"
}

terraform {
  backend "azurerm" {}
}
