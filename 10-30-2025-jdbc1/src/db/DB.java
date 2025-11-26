package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() { //conexão com o banco de dados
        if (conn == null) {//verificação se ele já existe
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl"); //endereço do banco
                conn = DriverManager.getConnection(url, props); //requisição de conexão
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) { //verificação se a conexão ainda existe
            try {
                conn.close(); // fechamento da conexão
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() { //carregamento de propriedades
        try (FileInputStream fs = new FileInputStream("db.properties")) { //arquivo a ser lido
            Properties props = new Properties();
            props.load(fs); //leitura do arquivo apontado pelo Input Stream fs e vai guardar as informações no objeto props
            return props;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
