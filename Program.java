package projetos.OnlineCV;

import projetos.OnlineCV.controller.Sistema;
import projetos.OnlineCV.data.UsuarioRepository;
import projetos.OnlineCV.model.Curriculo;
import projetos.OnlineCV.model.Usuario;
import projetos.OnlineCV.util.HashUtil;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        Sistema sistema = new Sistema();
        sistema.iniciar();
    }
}
