package one.digitalinnovation.banco;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import one.digitalinnovation.banco.cliente.Cliente;
import one.digitalinnovation.banco.contas.Conta;

@Getter(AccessLevel.PACKAGE) @Setter(AccessLevel.PACKAGE)
class Banco {

	public @Getter(AccessLevel.NONE) final int AGENCIA_PADRAO = 1;
	public @Getter(AccessLevel.NONE) final double RENDIMENTO_RATE = 0.00028d;
	
	private @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) int sequential = 1;

	private String nome;
	private List<Cliente> clientes = new ArrayList<>();
	private List<Conta> contas = new ArrayList<>();
	private List<Transacao> transacoes = new ArrayList<>();

	Banco(String nome) {
		this.nome = nome;
	}

	int incrementSequential() {
		return sequential++;
	}

}
