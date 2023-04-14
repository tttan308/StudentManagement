package database;

import model.Course;
import model.ManageCourse;

import java.sql.*;
import java.util.ArrayList;

public class ManageCourseDAO implements DAOInterface<ManageCourse>{
    @Override
    public ArrayList<ManageCourse> selectAll() {
        ArrayList<ManageCourse> res = new ArrayList<ManageCourse>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT * FROM MANAGECOURSE";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String username = rs.getString("USERNAME");
                String courseID = rs.getString("COURSEID");
                String lecture = rs.getString("LECTURE");
                String year = rs.getString("YEAR");
                int semester = rs.getInt("SEMESTER");
                ManageCourse manageCourse = new ManageCourse(username, courseID, lecture, year, semester);
                res.add(manageCourse);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int insert(ManageCourse manageCourse) {
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "INSERT INTO MANAGECOURSE VALUES(?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, manageCourse.getUsername());
            st.setString(2, manageCourse.getClassID());
            st.setString(3, manageCourse.getLecture());
            st.setString(4, manageCourse.getYear());
            st.setInt(5, manageCourse.getSemester());
            int rs = st.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(ManageCourse manageCourse) {
        Connection con =JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM MANAGECOURSE WHERE USERNAME = ? AND COURSEID = ? AND LECTURE = ? AND YEAR = ? AND SEMESTER = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, manageCourse.getUsername());
            st.setString(2, manageCourse.getClassID());
            st.setString(3, manageCourse.getLecture());
            st.setString(4, manageCourse.getYear());
            st.setInt(5, manageCourse.getSemester());
            int rs = st.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(ManageCourse manageCourse) {
        return 0;
    }

    public ArrayList<Course> getCourseOfAccount(String username) {
        Connection con = JDBCUtil.getConnection();
        ArrayList<Course> res = new ArrayList<Course>();
        try{
            String sql = "SELECT COURSE.* FROM MANAGECOURSE JOIN COURSE ON MANAGECOURSE.COURSEID = COURSE.IDCOURSE AND MANAGECOURSE.LECTURE = COURSE.LECTURE AND MANAGECOURSE.YEAR = COURSE.YEAR AND MANAGECOURSE.SEMESTER = COURSE.SEMESTER WHERE MANAGECOURSE.USERNAME = ? ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String idCourse = rs.getString("IDCOURSE");
                String nameCourse = rs.getString("NAME");
                String lecture = rs.getString("LECTURE");
                String year = rs.getString("YEAR");
                int semester = rs.getInt("SEMESTER");
                String notes = rs.getString("NOTES");
                int credit = rs.getInt("CREDIT");
                Course course = new Course(idCourse, nameCourse, lecture, year, semester, notes, credit);
                res.add(course);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void delete(String username, String id, String lecture, String year, int semester) {
        Connection con =JDBCUtil.getConnection();
        try{
            String sql = "DELETE FROM MANAGECOURSE WHERE USERNAME = ? AND COURSEID = ? AND LECTURE = ? AND YEAR = ? AND SEMESTER = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
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
