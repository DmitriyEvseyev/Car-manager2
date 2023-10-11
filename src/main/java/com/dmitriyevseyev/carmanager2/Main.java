package com.dmitriyevseyev.carmanager2;

import com.dmitriyevseyev.carmanager2.daomanager.DAOManager;
import com.dmitriyevseyev.carmanager2.view.CLIView;

public class Main {
    public static void main(String[] args) {
        DAOManager.getInstance();
        CLIView.run(args);
    }
}
