package database;

import model.ManageStudent;
import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class ManageStudentDAO implements DAOInterface<ManageStudent>{
    @Override
    public ArrayList<ManageStudent> selectAll() {
        ArrayList<ManageStudent> res = new ArrayList<ManageStudent>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT * FROM MANAGESTUDENT";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String username = rs.getString("USERNAME");
                String studentID = rs.getString("IDSTU");
                ManageStudent manageStudent = new ManageStudent(username, studentID);
                res.add(manageStudent);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int insert(ManageStudent manageStudent) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "INSERT INTO MANAGESTUDENT VALUES(?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, manageStudent.getUsername());
            st.setString(2, manageStudent.getId());
            int rs = st.executeUpdate();
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(ManageStudent manageStudent) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM MANAGESTUDENT WHERE USERNAME = ? AND STUDENTID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, manageStudent.getUsername());
            st.setString(2, manageStudent.getId());
            int rs = st.executeUpdate();
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(ManageStudent manageStudent) {
        return 0;
    }

    public ArrayList<Student> getStudentOfAccount(String username){
        ArrayList<Student> res = new ArrayList<Student>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT STUDENT.* FROM STUDENT JOIN MANAGESTUDENT ON STUDENT.IDSTU = MANAGESTUDENT.IDSTU WHERE MANAGESTUDENT.USERNAME = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
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

    public void delete(String id){
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM MANAGESTUDENT WHERE IDSTU = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Student s){
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "UPDATE MANAGESTUDENT SET NAME = ?, GRADE = ?, BIRTHDAY = ?, ADDRESS = ?, NOTES = ? WHERE IDSTU = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, s.getName());
            st.setString(2, s.getGrade());
            st.setDate(3, s.getBirthday());
            st.setString(4, s.getAddress());
            st.setString(5, s.getNotes());
            st.setString(6, s.getId());
            st.executeUpdate();
        }catch (Exception e) {

        }
    }
    public static void main(String[] args) {
        ManageStudentDAO manageStudentDAO = new ManageStudentDAO();
        ArrayList<Student> list = manageStudentDAO.getStudentOfAccount("admin");
        for(Student student : list) {
            System.out.println(student.getId());
        }
    }
}
