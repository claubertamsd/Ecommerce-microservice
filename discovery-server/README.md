# Discovery Server

O Discovery Server é o componente central de descoberta de serviços da arquitetura de microserviços, utilizando Netflix Eureka.

## 🎯 Propósito

Este serviço atua como um registro central onde todos os microserviços se registram e descobrem uns aos outros, permitindo comunicação dinâmica entre os serviços.

## 🏗️ Arquitetura

- **Framework**: Spring Boot 3.0.5
- **Discovery**: Netflix Eureka Server
- **Segurança**: Spring Security
- **Porta**: 8761



## ⚙️ Configuração

### application.properties
```properties
eureka.instance.hostname=localhost
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
server.port=8761
spring.security.user.name=eureka
spring.security.user.password=password
spring.application.name=discovery-server
```

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+

### Comandos
```bash
# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

## 📊 Dashboard

- **URL**: `http://localhost:8761`
- **Usuário**: `eureka`
- **Senha**: `password`

## 🔧 Funcionalidades

### Registro de Serviços
- Todos os microserviços se registram automaticamente
- Descoberta automática de novos serviços
- Health checks automáticos

### Segurança
- Autenticação básica configurada
- Proteção contra acesso não autorizado

### Monitoramento
- Dashboard web para visualização de serviços
- Status de saúde dos serviços
- Informações de instâncias

## 📡 Endpoints

| Endpoint | Descrição |
|----------|-----------|
| `/` | Dashboard principal |
| `/eureka/` | API do Eureka |
| `/actuator/` | Endpoints de monitoramento |

## 🔍 Serviços Registrados

O Discovery Server gerencia os seguintes serviços:

- **api-gateway** - Gateway de entrada
- **product-service** - Serviço de produtos
- **order-service** - Serviço de pedidos
- **inventory-service** - Serviço de estoque

## 🛠️ Configuração de Segurança

O serviço está configurado com Spring Security para proteger o dashboard:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Configurações de segurança
}
```



## 🔧 Troubleshooting

### Problemas Comuns

1. **Serviço não aparece no dashboard**
   - Verificar se o serviço está rodando
   - Verificar configuração do Eureka Client

2. **Erro de autenticação**
   - Verificar credenciais: `eureka/password`
   - Verificar configuração de segurança

3. **Porta já em uso**
   - Verificar se não há outro processo na porta 8761
   - Alterar porta no `application.properties`

## 📚 Documentação Adicional

- [Spring Cloud Netflix Eureka](https://spring.io/projects/spring-cloud-netflix)
- [Spring Boot Security](https://spring.io/projects/spring-security) 