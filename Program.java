package projetos.OnlineCV;

import projetos.OnlineCV.model.Curriculo;

public class Program {
    public static void main(String[] args) {
        Curriculo c = new Curriculo();
        c.adcionarExperiencia("desenvolvimento de programas");
        c.adcionarFormacao("ciencias da computaçao pelo centro universitario de brasilia");
        c.adcionarHabilidades("java");
        c.adcionarFormacao("medicina pela universidade nacional de brasilia");

        c.exibir();
    }
}
