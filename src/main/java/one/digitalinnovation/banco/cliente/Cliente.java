package one.digitalinnovation.banco.cliente;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Cliente {

    private String nome;
    private @Setter(AccessLevel.NONE) String identificador;

    public Cliente(String nome, String identificador){
        this.nome  = nome;
        this.identificador = identificador;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return Objects.equals(identificador, cliente.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, identificador);
    }

    @Override
    public String toString() {
        return "CLIENTE {" +
            " Nome: '" + getNome() + "'" +
            ", CPF/CNPJ: '" + getIdentificador() + "'" +
            "}";
    }

    
}
