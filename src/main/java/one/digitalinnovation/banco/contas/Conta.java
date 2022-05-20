package one.digitalinnovation.banco.contas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import one.digitalinnovation.banco.Transacao;
import one.digitalinnovation.banco.cliente.Cliente;

public abstract class Conta implements ContaInterface {

	protected @Getter int agencia;
	protected @Getter int numero;
	protected @Getter double saldo;
	protected @Getter Cliente cliente;
	protected @Getter String senha;
	protected @Getter @Setter List<Transacao> transacoes = new ArrayList<>();

	public Conta(Cliente cliente, String senha, int agencia, int numero) {
		this.agencia = agencia;
		this.numero = numero;
		this.cliente = cliente;
		this.senha = senha;
	}

	@Override
	public void sacar(double valor) throws Exception {
		if ((saldo - valor) < 0) 
			throw new Exception(String.format("Saldo insuficiente (R$%.2f)", saldo));

		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}

	@Override
	public void transferir(double valor, ContaInterface destinatario) throws Exception {
		this.sacar(valor);
		destinatario.depositar(valor);
	}

	@Override
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
