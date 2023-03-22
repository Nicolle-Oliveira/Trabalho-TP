# Trabalho-TP
Criação de um Chat utilizando os conceitos de Socket e Multithreading. 

## Classes Prinicipais
<strong>Cliente/Client</strong><br>
Cada usuário criará uma instância do cliente e fará uma conexão com o servidor socket. O cliente deverá informar o endereço do server socket e a respectiva porta, por isso é necessário executar o Server.java antes.

<strong>Servidor/Server</strong><br>
servidor servirá como unidade centralizadora de todas as conexões recebidas via socket e terá como responsabilidade o envio de uma mensagem (recebida de um cliente) para todos os demais conectados no servidor. Quando um cliente se conecta a ele o mesmo cria uma Thread para aquele cliente, ou seja, cada conexão terá sua respectiva Thread e o servidor fará a gestão disso;

## Checklist - 22/03
<li> Servidor </li>
<li> Cliente </li>

## Interface Gráfica
<a href="https://www.devmedia.com.br/como-criar-um-chat-multithread-com-socket-em-java/33639">Referência Dev Media</a>
<p>Alguns testes iniciais foram feitos, arquivo Main.java</p>

## Equipe
Artur Gonzaga, Nicolle Oliveira, Vitória Honório
