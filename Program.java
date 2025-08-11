package projetos.OnlineCV;

import projetos.OnlineCV.controller.Sistema;
import projetos.OnlineCV.model.Curriculo;
import projetos.OnlineCV.model.Usuario;
import projetos.OnlineCV.util.HashUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) throws IOException, SQLException {
        Sistema sistema = new Sistema();
        sistema.menuUsuario();
    }
}
