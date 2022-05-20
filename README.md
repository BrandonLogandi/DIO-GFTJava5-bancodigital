# DIO GFT Start #5 - Banco Digital

Este repositório contém minha resolução do desafio de projeto "Criando um Banco Digital com Java e Orientação a Objetos" da plataforma Digital Innovation One, adicionando algumas funcionalidades úteis e relevantes.  

## Funcionalidades implementadas:

- Banco é salvo em XML utilizando a biblioteca xStream

- Contas poupança rendem diariamente

  ![image](https://user-images.githubusercontent.com/59068101/169530773-8530f269-edca-4d05-9f54-2c1b990f20ba.png)
  
- [Histórico de transações é mantido tanto para contas quanto para o banco](https://github.com/BrandonLogandi/DIO-GFTJava5-bancodigital/blob/master/src/main/java/one/digitalinnovation/banco/Transacao.java)

- Menu de opções

  ![image](https://user-images.githubusercontent.com/59068101/169530407-5901525a-7f4d-4c82-a454-f30628c35cea.png)
  
- [Fachada para controlar o banco](https://github.com/BrandonLogandi/DIO-GFTJava5-bancodigital/blob/master/src/main/java/one/digitalinnovation/banco/BancoFacade.java)

## Dependências (inclusas no [pom.xml](https://github.com/BrandonLogandi/DIO-GFTJava5-bancodigital/blob/master/pom.xml)):
- Java 8
- [xStream](https://x-stream.github.io)
- [Lombok](https://projectlombok.org)
