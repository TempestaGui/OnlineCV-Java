```mermaid
    class Usuario {
        - String nome
        - String email
        - String senhaHash
        - Curriculo curriculo
        + autenticar()
    }
    
    class Curriculo {
        - List<String> formacoes
        - List<String> experiencias
        - List<String> habilidades
        + adicionarFormacao()
        + adicionarExperiencia()
        + adicionarHabilidade()
    }
    
    class UsuarioRepository {
        - List<Usuario> usuarios
        + salvar()
        + carregar()
        + autenticar()
    }
    
    class Sistema {
        + iniciar()
        + menuPrincipal()
    }

    class HashUtil {
        + gerarHash()
    }

    Usuario --> Curriculo
    UsuarioRepository --> Usuario
