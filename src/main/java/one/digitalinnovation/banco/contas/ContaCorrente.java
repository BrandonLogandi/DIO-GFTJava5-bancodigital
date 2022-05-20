package one.digitalinnovation.banco.contas;

import one.digitalinnovation.banco.cliente.Cliente;

public class ContaCorrente extends Conta {

	public ContaCorrente(Cliente cliente, String senha, int agencia, int numero) {
		super(cliente, senha, agencia, numero);
	}

    @Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirExtrato();
	}
	
}
