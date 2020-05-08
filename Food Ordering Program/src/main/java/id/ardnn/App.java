package id.ardnn;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    // global attributes
    private List<List<Integer>> lsOrderan = new ArrayList<>();
    private List<Text> lsTextOrder = new ArrayList<>();
    private List<Integer> lsTemp = new ArrayList<>();
    private boolean[] isAdded = new boolean[5];
    private boolean isError, isOrder;
    private CheckBox cekBox1, cekBox2, cekBox3, cekBox4, cekBox5;
    private TextField textField1, textField2, textField3, textField4, textField5;

    private Scene scene1, scene2;
    private VBox vbox2;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Restaurant");
        // scene utama ------------------------------------------

        // Text Menu
        Text textHeader = new Text("Ini Restoran");
        Text textMenu = new Text("Menu");
        Text textHarga = new Text("Harga");
        Text textJumlah = new Text("Jumlah");

        // menu makanan
        cekBox1 = new CheckBox("Nasi Goreng");
        cekBox2 = new CheckBox("Mie Goreng");
        cekBox3 = new CheckBox("Ayam Goreng");
        cekBox4 = new CheckBox("Coto");
        cekBox5 = new CheckBox("Gado - gado");

        // harga makanan
        for (int i = 0; i < 5; i++)
            lsOrderan.add(new ArrayList<>());

        lsOrderan.get(0).add(12000);
        lsOrderan.get(1).add(12000);
        lsOrderan.get(2).add(15000);
        lsOrderan.get(3).add(13000);
        lsOrderan.get(4).add(10000);

        Text text1 = new Text("Rp. " + lsOrderan.get(0).get(0));
        Text text2 = new Text("Rp. " + lsOrderan.get(1).get(0));
        Text text3 = new Text("Rp. " + lsOrderan.get(2).get(0));
        Text text4 = new Text("Rp. " + lsOrderan.get(3).get(0));
        Text text5 = new Text("Rp. " + lsOrderan.get(4).get(0));

        // orderan
        textField1 = new TextField("1");
        textField2 = new TextField("1");
        textField3 = new TextField("1");
        textField4 = new TextField("1");
        textField5 = new TextField("1");

        textField1.setMaxWidth(30);
        textField2.setMaxWidth(30);
        textField3.setMaxWidth(30);
        textField4.setMaxWidth(30);
        textField5.setMaxWidth(30);

        // Gridpane untuk nama menu dan jumlahnya
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(15, 15, 15, 15));
        grid1.setVgap(15);
        grid1.setHgap(35);
        grid1.setAlignment(Pos.CENTER);

        grid1.add(textMenu, 0, 0);
        grid1.add(cekBox1, 0, 1);
        grid1.add(cekBox2, 0, 2);
        grid1.add(cekBox3, 0, 3);
        grid1.add(cekBox4, 0, 4);
        grid1.add(cekBox5, 0, 5);

        grid1.add(textHarga, 1, 0);
        grid1.add(text1, 1, 1);
        grid1.add(text2, 1, 2);
        grid1.add(text3, 1, 3);
        grid1.add(text4, 1, 4);
        grid1.add(text5, 1, 5);

        grid1.add(textJumlah, 2, 0);
        grid1.add(textField1, 2, 1);
        grid1.add(textField2, 2, 2);
        grid1.add(textField3, 2, 3);
        grid1.add(textField4, 2, 4);
        grid1.add(textField5, 2, 5);

        // Button
        Button button1 = new Button("Bayar");
        button1.setOnAction(e -> {
            try {
                klikBayar(stage);
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        });

        // Layout scene 1
        VBox vbox1 = new VBox(15, textHeader, grid1, button1);
        vbox1.setPadding(new Insets(10, 10, 10, 10));
        vbox1.setAlignment(Pos.CENTER);

        scene1 = new Scene(vbox1, 500, 400);
        stage.setScene(scene1);
        stage.show();       
    }

    private void setScene2() {
        //layout scene 2--------------------------------------------------------

        // text header
        Text textBayar = new Text("Pembayaran");

        // garis batas
        Separator garis1 = new Separator(Orientation.HORIZONTAL);
        Separator garis2 = new Separator(Orientation.HORIZONTAL);

        //grid nota
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(12, 12, 12, 12));  
        grid2.setVgap(10);
        grid2.setHgap(25);
        grid2.setAlignment(Pos.CENTER);

        Integer sum = 0;
        for (int i = 0; i < lsTemp.size(); i++) {
            //jumlah orderan
            String temp1 = String.valueOf(lsOrderan.get(lsTemp.get(i)).get(1));
            grid2.add(new Text(temp1), 0, i);

            //nama orderan
            grid2.add(lsTextOrder.get(i), 1, i);

            //total harga orderan
            Integer totalOrderan = lsOrderan.get(lsTemp.get(i)).get(0)
                                   * lsOrderan.get(lsTemp.get(i)).get(1);
            String temp2 = String.valueOf(totalOrderan);
            grid2.add(new Text(temp2), 2, i);

            sum += totalOrderan;
        }
        grid2.add(new Text("Total"), 1, lsTemp.size() + 2);
        grid2.add(new Text(String.valueOf(sum)), 2, lsTemp.size() + 2);
        
        vbox2 = new VBox(12);
        vbox2.setPadding(new Insets(12, 120, 12, 120));
        vbox2.setAlignment(Pos.CENTER);

        vbox2.getChildren().addAll(textBayar, garis1, grid2, garis2);
        vbox2.getChildren().add(new Text("Terima Kasih. Selamat datang kembali :)"));

        
        scene2 = new Scene(vbox2, 500, 400);
    }

    private void klikBayar(Stage stage) throws Exception{
        getCheckBox(cekBox1, cekBox2, cekBox3, cekBox4, cekBox5);
        getOrderan(textField1, textField2, textField3, textField4, textField5);
        if (isError) return;

        boolean tempAdded = false;
        for (int i = 0; i < isAdded.length; i++) {
            if (isAdded[i]) lsTemp.add(i);
            tempAdded = tempAdded || isAdded[i];
        }

        if (tempAdded) {
            confirmBox();
            if (isOrder) {
                setScene2();
                stage.setScene(scene2);
            } else {
                return;
            }
        } else {
            AlertBox("Anda belum memesan menu apapun\nPesan minimal 1 menu.");
        } 
    }

    private void AlertBox(String message) {
        Stage tempStage = new Stage();
        tempStage.initModality(Modality.APPLICATION_MODAL);
        tempStage.setTitle("Error!");
        tempStage.setMinHeight(200);
        tempStage.setMinWidth(300);

        Text txt = new Text(message);
        Button buttonOk = new Button(" OK ");
        buttonOk.setOnAction(e -> tempStage.close());

        VBox tempBox = new VBox(15, txt, buttonOk);
        tempBox.setPadding(new Insets(12, 12, 12, 12));
        tempBox.setAlignment(Pos.CENTER);

        Scene tempScene = new Scene(tempBox);
        tempStage.setScene(tempScene);
        tempStage.showAndWait();
    }

    private void confirmBox() {
        Stage tempStage = new Stage();
        tempStage.initModality(Modality.APPLICATION_MODAL);
        tempStage.setTitle("Confirm");
        tempStage.setMinHeight(200);
        tempStage.setMinWidth(300);

        Text txt = new Text("Apakah pesanan anda sudah benar?");
        Button buttonYa = new Button(" YA ");
        Button buttonTidak = new Button(" TIDAK ");

        buttonYa.setOnAction(e -> {
            isOrder = true;
            tempStage.close();
        });
        buttonTidak.setOnAction(e -> {
            isOrder = false;
            lsTextOrder.clear();
            lsTemp.clear();
            for (int i = 0; i < isAdded.length; i++) {
                if (isAdded[i]) {
                    lsOrderan.get(i).remove(1);
                    isAdded[i] = false;
                }
            }
            tempStage.close();
        });

        HBox boxButton = new HBox(15, buttonYa, buttonTidak);
        boxButton.setAlignment(Pos.CENTER);
        VBox tempBox = new VBox(12, txt, boxButton);
        tempBox.setPadding(new Insets(12, 12, 12, 12));
        tempBox.setAlignment(Pos.CENTER);

        Scene tempScene = new Scene(tempBox);
        tempStage.setScene(tempScene);
        tempStage.showAndWait();

    }

    private void getCheckBox(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4, CheckBox cb5) {
        if (cb1.isSelected() && !isAdded[0]) {
            lsTextOrder.add(new Text(cb1.getText()));
            isAdded[0] = true;
        } 
        if (cb2.isSelected() && !isAdded[1]) {
            lsTextOrder.add(new Text(cb2.getText()));
            isAdded[1] = true;
        }
        if (cb3.isSelected() && !isAdded[2]) {
            lsTextOrder.add(new Text(cb3.getText()));
            isAdded[2] = true;
        }
        if (cb4.isSelected() && !isAdded[3]) {
            lsTextOrder.add(new Text(cb4.getText()));
            isAdded[3] = true;
        }
        if (cb5.isSelected() && !isAdded[4]) {
            lsTextOrder.add(new Text(cb5.getText()));
            isAdded[4] = true;
        }

    }

    private void getOrderan(TextField tf1, TextField tf2, TextField tf3, TextField tf4, TextField tf5) {
        isError = false;
        try {
            if (isAdded[0]) lsOrderan.get(0).add(Integer.valueOf(tf1.getText()));
            if (isAdded[1]) lsOrderan.get(1).add(Integer.valueOf(tf2.getText()));
            if (isAdded[2]) lsOrderan.get(2).add(Integer.valueOf(tf3.getText()));
            if (isAdded[3]) lsOrderan.get(3).add(Integer.valueOf(tf4.getText()));
            if (isAdded[4]) lsOrderan.get(4).add(Integer.valueOf(tf5.getText()));
        } catch (Exception e) {
            AlertBox("Tolong input angka dalam jumlah pesanan anda.");
            isError = true;
            for (int i = 0; i < isAdded.length; i++) 
                isAdded[i] = false;
        }
    }

    
}