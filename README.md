# TCC-Microservices
Exemplo de um projeto de microservices com comunicação entre dois serviços,

Utilizando  api-gateway  com a porta 8765 ele faz o rotiamento para o serviço escolhido que pode ser o boo-service ou cambio-service

Onde um serviço é uma loja de livros e o outro serviço é onde se encontra os valores base das moedas para conversão.

## Importante

Será necessario fazer os devidos ajustes no  yml para ser usado no seu banco de dados.

Endereços para visualizações:

Eureka onde é possivel acompanhar os serviçes que estão sendo executados: 

## http://localhost:8761

Loja dos livros, os valor numerico vai do 1 ao 15 (quantidade de livros contido no arquivo flyway),

A conversão é feita de maneira automatica, basta inserir o tipo da moeda que deseja saber o valor que são elas EUR, GBP, ARS, CLP, COP, MXN

## http://localhost:8765/book-service/14/BRL

Caminho para fazer a simulação de cmabio das moedas (O service book-service faz isso de maneira automatica),

Onde você insere o valor que deseja converter, a moeda base e a moeda que deseja retornar

## http://localhost:8765/cambio-service/10/USD/CLP
