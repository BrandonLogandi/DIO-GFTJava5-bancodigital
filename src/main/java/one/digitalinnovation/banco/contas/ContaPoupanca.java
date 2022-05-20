package one.digitalinnovation.banco.contas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import one.digitalinnovation.banco.cliente.Cliente;

public class ContaPoupanca extends Conta {

	private final double RENDIMENTO_RATE = 0.00028d;
	private LocalDate lastRendimentoDate;

	public ContaPoupanca(Cliente cliente, String senha) {
		super(cliente, senha);
		lastRendimentoDate = LocalDate.now();
	}

	public void render() {
		long days = ChronoUnit.DAYS.between(lastRendimentoDate, LocalDate.now());

		if (days > 0) {
			double totalRendimento = 0;

			for (int i = 0; i < days; i++) {
				double renda = super.getSaldo() * RENDIMENTO_RATE;
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
