/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.view;

import app.genieLogiciel.djangui.model.Utilisateur;
import app.genieLogiciel.djangui.service.UtilisateurDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javax.annotation.PostConstruct;

/**
 *
 * @author kevinash
 */
@FXMLController("login.fxml")
public class LoginController {
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    @ActionTrigger("login")
    private JFXButton loginButton;

    @FXML
    @ActionTrigger("close")
    private JFXButton closeButton;

    @FXML
    private Label messageLabel;
    
    private FlowHandler flowHandler;
    
    @PostConstruct
    public void init() {
        messageLabel.setVisible(false);
        Flow flow = new Flow(LoginController.class).
                withLink(LoginController.class, "next", DashboardController.class);
        flowHandler = flow.createHandler();
    }
    
    @ActionMethod("login")
    public void login() throws FlowException, VetoException{
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        Utilisateur user = UtilisateurDAO.getUtilisateur(username);
        
        if( user == null ) {
            System.out.println("user null");
            messageLabel.setVisible(true);
            return;
        } else if (!user.getPassword().equals(password)) {
            System.out.println("password incorrect");
            return;
        }
        if (flowHandler == null) {
            System.out.println("flowhandler null");
        }else {
            System.out.println("hei");
        }
        
        flowHandler.navigateTo(DashboardController.class);
        
        
    }
    
    @ActionMethod("close")
    public void close() {
        System.exit(0);
    }
}
