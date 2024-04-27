package org.agc.proyecto_m06_m09.fx;

import javafx.fxml.FXML;
import org.agc.proyecto_m06_m09.network.client.Client;

public class LogoutController {

    @FXML
    private void closeSocket() {
        Client.getInstance().logout();
    }
}
