provider "azurerm" {
  subscription_id = "${var.credential["subscription_id"]}"
  client_id = "${var.credential["client_id"]}"
  client_secret = "${var.credential["client_secret"]}"
  tenant_id = "${var.credential["tenant_id"]}"
}