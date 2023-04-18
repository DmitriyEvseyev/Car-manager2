import controller.IDGenerator;
import daomanager.DAOManager;
import view.CLIView;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DAOManager.getInstance();
        Integer StartId = 0;

        try {
            StartId = DAOManager.getInstance().maxIdCar();
        } catch (SQLException e) {
            System.out.println("Id not found");
        }

        IDGenerator idGeneratorXXX = IDGenerator.getInstance(StartId);

        CLIView cliView = CLIView.getInstance(idGeneratorXXX);
        cliView.run();
    }
}
