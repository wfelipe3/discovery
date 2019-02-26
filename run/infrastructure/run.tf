resource "azurerm_resource_group" "rg" {
  name = "${var.location}-${var.prefix_rs}-rg-${var.channel_g}"
  location = "${var.location}"
}

terraform {
  backend "azurerm" {}
}
