package org.agc.proyecto_m06_m09.fx;

import javafx.fxml.FXML;

public class LogoutController {

    @FXML
    private void closeSocket() {
        ChatManager.logout();
    }
}
