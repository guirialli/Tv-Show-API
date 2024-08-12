
# Tv Show API

## Descrição

O **Tv Show API** é um projeto desenvolvido para facilitar a interação com dados de séries de TV, utilizando a API do IMDb. A aplicação foi construída com Java Spring e faz uso de funções assíncronas para obter e armazenar dados localmente. Além disso, oferece funcionalidades para filtrar os melhores episódios de uma série ou temporada.

## Funcionalidades

- **Integração com IMDb API:** Obtém informações sobre séries e episódios diretamente da API do IMDb.
- **Armazenamento Local:** Salva dados localmente para acesso rápido e eficiente.
- **Filtragem de Episódios:** Permite filtrar e visualizar os melhores episódios de uma série ou temporada.

## Tecnologias Utilizadas

- **Java Spring:** Framework utilizado para o desenvolvimento da aplicação.
- **Async Functions:** Para operações assíncronas e otimização do desempenho.
- **IMDB API:** Fonte dos dados das séries e episódios.

## Instalação

Para rodar o Tv Show API em sua máquina, siga os passos abaixo:

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/guirialli/Tv-Show-API.git
   ```

2. **Navegue até o diretório do projeto:**

   ```bash
   cd Tv-Show-API
   ```

3. **Compile e execute o projeto:**

   Certifique-se de que você possui o [Maven](https://maven.apache.org/) instalado e então execute:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   Ou, se preferir, você pode usar um IDE como IntelliJ IDEA ou Eclipse para importar o projeto e executá-lo diretamente.

## Uso

Após a instalação e execução da aplicação, você pode interagir com a API usando os seguintes endpoints:

- **Buscar informações no repositório local ou no IMDb e salvar localmente:**

  ```http
  GET /tvshow?title={title}&season={season}&episode={episode}
  ```

  - `title` (obrigatório): Título da série.
  - `season` (opcional): Número da temporada.
  - `episode` (opcional): Número do episódio.

- **Buscar informações diretamente do IMDb:**

  ```http
  GET /tvshow/imdb?title={title}&season={season}&episode={episode}
  ```

  - `title` (obrigatório): Título da série.
  - `season` (opcional): Número da temporada.
  - `episode` (opcional): Número do episódio.

- **Filtrar pelos melhores episódios de uma série:**

  ```http
  GET /tvshow/best?title={title}&season={season}
  ```

  - `title` (obrigatório): Título da série.
  - `season` (opcional): Número da temporada.

- **Buscar os melhores episódios diretamente do IMDb:**

  ```http
  GET /tvshow/imdb/best?title={title}
  ```

  - `title` (obrigatório): Título da série.

## Contribuições

Se você quiser contribuir para o projeto, por favor, faça um fork do repositório e envie um pull request com suas melhorias ou correções.

## Contato

Desenvolvedor: Guilherme Rialli Oliviera  
E-mail: [gui.rialli@gmail.com](mailto:gui.rialli@gmail.com)

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
