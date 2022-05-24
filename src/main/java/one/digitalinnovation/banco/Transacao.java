package one.digitalinnovation.banco;

import java.util.Objects;

import lombok.Getter;
import one.digitalinnovation.banco.contas.Conta;

import java.time.Instant;
import java.util.Date;

@Getter
public class Transacao {

    private double valor;
    private Conta remetente;
    private Conta destinatario;
    private Date data;

    public Transacao(double valor, Conta remetente) {
        this.valor = valor;
        this.remetente = remetente;
        this.data = Date.from(Instant.now());
    }

    public Transacao(double valor, Conta remetente, Conta destinatario) {
        this(valor, remetente);
        this.destinatario = destinatario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transacao)) {
            return false;
        }
        Transacao transacao = (Transacao) o;
        return valor == transacao.valor && Objects.equals(remetente, transacao.remetente) && Objects.equals(destinatario, transacao.destinatario) && data == transacao.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, remetente, destinatario, data);
    }

    @Override
    public String toString() {
        return String.format("TRANSACAO: {Valor: R$%.2f, Remetente: %s, Destinatario: %s, Data: '%s'}", valor, remetente, destinatario, data);
    }
    

}
