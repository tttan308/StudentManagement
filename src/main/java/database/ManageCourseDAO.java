package database;

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
                int year = rs.getInt("YEAR");
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
            String sql = "INSERT INTO MANAGECOURSE VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, manageCourse.getUsername());
            st.setString(2, manageCourse.getClassID());
            st.setString(3, manageCourse.getLecture());
            st.setInt(4, manageCourse.getYear());
            st.setInt(5, manageCourse.getSemester());
            int rs = st.executeUpdate();
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
            st.setInt(4, manageCourse.getYear());
            st.setInt(5, manageCourse.getSemester());
            int rs = st.executeUpdate();
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
}
