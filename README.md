# MovieLens 20M
## Implementação de estruturas para consulta do Dataset
### Trabalho Final da matéria de Classificação e Pesquisa de Dados

Nesse projeto, foram implementadas as estruturas **Hash Table** e **Trie Tree** para
criar um sistema de pesquisa por:
- Filme (prefixo ou completo) [*Árvore Trie*]
- UserID [*Tabela Hash*]
- Tags [*Árvore Trie*]
- Gênero [*Árvore Trie*]

Todas as estruturas acima guardam os ids dos filmes que esses querem armazenar.
As informações dos filmes (nome, lista de gêneros, notas, número de avaliações, etc)
estão armazenadas em uma Tabela Hash.

A interface gráfica foi criada usando o sistema de criação de telas do IntelliJ
