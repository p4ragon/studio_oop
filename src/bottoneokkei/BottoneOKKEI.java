package bottoneokkei;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BottoneOKKEI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Etichette e campi di testo per l'input
        Label lbl1 = new Label("Inserisci la prima stringa:");
        TextField txtPrimaStringa = new TextField();
        txtPrimaStringa.setPromptText("Testo 1...");

        Label lbl2 = new Label("Inserisci la seconda stringa:");
        TextField txtSecondaStringa = new TextField();
        txtSecondaStringa.setPromptText("Testo 2...");

        // 2. Bottone OK e Label per il messaggio finale
        Button btnOk = new Button("OK");
        Label lblRisultato = new Label();
        lblRisultato.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");

        // 3. Logica: il bottone è disabilitato se i testi differiscono o sono vuoti
        btnOk.disableProperty().bind(
            Bindings.createBooleanBinding(
                () -> {
                    String str1 = txtPrimaStringa.getText();
                    String str2 = txtSecondaStringa.getText();
                    return str1.isEmpty() || !str1.equals(str2);
                },
                txtPrimaStringa.textProperty(),
                txtSecondaStringa.textProperty()
            )
        );

        // 4. Azione al click del bottone OK
        btnOk.setOnAction(event -> {
            lblRisultato.setText("Le stringhe coincidono!");
        });

        // 5. Layout: organizziamo gli elementi in verticale con VBox
        VBox root = new VBox(10); // 10 pixel di spaziatura tra gli elementi
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20)); // Margine interno di 20 pixel
        root.getChildren().addAll(
            lbl1, 
            txtPrimaStringa, 
            lbl2, 
            txtSecondaStringa, 
            btnOk, 
            lblRisultato
        );

        // 6. Configurazione della Scena e dello Stage
        Scene scene = new Scene(root, 360, 300);
        primaryStage.setTitle("Verifica Uguaglianza Stringhe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}