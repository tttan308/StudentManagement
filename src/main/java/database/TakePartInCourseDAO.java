package database;

import model.Student;
import model.TakePartInCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TakePartInCourseDAO implements DAOInterface<TakePartInCourse>{
    @Override
    public ArrayList<TakePartInCourse> selectAll() {
        ArrayList<TakePartInCourse> res = new ArrayList<>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT * FROM TAKEPARTINCOURSE";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String studentID = rs.getString("STUID");
                String courseID = rs.getString("COURSEID");
                String lecture = rs.getString("LECTURE");
                String year = rs.getString("YEAR");
                int semester = rs.getInt("SEMESTER");
                float grade = rs.getFloat("GRADE");
                TakePartInCourse takePartInCourse = new TakePartInCourse(studentID, courseID, lecture, year, semester, grade);
                res.add(takePartInCourse);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int insert(TakePartInCourse takePartInCourse) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "INSERT INTO TAKEPARTINCOURSE VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, takePartInCourse.getStudentID());
            st.setString(2, takePartInCourse.getClassID());
            st.setString(3, takePartInCourse.getLecture());
            st.setString(4, takePartInCourse.getYear());
            st.setInt(5, takePartInCourse.getSemester());
            st.setFloat(6, takePartInCourse.getScore());
            int rs = st.executeUpdate();
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(TakePartInCourse takePartInCourse) {
        Connection con =JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM TAKEPARTINCOURSE WHERE STUID = ? AND COURSEID = ? AND LECTURE = ? AND YEAR = ? AND SEMESTER = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, takePartInCourse.getStudentID());
            st.setString(2, takePartInCourse.getClassID());
            st.setString(3, takePartInCourse.getLecture());
            st.setString(4, takePartInCourse.getYear());
            st.setInt(5, takePartInCourse.getSemester());
            int rs = st.executeUpdate();
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(TakePartInCourse takePartInCourse) {
        return 0;
    }

    public ArrayList<String> getAllYear(){
        Connection con = JDBCUtil.getConnection();
        ArrayList<String> res = new ArrayList<>();
        try{
            String sql = "SELECT DISTINCT YEAR FROM TAKEPARTINCOURSE";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String year = rs.getString("YEAR");
                res.add(year);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<TakePartInCourse> getAllCourseOfStudent(String id, String year) {
        Connection con = JDBCUtil.getConnection();
        ArrayList<TakePartInCourse> res = new ArrayList<>();
        try{
            String url = "SELECT * FROM TAKEPARTINCOURSE WHERE STUID = ? AND YEAR = ?";
            PreparedStatement st = con.prepareStatement(url);
            st.setString(1, id);
            st.setString(2, year);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String studentID = rs.getString("STUID");
                String courseID = rs.getString("COURSEID");
                String lecture = rs.getString("LECTURE");
                String year1 = rs.getString("YEAR");
                int semester = rs.getInt("SEMESTER");
                float grade = rs.getFloat("SCORE");
                TakePartInCourse takePartInCourse = new TakePartInCourse(studentID, courseID, lecture, year1, semester, grade);
                res.add(takePartInCourse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<Map<Student, Float>> getAllStudentOfCourse(String classID, String lecture, String year, int semester){
        ArrayList<Map<Student, Float>> res = new ArrayList<>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT STUDENT.*, TAKEPARTINCOURSE.SCORE FROM STUDENT JOIN TAKEPARTINCOURSE ON STUDENT.IDSTU = TAKEPARTINCOURSE.STUID WHERE COURSEID = ? AND LECTURE = ? AND YEAR = ? AND SEMESTER = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, classID);
            st.setString(2, lecture);
            st.setString(3, year);
            st.setInt(4, semester);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String id = rs.getString("IDSTU");
                String name = rs.getString("NAME");
                String grade = rs.getString("GRADE");
                Date birthday = rs.getDate("BIRTHDAY");
                String address = rs.getString("ADDRESS");
                String notes = rs.getString("NOTES");
                Student student = new Student(id, name, grade, birthday, address, notes);
                float score = rs.getFloat("SCORE");
                Map<Student, Float> map = new HashMap<>();
                map.put(student, score);
                res.add(map);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<String> getAllStudentExcept(String classID, String lecture, String year, int semester) {
        ArrayList<String> res = new ArrayList<>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT STUDENT.IDSTU FROM STUDENT WHERE STUDENT.IDSTU NOT IN(SELECT TAKEPARTINCOURSE.STUID FROM TAKEPARTINCOURSE WHERE COURSEID = ? AND LECTURE = ? AND YEAR = ? AND SEMESTER = ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, classID);
            st.setString(2, lecture);
            st.setString(3, year);
            st.setInt(4, semester);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String id = rs.getString("IDSTU");
                res.add(id);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void delete(String idStudent, String id, String lecture, String year, int semester) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM TAKEPARTINCOURSE WHERE STUID = ? AND COURSEID = ? AND LECTURE = ? AND YEAR = ? AND SEMESTER = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idStudent);
            st.setString(2, id);
            st.setString(3, lecture);
            st.setString(4, year);
            st.setInt(5, semester);
            st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
