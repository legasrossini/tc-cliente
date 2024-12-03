variable "region" {
  description = "Região AWS"
  type        = string
  default     = "us-east-1"
}

variable "aws_access_key" {
  description = "Chave de acesso AWS"
  type        = string
}

variable "aws_secret_key" {
  description = "Chave secreta AWS"
  type        = string
}

variable "base_ami_id" {
  description = "AMI base para criar a instância EC2 (opcional)"
  type        = string
  default     = "ami-0453ec754f44f9a4a"
}

variable "subnet_id" {
  description = "ID da sub-rede onde a instância EC2 será criada"
  type        = string
}

variable "fixed_instance_ip" {
  description = "Endereço IP fixo para a instância EC2"
  type        = string
}