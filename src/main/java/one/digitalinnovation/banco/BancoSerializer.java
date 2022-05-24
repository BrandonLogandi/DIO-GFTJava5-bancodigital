package one.digitalinnovation.banco;

import java.io.File;
import java.io.FileWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import one.digitalinnovation.banco.cliente.Cliente;
import one.digitalinnovation.banco.contas.ContaCorrente;
import one.digitalinnovation.banco.contas.ContaPoupanca;

class BancoSerializer {

    private XStream xstream = new XStream();

    BancoSerializer() {
        xstream.addPermission(AnyTypePermission.ANY);

        xstream.alias("banco", Banco.class);
        xstream.alias("cliente", Cliente.class);
        xstream.alias("conta-corrente", ContaCorrente.class);
        xstream.alias("conta-poupanca", ContaPoupanca.class);
        xstream.alias("transacao", Transacao.class);
    }

    void salvarBanco(Banco banco) {
        try{
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            xml += xstream.toXML(banco);
			FileWriter writeFile = new FileWriter("banco.xml");
			writeFile.write(xml);
			writeFile.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }

    Banco carregarBanco() {
        try {
			File file = new File("banco.xml");
            if(!file.exists()) 
                return new Banco("Banco DIO");
			return (Banco) xstream.fromXML(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
}
