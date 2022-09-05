# TCC-Microservices
Exemplo de um projeto de microservices com comunicação entre dois serviços,

Utilizando  api-gateway  com a porta 8765 ele faz o rotiamento para o serviço escolhido que pode ser o boo-service ou cambio-service

Onde um serviço é uma loja de livros e o outro serviço é onde se encontra os valores base das moedas para conversão.


Endereços para visualizações:

Eureka onde é possivel acompanhar os serviçes que estão sendo executados: 

## http://localhost:8761

Loja dos livros, os valor numerico vai do 1 ao 15 (quantidade de livros contido no arquivo flyway),

A conversão é feita de maneira automatica, basta inserir o tipo da moeda que deseja saber o valor que são elas EUR, GBP, ARS, CLP, COP, MXN

## http://localhost:8765/book-service/14/BRL

Caminho para fazer a simulação de cmabio das moedas (O service book-service faz isso de maneira automatica),

Onde você insere o valor que deseja converter, a moeda base e a moeda que deseja retornar

## http://localhost:8765/cambio-service/10/USD/CLP

## Importante

Será necessario fazer os devidos ajustes no  yml para ser usado no seu banco de dados.


## Como realizar os testes do kafka com microsservice e monolitico

	Para realizar os testes é necessário ter o Docker instalado, a partir disso devemos seguir os seguintes passos : 


	Subir instancia do mysql e kafka no docker utilizando o seguinte docker-compose.
		Docker compose mysql + kafka + kafdrop + zookeper :
		 - https://gist.github.com/fabiofsilva92/2141a7a7e67743ce8d7035b964fcd97a
		Para realizar o procedimento, devemos colocar o arquivo docker-compose.yml em uma pasta e abrir o cmd no caminho da pasta, após isso executar o comando : docker-compose up
		Após esse procedimento o docker irá conter o banco de dados necessário para rodar a aplicação e também o kafka e suas configurações.
		Certifique-se que os containeres estão rodando e então siga  com a execução dos serviços na seguinte ordem : 
		
	Iniciar microsservice naming-service
	Iniciar microsservice api-gateway
	Iniciar microsservice book-service (porta 8100)
	Iniciar microsservice book-crud (porta 7000)
	Iniciar microsservice cambio-service (porta 8000)
	Iniciar microsservice cambio-crud (porta 7080)
	Iniciar microsservice kafka consumer
	Iniciar microsservice kafka producer (porta 8087)
	Iniciar monolitico
	
	Após isso devemos realizar o teste no endpoint do producer.
	Para realizar o teste com a mensageria deveremos enviar um POST para um endpoint do nosso producer
		- endpoint : localhost:8087/message
		
		- body: Devemos enviar no body o serviço que queremos testar e a operação, até o momento só implementamos o metodo GET onde trará todos os registros das tabelas do respectivo serviço escolhido
		podemos utilizar o service "BOOK" ou "CAMBIO"
		Exemplo:
				{
					"service": "CAMBIO",
					"type": "GET"
				}
	
	Ao enviar o post, o consumer dos microsserviços e o consumer do monolitico irá receber a mensagem e realizar as mesmas operações ao mesmo tempo, o console dos consumers irão logar o tempo percorrido em cada uma das arquiteturas.
 

