import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolDataAccess {

    private Connection conn;
    private static final String teacherTable = "teacher";
    private static final String classTable = "classes";
    private static final String teachesClassesTable = "teachers_classes";


    public SchoolDataAccess()
            throws SQLException, ClassNotFoundException {

        // Class.forName("org.hsqldb.jdbc.JDBCDriver" );

        //Check if JDBC driver is available
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/CR6" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        // connection to write to a file
        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }

    public void closeDb() throws SQLException {
        conn.close();
    }
    /**
     * Get all db records
     * @return
     * @throws SQLException
     */
    public List<Teacher> getAllRowsTeacher()  throws SQLException {

        String sql = "SELECT * FROM " + teacherTable + " ORDER BY teacherName";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Teacher> list = new ArrayList<>();

        while (rs.next()) {
            int teacherID = rs.getInt("teacherID");
            String teacherName = rs.getString("teacherName");
            String teacherSurname = rs.getString("teacherSurname");
            String teacherEmail = rs.getString("teacherEmail");
            list.add(new Teacher(teacherID, teacherName, teacherSurname, teacherEmail));
        }
        pstmnt.close(); // also closes related result set
        return list;
    }
    public List<SchoolClass> getAllRowsClass(int teacherID)  throws SQLException {

        String sql = "SELECT * FROM " + classTable + " JOIN " + teachesClassesTable +  " USING (classID) " +
                "WHERE teachers_classes.teacherID = ? ORDER BY classes.className;";
        PreparedStatement pstmnt = conn.prepareStatement(sql);

        pstmnt.setInt(1, teacherID);
        ResultSet rs = pstmnt.executeQuery();
        List<SchoolClass> list = new ArrayList<>();

        while (rs.next()) {
            int classID = rs.getInt("classID");
            String className = rs.getString("className");
            list.add(new SchoolClass(classID, className));
        }

        pstmnt.close(); // also closes related result set
        return list;
    }

}
