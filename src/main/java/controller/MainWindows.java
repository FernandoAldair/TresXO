package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindows implements Initializable {

    @FXML
    private Button start,save,b00,b01,b02,b10,b11,b12,b20,b21,b22;
    @FXML
    private TextField player1Name,player2Name;
    @FXML
    private Text player1NameText, player2NameText;
    @FXML
    private RadioButton pvsc, pvsp, cvsc;
    @FXML
    private ToggleGroup grupo1;

    //Controlar a que jugador le toca tirar
    private boolean turn = true;
    //Contar turno
    private int turno = 0;
    //Para controlar si todas las casillas han sido rellenadas
    private int casillasLlenas = 0;

    //Desactivar funciones
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player1Name.setVisible(false);
        player1NameText.setVisible(false);
        save.setVisible(false);
        player2Name.setVisible(false);
        player2NameText.setVisible(false);
        disableButtons();
    }

    //desactivar botones
    public void disableButtons(){
        b00.setDisable(true);
        b01.setDisable(true);
        b02.setDisable(true);
        b10.setDisable(true);
        b11.setDisable(true);
        b12.setDisable(true);
        b20.setDisable(true);
        b21.setDisable(true);
        b22.setDisable(true);
    }

    //Limpiar botones
    public void limpiar() {
        b00.setText("");
        b01.setText("");
        b02.setText("");
        b10.setText("");
        b11.setText("");
        b12.setText("");
        b20.setText("");
        b21.setText("");
        b22.setText("");
    }

    //Activar botones
    public void activarBotines(){
        b00.setDisable(false);
        b01.setDisable(false);
        b02.setDisable(false);
        b10.setDisable(false);
        b11.setDisable(false);
        b12.setDisable(false);
        b20.setDisable(false);
        b21.setDisable(false);
        b22.setDisable(false);
    }

    //Funciones segun el modo de juego seleccionado
    public void modojuego(){
        //Muestran un campo para añadir los nombres de los 2 jugadores
        if (pvsp.isSelected()){
            player1NameText.setVisible(true);
            player1Name.setVisible(true);
            player2NameText.setVisible(true);
            player2Name.setVisible(true);
            save.setVisible(true);
        }
        //Muestra un campo para añadir el nombre del jugador
        else if (pvsc.isSelected()){
            player2NameText.setVisible(false);
            player2Name.setVisible(false);
            player1NameText.setVisible(true);
            player1Name.setVisible(true);
            save.setVisible(true);
        }
        //No muestra ningun campo para llenar
        else if (cvsc.isSelected()){
            player1NameText.setVisible(false);
            player1Name.setVisible(false);
            player2NameText.setVisible(false);
            player2Name.setVisible(false);
            save.setVisible(false);
        }
    }

    private String player1, player2;
    //Guarda la informacion del jugador
    public void save(ActionEvent actionEvent){
        if (pvsp.isSelected()){
            player1 = player1Name.getText();
            player2 = player2Name.getText();
            player1NameText.setVisible(false);
            player1Name.setVisible(false);
            player2NameText.setVisible(false);
            player2Name.setVisible(false);
        }
        else if (pvsc.isSelected()){
            player1 = player1Name.getText();
            player1NameText.setVisible(false);
            player1Name.setVisible(false);
        }
        save.setVisible(false);
    }

    //Cambian la funcion de los botones segun la opcion seleccionada del modo de juego
    public void clickButton(ActionEvent actionEvent) {
        RadioButton radioButton = (RadioButton) grupo1.getSelectedToggle();

        if (radioButton != null){
            if (pvsp.isSelected()) pvspMode(actionEvent);
            if (pvsc.isSelected()) pvscMode(actionEvent);
        }
    }

    //Modo de juego jugador vs jugador
    public void pvspMode (ActionEvent actionEvent){
        Button button = (Button) actionEvent.getSource();

        if (turn){
            if (button.getText().equals("")){
                button.setText("X");
                ganar("X");
                turn = false;
            }
        }
        else {
            if (button.getText().equals("")){
                button.setText("O");
                ganar("O");
                turn = true;
            }
        }
    }

    //Modo de juego jugador vs cpu
    public void pvscMode (ActionEvent actionEvent){
        Button button = (Button) actionEvent.getSource();
        List<Button> buttons = new ArrayList<Button>(Arrays.asList(b00,b01,b02,b10,b11,b12,b20,b21,b22));
        Button boton;
        start.setVisible(false);
        int ronda;
        boolean b = false;
        if (button.getText().equals("")){
            button.setText("X");
            ganar("X");

            while (!b && turno <=3){
                ronda = (int) (Math.random()*9);
                boton = buttons.get(ronda);

                if (boton.getText().equals("")){
                    boton.setText("O");
                    turno++;
                    ganar("O");
                    b = true;
                }
            }
        }
    }

    //Modo de juego cpu vs cpu
    public void cvscMode (ActionEvent actionEvent){

    }

    //Comprueba si hay un posible ganaddor
    public boolean ganar(String texto){
        casillasLlenas++;

        //Horizontales
        if (b00.getText().equals(texto) && b01.getText().equals(texto) && b02.getText().equals(texto)) {
            disableButtons();
            return true;
        }
        else if (b10.getText().equals(texto) && b11.getText().equals(texto) && b12.getText().equals(texto)) {
            disableButtons();
            return true;
        }
        else if (b20.getText().equals(texto) && b21.getText().equals(texto) && b22.getText().equals(texto)) {
            disableButtons();
            return true;
        }
        //Verticales
        else if (b00.getText().equals(texto) && b10.getText().equals(texto) && b20.getText().equals(texto)) {
            disableButtons();
            return true;
        }
        else if (b01.getText().equals(texto) && b11.getText().equals(texto) && b21.getText().equals(texto)) {
            disableButtons();
            return true;
        }
        else if (b02.getText().equals(texto) && b12.getText().equals(texto) && b22.getText().equals(texto)) {
            disableButtons();
            return true;
        }
        //Diagonales
        else if (b00.getText().equals(texto) && b11.getText().equals(texto) && b22.getText().equals(texto)) {
            disableButtons();
            return true;
        }
        else if (b02.getText().equals(texto) && b11.getText().equals(texto) && b20.getText().equals(texto)) {
            disableButtons();
            return true;
        }

        if (casillasLlenas > 8){
            disableButtons();
            return true;
        }
        return false;
    }

    //Al dar el boton start se activan los botones y los limpia
    public void clickStart(){
        casillasLlenas = 0;
        activarBotines();
        limpiar();
    }
}