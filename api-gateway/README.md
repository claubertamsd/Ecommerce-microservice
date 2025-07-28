# API Gateway

O API Gateway é o ponto de entrada único para todas as requisições da aplicação, responsável por roteamento, segurança e balanceamento de carga.

## 🎯 Propósito

Este serviço atua como um proxy reverso, direcionando as requisições para os microserviços apropriados e implementando funcionalidades como autenticação, autorização e rate limiting.

## 🏗️ Arquitetura

- **Framework**: Spring Boot 3.0.5
- **Gateway**: Spring Cloud Gateway
- **Discovery**: Netflix Eureka Client
- **Segurança**: OAuth2 Resource Server
- **Porta**: 8080



## ⚙️ Configuração

### application.properties
```properties
spring.application.name=api-gateway
eureka.client.serviceUrl.defaultZone=http://eureka:password@localhost:8761/eureka

logging.level.root=INFO
logging.level.org.springframework.cloud.gateway.route.RouteDefinitionLocator=INFO
logging.level.org.springframework.cloud.gateway=TRACE

## Route product
spring.cloud.gateway.routes[0].id=product-service
spring.cloud.gateway.routes[0].uri=lb://product-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/product

## Route Order
spring.cloud.gateway.routes[1].id=order-service
spring.cloud.gateway.routes[1].uri=lb://order-service
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/order

## Route Inventory
spring.cloud.gateway.routes[2].id=inventory-service
spring.cloud.gateway.routes[2].uri=lb://inventory-service
spring.cloud.gateway.routes[2].predicates[0]=Path=/api/inventory

server.port=8080

spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8181/realms/spring-boot-microservices-realm
```

## 🚀 Como Executar

### Pré-requisitos
- Java 18+
- Maven 3.6+
- Discovery Server rodando na porta 8761

### Comandos
```bash
# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

## 📡 Rotas Configuradas

| Rota | Serviço Destino | Endpoint |
|------|-----------------|----------|
| `/api/product` | Product Service | `lb://product-service` |
| `/api/order` | Order Service | `lb://order-service` |
| `/api/inventory` | Inventory Service | `lb://inventory-service` |

## 🔧 Funcionalidades

### Roteamento
- Roteamento baseado em path
- Load balancing automático
- Descoberta de serviços via Eureka

### Segurança
- OAuth2 Resource Server configurado
- Integração com Keycloak
- Autenticação JWT

### Logging
- Logs detalhados de roteamento
- Rastreamento de requisições
- Monitoramento de performance

## 🔐 Configuração de Segurança

O gateway está configurado com OAuth2 para autenticação:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Configurações de segurança OAuth2
}
```

### Keycloak Integration
- **Issuer URI**: `http://localhost:8181/realms/spring-boot-microservices-realm`
- **Realm**: `spring-boot-microservices-realm`
- **Client**: Configurado para resource server



## 🔍 Endpoints Disponíveis

### Produtos
- `GET /api/product` - Listar produtos
- `POST /api/product` - Criar produto

### Pedidos
- `GET /api/order` - Listar pedidos
- `POST /api/order` - Criar pedido

### Estoque
- `GET /api/inventory?skuCode=...` - Verificar estoque

## 🛠️ Configuração Avançada

### CORS
```yaml
spring:
  cloud:
    gateway:
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins: "*"
            allowedMethods: "*"
            allowedHeaders: "*"
```

### Rate Limiting
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: rate_limit_route
          uri: lb://service
          predicates:
            - Path=/api/**
          filters:
            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 10
                redis-rate-limiter.burstCapacity: 20
```

## 🔧 Troubleshooting

### Problemas Comuns

1. **Serviço não encontrado**
   - Verificar se o serviço está registrado no Eureka
   - Verificar configuração de rota

2. **Erro de autenticação**
   - Verificar configuração do Keycloak
   - Verificar JWT token

3. **Timeout de conexão**
   - Verificar se o serviço de destino está rodando
   - Verificar configuração de timeout

## 📈 Performance

### Otimizações
- Load balancing automático
- Circuit breaker (configurável)
- Cache de rotas
- Compressão de resposta

### Métricas
- Latência por rota
- Throughput
- Taxa de erro

## 📚 Documentação Adicional

- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- [Spring Security OAuth2](https://spring.io/projects/spring-security-oauth)
- [Keycloak Documentation](https://www.keycloak.org/documentation) 