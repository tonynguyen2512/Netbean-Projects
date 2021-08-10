package thien.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thien.dtos.UserDTO;
import thien.utils.DBUtils;

public class UserDAO {
    public UserDTO checkLogin(String userID, String password) throws SQLException{
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
            String sql="SELECT userID, fullName, roleID " 
                    + " FROM tblUsers "
                    + " WHERE userID = '" + userID + "' AND password = " 
                    + password ;
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if(rs.next()){
                String fullName = rs.getString ("fullName");
                String roleID = rs.getString ("roleID");
                user = new UserDTO (userID, fullName, roleID, "");
                }
            }
        }catch(Exception e){
            
        }finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
        return user;
    }
    public List<UserDTO> getListUser(String search) throws SQLException{
        List<UserDTO> listUser = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                String sql = "SELECT userID, fullName, roleID "
                        + " FROM tblUsers "
                        + " WHERE fullName like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1,"%"+search+"%");
                rs = stm.executeQuery();
                while(rs.next()){
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    if(listUser == null) {
                        listUser = new ArrayList<>();
                    }
                    listUser.add(new UserDTO(userID, fullName, roleID, password));
                }
            }
        }catch(Exception e){
            
        }finally{
            if(rs!=null) {rs.close();}
            if(stm!=null) {stm.close();}
            if(conn!=null) {conn.close();}
        }
        return listUser;
    }
    public boolean deleteUser(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if (conn!=null) {
                String sql="DELETE tblUsers " + " WHERE userID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                check = stm.executeUpdate()>0?true:false;
            } 
        } catch (Exception e) {
         } finally {
            if(rs!=null) rs.close();
            if (stm!=null) stm.close();
            if (conn!=null) conn.close();
        }
        return check;
    }
}
