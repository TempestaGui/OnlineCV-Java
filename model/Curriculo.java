package projetos.OnlineCV.model;

import projetos.OnlineCV.db.DB;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Curriculo {
    public void exibir(Connection connection, PreparedStatement st) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("====Curriculo====");
        System.out.println("Formaçoes: ");
        String formacao = sc.nextLine();
        st.setString(1, formacao);
        System.out.println("Experiencias: ");
        String experiencia = sc.nextLine();
        st.setString(2, experiencia);
        System.out.println("habilidades: ");
        String habilidade = sc.nextLine();
        st.setString(3, habilidade);
        System.out.println("=================");
    }

    public void InserirDados() throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        Connection conexao = null;
        PreparedStatement st = null;

        try{
            conexao = DB.getConnection();
            st = conexao.prepareStatement(
                    "INSERT INTO curriculo"+
                            "(formacoes, experiencias, habilidades)"+
                            "values "+
                            "(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            this.exibir(conexao, st);
            int rowAffected = st.executeUpdate();
            if(rowAffected>0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("done, Id = " + id);
                }
            }else{
                System.out.println("no rows affected");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void carregarDados() throws SQLException {
        Connection connection = null; //conectar
        Statement st = null;//bucar
        ResultSet rs = null;
        try{
            connection = DB.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("select * from curriculo");
            while(rs.next()){
                System.out.println(rs.getInt("id_curriculo")+", "+
                        rs.getString("formacoes")+", "+
                        rs.getString("experiencias")+", "+
                        rs.getString("habilidades")+", ");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
