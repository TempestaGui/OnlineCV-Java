package projetos.OnlineCV.data;

import projetos.OnlineCV.model.Curriculo;
import projetos.OnlineCV.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();
    private final String path = "C:\\IdeaProjects\\primeiro projeto\\src\\projetos\\OnlineCV\\docs\\usuarios";

    public void adcionar(Usuario us){
        usuarios.add(us);
    }

    public Usuario autentificar(String email, String Senha){
        for (Usuario u: usuarios){
            if(u.getEmail().equals(email) && u.autentificar(Senha)){
                return u;
            }
        }
            return null;
    }

    public void carregar() throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists())  return;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine())!= null){
                String[] fields = line.split(";");
                if(fields.length == 4){
                    String nome = fields[0];
                    String emial = fields[1];
                    String senhaHash = fields[2];
                    Curriculo curriculo = Curriculo.desserializar(fields[3]);
                    usuarios.add(new Usuario(nome,emial,senhaHash,curriculo));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvar() throws FileNotFoundException {
        try(PrintWriter pw = new PrintWriter(path)){
            for(Usuario u: usuarios){
                String line = u.getNome()+":"+u.getEmail()+";"+u.getSenhaHash()+";"+u.getCurriculo().serializar();
                pw.println(line);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
