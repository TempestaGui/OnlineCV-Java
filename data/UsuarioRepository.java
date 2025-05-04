package projetos.OnlineCV.data;

import projetos.OnlineCV.model.Curriculo;
import projetos.OnlineCV.model.Usuario;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioRepository {
    private final List<Usuario> usuarios = new ArrayList<>();
    private final Path arquivoPath;

    public UsuarioRepository(String path){
        this.arquivoPath = Paths.get(Objects.requireNonNull(path, "Caminho do arquivo nao pode ser nulo"));
        carregar();
    }
    public void adcionar(Usuario us){
        if(usuarios == null){
            throw new IllegalArgumentException("o usuario nao pode ser nulo");
        }
        if(buscarEmail(us.getEmail())!= null){
            throw new IllegalStateException("o usuario com esse email ja existe");
        }
        usuarios.add(us);
    }

    public Usuario buscarEmail(String email){
        return usuarios.stream().
                filter(u -> u.getEmail().equalsIgnoreCase(email)).
                findFirst().orElse(null);
    }


    public Usuario autentificar(String email, String Senha){
        Usuario us = buscarEmail(email);
        if(us != null && us.autentificar(Senha)){return us;}
            return null;
    }


    public void carregar(){
        if(!Files.exists(arquivoPath))  return;

        try(BufferedReader br = Files.newBufferedReader(arquivoPath)){
            String line;
            while((line = br.readLine())!= null){
                String[] fields = line.split("\\|",4);
                if(fields.length == 4){
                    String nome = fields[0];
                    String emial = fields[1];
                    String senhaHash = fields[2];
                    Curriculo curriculo = Curriculo.desserializar(fields[3]);
                    usuarios.add(new Usuario(nome,emial,senhaHash,curriculo));
                }
            }
        } catch (IOException e) {
            throw new DataAccessException("Falha ao carregar dados",e);
        }
    }

    public void salvar() throws FileNotFoundException {
        try(PrintWriter pw = new PrintWriter(Files.newBufferedWriter(arquivoPath))){
            for(Usuario u: usuarios){
                String Line = String.join("|",
                 u.getNome(),
                        u.getEmail(),
                        u.getSenhaHash(),
                        u.getCurriculo().serializar());
                pw.println(Line);
            }
        }catch (IOException e){
            throw new DataAccessException("Falha ao salvar dados", e);
        }
    }

    public static class DataAccessException extends RuntimeException{
        public DataAccessException(String message, Throwable cause){
            super(message, cause);
        }
    }
}
