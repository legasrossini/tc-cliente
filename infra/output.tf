output "instance_id" {
  description = "ID da instância EC2 criada"
  value       = aws_instance.app.id
}

output "instance_public_ip" {
  description = "Endereço IP público da instância EC2"
  value       = aws_instance.app.public_ip
}

output "key_pair_name" {
  description = "Nome do par de chaves"
  value       = aws_key_pair.deployer.key_name
}

output "key_pair_public_key" {
  description = "Chave priviada PEN"
  value       = aws_key_pair.deployer.public_key
}

output "private_key_pem" {
  description = "Chave privada PEM"
  value       = tls_private_key.deployer.private_key_pem
  sensitive   = true
}

output "s3_bucket_name" {
  description = "Nome do bucket S3"
  value       = aws_s3_bucket.application_jar.bucket
}