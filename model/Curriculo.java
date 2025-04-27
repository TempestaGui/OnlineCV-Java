package projetos.OnlineCV.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Curriculo {

    private final List<String> formacoes = new ArrayList<>();
    private final List<String> experiencias = new ArrayList<>();
    private final List<String> habilidades = new ArrayList<>();

    public void adicionarFormacao(String f){
        if(f == null || f.trim().isEmpty()){
            throw new IllegalArgumentException("[ERROR] -> formaçao nao pode ser nula ou vazia");
        }
        formacoes.add(f);
    }
    public void adicionarExperiencia(String e){
        if(e == null || e.trim().isEmpty()){
         throw new IllegalArgumentException("[ERROR] -> experiencia não pode esta vazia");
        }
        experiencias.add(e);
    }
    public void adicionarHabilidades(String h){
        if(h == null|| h.trim().isEmpty()){
            throw new IllegalArgumentException("[ERROR] -> habilidade nao pode esta vazia");
        }
        habilidades.add(h);
    }

    public void exibir(){
        System.out.println("====Curriculo====");
        System.out.println("Formaçoes: ");
        formacoes.forEach(f -> System.out.println("- "+f));
        System.out.println("Experiencias: ");
        experiencias.forEach(e -> System.out.println("- "+e));
        System.out.println("habilidades: ");
        habilidades.forEach(h -> System.out.println("- "+h));
        System.out.println("=================");
    }
//transformar o objeto curriculo em uma string formatada para armazenamento em arquivo ou banco de dados

    public String serializar(){
        return  String.join(";", formacoes) + "|" +
                String.join(";", experiencias) + "|" +
                String.join(";", habilidades);
    }
//converte a string serializada de volta em um objeto curriculo

    public static Curriculo desserializar(String data){
        if(data == null || data.trim().isEmpty()){
            throw new IllegalArgumentException("[ERROR]-> dados de serializaçao nao podem ser nulos ou vazios");
        }
        
        String[] fields = data.split("\\|");
        Curriculo c = new Curriculo();

//c:: metodo lambda
        processarCampo(fields[0], c::adicionarFormacao);
        processarCampo(fields[1], c::adicionarExperiencia);
        processarCampo(fields[2], c::adicionarHabilidades);

        return c;
    }

    private static void processarCampo(String campo, Consumer<String> adcionador){
        if(!campo.isEmpty()){
            for (String item: campo.split(";")){
                if (!item.isEmpty()){
                    adcionador.accept(item.trim());
                }
            }
        }
    }
}
