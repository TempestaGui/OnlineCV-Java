```mermaid
classDiagram
    direction TB
    
    class Usuario {
        <<Entity>>
        -String nome
        -String email
        -String senhaHash
        -Curriculo curriculo
        +autenticar() Boolean
    }
    
    class Curriculo {
        <<Value Object>>
        -List~String~ formacoes
        -List~String~ experiencias
        -List~String~ habilidades
        +adicionarFormacao(String formacao)
        +adicionarExperiencia(String experiencia)
        +adicionarHabilidade(String habilidade)
    }
    
    class UsuarioRepository {
        <<Repository>>
        -List~Usuario~ usuarios
        +salvar(Usuario usuario) void
        +carregar() List~Usuario~
        +buscarPorEmail(String email) Usuario
    }
    
    class Sistema {
        <<Controller>>
        -UsuarioRepository repository
        +iniciar() void
        +menuPrincipal() void
    }

    class HashUtil {
        <<Utility>>
        +gerarHash(String senha) String$
    }

    Usuario "1" *-- "1" Curriculo : contém
    UsuarioRepository ||.. Usuario : gerencia
    Sistema --> UsuarioRepository : usa
    Usuario --> HashUtil : utiliza
```
