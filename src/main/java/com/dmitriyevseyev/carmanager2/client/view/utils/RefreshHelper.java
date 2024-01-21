package com.dmitriyevseyev.carmanager2.client.view.utils;

import com.dmitriyevseyev.carmanager2.client.view.controller.ViewController;

public class RefreshHelper {
    private ViewController viewController;
    private static RefreshHelper instance;

    private RefreshHelper() {
    }

    public static RefreshHelper getInstance() {
        if (instance == null) {
            instance = new RefreshHelper();
        }
        return instance;
    }

    public ViewController getControllerView() {
        return viewController;
    }

    public void setControllerView (ViewController viewController) {
        this.viewController = viewController;
    }
}