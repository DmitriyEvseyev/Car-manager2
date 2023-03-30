package daomanager;

import dao.DAOCar;

import java.sql.*;


public class DAOManager  {
    public static java.sql.Connection connection;
    public static DAOManager instance;
    private DAOCar daoCar;

    public static DAOManager getInstance() {
        if (instance == null) {
            instance = new DAOManager();
        }
        return instance;
    }

    public DAOCar getDaoCar() {
        return daoCar;
    }

    public DAOManager() {
        this.daoCar = DAOCar.getInstance(connection);
    }

    public static void connection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CAR",
                    "postgres", "5b6eu5");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void createTable(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS CAR" +
                    "(ID INT PRIMARY KEY NOT NULL GENERATED BY DEFAULT AS IDENTITY, " +
                    " NAME           CHAR(50)    NOT NULL, " +
                    " DATE           DATE        NOT NULL, " +
                    " COLOR          CHAR(50)    NOT NULL, " +
                    " ISAFTERCRASH   BOOLEAN)";
            stmt.executeUpdate(sql);

        } catch (SQLException  e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    public Integer maxIdCar() throws SQLException {
        Integer MaxIdCar = 0;
        String sql = "SELECT MAX(ID) FROM CAR";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MaxIdCar = rs.getInt("MAX");
            }
        }
        return MaxIdCar;
    }
}

