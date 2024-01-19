# API REST de Produtos

Este projeto se trata de uma API REST para gerenciar produtos. A API permite realizar operações básicas como criação, leitura, atualização e exclusão de produtos.

## Endpoints

### 1. Listar todos os produtos

- **URL:** `/produtos`
- **Método:** GET
- **Descrição:** Retorna uma lista de todos os produtos cadastrados.

### 2. Obter um produto específico

- **URL:** `/produtos/{id}`
- **Método:** GET
- **Descrição:** Retorna os detalhes de um produto com o ID específico.

### Exceções

Ao tentar localizar um produto pelo ID, a API pode lançar exceções em situações específicas. Aqui estão alguns cenários de exceção e como a API os trata:

#### 2.1 Produto não encontrado

- **Status:** 404 Not Found
- **Descrição:** Se a solicitação para localizar um produto contiver um ID que não existe no banco de dados, a API retornará um status 404 indicando que o produto não foi encontrado.

### 3. Adicionar um novo produto

- **URL:** `produtos`
- **Método:** POST
- **Descrição:** Adiciona um novo produto ao banco de dados.
- **Corpo da requisição:**
  ```json
  {
    "nome": "Nome do Produto",
    "valor": 19.99,
  }

### Exceções

Ao tentar cadastrar um produto, a API pode lançar exceções em situações específicas. Aqui estão alguns cenários de exceção e como a API os trata:

#### 3.1 Dados Inválidos

- **Status:** 422 Unprocessable Entity
- **Descrição:** Se a requisição para adicionar um produto contiver dados inválidos, ou em um formato incorreto, a API responderá com um status 422 e uma mensagem indicando os problemas de validação.

### 4. Alterar um produto cadastrado.

- **URL:** `/produtos/{id}`
- **Método:** PUT
- **Descrição:** Altera as informações de um produto cadastrado no banco de dados.
  
### Exceções

Ao tentar alterar um produto, a API pode lançar exceções em situações específicas. Aqui estão alguns cenários de exceção e como a API os trata:

#### 4.1 Produto não encontrado

- **Status:** 404 Not Found
- **Descrição:** Se a solicitação para alterar um produto contiver um ID que não existe no banco de dados, a API retornará um status 404 indicando que o produto não foi encontrado.

### 5. Excluir um produto cadastrado.
- **URL:** `/produtos/{id}`
- **Método:** DELETE
- **Descrição:** Deleta um produto pelo ID cadastrado no banco de dados.

  ## Exceções

  Ao tentar excluir um produto, a API pode lançar exceções em situações específicas. Aqui estão alguns cenários de exceção e como a API os trata:

  #### 5.1 Produto não encontrado

- **Status:** 404 Not Found
- **Descrição:** Se a solicitação para excluir um produto contiver um ID que não existe no banco de dados, a API retornará um status 404 indicando que o produto não foi encontrado.
