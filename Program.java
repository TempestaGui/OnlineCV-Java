package projetos.OnlineCV;

import projetos.OnlineCV.model.Curriculo;
import projetos.OnlineCV.util.HashUtil;

public class Program {
    public static void main(String[] args) {
        Curriculo c = new Curriculo();
        c.adicionarExperiencia("desenvolvimento de programas; criaçao de sites");
        c.adicionarFormacao("ciencias da computaçao pelo centro universitario de brasilia");
        c.adicionarHabilidades("java");
        c.exibir();

        String serializado = c.serializar();
        System.out.println("serializando o curriculo:");
        System.out.println(serializado);

        System.out.println();
        System.out.println("desserializando o curriculo:");

        Curriculo novoCv = Curriculo.desserializar(serializado);
        novoCv.exibir();

        //testando a class HashUtil

        String senha = "minhaSenhaSecreta123";
        String hash = HashUtil.hash(senha);

        System.out.println("Senha original: "+senha);
        System.out.println("Hash SHA-246: "+hash);
    }
}
