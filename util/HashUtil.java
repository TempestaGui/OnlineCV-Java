package projetos.OnlineCV.util;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashUtil {
    private static final String DEFAULT_ALGORITHM = "SHA-256";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    //gerar hash SHA-246 de uma string sem salt
    public static String hash(String input){
        return hash(input, DEFAULT_ALGORITHM);
    }


    public static String hash(String input, String algorithm) {
            try {
                //obter uma instancia do algoritmo SHA-256
                MessageDigest md = MessageDigest.getInstance(algorithm);
                //converter a string de entrada em bytes
                //aplica o algoritmo hash, resultando em um array de bytes
                byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

                //converter cada byte para sua representaçao hexadecimal de 2 digitos
                StringBuilder hex = new StringBuilder();
                for (byte b : hashBytes) {
                    hex.append(String.format("%02x", b));
                }
                return hex.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("[ERROR]-> algoritmo hash nao disponivel"+algorithm,e);
            }
        }


    //gerar hash com salt aleatorio
    public static String[] hashWithRandomSalt(String input){
        String salt = generateSalt();
        String hashed = hash(input + salt, DEFAULT_ALGORITHM);
        return new String[]{salt, hashed};
    }

    //gerar um hash com salt especifico
    public static String hashWithSalt(String input, String salt){
        return hash(input + salt, DEFAULT_ALGORITHM);
    }

    //gerar um salt aleatorio
    public static String generateSalt(){
        byte[] salt = new byte[16];
        SECURE_RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    //verificar se a string corresponde a uma hash com salt
    public static boolean verify(String input, String salt, String hash){
        return hashWithSalt(input, salt).equals(hash);
    }
    }


