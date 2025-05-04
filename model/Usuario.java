package projetos.OnlineCV.model;

import projetos.OnlineCV.util.HashUtil;

public class Usuario {
    private String nome;
    private String email;
    private String senhaHash;
    private String senhaSalt;
    private Curriculo curriculo = new Curriculo();

    public Usuario(String nome, String email, String senha, Curriculo curriculo) {
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("[ERROR]-> o nome nao pode ser null ou vazio");
        }
        if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("[ERROR]-> o email nao pode ser null ou vazio");
        }
        this.nome = nome.trim();
        this.email = email.trim();
        setSenha(senha);
        this.curriculo = curriculo;
    }

    public Usuario(String nome, String email, String senha) {
        this(nome, email, senha, new Curriculo());
    }

    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenhaHash() {
        return senhaHash;
    }
    public String getSenhaSalt(String senha){
        return senhaSalt;
    }
    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches(".+@.+\\..+")){
            throw new IllegalArgumentException("email invalido");
        }
        this.email = email.trim();
    }

    public void setSenha(String senha){
        String[] saltAndHash = HashUtil.hashWithRandomSalt(senha);
        this.senhaSalt = saltAndHash[0];
        this.senhaHash = saltAndHash[1];
    }

    public boolean autentificar(String senha){

        return HashUtil.verify(senha, this.senhaSalt, this.senhaHash);
    }
}
