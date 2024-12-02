variable "region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

variable "aws_access_key" {
  description = "AWS access key"
  type        = string
}

variable "aws_secret_key" {
  description = "AWS secret key"
  type        = string
}

variable "base_ami_id" {
  description = "ID da AMI base a ser usada para a criação da instância EC2"
  type        = string
  default     = "ami-0453ec754f44f9a4a"
}
