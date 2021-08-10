/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thiennh.dto.UserDTO;
import thiennh.utils.DButils;

/**
 *
 * @author Arslan
 */
public class UserDAO {
    public boolean checkLogin(String userID, String password) throws SQLException{
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            conn = DButils.getConnection();
            if(conn!=null){
            String sql="SELECT USERID FROM tblUsers "
                    +" WHERE userID=? AND password=?";
            //String sql2="SELECT USERID FROM tblUsers"
            stm= conn.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, password);
            rs=stm.executeQuery();
            if(rs.next()){
                result = true;
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
        return result;
    }
    public List<UserDTO> getListUser(String search) throws SQLException{
        List<UserDTO> result = new ArrayList<>();
        Connection conn= null;
        PreparedStatement stm= null;
        ResultSet rs= null;
        try{
            conn=DButils.getConnection();
            if(conn!=null){
                String sql="SELECT USERID, fullName, roleID "
                        +" FROM tblUsers "
                        +" WHERE fullName like ? ";
                stm= conn.prepareStatement(sql);
                stm.setString(1, "%"+search+"%");
                rs= stm.executeQuery();
                while(rs.next()){
                    String userID= rs.getString("userID");
                    String fullName= rs.getString("fullName");
                    String roleID= rs.getString("roleID");
                    String password= "***";
                    result.add(new UserDTO(userID, fullName, roleID, password));
                }
            }
        }catch(Exception e){
            
        }finally{
            if(rs!=null) {rs.close();}
            if(stm!=null) {stm.close();}
            if(conn!=null) {conn.close();}
        }
        return result;
    }
}
