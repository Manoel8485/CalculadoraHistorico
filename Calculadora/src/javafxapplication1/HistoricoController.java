/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Historico;

/**
 * FXML Controller class
 *
 * @author manoel8485
 */
public class HistoricoController implements Initializable {

    @FXML
    private TableView<?> tbResult;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT h FROM Historico as h");
        
        List <Historico> historico = query.getResultList();
        
        ObservableList itens = FXCollections.observableArrayList(historico);
        
        tbResult.setItems(itens);
    }

}
