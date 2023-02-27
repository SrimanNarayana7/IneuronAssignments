package util;

import java.sql.*;

public class JdbcUtil {

    public static Connection getConnection(){
        Connection connection=null;

        String url="jdbc:mysql://localhost:3306/sriman";
        String user="root";
        String password="Sriman@7849";
        try {
            connection = DriverManager.getConnection(url, user, password);
            if(connection!=null){
                return connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeResources(ResultSet resultSet, Statement statement, PreparedStatement preparedStatement,
                                      Connection connection){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
