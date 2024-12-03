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
resource "aws_api_gateway_method" "get_m