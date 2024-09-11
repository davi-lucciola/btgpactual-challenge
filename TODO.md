# Plano de Trabalho

O plano de trabalho será dividido em 3 etapas:

1. Configuração do Projeto e da Infraestrutura (4 Horas)
2. Implementação das Filas e Consumers do RabbitMQ (2 Horas)
3. Implementação das Funcionalidades (10 Horas)

Dentro de cada etapa, haverá mais atividades.

## Configuração do Projeto e da Infraestrutura

- [x] Task 1: Criar Projeto Spring Boot e adicionar Depêndencias (1 Hora)
- [x] Task 2: Criar containers do RabbitMQ, PostgreSQL e aplicação e conecta-los (3 Horas)

## Implementação das Filas

- [x] Task 3: Implementar configurações do RabbitMQ (1 Hora)
- [x] Task 4: Implementar consumidor para receber mensagens (1 Hora)

## Implementação das Filas e Consumers do RabbitMQ

- [x] Task 5 : Implementar funcionalidade de processar pedido e testes automatizados (5 Horas).

- [ ] Task 6: Implementando endpoint para detalhar um pedido e testes automatizados (1 Hora)

- [ ] Task 7: Implementando endpoint para listar todos os pedidos de forma paginada, com filtro por cliente e testes automatizados (2 Horas e 30 Minutos)

- [ ] Task 8: Implementar endpoint para listar pedidos dado o codigo do cliente e testes automatizados (30 Minutos)

# Mudança de Planos

Ao começar a realizar as implementações, acabei gastando mais tempo do que imaginava para serializar as consultas
em queries nativas, então após fazer a funcionalidade de processamento do pedido, acabei implementando todas as 
funcionalidades de buscas de dados (não implementei a paginação) de uma só vez, e acabei deixando a configuração dos 
outros testes para depois:

## Implementação da API Rest

- [x] Task 6: Implementando endpoint para listar todos os pedidos, com filtro por cliente e preço (3 Horas)

- [x] Task 7: Implementando endpoint para detalhar um pedido (30 Minutos)

- [x] Task 8: Implementar endpoint para listar pedidos dado o codigo do cliente (30 Minutos)

Na execução, as tarefas 6, 7 e 8 acima, respectivamente foram executadas ao invés das declaradas no plano de trabalho.
Então acabou sobrando as seguintes tarefas a mais:

## Testes Automatizados

- [ ] Task 9: Configurar banco h2 no ambiente de testes

- [ ] Task 10: Implementar testes automatizados das consultas de pedidos
