# tc-ec2-cliente

Este é um projeto de exemplo para gerenciar uma aplicação cliente com deploy em instâncias EC2 utilizando Terraform e GitHub Actions.

![AWS EC2](https://img.shields.io/badge/AWS-EC2-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## Descrição

Este projeto configura uma aplicação cliente que é implantada em instâncias EC2. Ele utiliza Terraform para gerenciar a infraestrutura como código e GitHub Actions para automatizar o fluxo de trabalho de desenvolvimento e implantação.

## Estrutura do Projeto e Arquitetura Hexagonal

O projeto segue a arquitetura hexagonal, que facilita a separação de responsabilidades e a manutenção do código.

- **infra/**: Contém os arquivos de configuração do Terraform.
- **src/**: Contém o código-fonte da aplicação.
   - **main/java/com/example/cliente**: Contém o código-fonte principal da aplicação.
      - **adapter**: Contém os adaptadores, incluindo controladores REST, conversores e constantes.
         - **constants**: Contém constantes utilizadas na aplicação.
         - **controller**: Contém os controladores REST.
         - **converter**: Contém classes para conversão de dados.
      - **config**: Contém classes de configuração da aplicação.
      - **core**: Contém a lógica central da aplicação, incluindo casos de uso e serviços.
      - **exceptions**: Contém classes para tratamento de exceções.
- **.github/workflows/**: Contém os arquivos de workflow do GitHub Actions.
- **README.md**: Este arquivo.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para desenvolver a aplicação.
- **Spring Boot**: Framework utilizado para criar a aplicação.
- **Maven**: Ferramenta de automação de build utilizada para gerenciar dependências e construir a aplicação.
- **Terraform**: Ferramenta de infraestrutura como código utilizada para gerenciar os recursos da AWS.
- **GitHub Actions**: Ferramenta de CI/CD utilizada para automatizar o fluxo de trabalho de desenvolvimento e implantação.

## Fluxo de Trabalho

### Branch `develop`

O branch `develop` é utilizado para desenvolvimento contínuo. Todas as mudanças devem ser feitas neste branch e validadas antes de serem mescladas no branch `main`.

#### Workflow de Validação (`create-pr.yml`)

![Workflow](https://img.shields.io/badge/GitHub%20Actions-Workflow%20de%20Validação-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

1. **Checkout do Repositório**: Faz o checkout do código do repositório.
2. **Configuração do Terraform**: Configura o Terraform.
3. **Inicialização do Terraform**: Inicializa o Terraform.
4. **Validação do Terraform**: Valida a configuração do Terraform.
5. **Busca de Todos os Branches**: Garante que todos os branches estejam atualizados.
6. **Atualização do Branch `develop`**: Garante que o branch `develop` esteja atualizado.
7. **Criação ou Atualização do Pull Request**: Cria ou atualiza um Pull Request para mesclar as mudanças do branch `develop` no branch `main`.

### Branch `main`

O branch `main` é utilizado para a versão estável do código. Todas as mudanças no branch `main` são automaticamente implantadas.

#### Workflow de Implantação (`deploy.yml`)

![Workflow](https://img.shields.io/badge/GitHub%20Actions-Workflow%20de%20Implantação-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

1. **Checkout do Repositório**: Faz o checkout do código do repositório.
2. **Configuração do Terraform**: Configura o Terraform.
3. **Inicialização do Terraform**: Inicializa o Terraform.
4. **Validação do Terraform**: Valida a configuração do Terraform.
5. **Configuração das Credenciais AWS**: Configura as credenciais AWS.
6. **Exportação de Variáveis de Ambiente**: Exporta as variáveis de ambiente necessárias.
7. **Atualização do Estado do Terraform**: Atualiza o estado do Terraform.
8. **Aplicação do Terraform**: Aplica as mudanças do Terraform.

## Endpoints

### Cliente

- **GET /cliente**: Retorna a lista de clientes.
   - **Resposta**:
     ```json
     [
       {
         "cpf": "61773457004",
         "nome": "João Silvas",
         "email": "joao.silva@example.com"
       }
     ]
     ```
   - **Status Code**: 200 OK

- **POST /cliente**: Adiciona um novo cliente.
   - **Requisição**:
     ```json
     {
       "cpf": "61773457004",
       "nome": "João Silvas",
       "email": "joao.silva@example.com"
     }
     ```
   - **Status Code**: 201 Created

## Como Rodar Localmente

1. **Clone o Repositório**:
   ```sh
   git clone https://github.com/legasrossini/tc-ec2-cliente.git
   cd tc-ec2-cliente