output "instance_id" {
  description = "ID da instância EC2 criada"
  value       = aws_instance.app.id
}

output "instance_public_ip" {
  description = "Endereço IP público da instância EC2"
  value       = aws_instance.app.public_ip
}
