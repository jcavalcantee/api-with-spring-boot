# API REST de Produtos

Este projeto se trata de uma API REST para gerenciar produtos. A API permite realizar operações básicas como criação, leitura, atualização e exclusão de produtos.

## Endpoints

### 1. Listar todos os produtos

- **URL:** `/api/v1/products`
- **Método:** GET
- **Descrição:** Retorna uma lista de todos os produtos cadastrados.

### 2. Obter um produto específico

- **URL:** `/api/v1/products{id}`
- **Método:** GET
- **Descrição:** Retorna os detalhes de um product com o ID específico.

 ### 3. Obter um produto específico

- **URL:** `/api/v1/products{name}`
- **Método:** GET
- **Descrição:** Retorna os detalhes de um product com o nome específico.

### Exceções

Ao tentar localizar um produto pelo ID, a API pode lançar exceções em situações específicas. Aqui estão alguns cenários de exceção e como a API os trata:

#### 2.1 Produto não encontrado

- **Status:** 404 Not Found
- **Descrição:** Se a solicitação para localizar um produto contiver um ID que não existe no banco de dados, a API retornará um status 404 indicando que o product não foi encontrado.

### 3. Adicionar um novo product

- **URL:** `/api/v1/products`
- **Método:** POST
- **Descrição:** Adiciona um novo produto ao banco de dados.
- **Corpo da requisição:**
  ```json
  {
    "name": "Nome do Produto",
    "price": 19.99
  }

### Exceções

Ao tentar cadastrar um producto, a API pode lançar exceções em situações específicas. Aqui estão alguns cenários de exceção e como a API os trata:

#### 3.1 Dados Inválidos

- **Status:** 422 Unprocessable Entity
- **Descrição:** Se a requisição para adicionar um produto contiver dados inválidos, ou em um formato incorreto, a API responderá com um status 422 e uma mensagem indicando os problemas de validação.

### 4. Alterar um product cadastrado.

- **URL:** `/api/v1/products/update/{id}`
- **Método:** PUT
- **Descrição:** Altera as informações de um produto cadastrado no banco de dados.
  
### Exceções

Ao tentar alterar um produto, a API pode lançar exceções em situações específicas. Aqui estão alguns cenários de exceção e como a API os trata:

#### 4.1 Produto não encontrado

- **Status:** 404 Not Found
- **Descrição:** Se a solicitação para alterar um produto contiver um ID que não existe no banco de dados, a API retornará um status 404 indicando que o product não foi encontrado.

### 5. Excluir um product cadastrado.
- **URL:** `/api/v1/products/delete/{id}`
- **Método:** DELETE
- **Descrição:** Deleta um produto pelo ID cadastrado no banco de dados.

  ## Exceções

  Ao tentar excluir um produto, a API pode lançar exceções em situações específicas. Aqui estão alguns cenários de exceção e como a API os trata:

  #### 5.1 Produto não encontrado

- **Status:** 404 Not Found
- **Descrição:** Se a solicitação para excluir um produto contiver um ID que não existe no banco de dados, a API retornará um status 404 indicando que o produto não foi encontrado.
