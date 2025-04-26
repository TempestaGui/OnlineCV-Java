package projetos.OnlineCV.model;

import java.util.ArrayList;
import java.util.List;

public class Curriculo {

    private List<String> formacoes = new ArrayList<>();
    private List<String> experiencias = new ArrayList<>();
    private List<String> habilidades = new ArrayList<>();

    public void adicionarFormacao(String f){
        formacoes.add(f);
    }
    public void adicionarExperiencia(String e){
        experiencias.add(e);
    }
    public void adicionarHabilidades(String h){
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
        return  String.join(",", formacoes) + "|" +
                String.join(",", experiencias) + "|" +
                String.join(",", habilidades);
    }
//converte a string serializada de volta em um objeto curriculo

    public static Curriculo desserializar(String data){
        String[] fields = data.split("\\|");
        Curriculo c = new Curriculo();

        if(fields.length>0 && !fields[0].isEmpty()){
            for(String f: fields[0].split(",")){
                if (!f.isEmpty()){
                    c.adicionarFormacao(f.trim());
                }
            }
        }
        if (fields.length> 1 && !fields[1].isEmpty()){
            for (String e: fields[1].split(",")){
                if (!e.isEmpty()){
                    c.adicionarExperiencia(e.trim());
                }
            }
        }
        if(fields.length>2 && !fields[2].isEmpty()){
            for (String h: fields[2].split(",")){
                if(!h.isEmpty()){
                    c.adicionarHabilidades(h.trim());
                }
            }
        }
        return c;
    }
}
