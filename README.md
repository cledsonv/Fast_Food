# Fast Food App

## Descrição
O **Fast Food App** é uma aplicação móvel desenvolvida em Kotlin utilizando o framework Jetpack Compose. A aplicação permite aos usuários visualizar e fazer pedidos de diversos tipos de alimentos, como sorvetes, pizzas, carnes de porco, sanduíches, salsichas, bifes e outros.

## Imagens

<p align="center">
  <img src="https://raw.githubusercontent.com/cledsonv/Fast_Food/master/app/src/assets/foods_page.jpg" width="30%" />
  <img src="https://raw.githubusercontent.com/cledsonv/Fast_Food/master/app/src/assets/all_order.jpg" width="30%" />
  <img src="https://raw.githubusercontent.com/cledsonv/Fast_Food/master/app/src/assets/delete_order.jpg" width="30%" />
</p>

<p align="center">
   <img src="https://raw.githubusercontent.com/cledsonv/Fast_Food/master/app/src/assets/category_page.jpg" width="30%" />
  <img src="https://raw.githubusercontent.com/cledsonv/Fast_Food/master/app/src/assets/select_order.jpg" width="30%" />
  <img src="https://raw.githubusercontent.com/cledsonv/Fast_Food/master/app/src/assets/finish_order.jpg" width="30%" />
 
</p>

## Funcionalidades
- Visualização de diferentes categorias de alimentos.
- Detalhamento de cada alimento com imagem, descrição, preço e avaliação.
- Adição de itens ao pedido com seleção de quantidade.
- Finalização de pedidos.
- Armazenamento de pedidos no banco de dados local.
- Pesquisa de alimentos.

## Tecnologias Utilizadas
- **Linguagem:** Kotlin
- **Framework:** Jetpack Compose
- **Requisição de API:** Retrofit
- **Gerenciamento de Dependências:** Hilt
- **Arquitetura:** MVVM (Model-View-ViewModel)
- **Banco de Dados:** Room
- **Design de Interface:** Atomic Design para organização das pastas.

## Estrutura do Projeto
- `app/src/main/java/com/codeventura/fast_food/`
    - `data/`:
        - `api/`: Contém as rotas e APIs.
        - `dao/`: Contém as interfaces para o banco de dados.
        - `database/`: Contém a configuração do banco de dados Room e DAOs.
        - `mappers/`: Contém os conversores `fromJson` e `toJson`.
        - `model/`: Contém as classes de dados (e.g., `FoodItem.kt`).
        - `repository/`: Contém os repositórios para acesso aos dados.
    - `ui/`:
        - `atomic/`: Contém componentes simples e reutilizáveis, como `NetworkImage` e `Rating`.
        - `molecules/`: Agrupa átomos para criar componentes principais.
        - `organisms/`: Combina moléculas para formar listas e outras estruturas.
        - `pages/`: Contém as telas principais da aplicação (e.g., `FoodPage.kt`, `OrderPage.kt`).
        - `themes/`: Contém as cores e temas do aplicativo.
    - `viewmodel/`:
        - Contém os ViewModels que gerenciam a lógica de negócios e a comunicação com os repositórios de dados.

## Como Executar
1. Clone o repositório:
   ```sh
   git clone https://github.com/cledsonv/Fast_Food.git
   ```
2. Abra o projeto no Android Studio.
3. Construa e execute o projeto no emulador ou em um dispositivo físico.


