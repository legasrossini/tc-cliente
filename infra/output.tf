output "instance_id" {
  description = "ID da instância EC2"
  value       = aws_spot_instance_request.app.id
}