package database;

import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO implements DAOInterface<Student>{
    @Override
    public ArrayList selectAll() {
        ArrayList<Student> res = new ArrayList<Student>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT * FROM STUDENT";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String id = rs.getString("IDSTU");
                String name = rs.getString("NAME");
                String grade = rs.getString("GRADE");
                Date birthday = rs.getDate("BIRTHDAY");
                String address = rs.getString("ADDRESS");
                String notes = rs.getString("NOTES");
                Student student = new Student(id, name, grade, birthday, address, notes);
                res.add(student);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int insert(Student s) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, s.getId());
            st.setString(2, s.getName());
            st.setString(3, s.getGrade());
            st.setDate(4, s.getBirthday());
            st.setString(5, s.getAddress());
            st.setString(6, s.getNotes());
            int rs = st.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Student s) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM STUDENT WHERE ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, s.getId());
            int rs = st.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void delete(String id){
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM STUDENT WHERE ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            int rs = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int update(Student s){
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "UPDATE STUDENT SET NAME = ?, GRADE = ?, BIRTHDAY = ?, ADDRESS = ?, NOTES = ? WHERE IDSTU = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, s.getName());
            st.setString(2, s.getGrade());
            st.setDate(3, s.getBirthday());
            st.setString(4, s.getAddress());
            st.setString(5, s.getNotes());
            st.setString(6, s.getId());
            int rs = st.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Student> getStudentByName(String name) {
        Connection con = JDBCUtil.getConnection();
        ArrayList<Student> res = new ArrayList<Student>();
        try{
            String sql = "SELECT * FROM STUDENT WHERE NAME LIKE ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String id = rs.getString("IDSTU");
                String name1 = rs.getString("NAME");
                String grade = rs.getString("GRADE");
                Date birthday = rs.getDate("BIRTHDAY");
                String address = rs.getString("ADDRESS");
                String notes = rs.getString("NOTES");
                Student student = new Student(id, name1, grade, birthday, address, notes);
                res.add(student);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
