package main;

import util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Jdbc{

    static Connection connection=null;
    static Statement statement=null;
    static ResultSet resultSet=null;
    static Scanner sc = new Scanner(System.in);
    public static void selectQuery() throws SQLException {
        connection = JdbcUtil.getConnection();
        if(connection!=null){
            statement = connection.createStatement();
            if(statement!=null){
                System.out.print("Enter the sid :: ");
                int id=sc.nextInt();
                String sqlSelectQuery="select * from student where sid="+id+"";
                resultSet=statement.executeQuery(sqlSelectQuery);
                if(resultSet!=null){
                    if(resultSet.next()){
                        System.out.println("sid\t sname\t sage\t saddr");
                        System.out.println("===============================");
                        int sid = resultSet.getInt("sid");
                        String sname = resultSet.getString("sname");
                        int sage = resultSet.getInt("sage");
                        String saddr = resultSet.getString("saddr");
                        System.out.println("\t"+sid+"\t"+ sname+"\t"+ sage+"\t"+ saddr);
                    }
                    else{
                        System.out.println("Record not found for the given id :: "+id);
                    }
                }
            }
        }
        JdbcUtil.closeResources(resultSet,statement,null,connection);
        if(sc!=null){
            sc.close();
        }
    }
    public static void insertQuery() throws SQLException {
        connection = JdbcUtil.getConnection();
        if(connection!=null){
            statement = connection.createStatement();
            if(statement!=null){

                System.out.print("Enter the sname :: ");
                String sname=sc.next();
                sname="'"+sname+"'";
                System.out.print("Enter the sage :: ");
                int sage=sc.nextInt();
                System.out.print("Enter the saddr :: ");
                String saddr=sc.next();
                saddr="'"+saddr+"'";

                String sqlCreateQuery="insert into student(sname,sage,saddr) values("+sname+","+sage+","+saddr+")";
                int noOfRowsAffected=statement.executeUpdate(sqlCreateQuery);
                System.out.println("No of Rows Affected ::" + noOfRowsAffected);
            }
        }
        JdbcUtil.closeResources(null,statement,null,connection);
        if(sc!=null){
            sc.close();
        }
    }
    public static void updateQuery() throws SQLException {
        connection = JdbcUtil.getConnection();
        if(connection!=null){
            statement = connection.createStatement();
            if(statement!=null){

                System.out.print("Enter the sid :: ");
                int sid=sc.nextInt();
                System.out.print("Enter the saddr to update :: ");
                String saddr=sc.next();
                saddr="'"+saddr+"'";

                String sqlUpdateQuery="update student set saddr="+saddr+" where sid="+sid+"";
                int noOfRowsAffected=statement.executeUpdate(sqlUpdateQuery);
                System.out.println("No of Rows Affected ::" + noOfRowsAffected);
            }
        }
        JdbcUtil.closeResources(null,statement,null,connection);
        if(sc!=null){
            sc.close();
        }
    }
    public static void deleteQuery() throws SQLException {
        connection = JdbcUtil.getConnection();
        if(connection!=null){
            statement = connection.createStatement();
            if(statement!=null){

                System.out.print("Enter the sid :: ");
                int sid=sc.nextInt();

                String sqlDeleteQuery="delete from student where sid="+sid+"";
                int noOfRowsAffected=statement.executeUpdate(sqlDeleteQuery);
                System.out.println("No of Rows Affected ::" + noOfRowsAffected);
            }
        }
        JdbcUtil.closeResources(null,statement,null,connection);
        if(sc!=null){
            sc.close();
        }
    }
}

public class StatementQueries {
    public static void main(String[] args) throws SQLException {
        System.out.println("1.Create\n2.Read\n3.Update\n4.Delete");
        System.out.print("Select the operation you wa" +
                "nt to perform w.r.t database :: ");
        Scanner sc =new Scanner(System.in);
        int value=sc.nextInt();
        switch(value){
            case 1:
                System.out.println("You have selected Insert operation");
                Jdbc.insertQuery();
                break;
            case 2:
                System.out.println("You have selected Read operation");
                Jdbc.selectQuery();
                break;
            case 3:
                System.out.println("You have selected Update operation");
                Jdbc.updateQuery();
                break;
            case 4:
                System.out.println("You have selected Delete operation");
                Jdbc.deleteQuery();
                break;
            default:
                System.out.println("provide the input correctly....!");
                break;
        }

        if(sc!=null){
            sc.close();
        }
    }
}