output "instance_id" {
  description = "ID da instância EC2"
  value       = aws_instance.app.id
}