import controller.IDGenerator;
import daomanager.DAOManager;
import view.CLIView;
import java.sql.SQLException;

import static daomanager.DAOManager.connection;

public class Main {
   public static void main(String[] args) throws SQLException {
        DAOManager.connection();
        DAOManager.createTable(connection);

        IDGenerator idGeneratorXXX = IDGenerator.getInstance(DAOManager.getInstance().maxIdCar());

        CLIView cliView = CLIView.getInstance(idGeneratorXXX);
        cliView.run();
    }
}
