package one.digitalinnovation.banco.contas;

public interface ContaInterface {
	
	void sacar(double valor) throws Exception;
	
	void depositar(double valor);
	
	void transferir(double valor, ContaInterface destinatario) throws Exception;

	void imprimirExtrato();

}
