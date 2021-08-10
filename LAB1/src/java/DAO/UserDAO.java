package DAO;

import DTO.UserDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static boolean checkLogin(String userID, String password) throws SQLException {
        boolean result = true;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID FROM tblUsers"
                        + " WHERE userID=? AND password=?";
                /*String sql2= "SELECT USERID FROM tblUsers" 
                    + "WHERE userID="+ userID + "AND password="+ password; */
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery(sql);
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    public List<UserDTO> getListUser(String search) throws SQLException{
        List<UserDTO> result= new ArrayList<>();
        Connection conn=null;
        PreparedStatement stm =null;
        ResultSet rs=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                String sql="SELECT userID, fullName, roleID "
                        + "FROM tblUsers"
                        + "Where fullName like ?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs=stm.executeQuery(sql);
                while(rs.next()){
                    String userID=rs.getString("userID");
                    String fullName=rs.getString("fullName");
                    String roleID=rs.getString("roleID");
                    String password= "***";
                    result.add(new UserDTO(userID, fullName, roleID, password));

                }
            }
            
        }catch(Exception e){
            
        }finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();

        }
        return result;
    }
}
