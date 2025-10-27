## Projeto Individual PDM - Catalog Cinema
&nbsp;&nbsp;&nbsp;Este projeto consiste em um aplicativo Android desenvolvido para a disciplina de Programação para Dispositivos Móveis. O app permite que o usuário pesquise filmes e séries, visualize uma lista de resultados, adicione-os aos favoritos e, ao selecionar um item, veja seus detalhes completos, incluindo a opção de buscar o trailer no YouTube.

## Prints de Tela

| Tela Inicial | Busca de item | Adicionar item em Favoritos | 
| :---: | :---: | :---: | 
| <img src="https://github.com/user-attachments/assets/ef25f9e4-7006-4e8f-8eb2-96eeca2a1f97" alt="Tela Inicial" width="300"> | <img src="https://github.com/user-attachments/assets/a377ea00-3ebc-4e93-8d69-c41b2d38a4ed" alt="Busca de item" width="300"> | <img src="https://github.com/user-attachments/assets/06afce4c-c049-4962-89fc-2bf2a1edfa1c" alt="Adicionar item em Favoritos" width="300"> | 

|  Tela de Favoritos| Tela de Detalhes | Pesquisa no Youtube |
| :---: | :---: | :---: | 
| <img src="https://github.com/user-attachments/assets/6b707eec-8d58-438e-b378-cf6ebd7062ce" alt="Tela de favoritos" width="300"> | <img src="https://github.com/user-attachments/assets/defb0f16-84cd-4b39-8316-f1ca8a894c24" alt="Tela de Detalhes" width="300"> | <img src="https://github.com/user-attachments/assets/e5253ce2-0b33-4c68-b2c8-8298c12b04dd" alt="Pesquisa no YouTube" width="300"> | 


## Funcionalidades

**Busca em Tempo Real:** Campo de texto para pesquisar filmes e séries.

**Lista de Resultados:** Exibe os resultados da busca em um RecyclerView, mostrando o pôster e o título.

**Gestão de Favoritos:** Permite ao usuário adicionar e remover filmes da sua lista de favoritos, acessível através de um ícone na tela de busca.

**Visualização de Detalhes:** Ao selecionar um item (seja da lista de busca ou da tela de favoritos), o usuário é levado a uma tela com informações detalhadas, como sinopse, data de lançamento, duração e avaliação em estrelas.

**Integração com YouTube:** Um botão na tela de detalhes dispara uma Intent para buscar o trailer do filme ou série diretamente no YouTube.

**Interface Intuitiva:** Design limpo e navegação simples entre as telas.

## API Utilizada

&nbsp;&nbsp;&nbsp;O projeto consome dados da OMDB (The Open Movie Database). Esta API fornece informações detalhadas sobre filmes e séries a partir de buscas por título ou por ID.

> URL Base:

    http://www.omdbapi.com
   
> Autenticação: Requer uma chave de API (API Key) gratuita, que pode ser obtida no site oficial.

## Como Executar

&nbsp;&nbsp;&nbsp;Para executar este projeto, siga os passos abaixo:
  
Clonar o Repositório

    git clone https://github.com/amandamatosb/CatalogCinema

Abrir no Android Studio

> Abra o Android Studio e selecione Open an existing project.
  
> Navegue até a pasta do projeto clonado e abra-o.

Compilar e Executar

> Aguarde o Android Studio sincronizar o projeto com os arquivos Gradle.
   
> Compile e execute o aplicativo em um emulador ou dispositivo físico.
