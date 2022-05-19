package one.digitalinnovation.banco;

import java.util.ArrayList;
import java.util.List;

import one.digitalinnovation.banco.cliente.Cliente;
import one.digitalinnovation.banco.contas.Conta;

class Banco {

	private String nome;
	private List<Cliente> clientes = new ArrayList<>();
	private List<Conta> contas = new ArrayList<>();
	private List<Transacao> transacoes = new ArrayList<>();

	Banco(String nome) {
		this.nome = nome;
	}

	String getNome() {
		return this.nome;
	}

	void setNome(String nome) {
		this.nome = nome;
	}

	List<Cliente> getClientes() {
		return this.clientes;
	}

	void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	List<Conta> getContas() {
		return this.contas;
	}

	void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	List<Transacao> getTransacoes() {
		return this.transacoes;
	}

	void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

}
