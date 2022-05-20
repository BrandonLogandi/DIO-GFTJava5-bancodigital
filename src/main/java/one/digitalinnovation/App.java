package one.digitalinnovation;

import java.util.Scanner;

import one.digitalinnovation.banco.*;
import one.digitalinnovation.banco.cliente.*;
import one.digitalinnovation.banco.contas.Conta;

public final class App {

    private static Scanner scanner = new Scanner(System.in);
    private static BancoFacade bancoFacade = new BancoFacade();

    public static void main(String[] args) {
        userInput();
    }

    private static void userInput() {
        System.out.print("Digite a opcao: ");
        try {
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    cadastrarCliente();
                    userInput();
                    break;
                case 2:
                    System.out.println(encontrarCliente());
                    userInput();
                    break;
                case 3:
                    bancoFacade.listarClientes();
                    userInput();
                    break;
                case 4:
                    cadastrarConta();
                    userInput();
                    break;
                case 5:
                    System.out.println(encontrarConta());
                    userInput();
                    break;
                case 6:
                    bancoFacade.listarContas();
                    userInput();
                    break;
                case 7:
                    sacar();
                    userInput();
                    break;
                case 8:
                    depositar();
                    userInput();
                    break;
                case 9:
                    transferir();
                    userInput();
                    break;
                case 10:
                    imprimirExtrato();
                    userInput();
                    break;
                case 11:
                    bancoFacade.listarAllTransacoes();
                    userInput();
                    break;
                case 12:
                    System.exit(0);
                default:
                    throw new Exception("Opcao invalida");
            }
        } catch (Exception e) {
            System.out.printf("Erro: %s\n", e.getMessage());
            userInput();
        }
        
    }

    private static void cadastrarCliente() throws Exception {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF/CNPJ: ");
        String identificador = scanner.nextLine();
        bancoFacade.cadastrarCliente(new Cliente(nome, identificador));
    }

    private static Cliente encontrarCliente() throws Exception {
        System.out.print("Digite o CPF/CNPJ: ");
        return bancoFacade.encontrarCliente(scanner.nextLine());
    }

    private static void cadastrarConta() throws Exception {
        System.out.print("CPF/CNPJ do titular: ");
        Cliente titular = bancoFacade.encontrarCliente(scanner.nextLine());
        System.out.print("Crie uma senha: ");
        bancoFacade.cadastrarConta(titular, scanner.nextLine());
    }

    private static Conta encontrarConta() throws Exception {
        System.out.print("CPF/CNPJ do titular\nOU\nAgencia e Numero da conta (ex.: 0 000):\n");
        String input = scanner.nextLine();
        String[] dados = input.split(" ");

        if(dados.length == 2) {
            int agencia = Integer.parseInt(dados[0]);
            int numero = Integer.parseInt(dados[1]);

            return (bancoFacade.encontrarConta(agencia, numero));
        }
        else {
            return (bancoFacade.encontrarConta(input));
        }

    }

    private static void sacar() throws Exception {
        Conta c = encontrarConta();
        System.out.print("Valor a sacar: R$");
        double valor = Double.parseDouble(scanner.nextLine());

        bancoFacade.sacar(c, valor);
    }

    private static void depositar() throws Exception {
        Conta c = encontrarConta();
        System.out.print("Valor a depositar: R$");
        double valor = Double.parseDouble(scanner.nextLine());

        bancoFacade.depositar(c, valor);
    }

    private static void transferir() throws Exception {
        System.out.println("Transferindo de: ");
        Conta remetente = encontrarConta();
        System.out.println("Transferindo para: ");
        Conta destinatario = encontrarConta();

        System.out.print("Valor a transferir: R$");
        double valor = Double.parseDouble(scanner.nextLine());

        bancoFacade.transferir(valor, remetente, destinatario);
    }

    private static void imprimirExtrato() throws Exception {
        bancoFacade.imprimirExtrato(encontrarConta());
    }
        
}

