provider "aws" {
  region     = var.region
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}

# Tente buscar o Security Group existente pelo ID
data "aws_security_group" "existing_sg" {
  id = "sg-0382b6481e8c20d6d"
}

# Defina local para calcular o id do grupo de segurança
locals {
  security_group_id = length(data.aws_security_group.existing_sg.id) > 0 ? data.aws_security_group.existing_sg.id : null
}

# Se o grupo de segurança não for encontrado, crie um novo
resource "aws_security_group" "app_sg" {
  count       = local.security_group_id == null ? 1 : 0
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
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_spot_instance_request" "app" {
  ami           = length(data.aws_ami.existing_ami.id) > 0 ? data.aws_ami.existing_ami.id : aws_ami_from_instance.app_ami[0].id
  instance_type = "t3.micro"
  spot_price    = "0.005"

  tags = {
    Name = "tc-cliente"
  }

  # Use o local para determinar o grupo de segurança
  security_groups = local.security_group_id != null ? [local.security_group_id] : [aws_security_group.app_sg[0].id]
}