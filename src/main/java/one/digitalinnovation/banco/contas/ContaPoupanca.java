package one.digitalinnovation.banco.contas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import one.digitalinnovation.banco.cliente.Cliente;

public class ContaPoupanca extends Conta {

	private LocalDate lastRendimentoDate;

	public ContaPoupanca(Cliente cliente, String senha, int agencia, int numero) {
		super(cliente, senha, agencia, numero);
		lastRendimentoDate = LocalDate.now();
	}

	public void render(double rendimentoRate) {
		long days = ChronoUnit.DAYS.between(lastRendimentoDate, LocalDate.now());

		if (days > 0) {
			double totalRendimento = 0;

			for (int i = 0; i < days; i++) {
				double renda = super.getSaldo() * rendimentoRate;
				totalRendimento += renda;
				super.depositar(renda);
			}
			
			System.out.printf("Conta %s rendeu R$%.2f desde %s\n", getCliente().getIdentificador(), totalRendimento, lastRendimentoDate.toString());

			lastRendimentoDate = LocalDate.now();
		}
			
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Poupanca ===");
		super.imprimirExtrato();
	}
	
}
