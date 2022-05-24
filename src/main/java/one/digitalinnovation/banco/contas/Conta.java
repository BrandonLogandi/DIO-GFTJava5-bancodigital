package one.digitalinnovation.banco.contas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import one.digitalinnovation.banco.Transacao;
import one.digitalinnovation.banco.cliente.Cliente;

@Getter
public abstract class Conta {

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected String senha;
	protected List<Transacao> transacoes = new ArrayList<>();

	protected Conta(Cliente cliente, String senha, int agencia, int numero) {
		this.agencia = agencia;
		this.numero = numero;
		this.cliente = cliente;
		this.senha = senha;
	}

	public void sacar(double valor) throws Exception {
		if ((saldo - valor) < 0) 
			throw new Exception(String.format("Saldo insuficiente (R$%.2f)", saldo));

		saldo -= valor;
	}

	public void depositar(double valor) {
		saldo += valor;
	}

	public void transferir(double valor, Conta destinatario) throws Exception {
		this.sacar(valor);
		destinatario.depositar(valor);
	}

    public void imprimirExtrato() {
        for (Transacao transacao : transacoes) {
			System.out.println(transacao.toString());
		}
    }

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Conta)) {
			return false;
		}
		Conta conta = (Conta) o;
		return agencia == conta.agencia && numero == conta.numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, numero);
	}

	@Override
	public String toString() {
		return String.format(
			"CONTA { Agencia: '%d', Numero: '%d', Titular: '%s', Saldo: R$%.2f}", 
			getAgencia(),
			getNumero(),
			getCliente().getIdentificador(),
			getSaldo()
		);
	}

	
}
