# MinicursoSBSI

#### Informações gerais do minicurso:
* **Evento:** XI Simpósio Brasileiro de Sistemas de Informação
* **Ministrantes:** Ernesto Veiga([@ErnestoVeiga](https://github.com/ErnestoVeiga)), Márcio Vinícius Oliveira Sena ([@marciovsena](https://github.com/marciovsena)) e Renato Bulcão Neto
* **Tema:** Padrões, ferramentas e boas práticas no desenvolvimento de software para Web
* **Data:** 28 de maio
* **Carga horária:** 8 horas

#### Resumo do minicurso:
*"A Web Semântica é uma Web de dados descritos e interligados de maneira a se estabelecer um contexto ou semântica que adere a uma linguagem e regras gramaticais bem definidas. Para atingir esse propósito, foram criadas especificações para representar informação na Web de forma padronizada, desde o nível de caractere até o de expressão semântica e lógica. Paralelamente, foram também desenvolvidos frameworks, APIs, processadores de consultas e máquinas de inferência com suporte a essas especificações. Nesse contexto, esta proposta de minicurso visa apresentar, com enfoque prático, o uso conjunto de ferramentas, especificações e boas práticas no desenvolvimento de software para Web Semântica, assunto diretamente relacionado ao desenvolvimento de sistemas de informação inteligentes. Com base em protótipos de aplicações desenvolvidos pelos autores, serão discutidas especificações do Consórcio W3 quanto ao seu papel e uso conjunto com o framework Apache Jena e a máquina de inferência Pellet. Após oito horas de curso, espera-se que cada participante seja capaz, principalmente, de construir uma aplicação com as capacidades de armazenamento, consulta e inferência de dados com semântica explícita."*

#### Principais dependências:
* Tecnologias e versões utilizadas - *estão na pasta lib*: 
  * **[Apache Jena](http://jena.apache.org/)**: 2.10.0
  * **[TDB](http://jena.apache.org/documentation/tdb/index.html)**: 0.10.0
  * **[Pellet](https://github.com/Complexible/pellet)**: Última versão
* Ontologias - *estão incluídas no projeto*
 *  **[FOAF](http://xmlns.com/foaf/spec/):** - Friend of a Friend
 *  **ACM CS** 

#### Aplicações e requisitos:
* src/app1
 * **Título:** Recomendação de Revisores de Artigos Científicos 
 * **Problema:** encontrar revisores para artigos científicos pode ser um problema se não se conhecer um
pesquisador especialista na área de conhecimento de cada artigo
 * **Solução:** sabendo-se a(s) área(s) de conhecimento de um artigo científico, deve-se localizar especialista(s) dessa mesma área de conhecimento, ou de subáreas da mesma. Esses revisores são candidatos em potencial para revisar os devidos artigos.
 * **Ontologias:** FOAF e ACM CS
* src/app2
 * **Título:** Socialização de pessoas com interesses comuns 
 * **Problema:** uma pessoa deseja se socializar com outras pessoas que possuam interessem em comum, por exemplo, que têm interesse nas mesmas áreas de conhecimento
 * **Solução:** sabendo-se a(s) área(s) de conhecimento na(s) qual(is) a pessoa tem interesse, deve-se localizar outras pessoas que possuam as mesmas áreas de interesse, ou interesse em subáreas dessas. Essas pessoas podem ser socializadas, por exemplo, por meio da criação de grupos, cujo tema/título representa a área de conhecimento em questão.
 * **Ontologias:** FOAF e ACM CS
