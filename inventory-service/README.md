# Inventory Service

O Inventory Service é responsável pelo controle de estoque do sistema de e-commerce, gerenciando a disponibilidade de produtos e verificando se SKUs estão em estoque.

## 🎯 Propósito

Este serviço gerencia o inventário de produtos, verificando a disponibilidade de SKUs e fornecendo informações de estoque para outros serviços do sistema.

## 🏗️ Arquitetura

- **Framework**: Spring Boot 3.0.5
- **Database**: MySQL
- **Discovery**: Netflix Eureka Client
- **Porta**: 8082
- **ORM**: Spring Data JPA



## ⚙️ Configuração

### application.properties
```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/inventory-service
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=create-drop
server.port=8082

eureka.client.serviceUrl.defaultZone=http://eureka:password@localhost:8761/eureka
spring.application.name=inventory-service
```

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- MySQL rodando na porta 3306
- Discovery Server rodando na porta 8761

### Comandos
```bash
# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

## 📡 Endpoints

### Estoque
| Método | Endpoint | Descrição | Parâmetros |
|--------|----------|-----------|------------|
| `GET` | `/api/inventory` | Verificar disponibilidade de SKUs | `skuCode` (List) |

### Exemplo de Requisição
```
GET /api/inventory?skuCode=iphone_15&skuCode=macbook_pro
```

### Exemplo de Resposta
```json
[
  {
    "skuCode": "iphone_15",
    "isInStock": true
  },
  {
    "skuCode": "macbook_pro",
    "isInStock": false
  }
]
```

## 🗄️ Modelo de Dados

### Inventory Entity
```java
@Entity
@Table(name = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
```

### DTOs
- **InventoryResponse**: DTO para resposta de verificação de estoque

## 🔧 Funcionalidades

### Controle de Estoque
- Verificação de disponibilidade de SKUs
- Consulta de quantidade em estoque
- Validação de produtos existentes

### Integração
- Registro automático no Eureka
- Descoberta de serviços
- Health checks automáticos

### Processamento
- Verificação em lote de múltiplos SKUs
- Resposta com status de disponibilidade
- Validação de dados de entrada

## 🗄️ Banco de Dados

### MySQL
- **Database**: `inventory-service`
- **Table**: `inventory`
- **Connection**: `jdbc:mysql://localhost:3306/inventory-service`

### Estrutura da Tabela
```sql
CREATE TABLE inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sku_code VARCHAR(255) UNIQUE,
    quantity INT
);
```

### Dados de Exemplo
```sql
INSERT INTO inventory (sku_code, quantity) VALUES
('iphone_15', 100),
('macbook_pro', 50),
('airpods_pro', 200);
```

## 🔄 Integração com Outros Serviços

### Order Service
O Inventory Service é chamado pelo Order Service para verificar a disponibilidade de produtos antes de criar um pedido:

```java
// Order Service chama Inventory Service
GET /api/inventory?skuCode=iphone_15&skuCode=macbook_pro
```

### Resposta para Order Service
```json
[
  {
    "skuCode": "iphone_15",
    "isInStock": true
  },
  {
    "skuCode": "macbook_pro",
    "isInStock": false
  }
]
```

## 🧪 Testes

### Executar Testes
```bash
# Executar todos os testes
mvn test

# Executar testes específicos
mvn test -Dtest=InventoryServiceTest
```



## 🔧 Troubleshooting

### Problemas Comuns

1. **Erro de conexão com MySQL**
   - Verificar se MySQL está rodando na porta 3306
   - Verificar credenciais de acesso
   - Verificar se database `inventory-service` existe

2. **SKU não encontrado**
   - Verificar se SKU existe na tabela inventory
   - Verificar se dados foram inseridos corretamente
   - Verificar logs de consulta

3. **Serviço não registrado no Eureka**
   - Verificar configuração do Eureka Client
   - Verificar conectividade de rede

## 📈 Performance

### Otimizações
- Índices no MySQL para SKU
- Consultas otimizadas para múltiplos SKUs
- Cache de consultas frequentes
- Connection pooling

### Métricas
- Tempo de resposta das verificações
- Taxa de consultas por SKU
- Uso de memória


## 📚 Documentação Adicional

- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Spring Boot Actuator](https://spring.io/projects/spring-boot-actuator) 