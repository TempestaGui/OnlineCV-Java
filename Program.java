package projetos.OnlineCV;

import projetos.OnlineCV.model.Curriculo;
import projetos.OnlineCV.model.Usuario;
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
        System.out.println("Hash simples: "+hash);

       //hash com salt aleatorio

        String[] hashComSalt = HashUtil.hashWithRandomSalt(senha);
        System.out.println("\nhash com salt");
        System.out.println("salt: "+hashComSalt[0]);
        System.out.println("hash: "+hashComSalt[1]);

        //verificaçao
        boolean valido = HashUtil.verify(senha,hashComSalt[0],hashComSalt[1]);
        System.out.println("\nVerificaçao: "+ (valido ? "Sucesso": "Falha"));

        //hash com algotitmo especifico
        String Hash512 = HashUtil.hash(senha, "SHA-512");
        System.out.println("\nhash SHA-512: "+Hash512);

        //teste de usuario

        System.out.println("teste 1 -> criaçao e autentificaçao");
        Usuario us1 = new Usuario("Guilherme","guilherme@gmail.com","Senha123");
        System.out.println("Usuario criado: "+us1.getNome());

        //teste de autentificaçao
        System.out.println("\ntentativa de login");
        System.out.println("Senha correta: "+us1.autentificar("Senha123"));
        System.out.println("Senha incorreta: "+us1.autentificar("senha321"));

        //teste com curriculo
        System.out.println("teste 2 -> com curriculo");
        Usuario us2 = new Usuario("gui", "gui@gmail.com", "senha123",c);
        System.out.println("Usuario: "+us2.getNome());
        System.out.println("Curriculo: "+us2.getCurriculo().serializar());

        //teste de validaçao
        System.out.println("teste 3 -> validaçao");
        try{
            new Usuario("","email@invalido","senha");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            us1.setEmail("email-sem-arroba");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
