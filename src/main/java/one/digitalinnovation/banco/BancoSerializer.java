package one.digitalinnovation.banco;

import java.io.File;
import java.io.FileWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

public abstract class BancoSerializer {

    private static XStream xstream = new XStream();

    private static void permissions() {
        xstream.addPermission(AnyTypePermission.ANY);
    }

    public static void salvarBanco(Banco banco) {
        permissions();
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

    public static Banco carregarBanco() {
        permissions();
        try {
			File f = new File("banco.xml");
            if(!f.exists()) {
                return new Banco("Banco DIO");
            }
			return (Banco) xstream.fromXML(f);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
}
