provider "aws" {
  region     = var.region
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}

# Security Group
resource "aws_security_group" "app_sg" {
  name_prefix = "app-security-group-"
  description = "Allow SSH and HTTP traffic"
  vpc_id      = var.vpc_id

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
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
  }
}

# Generate SSH Key Pair
resource "tls_private_key" "deployer" {
  algorithm = "RSA"
  rsa_bits  = 2048
}

# Key Pair
resource "aws_key_pair" "deployer" {
  key_name   = "deployer_key"
  public_key = tls_private_key.deployer.public_key_openssh
}

# EC2 Instance
resource "aws_instance" "app" {
  ami           = var.base_ami_id
  instance_type = "t2.micro"
  subnet_id     = var.subnet_id
  private_ip    = var.fixed_instance_ip
  key_name      = aws_key_pair.deployer.key_name

  tags = {
    Name = "tc-app-cliente"
  }

  vpc_security_group_ids = [aws_security_group.app_sg.id]

  depends_on = [aws_security_group.app_sg, aws_key_pair.deployer]
}

resource "aws_s3_bucket" "application_jar" {
  bucket = var.s3_bucket_name
  acl    = "private"

  tags = {
    Name        = "tc-app-client-bucket"
    Environment = "Production"
  }

  lifecycle {
    prevent_destroy = true  # Prevenir que o bucket seja destruído acidentalmente
  }
}
