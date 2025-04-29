package projetos.OnlineCV.util;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class HashUtil {

    public static String hash(String input) {
            try {
                //obter uma instancia do algoritmo SHA-246
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                //converter a string de entrada em bytes
                //aplica o algoritmo hash, resultando em um array de bytes
                byte[] hashBytes = md.digest(input.getBytes());

                //converter cada byte para sua representaçao hexadecimal de 2 digitos
                StringBuilder hex = new StringBuilder();
                for (byte b : hashBytes) {
                    hex.append(String.format("%02x", b));
                }
                return hex.toString();
            } catch (Exception e) {
                throw new RuntimeException("Erro ao gerar hash");
            }
        }
    }


