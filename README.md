### Nota sobre a Autoria do Commit

> Olá, professor!
> Gostaria de esclarecer que todo o desenvolvimento deste projeto foi realizado por mim. Para a codificação, utilizei o computador do Feliph e, embora eu tenha feito o login e o push com a minha conta do GitHub, a configuração do Git na máquina dele registrou o nome dele como autor.

## Atividade 2 PDM - Catalog Cinema (App de consulta de filmes e séries)
&nbsp;&nbsp;&nbsp;Este projeto consiste em um aplicativo Android desenvolvido para a disciplina de Programação para Dispositivos Móveis. O app permite que o usuário pesquise filmes e séries, visualize uma lista de resultados e, ao selecionar um item, veja seus detalhes completos em uma nova tela.

## Prints de Tela

| Tela Inicial| Tela de Busca | Tela de Detalhes |
| :---: | :---: | :---: |
| <img src="https://github.com/user-attachments/assets/9d855161-216f-438d-adc7-a8048d90c76a" alt="Tela Inicial" width="300"> | <img src="https://github.com/user-attachments/assets/24b2e882-97e3-4b31-b016-1fb1073808f5" alt="Tela de Resultado" width="300"> | <img src="https://github.com/user-attachments/assets/dc9274d6-d8b8-4747-8134-b1313997f8ce" alt="Tela de Detalhes" width="300"> |

## Funcionalidades

**Busca em Tempo Real:** Campo de texto para pesquisar filmes e séries.

**Lista de Resultados:** Exibe os resultados da busca em um RecyclerView, mostrando o pôster e o título de cada obra.

**Tela de Detalhes:** Apresenta informações detalhadas do filme selecionado, como sinopse, data de lançamento, duração e avaliação.

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
