package hoadnt.daos;

public class UserDAO {

}

public List<UserDTO> getListUser (String search) {
    List <UserDTO> result = new ArrayList <> ();
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        conn = DBUnits.getConnection();
        if (conn != null) {
            String sql = "SELECT userID, fullName, role "
            + "FROM tblUSers " + "WHERE fullName like ?";
            stm = conn.prepareStatement (sql);
            stm.setString (1, search);
            rs = stm.executeQuery();
            while (rs.next()) {
            String userID = rs. getString ("userID");
            String fullName = rs.getString ("fullName");
            String roleID
}
}
    }
}
