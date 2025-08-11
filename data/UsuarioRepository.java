package projetos.OnlineCV.data;

import projetos.OnlineCV.db.DB;
import projetos.OnlineCV.model.Curriculo;
import projetos.OnlineCV.model.Usuario;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UsuarioRepository {
    public void inserirDados(){
        Connection conexao = null;
        PreparedStatement st = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            conexao = DB.getConnection();
            st = conexao.prepareStatement(
                    "insert into user"+
                            "(nome, email, senha, data_cadastro, id_curriculo)"+
                            "values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            this.menu(st, conexao);
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

        } catch (SQLException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void carregarDados() throws SQLException {
        Connection conexao = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conexao = DB.getConnection();
            st = conexao.createStatement();
            rs = st.executeQuery("select * from user");
            while(rs.next()){
                System.out.println(rs.getInt("id_user")+ ", "+
                                    rs.getString("nome")+", "+
                                    rs.getString("email")+". ");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
    private void menu(PreparedStatement st, Connection conexao) throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("nome: ");
        String name = sc.nextLine();
        st.setString(1,name);
        System.out.println("email: ");
        String email = sc.nextLine();
        st.setString(2,email);
        System.out.println("senha: ");
        String senha = sc.nextLine();
        st.setString(3,senha);
        System.out.println("data de nascimento: ");
        String data_cadastro = sc.next();
        st.setDate(4, new java.sql.Date(sdf.parse(data_cadastro).getTime()));
        System.out.println("id_curriculo: ");
        int id_curriculo = sc.nextInt();
        st.setInt(5,id_curriculo);
    }
}