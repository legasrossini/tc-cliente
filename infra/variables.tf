provider "aws" {
  region     = var.region
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}

# Security Group
resource "aws_security_group" "app_sg" {
  name        = "app-security-group"
  description = "Allow SSH and HTTP traffic"

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

  lifecycle {
    create_before_destroy = true
    replace_triggered_by = [var.region, var.aws_access_key, var.aws_secret_key]
  }
}

# EC2 Instance
resource "aws_instance" "app" {
  ami           = var.base_ami_id
  instance_type = "t2.micro"
  private_ip    = var.fixed_instance_ip

  tags = {
    Name = "app-instance"
  }

  security_groups = [aws_security_group.app_sg.name]
}