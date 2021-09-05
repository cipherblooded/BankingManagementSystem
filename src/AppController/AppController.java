package AppController;

import BankUI.AccountDetail;

import java.io.IOException;
import java.sql.*;

public class AppController {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/bank";
    private static final String root = "root";
    private static final String password = "root";

    public static String sqlQuery = null;
    public static PreparedStatement preparedStatement = null;
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;


    public static void startConnection() {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url, root, password);
            statement = connection.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void endConnection() {

        try{
            connection.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void openBankAccount(String accountNo, String name, String userID, String userPassword,String accountType,String balance) throws IOException {

        try {

            sqlQuery = "INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setNString(1, accountNo);
            preparedStatement.setNString(2, name);
            preparedStatement.setNString(3, userID);
            preparedStatement.setNString(4, userPassword);
            preparedStatement.setNString(5, accountType);
            preparedStatement.setNString(6, balance);
            int result = preparedStatement.executeUpdate();

            if(result==1){
                printAccountDetail(accountNo);
            }
            else {
                System.out.println("Account can n't be created");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static boolean isValidBalance(String balance){

        char ch = ' ';

        if (balance.equals("")){
            return false;
        }else {
            for (int i = 0; i < balance.length(); i++) {

                ch = balance.charAt(i);

                if(!(ch>=48&&ch<=57)){
                    return false;
                }

            }
        }

        return true;
    }

    public static boolean isValidPassword(String password) {

        if(password.length()>=8){

            char ch = ' ';
            boolean flag1=false, flag2=false, flag3=false, flag4=false;

            for (int i = 0; i < password.length(); i++) {

                ch = password.charAt(i);
                if(ch>=65&&ch<=90){
                    flag1=true;
                }else if(ch>=97&&ch<=122){
                    flag2=true;
                }else if(ch>=48&&ch<=57){
                    flag3=true;
                }else if(ch>=35&&ch<=38||ch==63||ch==64){
                    flag4=true;
                }

            }
            if (flag1&&flag2&&flag3&&flag4){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    public static boolean isValidUser(String userID, String userPassword){

        try{

            String sqlQuery = "SELECT User_ID, Password FROM customers WHERE User_ID = ? and Password = ?";
            Connection connection = AppController.connection;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static String getBalance(String accountNo) {

        String balance = "";
        try{
            sqlQuery = "SELECT Balance FROM customers WHERE Account_No = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setNString(1, accountNo);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                balance = resultSet.getString(1);
            }
            return balance;
        }catch (Exception e){
            System.out.println(e);
            return balance;
        }

    }

    public static void setBalance(String accountNo, String balance) {

        try{
            sqlQuery = "UPDATE customers SET Balance = ? WHERE Account_No = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setNString(1, balance);
            preparedStatement.setNString(2, accountNo);
            int status = preparedStatement.executeUpdate();

            if(status==1){
                System.out.println("Transaction Completed ");
            }else {
                System.out.println("Transaction Failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void printAccountDetail(String accountNo) {

        try {
            sqlQuery = "SELECT * FROM customers WHERE Account_No = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setNString(1, accountNo);
            resultSet = preparedStatement.executeQuery();
            printResultSet(resultSet);

        }catch (Exception e){
            System.out.println(e);
        }

    }

    private static void printResultSet(ResultSet resultSet) {

        try{

            String name, accountNo, userID, accountType, accountBalance;
            while(resultSet.next()) {

                accountNo = resultSet.getNString(1);
                name = resultSet.getString(2);
                userID = resultSet.getString(3);
                accountType = resultSet.getString(5);
                accountBalance = resultSet.getNString(6);

                System.out.println("Print Account Details");

                new AccountDetail(name, accountNo, userID, accountType, accountBalance);

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static String getAccountNo(String userID){

        String accountNo = "";
        for (int i = 0; i < userID.length() ; i++) {

            if(userID.charAt(i)=='@'){
                break;
            }
            accountNo = accountNo + userID.charAt(i);

        }
        return accountNo;
    }

}
