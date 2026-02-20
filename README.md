# Coupon_API

Objetivo
--------
Este projeto é um desafio técnico para a criação de uma API REST para gerenciamento de cupons de desconto. A API permite criar, consultar e desativar (soft delete) cupons, garantindo as validações de regras de negócio essenciais:
- código alfanumérico com tamanho exatamente 6 (após remoção de caracteres especiais),
- desconto com saldo mínimo de 0.5 (sem máximo predeterminado),
- não permitir criar cupom com data de expiração no passado,
- não permitir deletar (soft delete) um cupom que já esteja deletado.

Tecnologias
-----------
- Java 17
- Spring Boot 3 
- H2 Database (ambiente de desenvolvimento/testes)
- springdoc-openapi (Swagger UI)
- Docker & Docker Compose

Como executar 
---------------------
Pré-requisitos:
- Java 17+
- Maven 
- Docker & Docker Compose

## Passo 1
Suba a API via Docker: 

docker-compose up -d

A aplicação estará disponível em http://localhost:8080/coupon_api.
-----------------------------------------------------------------------------------

# Documentação da API:

Abaixo esta os detalhes dos Endpoints e como interegir com eles


## Criar Cupom
- URL: /v1/coupons
- Método: POST
- Resposta esperada: 201 Created
- Regras de negócio:
- code: string alfanumérica, exatamente 6 caracteres (antes da validação o sistema remove caracteres especiais; o resultado deve ter 6 chars).
- discountValue: mínimo permitido = 0.5 (BigDecimal).
- expirationDate: deve ser no futuro (não aceita data no passado).
- published: boolean define se o cupom inicia como ACTIVE (true) ou INACTIVE (false).

## Exemplo de requisição:
{
  "code": "ABC123",
  "description": "Cupom de desconto de verão",
  "discountValue": 15.00,
  "expirationDate": "2025-12-31T23:59:59",
  "published": true
}

##  Retorno Esperado:
{
  "id": "59728027-e986-4827-a925-189978d77d07",
  "code": "ABC123",
  "description": "Cupom de desconto de verão",
  "discountValue": 15.00,
  "createdAt": "2024-01-01T10:00:00",
  "expirationDate": "2025-12-31T23:59:59",
  "deletedAt": null,
  "status": "ACTIVE",
  "published": true,
  "redeemed": false
}

-----------------------------------------------------------------------------------
## Detalhar Cupom:
- URL: /v1/coupons/{id}
- Método: GET
- Resposta esperada: 200 OK
- Retorna a mesma estrutura do objeto de resposta da criação.

  
## Deletar Cupom (Soft Delete):
- URL: /v1/coupons/{id}
- Comportamento: marca o cupom como DELETED e popula deletedAt.
- Resposta esperada: 204 No Content (sem corpo).
- Regras: não é permitido deletar um cupom que já está com status = DELETED — tentativa resultará em erro (conflito).


