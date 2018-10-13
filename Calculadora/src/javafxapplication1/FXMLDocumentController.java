/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Historico;

/**
 *
 * @author Manoel Junior
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField calc;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private Button b7;

    @FXML
    private Button b8;

    @FXML
    private Button b9;

    @FXML
    private Button b00;

    @FXML
    private Button bp;

    @FXML
    private Button b0;

    @FXML
    private Button bmais;

    @FXML
    private Button bmenos;

    @FXML
    private Button bvezes;

    @FXML
    private Button bdiv;

    @FXML
    private Button bigual;

    @FXML
    private Button bc;

    @FXML
    private Button boff;

    private double result = 0;
    private int num = 0, num2 = 0;
    private int operando = 0;
    private Historico historico;

    public void iniciarCalculo() {
        this.historico = new Historico();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();

        char i = event.getSource().toString().substring(33).replace("'", "").charAt(0);
        if (Character.isDigit(i)) {
            if (operando != 0) {
                calc.setText(calc.getText() + String.valueOf(event.getSource().toString().substring(33).replace("'", "")));
            } else {
                calc.setText(calc.getText() + String.valueOf(event.getSource().toString().substring(33).replace("'", "")));
            }
        }

        if (event.getSource() == bmais) {
            operando = 1;
            num = Integer.parseInt(calc.getText());
            calc.setText("");
            historico.setOperador("+");
            historico.setPrimeiroValor(num);
        }
        if (event.getSource() == bmenos) {
            operando = 2;
            num = Integer.parseInt(calc.getText());
            calc.setText("");
            historico.setOperador("-");
            historico.setPrimeiroValor(num);
        }
        if (event.getSource() == bdiv) {
            operando = 3;
            num = Integer.parseInt(calc.getText());
            calc.setText("");
            historico.setOperador("/");
            historico.setPrimeiroValor(num);
        }
        if (event.getSource() == bvezes) {
            operando = 4;
            num = Integer.parseInt(calc.getText());
            calc.setText("");
            historico.setOperador("*");
            historico.setPrimeiroValor(num);
        }

        if (event.getSource() == bigual) {
            num2 = Integer.parseInt(calc.getText());
            historico.setSegundoValor(num2);
            switch (operando) {
                case 1:
                    result = num + num2;
                    break;
                case 2:
                    result = num - num2;
                    break;
                case 3:
                    result = num / num2;
                    break;
                case 4:
                    result = num * num2;
                    break;
                default:
                    break;
            }
            historico.setResultado(result);
            calc.setText(String.valueOf(result));
            result = 0;
            em.getTransaction().begin();
            em.persist(historico);
            em.getTransaction().commit();
            iniciarCalculo();
        }
        if (event.getSource() == boff) {
            System.exit(1000);
        }
        if (event.getSource() == bc) {
            calc.setText("");
            result = 0;
            num = 0;
            num = 0;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        b1.setText("1");
        b2.setText("2");
        b3.setText("3");
        b4.setText("4");
        b5.setText("5");
        b6.setText("6");
        b7.setText("7");
        b8.setText("8");
        b9.setText("9");
        b0.setText("0");
        b00.setText("00");
        bp.setText(".");
        iniciarCalculo();
    }

    @FXML
    private void historico(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("HistoricoFXML.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Histórico");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
