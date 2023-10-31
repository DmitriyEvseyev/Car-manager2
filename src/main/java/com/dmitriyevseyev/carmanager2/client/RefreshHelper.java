package com.dmitriyevseyev.carmanager2.client;

public class RefreshHelper {
    private ControllerView controllerView;
    private static RefreshHelper instance;

    private RefreshHelper() {
    }

    public static RefreshHelper getInstance() {
        if (instance == null) {
            instance = new RefreshHelper();
        }
        return instance;
    }

    public ControllerView getControllerView() {
        return controllerView;
    }

    public void setControllerView (ControllerView controllerView) {
        this.controllerView = controllerView;
    }
}