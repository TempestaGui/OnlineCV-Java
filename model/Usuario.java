package projetos.OnlineCV.model;

import projetos.OnlineCV.util.HashUtil;

public class Usuario {
    private String nome;
    private String email;
    private String senhaHash;
    private Curriculo curriculo = new Curriculo();

    public Usuario(String nome, String email, String senhaHash) {
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
    }

    public Usuario(String nome, Curriculo curriculo, String senhaHash, String email) {
        this.nome = nome;
        this.curriculo = curriculo;
        this.senhaHash = senhaHash;
        this.email = email;
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
    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public boolean autentificar(String senha){
        return senhaHash.equals(HashUtil.hash(senha));
    }
}
