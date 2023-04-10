package database;

import model.TakePartInCourse;

import java.sql.*;
import java.util.ArrayList;

public class TakePartInCourseDAO implements DAOInterface<TakePartInCourse>{
    @Override
    public ArrayList<TakePartInCourse> selectAll() {
        ArrayList<TakePartInCourse> res = new ArrayList<TakePartInCourse>();
        Connection con = JDBCUtil.getConnection();
        try{
            String sql = "SELECT * FROM TAKEPARTINCOURSE";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String studentID = rs.getString("STUID");
                String courseID = rs.getString("COURSEID");
                String lecture = rs.getString("LECTURE");
                int year = rs.getInt("YEAR");
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
            st.setInt(4, takePartInCourse.getYear());
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
            st.setInt(4, takePartInCourse.getYear());
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
}
