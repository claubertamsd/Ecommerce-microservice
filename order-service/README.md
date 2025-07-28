# Order Service

O Order Service é responsável pelo gerenciamento de pedidos do sistema de e-commerce, incluindo criação de pedidos, verificação de estoque e integração com outros serviços.

## 🎯 Propósito

Este serviço gerencia todo o processo de criação de pedidos, verificando a disponibilidade de produtos em estoque e mantendo o histórico de pedidos dos clientes.

## 🏗️ Arquitetura

- **Framework**: Spring Boot 3.0.5
- **Database**: MySQL
- **Discovery**: Netflix Eureka Client
- **Web Client**: Spring WebFlux
- **Porta**: 8081
- **ORM**: Spring Data JPA



## ⚙️ Configuração

### application.properties
```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/order_service
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update

server.port=8081
eureka.client.serviceUrl.defaultZone=http://eureka:password@localhost:8761/eureka
spring.application.name=order-service
```

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- MySQL rodando na porta 3306
- Discovery Server rodando na porta 8761
- Inventory Service rodando

### Comandos
```bash
# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

## 📡 Endpoints

### Pedidos
| Método | Endpoint | Descrição | Payload |
|--------|----------|-----------|---------|
| `POST` | `/api/order` | Criar novo pedido | `OrderRequest` |
| `GET` | `/api/order` | Listar todos os pedidos | - |

### Exemplo de Payload (OrderRequest)
```json
{
  "orderLineItemsDtoList": [
    {
      "skuCode": "iphone_15",
      "price": 4999.99,
      "quantity": 2
    }
  ]
}
```

### Exemplo de Resposta
```json
{
  "id": 1,
  "orderNumber": "ORDER-2024-001",
  "orderLineItemsList": [
    {
      "id": 1,
      "skuCode": "iphone_15",
      "price": 4999.99,
      "quantity": 2
    }
  ],
  "orderDate": "2024-01-15T10:30:00"
}
```

## 🗄️ Modelo de Dados

### Order Entity
```java
@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;
}
```

### OrderLineItems Entity
```java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
```

### DTOs
- **OrderRequest**: DTO para criação de pedidos
- **OrderLineItemsDto**: DTO para itens do pedido
- **InventoryResponse**: DTO para resposta do Inventory Service

## 🔧 Funcionalidades

### Gerenciamento de Pedidos
- Criação de novos pedidos
- Listagem de todos os pedidos
- Verificação de estoque via Inventory Service
- Validação de dados de entrada

### Integração com Outros Serviços
- Comunicação com Inventory Service via WebClient
- Verificação de disponibilidade de produtos
- Registro automático no Eureka

### Processamento de Pedidos
- Geração automática de número do pedido
- Validação de quantidade em estoque
- Persistência em MySQL

## 🗄️ Banco de Dados

### MySQL
- **Database**: `order_service`
- **Tables**: `orders`, `order_line_items`
- **Connection**: `jdbc:mysql://localhost:3306/order_service`

### Estrutura das Tabelas
```sql
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_number VARCHAR(255)
);

CREATE TABLE order_line_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sku_code VARCHAR(255),
    price DECIMAL(10,2),
    quantity INT,
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);
```

## 🔄 Integração com Inventory Service

### WebClient Configuration
```java
@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
```



## 🧪 Testes

### Executar Testes
```bash
# Executar todos os testes
mvn test

# Executar testes específicos
mvn test -Dtest=OrderServiceTest
```



## 🔧 Troubleshooting

### Problemas Comuns

1. **Erro de conexão com MySQL**
   - Verificar se MySQL está rodando na porta 3306
   - Verificar credenciais de acesso
   - Verificar se database `order_service` existe

2. **Erro de verificação de estoque**
   - Verificar se Inventory Service está rodando
   - Verificar se SKU existe no Inventory Service
   - Verificar logs de comunicação

3. **Pedido não criado**
   - Verificar payload da requisição
   - Verificar se produtos estão em estoque
   - Verificar logs de erro

## 📈 Performance

### Otimizações
- Índices no MySQL
- Paginação de resultados
- Cache de consultas frequentes
- Connection pooling

### Métricas
- Tempo de resposta das APIs
- Taxa de criação de pedidos
- Taxa de sucesso de verificações de estoque


## 📚 Documentação Adicional

- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring WebFlux](https://spring.io/projects/spring-webflux)
- [MySQL Documentation](https://dev.mysql.com/doc/) 