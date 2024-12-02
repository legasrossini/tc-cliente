provider "aws" {
  region     = var.region
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}

resource "aws_instance" "source_instance" {
  ami           = "ami-12345678" # Substitua por uma AMI base válida
  instance_type = "t3.micro"
  tags = {
    Name = "SourceInstance"
  }
}

resource "aws_ami_from_instance" "app_ami" {
  name               = "app-ami"
  source_instance_id = aws_instance.source_instance.id
  tags = {
    Name = "AppAMI"
  }
}

resource "aws_spot_instance_request" "app" {
  ami           = aws_ami_from_instance.app_ami.id
  instance_type = "t3.micro"
  spot_price    = "0.005"

  tags = {
    Name = "tc-cliente"
  }

  security_groups = [aws_security_group.app_sg.name]
}

resource "aws_security_group" "app_sg" {
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