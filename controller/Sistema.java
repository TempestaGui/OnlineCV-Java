package projetos.OnlineCV.controller;

import projetos.OnlineCV.data.UsuarioRepository;
import projetos.OnlineCV.model.Usuario;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Sistema {
    private UsuarioRepository usuarioRepository = new UsuarioRepository("C:\\IdeaProjects\\primeiro projeto\\src\\projetos\\OnlineCV\\docs\\usuarios.txt");
    Scanner sc = new Scanner(System.in);

    public void iniciar() throws FileNotFoundException{
        usuarioRepository.carregar();
        System.out.println("==== Online CV ====");

        while(true){
            System.out.println("\n1. cadastrar\n2. login\n3. sair");
            int opc = sc.nextInt();
            switch (opc){
                case 1 -> cadastrar();
                case 2 -> login();
                case 3 -> {
                    usuarioRepository.salvar();
                    System.exit(0);
                }
                default -> System.out.println("Opçao invalida");
            }
        }
    }

    public void cadastrar(){
        System.out.print("Nome: ");
        String nomeUsuario = sc.nextLine();
        System.out.print("Email: ");
        String emailusuario = sc.nextLine();
        System.out.print("Senha: ");
        String senhaUsuario = sc.nextLine();

        Usuario u = new Usuario(nomeUsuario,emailusuario,senhaUsuario);
        usuarioRepository.adcionar(u);
        System.out.println("Cadastro realizado com sucesso");
    }

    public void login() throws FileNotFoundException {
        System.out.println("email: ");
        String emaillogin = sc.nextLine();
        System.out.println("Senha: ");
        String senhaLogin = sc.nextLine();

        Usuario u = usuarioRepository.autentificar(emaillogin,senhaLogin);
        if (u != null){
            menuUsuario(u);
        }else {
            System.out.println("Login falhou");
        }
    }

    private void menuUsuario(Usuario usuario) throws FileNotFoundException {
        while (true){
            System.out.println("ola, "+ usuario.getNome());
            System.out.println("1. adcionar formaçao");
            System.out.println("2. adcionar experiencia");
            System.out.println("3. adcionar habilidade");
            System.out.println("4. ver curriculo");
            System.out.println("5. sair");
            int opc = sc.nextInt();

            switch (opc){
                case 1 ->{
                    System.out.println("Formaçao: ");
                    usuario.getCurriculo().adicionarFormacao(sc.nextLine());
                }
                case 2 -> {
                    System.out.println("experiencia: ");
                    usuario.getCurriculo().adicionarExperiencia(sc.nextLine());
                }
                case 3 -> {
                    System.out.println("habilidade: ");
                    usuario.getCurriculo().adicionarHabilidades(sc.nextLine());
                }
                case 4 -> {
                    usuario.getCurriculo().exibir();
                }
                case 5 -> {
                    usuarioRepository.salvar();
                    return;
                }
                default -> System.out.println("opçao invalida");
            }
        }
    }


}
