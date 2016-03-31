# TrabalhoOOPos
Aplicação Web em Java para cadastro de clientes desenvolvida como trabalho de conclusão da matéria de Desenvolvimento de Aplicações Corporativas II.

# Características
A aplicação segue a arquitetura mvc e dao, utilizando o padrão Facade na operação de acesso e manutenção dos objetos de dados.

# Requisitos atendidos
RF-01 - O sistema deve possibilitar o cadastro de clientes;

RN-01 - O cadastro de um cliente só é considerado válido se os campos: nome, cpf, credito, rua, cep e cidades, estiverem preenchidos;

RN-02 - Um cliente deve ter somente um registro no sistema;

RN-03 - Um o cpf é válido se somente se contiver 11 dígitos;

RN-04 - Cada cliente pode ter somente dois dependentes vinculados;

RN-05 - Cada dependente deve obrigatoriamente ter um parentesco com o cliente.

RN-06 - É obrigatório que o dependente tenha um nome preenchido;

RN-07 - O Crédito do cliente deve ser no mínimo 1000;

RNF-01 - Todo cliente cadastrado deve ter em seu registro a data em que seu cadastro foi realizado no sistema.

#Tecnologias e Padrões utuilizados
ORM - Mapeamento relacional para banco de dados = Hibernate com JPA.

Framework MVC - VRaptor

Base de Dados - MySQL
