package database;

import model.Course;

import java.sql.*;
import java.util.ArrayList;

public class CourseDAO implements DAOInterface<Course>{
    @Override
    public ArrayList<Course> selectAll() {
        ArrayList<Course> res = new ArrayList<Course>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT * FROM COURSE";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String classID = rs.getString("CLASSID");
                String name = rs.getString("NAME");
                String lecture = rs.getString("LECTURE");
                String year = rs.getString("YEAR");
                int semester = rs.getInt("SEMESTER");
                String notes = rs.getString("NOTES");
                int credits = rs.getInt("CREDITS");
                Course course = new Course(classID, name, lecture, year, semester, notes, credits);
                res.add(course);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int insert(Course course) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "INSERT INTO COURSE VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, course.getClassID());
            st.setString(2, course.getName());
            st.setString(3, course.getLecture());
            st.setString(4, course.getYear());
            st.setInt(5, course.getSemester());
            st.setString(6, course.getNotes());
            st.setInt(7, course.getCredits());
            int rs = st.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Course course) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM COURSE WHERE CLASSID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, course.getClassID());
            int rs = st.executeUpdate();
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Course course) {
        Connection con = JDBCUtil.getConnection();
        try {
            String sql = "UPDATE COURSE SET NAME = ?, NOTES = ?, CREDIT = ? WHERE IDCOURSE = ? AND LECTURE = ? AND YEAR = ? AND SEMESTER = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, course.getName());
            st.setString(2, course.getNotes());
            st.setInt(3, course.getCredits());
            st.setString(4, course.getClassID());
            st.setString(5, course.getLecture());
            st.setString(6, course.getYear());
            st.setInt(7, course.getSemester());
            int rs = st.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rs;
        }
            catch (Exception e) {
                e.printStackTrace();
            }
        return 0;
    }


    public void delete(String id, String lecture, String year, int semester) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM COURSE WHERE IDCOURSE = ? AND LECTURE = ? AND YEAR = ? AND SEMESTER = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, lecture);
            st.setString(3, year);
            st.setInt(4, semester);
            st.executeUpdate();
            JDBCUtil.closeConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Course> getAllCoursesByName(String courseName) {
        Connection con = JDBCUtil.getConnection();
        ArrayList<Course> res = new ArrayList<Course>();
        try{
            String sql = "SELECT * FROM COURSE WHERE NAME LIKE ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + courseName + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String classID = rs.getString("IDCOURSE");
                String name = rs.getString("NAME");
                String lecture = rs.getString("LECTURE");
                String year = rs.getString("YEAR");
                int semester = rs.getInt("SEMESTER");
                String notes = rs.getString("NOTES");
                int credits = rs.getInt("CREDIT");
                Course course = new Course(classID, name, lecture, year, semester, notes, credits);
                res.add(course);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
