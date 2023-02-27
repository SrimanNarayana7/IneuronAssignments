package main;

import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



class Operations{
    static Connection connection=null;
    static PreparedStatement pstmt =null;
    static ResultSet resultSet=null;
    static Scanner sc = new Scanner(System.in);

    public static void selectQuery() {
        System.out.print("Enter the name:: ");
        String name = sc.next();

        String sqlSelectQuery = "select * from userdata where name = ?";

        try{
            connection=JdbcUtil.getConnection();
            if(connection!=null){
                pstmt=connection.prepareStatement(sqlSelectQuery);
            }
            if(pstmt!=null){
                pstmt.setString(1,name);
                resultSet=pstmt.executeQuery();
            }
            if(resultSet!=null){
                if(resultSet.next()){
                    System.out.println("NAME\t\t ADDR\t\t GENDER\t\t DOB\t\t DOJ\t\t DOM");
                    System.out.println("====================================================================================");
                    String uName = resultSet.getString(1);
                    String uAddr = resultSet.getString(2);
                    String uGender = resultSet.getString(3);
                    java.sql.Date birthday = resultSet.getDate(4);
                    java.sql.Date joiningdate = resultSet.getDate(5);
                    java.sql.Date marriagedate = resultSet.getDate(6);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    System.out.println(uName+"\t\t"+uAddr+"\t\t"+uGender+"\t\t"+sdf.format(birthday)+"\t\t"+sdf.format(joiningdate)+"\t\t"+sdf.format(marriagedate));
                }
                else{
                    System.out.println("No record for the given name :: "+name);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JdbcUtil.closeResources(resultSet,null,pstmt,connection);
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void insertQuery() throws ParseException {

        System.out.print("Enter the name:: ");
        String name = sc.next();
        System.out.print("Enter the addr:: ");
        String addr = sc.next();
        System.out.print("Enter the gender:: ");
        String gender = sc.next();
        System.out.print("Enter the dob::(dd-mm-YYYY) ");
        String sdob = sc.next();
        System.out.print("Enter the doj::(MM-dd-yyyy) ");
        String sdoj = sc.next();
        System.out.print("Enter the dom::(yyyy-MM-dd) ");
        String sdom = sc.next();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date uDob = sdf.parse(sdob);
        Date uDoj = sdf.parse(sdoj);

        //already formatted to sql type date so ValueOf() function
        java.sql.Date sqlDateOfDom = java.sql.Date.valueOf(sdom);

        //util date to sql date
        long time = uDob.getTime();
        java.sql.Date sqlDateofDob = new java.sql.Date(time);

        long time1 = uDoj.getTime();
        java.sql.Date sqlDateOfDoj = new java.sql.Date(time1);

        String sqlInsertQuery = "insert into userdata(name,addr,gender,dob,doj,dom) values (?,?,?,?,?,?)";

        try {

            connection = JdbcUtil.getConnection();

            if (connection != null)
                pstmt = connection.prepareStatement(sqlInsertQuery);

            if (pstmt != null) {
                pstmt.setString(1, name);
                pstmt.setString(2,addr);
                pstmt.setString(3,gender);
                pstmt.setDate(4, sqlDateofDob);
                pstmt.setDate(5, sqlDateOfDoj);
                pstmt.setDate(6, sqlDateOfDom);

                int rowAffected = pstmt.executeUpdate();
                System.out.println("No of rows affected is:: " + rowAffected);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JdbcUtil.closeResources(null,null, pstmt, connection);
            if (sc != null) {
                sc.close();
            }
        }

    }

}
public class DateOperations {
    public static void main(String[] args) throws ParseException {
        System.out.println("1.Select\n2.Insert");
        System.out.print("Select the Date operations you want to perform w.r.t database :: ");
        Scanner sc =new Scanner(System.in);
        int value=sc.nextInt();
        switch(value){
            case 1:
                System.out.println("You have selected select operation");
                Operations.selectQuery();
                break;
            case 2:
                System.out.println("You have selected insert operation");
                Operations.insertQuery();
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
