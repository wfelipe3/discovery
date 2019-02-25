resource "azurerm_resource_group" "rg" {
  name = "${var.locations}-${var.prefix_rs}-rg-${var.channel_g}"
  location = "${var.location}"
}

terraform {
  backend "azurerm" {}
}
