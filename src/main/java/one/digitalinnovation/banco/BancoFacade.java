package one.digitalinnovation.banco;

import java.util.Scanner;

import one.digitalinnovation.banco.cliente.Cliente;
import one.digitalinnovation.banco.contas.*;

public class BancoFacade {

    private Banco banco = BancoSerializer.carregarBanco();
    private static Scanner scanner = new Scanner(System.in);

    public BancoFacade() {
        renderAllPoupanca();
    }

    private void renderAllPoupanca() {
        for (Conta c : banco.getContas()) {
            if (c instanceof ContaPoupanca) {
                ContaPoupanca poupanca = (ContaPoupanca) c;
                poupanca.render();
            }
        }
        BancoSerializer.salvarBanco(banco);
    }

    public void cadastrarCliente(Cliente c) throws Exception {
        if(banco.getClientes().contains(c))
            throw new Exception("Cliente com mesmo CPF/CNPJ já cadastrado");
            
        banco.getClientes().add(c);

        BancoSerializer.salvarBanco(banco);
        System.out.println("Cliente cadastrado com sucesso");
    }

    public Cliente encontrarCliente(String identificador) throws Exception {
        for (Cliente c : banco.getClientes()) 
            if (c.getIdentificador().equals(identificador)) 
                return c;
            
        throw new Exception(String.format("Cliente com CPF/CNPJ %s não encontrado", identificador));
    }

    public void listarClientes() {
        for (Cliente c : banco.getClientes()) 
            System.out.println(c.toString());
    }

    public void cadastrarConta(Cliente titular, String senha) throws Exception {
        for (Conta c : banco.getContas()) 
            if(c.getCliente().equals(titular))
                throw new Exception("Cliente já possui uma conta");
            
        System.out.print("Tipo de conta: [\"C\"orrente/\"P\"oupanca]: ");
        switch (scanner.nextLine().toUpperCase()) {
            case "C":
                banco.getContas().add(new ContaCorrente(titular, senha, banco.AGENCIA_PADRAO, banco.incrementSequential()));
                break;
            case "P":
                banco.getContas().add(new ContaPoupanca(titular, senha, banco.AGENCIA_PADRAO, banco.incrementSequential()));
                break;
            default:
                throw new Exception("Tipo invalido");
        }

        BancoSerializer.salvarBanco(banco);

        System.out.println("Conta criada com sucesso");
    }

    public Conta encontrarConta(int agencia, int numero) throws Exception {  
        for (Conta c : banco.getContas()) 
            if (c.getAgencia() == agencia && c.getNumero() == numero) {
                return c;
            }
                   
        throw new Exception(String.format("Conta com (agência %d número %d) não encontrada", agencia, numero));
    }

    public Conta encontrarConta(String indentificadorCliente) throws Exception {
        for (Conta c : banco.getContas()) 
            if (c.getCliente().getIdentificador().equals(indentificadorCliente)) {
                return c;
            }
                
            
        throw new Exception(String.format("Conta com titular %s não encontrada", indentificadorCliente));
    }

    public void listarContas() {
        for (Conta c : banco.getContas()) 
            System.out.println(c.toString());
    }

    public void sacar(Conta conta, double valor) throws Exception {
        if (!autorizar(conta)) 
            throw new Exception("Senha invalida");
        
        conta.sacar(valor);

        Transacao t = new Transacao(valor*(-1), conta);
        conta.getTransacoes().add(t);
        banco.getTransacoes().add(t);

        BancoSerializer.salvarBanco(banco);
        System.out.printf("R$%.2f sacados com sucesso\n", valor);
    }

    public void depositar(Conta conta, double valor) {
        conta.depositar(valor);

        Transacao t = new Transacao(valor, conta);
        conta.getTransacoes().add(t);
        banco.getTransacoes().add(t);

        BancoSerializer.salvarBanco(banco);
        System.out.printf("R$%.2f depositados com sucesso\n", valor);
    }

    public void transferir(double valor, Conta remetente, Conta destinatario) throws Exception {
        if (!autorizar(remetente)) 
            throw new Exception("Senha invalida");

        remetente.transferir(valor, destinatario);

        Transacao t = new Transacao(valor, remetente, destinatario);
        remetente.getTransacoes().add(t);
        destinatario.getTransacoes().add(t);
        banco.getTransacoes().add(t);

        BancoSerializer.salvarBanco(banco);
        System.out.printf("R$%.2f transferido para titular %s com sucesso\n", valor, destinatario.getCliente().getIdentificador());
    }

    public void imprimirExtrato(Conta conta) throws Exception {
        if (!autorizar(conta)) 
            throw new Exception("Senha invalida");
        conta.imprimirExtrato();
    }

    public void listarTransacoes(Conta conta) throws Exception {
        if (!autorizar(conta)) 
            throw new Exception("Senha invalida");
        for (Transacao t : conta.getTransacoes()) 
            System.out.println(t);
        
    }

    public void listarAllTransacoes() {
        for (Transacao t : banco.getTransacoes()) 
            System.out.println(t);
    }

    private boolean autorizar(Conta conta) {
        System.out.print("Digite a senha: ");
        if (conta.getSenha().equals(scanner.nextLine())) 
            return true;
            
        return false;
    }
}

