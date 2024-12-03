data "aws_instance" "app" {
  filter {
    name   = "tag:Name"
    values = ["tc-app-cliente"]
  }
  filter {
    name   = "instance-state-name"
    values = ["running"]
  }
  depends_on = [aws_instance.app]
}

resource "aws_api_gateway_rest_api" "my_api" {
  name        = "gtw-cliente"
  description = "API Gateway da aplicação de cliente"
}

resource "aws_api_gateway_resource" "my_resource" {
  rest_api_id = aws_api_gateway_rest_api.my_api.id
  parent_id   = aws_api_gateway_rest_api.my_api.root_resource_id
  path_part   = "cliente"
}

# Método GET
resource "aws_api_gateway_method" "get_method" {
  rest_api_id   = aws_api_gateway_rest_api.my_api.id
  resource_id   = aws_api_gateway_resource.my_resource.id
  http_method   = "GET"
  authorization = "NONE"
}

resource "aws_api_gateway_integration" "get_integration" {
  rest_api_id = aws_api_gateway_rest_api.my_api.id
  resource_id = aws_api_gateway_resource.my_resource.id
  http_method = "GET"  # Define o método explicitamente
  type        = "HTTP"
  uri         = "http://${data.aws_instance.app.public_ip}:8080/cliente"
  depends_on  = [
    aws_api_gateway_method.get_method, # Adiciona dependência explícita
    aws_instance.app
  ]
}

# Método POST
resource "aws_api_gateway_method" "post_method" {
  rest_api_id   = aws_api_gateway_rest_api.my_api.id
  resource_id   = aws_api_gateway_resource.my_resource.id
  http_method   = "POST"
  authorization = "NONE"
}

resource "aws_api_gateway_integration" "post_integration" {
  rest_api_id = aws_api_gateway_rest_api.my_api.id
  resource_id = aws_api_gateway_resource.my_resource.id
  http_method = "POST"  # Define o método explicitamente
  type        = "HTTP"
  uri         = "http://${data.aws_instance.app.public_ip}:8080/cliente"
  depends_on  = [
    aws_api_gateway_method.post_method, # Adiciona dependência explícita
    aws_instance.app
  ]
}

resource "aws_api_gateway_deployment" "my_deployment" {
  depends_on  = [
    aws_api_gateway_integration.get_integration,
    aws_api_gateway_integration.post_integration
  ]
  rest_api_id = aws_api_gateway_rest_api.my_api.id
}

resource "aws_api_gateway_stage" "prod" {
  deployment_id = aws_api_gateway_deployment.my_deployment.id
  rest_api_id   = aws_api_gateway_rest_api.my_api.id
  stage_name    = "prod"
}

output "api_url" {
  value = "${aws_api_gateway_stage.prod.invoke_url}/cliente"
}