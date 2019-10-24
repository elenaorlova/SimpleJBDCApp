package connection;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection("C:\\Users\\Elena\\Documents" +
                "\\GitHub\\SimpleJBDCApp" +
                "\\src\\main\\resources\\db.properties");
    }
}
