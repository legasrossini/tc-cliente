provider "aws" {
  region     = var.region
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}

# Tente buscar o Security Group existente
data "aws_security_group" "existing_sg" {
  filter {
    name   = "group-name"
    values = ["app_sg"]
  }
}

resource "aws_security_group" "app_sg" {
  count       = length(data.aws_security_group.existing_sg.id) == 0 ? 1 : 0
  name        = "app_sg"
  description = "Allow HTTP and SSH"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"