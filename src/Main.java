import controller.IDGenerator;
import dao.DAOCar;
import daomanager.DAOManager;
import view.CLIView;
import dao.database.MaxId;
import exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;


import static daomanager.DAOManager.connection;

public class Main {

    // fixme main class should not throw exceptions - all of them should be catched
    public static void main(String[] args) throws NotFoundException, SQLException {
        // fixme this logic should be moved to dao daomanager class initialisation
        DAOManager.connection();
        // fixme similar to previous
        DAOManager.createTable(connection);

        IDGenerator idGeneratorXXX = IDGenerator.getInstance(DAOManager.getInstance().maxIdCar());

        // fixme what is maxid - why it places here?
        CLIView cliView = CLIView.getInstance(idGeneratorXXX);
        cliView.run();
    }
}
