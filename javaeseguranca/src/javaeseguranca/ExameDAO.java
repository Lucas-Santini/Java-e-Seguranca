package javaeseguranca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class ExameDAO {
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/javaeseguranca";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";
    private static final String ALGORITHM = "DES";
    private static final String CHAVE = "chave123";

    public void inserirExame(Exame exame) {
        String sql = "INSERT INTO exame (potassio, tipoDado) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, encrypt(exame.getPotassio()));
            statement.setString(2, encrypt(exame.getTipoDado()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarExame(int id, Exame exame) {
        String sql = "UPDATE exame SET potassio=?, tipoDado=? WHERE id=?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, encrypt(exame.getPotassio()));
            statement.setString(2, encrypt(exame.getTipoDado()));
            statement.setInt(3, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarExame(int id) {
        String sql = "DELETE FROM exame WHERE id=?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Exame> listarExames() {
        List<Exame> exames = new ArrayList<>();
        String sql = "SELECT * FROM exame";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Exame exame = new Exame(decrypt(resultSet.getString("potassio")),
                                        decrypt(resultSet.getString("tipoDado")));
                exames.add(exame);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exames;
    }

    private String encrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKey secretKey = getSecretKey();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String decrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKey secretKey = getSecretKey();
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(value));
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private SecretKey getSecretKey() {
        try {
            KeySpec keySpec = new DESKeySpec(CHAVE.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            return secretKeyFactory.generateSecret(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Erro ao gerar a chave secreta", (Throwable) e);
        }
    }
}
