# Product Service

O Product Service é responsável pelo gerenciamento de produtos do sistema de e-commerce, incluindo criação, listagem e armazenamento de informações de produtos.

## 🎯 Propósito

Este serviço gerencia todo o ciclo de vida dos produtos, desde sua criação até a disponibilização para vendas, utilizando MongoDB como banco de dados principal.

## 🏗️ Arquitetura

- **Framework**: Spring Boot 3.0.5
- **Database**: MongoDB
- **Discovery**: Netflix Eureka Client
- **Porta**: Dinâmica (porta 0)
- **ORM**: Spring Data MongoDB



## ⚙️ Configuração

### application.properties
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/product-service
eureka.client.serviceUrl.defaultZone=http://eureka:password@localhost:8761/eureka
spring.application.name=product-service
server.port=0
```

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- MongoDB rodando na porta 27017
- Discovery Server rodando na porta 8761

### Comandos
```bash
# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

## 📡 Endpoints

### Produtos
| Método | Endpoint | Descrição | Payload |
|--------|----------|-----------|---------|
| `POST` | `/api/product` | Criar novo produto | `ProductRequest` |
| `GET` | `/api/product` | Listar todos os produtos | - |

### Exemplo de Payload (ProductRequest)
```json
{
  "name": "iPhone 15",
  "description": "Smartphone Apple iPhone 15",
  "price": 4999.99
}
```

### Exemplo de Resposta (ProductResponse)
```json
{
  "id": "507f1f77bcf86cd799439011",
  "name": "iPhone 15",
  "description": "Smartphone Apple iPhone 15",
  "price": 4999.99
}
```

## 🗄️ Modelo de Dados

### Product Entity
```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
```

### DTOs
- **ProductRequest**: DTO para criação de produtos
- **ProductResponse**: DTO para resposta de produtos

## 🔧 Funcionalidades

### Gerenciamento de Produtos
- Criação de novos produtos
- Listagem de todos os produtos
- Validação de dados de entrada
- Persistência em MongoDB

### Integração
- Registro automático no Eureka
- Descoberta de serviços
- Health checks automáticos

### Testes
- Testes unitários configurados
- TestContainers para MongoDB
- Testes de integração

## 🗄️ Banco de Dados

### MongoDB
- **Database**: `product-service`
- **Collection**: `product`
- **URI**: `mongodb://localhost:27017/product-service`

### Estrutura do Documento
```json
{
  "_id": "ObjectId",
  "name": "String",
  "description": "String",
  "price": "BigDecimal"
}
```

## 🧪 Testes

### Executar Testes
```bash
# Executar todos os testes
mvn test

# Executar testes específicos
mvn test -Dtest=ProductServiceTest
```

### TestContainers
O projeto utiliza TestContainers para testes de integração com MongoDB:

```java
@Testcontainers
class ProductServiceApplicationTests {
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
}
```



## 🔧 Troubleshooting

### Problemas Comuns

1. **Erro de conexão com MongoDB**
   - Verificar se MongoDB está rodando na porta 27017
   - Verificar URI de conexão

2. **Produto não criado**
   - Verificar payload da requisição
   - Verificar logs de erro

3. **Serviço não registrado no Eureka**
   - Verificar configuração do Eureka Client
   - Verificar conectividade de rede

## 📈 Performance

### Otimizações
- Índices no MongoDB
- Paginação de resultados
- Cache de consultas frequentes

### Métricas
- Tempo de resposta das APIs
- Taxa de criação de produtos
- Uso de memória



## 📚 Documentação Adicional

- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
- [MongoDB Documentation](https://docs.mongodb.com/)
- [TestContainers](https://www.testcontainers.org/) 