# E-commerce Microservices

Este projeto implementa uma arquitetura de microserviços para um sistema de e-commerce, utilizando Spring Boot e Spring Cloud.

## 🏗️ Arquitetura

O projeto é composto por 5 microserviços principais:

- **Discovery Server** - Servidor de descoberta de serviços (Eureka)
- **API Gateway** - Gateway de entrada para todas as requisições
- **Product Service** - Gerenciamento de produtos
- **Order Service** - Gerenciamento de pedidos
- **Inventory Service** - Controle de estoque

## 🚀 Tecnologias Utilizadas

- **Java 17/18**
- **Spring Boot 3.0.5**
- **Spring Cloud 2022.0.1**
- **Spring Cloud Gateway**
- **Netflix Eureka**
- **Spring Data JPA**
- **Spring Data MongoDB**
- **MySQL**
- **MongoDB**
- **Lombok**
- **Maven**

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0+
- MongoDB 4.4+
- Docker (opcional)

## 🛠️ Configuração do Ambiente

### 1. Banco de Dados

#### MySQL
```sql
CREATE DATABASE order_service;
CREATE DATABASE inventory_service;
```

#### MongoDB
```bash
# MongoDB deve estar rodando na porta 27017
```

### 2. Variáveis de Ambiente

Certifique-se de que as seguintes configurações estão corretas:

- **MySQL**: usuário `root`, senha `root`
- **MongoDB**: sem autenticação (desenvolvimento)
- **Eureka**: usuário `eureka`, senha `password`

## 🚀 Como Executar

### Ordem de Inicialização

1. **Discovery Server** (Porta 8761)
2. **API Gateway** (Porta 8080)
3. **Product Service** (Porta dinâmica)
4. **Order Service** (Porta 8081)
5. **Inventory Service** (Porta 8082)

### Comandos de Execução

```bash
# Compilar todos os projetos
mvn clean install

# Executar Discovery Server
cd discovery-server
mvn spring-boot:run

# Executar API Gateway
cd api-gateway
mvn spring-boot:run

# Executar Product Service
cd product-service
mvn spring-boot:run

# Executar Order Service
cd order-service
mvn spring-boot:run

# Executar Inventory Service
cd inventory-service
mvn spring-boot:run
```

## 📡 Endpoints

### API Gateway (Porta 8080)
- **Produtos**: `http://localhost:8080/api/product`
- **Pedidos**: `http://localhost:8080/api/order`
- **Estoque**: `http://localhost:8080/api/inventory`

### Discovery Server (Porta 8761)
- **Dashboard**: `http://localhost:8761`
- **Credenciais**: `eureka/password`

## 🔧 Funcionalidades

### Product Service
- Criação de produtos
- Listagem de produtos
- Armazenamento em MongoDB

### Order Service
- Criação de pedidos
- Listagem de pedidos
- Verificação de estoque via Inventory Service
- Armazenamento em MySQL

### Inventory Service
- Verificação de disponibilidade em estoque
- Gerenciamento de SKUs
- Armazenamento em MySQL

## 🔐 Segurança

- **API Gateway**: Configurado com OAuth2 Resource Server
- **Discovery Server**: Protegido com Spring Security
- **Keycloak**: Configurado para autenticação (porta 8181)

## 📊 Monitoramento

- **Eureka Dashboard**: `http://localhost:8761`
- **Logs**: Configurados para nível INFO/TRACE no Gateway

## 🧪 Testes

Cada serviço possui testes unitários configurados:

```bash
# Executar testes de um serviço específico
cd [service-name]
mvn test
```



## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Suporte

Para dúvidas ou suporte, entre em contato através dos issues do projeto. 