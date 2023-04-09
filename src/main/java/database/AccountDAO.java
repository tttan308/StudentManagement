package database;

import model.Account;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO implements DAOInterface<Account>{

    @Override
    public ArrayList<Account> selectAll() {
        ArrayList<Account> res = new ArrayList<Account>();
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ACCOUNT";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                Account account = new Account(username, password);
                res.add(account);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    @Override
    public int insert(Account account) {
        Connection con =JDBCUtil.getConnection();
        try{
            String sql = "INSERT INTO ACCOUNT VALUES(?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, account.getUsername());
            st.setString(2, account.getPassword());
            int rs = st.executeUpdate();
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Account account) {
        return 0;
    }

    @Override
    public int update(Account account) {
        Connection con =JDBCUtil.getConnection();
        try{
            String sql = "UPDATE ACCOUNT SET PASSWORD = ? WHERE USERNAME = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, account.getPassword());
            st.setString(2, account.getUsername());
            int rs = st.executeUpdate();
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        JDBCUtil.closeConnection(con);
        return 0;
    }

    public Account selectByUsernameAndPassword(Account account) {
        Account res = null;
        try{
            Connection con = JDBCUtil.getConnection();
            if(con != null){
                String sql = "SELECT * FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, account.getUsername());
                st.setString(2, account.getPassword());
                System.out.println(account.getUsername());
                System.out.println(account.getPassword());
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    String username1 = rs.getString("USERNAME");
                    String password1 = rs.getString("PASSWORD");
                    res = new Account(username1, password1);
                }
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean checkAccountExist(Account account) {
        boolean res = false;
        try{
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ACCOUNT WHERE USERNAME = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, account.getUsername());
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                res = true;
            }
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String args[]){
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account("tan", "tan");
        Account acc = accountDAO.selectByUsernameAndPassword(account);
        System.out.println(acc);
    }
}
