package projetos.OnlineCV.controller;


import projetos.OnlineCV.data.UsuarioRepository;
import projetos.OnlineCV.model.Curriculo;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Scanner;

public class Sistema {
    public void menuCurriculo() throws IOException, SQLException {
        Scanner sc = new Scanner(System.in);
        Curriculo curriculo = new  Curriculo();
        while (true){
            exibirMenuCurriculo();
            int opc = sc.nextInt();

            switch (opc){
                case 1 ->{
                    curriculo.InserirDados();
                }
                case 2 -> {
                    curriculo.carregarDados();
                }
                    case 3 -> {
                    System.out.println("Alteraçoes salvas! ate logo.");
                    return;
                }
                default -> System.out.println("opçao invalida");
            }
        }
    }

    private void exibirMenuCurriculo(){
        System.out.println("==== menu Curriculo ====");
        System.out.println("1. Inserir dados");
        System.out.println("2. ver curriculo");
        System.out.println("3. sair");
        System.out.println("escolha a opçao");
    }

    public void menuUsuario() throws IOException, SQLException {
        Scanner sc = new Scanner(System.in);
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        while (true){
            exibirMenuUsuario();
            int opc = sc.nextInt();

            switch (opc){
                case 1 ->{
                    usuarioRepository.inserirDados();
                }
                case 2 -> {
                    usuarioRepository.carregarDados();
                }
                case 3 -> {
                    this.menuCurriculo();
                }
                case 4 -> {
                    System.out.println("Alteraçoes salvas! ate logo.");
                    return;
                }
                default -> System.out.println("opçao invalida");
            }
        }
    }

    private void exibirMenuUsuario(){
        System.out.println("==== menu Usuario ====");
        System.out.println("1. Inserir dados");
        System.out.println("2. ver dados");
        System.out.println("3. curriculo");
        System.out.println("4. sair");
        System.out.println("escolha a opçao");
    }
}
