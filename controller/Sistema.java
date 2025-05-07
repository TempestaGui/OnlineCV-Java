package projetos.OnlineCV.controller;

import projetos.OnlineCV.data.UsuarioRepository;
import projetos.OnlineCV.model.Usuario;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Sistema {
    private final UsuarioRepository usuarioRepository;
    private final Scanner sc;
    private Usuario usuarioLogado;

    public Sistema(){
        this.usuarioRepository = new UsuarioRepository("C:\\IdeaProjects\\primeiro projeto\\src\\projetos\\OnlineCV\\docs\\usuarios.txt");
        this.sc = new Scanner(System.in);
    }


    public void iniciar() throws FileNotFoundException{
        try {
            usuarioRepository.carregar();
            System.out.println("==== Online CV ====");

            while (true) {
                exibirMenuPrincipal();
                int opc = lerinteiro("");
                switch (opc) {
                    case 1 -> cadastrar();
                    case 2 -> login();
                    case 3 -> {
                        usuarioRepository.salvar();
                        System.exit(0);
                    }
                    default -> System.out.println("Opçao invalida");
                }
            }
        }catch (FileNotFoundException e){
            System.err.print("Erro ao carregar dados: "+e.getMessage());
        }
    }

    private void exibirMenuPrincipal(){
        System.out.println("==== Menu principal ====");
        System.out.println("1. cadastrar");
        System.out.println("2. login");
        System.out.println("3. sair");
        System.out.print("escolha uma opçao: ");
    }

    public void cadastrar(){
        System.out.println("==== Novo cadastro ====");
        String nomeUsuario = lerString("nome: ");
        String emailusuario = lerString("email: ");
        String senhaUsuario = lerString("senha: ");
        try{
            Usuario u = new Usuario(nomeUsuario,emailusuario,senhaUsuario);
            usuarioRepository.adcionar(u);
            System.out.println("Cadastro realizado com sucesso");
        }catch (Exception e){
            System.err.print("Erro ao cadastrar"+e.getMessage());
        }
    }

    public void login() throws FileNotFoundException {
        System.out.println("==== Login =====");
        String emaillogin = lerString("email: ");
        String senhaLogin = lerString("senha: ");

        try {
            usuarioLogado = usuarioRepository.autentificar(emaillogin, senhaLogin);
            if (usuarioLogado != null) {
                System.out.println("Login bem sucedido! bem vindo, " + usuarioLogado.getNome());
                menuUsuario();
            } else {
                System.out.println("Login falhou");
            }
        }catch (Exception e){
            System.err.print("Erro ao efetuar login"+e.getMessage());
        }
    }

    private void menuUsuario() throws FileNotFoundException {
        while (true){
            exibirMenuUsuario();
            int opc = lerinteiro("");

            switch (opc){
                case 1 ->{
                    String formacao = lerString("formaçao: ");
                    usuarioLogado.getCurriculo().adicionarFormacao(formacao);
                }
                case 2 -> {
                    String experiencia = lerString("experiencia: ");
                    usuarioLogado.getCurriculo().adicionarExperiencia(experiencia);
                }
                case 3 -> {
                    String habilidade = lerString("habilidade");
                    usuarioLogado.getCurriculo().adicionarHabilidades(habilidade);
                }
                case 4 -> {
                    System.out.println("==== Seu curriculo ====");
                    usuarioLogado.getCurriculo().exibir();
                }
                case 5 -> {
                    usuarioRepository.salvar();
                    System.out.println("Alteraçoes salvas! ate logo.");
                    return;
                }
                default -> System.out.println("opçao invalida");
            }
        }
    }

    private void exibirMenuUsuario(){
        System.out.println("==== menu usuario ====");
        System.out.println("ola, "+ usuarioLogado.getNome());
        System.out.println("1. adcionar formaçao");
        System.out.println("2. adcionar experiencia");
        System.out.println("3. adcionar habilidade");
        System.out.println("4. ver curriculo");
        System.out.println("5. sair");
        System.out.println("escolha a opçao");
    }

    private String lerString(String menssagem){
        System.out.println(menssagem);
        return sc.nextLine().trim();
    }

    private int lerinteiro(String menssagem){
        while(true){
            try {
                if(!menssagem.isEmpty()){
                    System.out.println(menssagem);
                }
                int valor = Integer.parseInt(sc.nextLine());
                return valor;
            }catch (NumberFormatException e){
                System.out.println("valor inalido! digite novamente: ");
            }
        }
    }

}
