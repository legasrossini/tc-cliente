provider "aws" {
  region     = var.region
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}

# Tente buscar o Security Group existente
data "aws_security_group" "existing_sg" {
  # Substitua com o ID ou nome do Security Group desejado
  filter {
    name   = "group-name"
    values = ["existing-security-group-name"]  # Substitua pelo nome do SG existente
  }
}

# Se o SG não existir, crie um novo
resource "aws_security_group" "app_sg" {
  count       = length(data.aws_security_group.existing_sg.id) == 0 ? 1 : 0
  name        = "app_sg"
  description = "Allow HTTP and SSH"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    =
