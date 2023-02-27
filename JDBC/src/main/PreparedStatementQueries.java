package main;

import util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

class JdbcPrepared{
    static Connection connection=null;
    static PreparedStatement pstmt =null;
    static ResultSet resultSet=null;
    static Scanner sc = new Scanner(System.in);
    public static void insertQuery() {

        System.out.print("Enter the sname :: ");
        String sname = sc.next();
        System.out.print("Enter the sage:: ");
        int sage = sc.nextInt();
        System.out.print("Enter the saddr :: ");
        String saddr = sc.next();

        String sqlUpdateQuery = "insert into student(sname,sage,saddr) values(?,?,?) ";

        try{
            connection=JdbcUtil.getConnection();
            if(connection!=null){
                pstmt=connection.prepareStatement(sqlUpdateQuery);
            }
            if(pstmt!=null){
                pstmt.setString(1,sname);
                pstmt.setInt(2,sage);
                pstmt.setString(3,saddr);
                int noOfRowsAffected=pstmt.executeUpdate();
                System.out.println("No of Rows Affected :: "+noOfRowsAffected);
            }
        }catch (Exception se){
            se.printStackTrace();
        }
        finally {
            JdbcUtil.closeResources(null,null,pstmt,connection);
            if(sc!=null){
                sc.close();
            }
        }

    }

    public static void selectQuery()  {

        System.out.print("Enter the sid: ");
        int sid = sc.nextInt();

        String sqlSelectQuery = "select sid,sname,sage,saddr from student where sid=?";

        try {
            connection = JdbcUtil.getConnection();
            if (connection != null){
                pstmt = connection.prepareStatement(sqlSelectQuery);
            }
            if (pstmt != null) {
                pstmt.setInt(1, sid);
                resultSet = pstmt.executeQuery();
            }
            if (resultSet != null) {
                if (resultSet.next()) {
                    System.out.println("SID\tSNAME\tSAGE\tSADDR");
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
                            + "\t" + resultSet.getString(4));
                } else {
                    System.out.println("Record not available for the given id:: " + sid);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResources(resultSet, null, pstmt, connection);
            if (sc != null)
                sc.close();
        }

    }

    public static void updateQuery() {

        System.out.print("Enter the sid:: ");
        int sid = sc.nextInt();
        System.out.print("Enter the saddr to update :: ");
        String saddr = sc.next();

        String sqlUpdateQuery = "update student set saddr=? where sid=?";

        try{
            connection=JdbcUtil.getConnection();
            if(connection!=null){
                pstmt=connection.prepareStatement(sqlUpdateQuery);
            }
            if(pstmt!=null){
                pstmt.setString(1,saddr);
                pstmt.setInt(2,sid);
                int noOfRowsAffected=pstmt.executeUpdate();
                System.out.println("No of Rows Affected :: "+noOfRowsAffected);
            }
        }catch (Exception se){
            se.printStackTrace();
        }
        finally {
            JdbcUtil.closeResources(null,null,pstmt,connection);
            if(sc!=null){
                sc.close();
            }
        }

    }

    public static void deleteQuery() {

        System.out.print("Enter the sid:: ");
        int sid = sc.nextInt();

        String sqlUpdateQuery = "delete from student where sid=?";

        try{
            connection=JdbcUtil.getConnection();
            if(connection!=null){
                pstmt=connection.prepareStatement(sqlUpdateQuery);
            }
            if(pstmt!=null){
                pstmt.setInt(1,sid);
                int noOfRowsAffected=pstmt.executeUpdate();
                System.out.println("No of Rows Affected :: "+noOfRowsAffected);
            }
        }catch (Exception se){
            se.printStackTrace();
        }
        finally {
            JdbcUtil.closeResources(null,null,pstmt,connection);
            if(sc!=null){
                sc.close();
            }
        }

    }
}



public class PreparedStatementQueries {
    public static void main(String[] args) throws SQLException {
        System.out.println("************Using Prepared Statement*************");
        System.out.println("1.Create\n2.Read\n3.Update\n4.Delete");
        System.out.print("Select the operation you want to perform w.r.t database :: ");
        Scanner sc =new Scanner(System.in);
        int value=sc.nextInt();
        switch(value){
            case 1:
                System.out.println("You have selected Insert operation");
                JdbcPrepared.insertQuery();
                break;
            case 2:
                System.out.println("You have selected Read operation");
                JdbcPrepared.selectQuery();
                break;
            case 3:
                System.out.println("You have selected Update operation");
                JdbcPrepared.updateQuery();
                break;
            case 4:
                System.out.println("You have selected Delete operation");
                JdbcPrepared.deleteQuery();
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
