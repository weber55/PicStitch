/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs1302.p2;

import cs1302.effects.Artsy;
import java.awt.Desktop;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;

/**
 * This is the driver for this application.
 * This class holds method that allow the user to interact with the GUI in order to add effects to images.
 * @author SAMANTHA CARLILE <sjc13678@uga.edu>
 * @author BRIAN WEBER <bw75762@uga.edu>
 */

public class Driver extends Application {
    final ImageView pic = new ImageView();
    final ImageView pic2 = new ImageView();
    final ImageView pic3 = new ImageView();
    final Button btn = new Button();
    final Button btn2 = new Button();
    final Button btn3 = new Button();
    final Button rotate1 = new Button();
    final Button rotate2 = new Button();
    final Button rotate3 = new Button();
    final Button reset1 = new Button();
    final Button reset2 = new Button();
    final Button reset3 = new Button();
    Label picture = new Label();
    Label picture2 = new Label();
    Label picture3 = new Label();
    public Image defaultImage = new Image("file:resources/default.png");
    public double degrees;
    public Image image1 = defaultImage;
    public Image image2 = defaultImage;
    public Image imageResult = defaultImage;
    public Image image1Rotated = null;
    public Image image2Rotated = null;
    public Image imageResultRotated = null;
    public int width;
    public int height;
    public int size;
    public String imagePath1 = "default.png";
    public String imagePath2 = "default.png";
    public Label title1;
    public Label title2;
    
    Desktop desktop = Desktop.getDesktop();
    
/**
 * This method creates and shows the GUI for the user to interact with.
 * @param stage to display contents
 */
    
    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        border.setTop(addHbox());
        border.setLeft(addVBox()); 
        border.setCenter(addVBox1());
        border.setRight(addVBox2());
        Scene scene = new Scene(border,1100,560);
        stage.setScene(scene);
        stage.setTitle("Artsy!");
        stage.sizeToScene();
        stage.show();
    } // createAndShowGUI
    
 /**
 * This is the main method. 
 */

    public static void main(String[] args) {
        launch(args);
    } // main
/**
 * This method creates an HBox to add contents for the user to use on the GUI.
 * It sets up all the features for the user to interact with including buttons and image options
 * @return BorderPane layout to add to the stage to display to the user. 
 */
    
    public BorderPane addHbox() {
        BorderPane border = new BorderPane(); //variables
        VBox vbox = new VBox(10);
        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.TOP_LEFT);
        vbox.setAlignment(Pos.BOTTOM_LEFT);
        vbox.setPadding(new Insets(10,0,10,10));
        
        //Menu with FileChooser
        Stage stage = new Stage();
        FlowPane root = new FlowPane();
        Scene scene = new Scene(root);
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        root.getChildren().addAll(menuBar);
        final Menu menu = new Menu("File");
        Menu open = new Menu("Open");
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif", "*.BMP", "*.PNG", "*.JPG", "*.GIF"));
        
        //Open Image 1
        MenuItem openImage1 = new MenuItem("Open Image 1");
        openImage1.setOnAction((final ActionEvent e) -> {
        File file1 = fileChooser.showOpenDialog(stage);
        if(file1 != null) {
            try {
                imagePath1 = file1.toURI().toURL().toString();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
            }
            image1 = new Image(imagePath1, 300, 300, true, true);
            title1.setText("Image 1: " + file1.getName());
            ImageView iv = new ImageView();
            iv.setImage(image1);
            iv.setFitWidth(300);
            iv.setFitHeight(300);
            picture.setGraphic(iv);
        }});
        //Open Image 1
        
        //Open Image 2
        MenuItem openImage2 = new MenuItem("Open Image 2");
        openImage2.setOnAction((final ActionEvent e) -> {
        File file2 = fileChooser.showOpenDialog(stage);
        if(file2 != null) {
            try {
                imagePath2 = file2.toURI().toURL().toString();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
            }
            image2 = new Image(imagePath2, 300, 300, true, true);
            title2.setText("Image 2: " + file2.getName());
            ImageView iv2 = new ImageView();
            iv2.setImage(image2);
            iv2.setFitWidth(300);
            iv2.setFitHeight(300);
            picture2.setGraphic(iv2);
        }});
        //Open Image 2
        
        open.getItems().addAll(openImage1, openImage2);
        menu.getItems().add(open);
        //Save Image
        MenuItem saveResultAs = new MenuItem("Save Result As");
        saveResultAs.setOnAction(ActionEvent -> {
            FileChooser fileChooser1 = new FileChooser();
            fileChooser1.setInitialFileName(".png");
            fileChooser1.setTitle("Save Image");
            File file = fileChooser1.showSaveDialog(stage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(pic3.getImage(),
                            null), "png", file);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }); //Save image
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(actionEvent -> Platform.exit());
        menu.getItems().addAll(open, new SeparatorMenuItem(), saveResultAs, new SeparatorMenuItem(), exit);
        menuBar.getMenus().addAll(menu);
        //Menu
        
        //Checkers
        btn.setText("Checkers");
        btn.setOnAction((ActionEvent event) -> {
            if (image1 == defaultImage || image2 == defaultImage) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please open your images first");
                alert.show();
            }
            else {
                Stage stage2 = new Stage();
                stage2.setHeight(150);
                stage2.setWidth(450);
                stage2.setTitle("Checkers Options");
                Label label1 = new Label("Please enter the desired checker width, in pixels: ");
                TextField textField = new TextField ();
                VBox vb = new VBox();
                label1.setStyle("-fx-font-size: 12pt;");
                vb.getChildren().addAll(label1, textField);
                vb.setSpacing(20);
                Button cancel = new Button();
                cancel.setText("Cancel");
                cancel.setOnAction(actionEvent -> stage2.close());
                Button ok = new Button();
                ok.setText("Ok");
                ok.setOnAction((ActionEvent event1) -> {
                    String value = textField.getText();
                    if (value.matches("[0-9]*") && Integer.parseInt(value) <= 300 && Integer.parseInt(value) > 0 && (300%Integer.parseInt(value) == 0)) { 
                        size = Integer.parseInt(value);
                        MyArtsy checkers = new MyArtsy();
                        if (image1Rotated != null && image2Rotated != null) {
                            imageResult = checkers.doCheckers(image1Rotated, image2Rotated, size);
                        }
                        else if (image1Rotated != null && image2Rotated == null) {
                            imageResult = checkers.doCheckers(image1Rotated, image2, size);
                        }
                        else if (image1Rotated == null && image2Rotated != null) {
                            imageResult = checkers.doCheckers(image1, image2Rotated, size);
                        }
                        else {
                            imageResult = checkers.doCheckers(image1, image2, size);
                        }
                        pic3.setImage(imageResult);
                        pic3.setFitWidth(300);
                        pic3.setFitHeight(300);
                        picture3.setGraphic(pic3);
                        stage2.close();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a valid size. (1-300 and a factor of 300)");
                        alert.show();
                    }
                });
                HBox hb = new HBox();
                hb.getChildren().addAll(cancel, ok);
                hb.setSpacing(20);
                BorderPane border2 = new BorderPane();
                border2.setStyle("-fx-background-color: #438693;");
                border2.setTop(vb);
                border2.setBottom(hb);
                Scene scene2 = new Scene(border2);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(
                        ((Node)event.getSource()).getScene().getWindow());
                stage2.setScene(scene2);
                stage2.show();
            }
        }); //Checkers
        
        //Horizontal Stripes
        btn2.setText("Horizontal Stripes");
        btn2.setOnAction((ActionEvent event) -> {
            if (image1 == defaultImage || image2 == defaultImage) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please open your images first");
                alert.show();
            }
            else {
                Stage stage2 = new Stage();
                stage2.setHeight(150);
                stage2.setWidth(450);
                stage2.setTitle("Horizontal Stripes Options");
                Label label1 = new Label("Please enter the desired stripe height, in pixels: ");
                TextField textField = new TextField ();
                VBox vb = new VBox();
                label1.setStyle("-fx-font-size: 12pt;");
                vb.getChildren().addAll(label1, textField);
                vb.setSpacing(20);
                Button cancel = new Button();
                cancel.setText("Cancel");
                cancel.setOnAction(actionEvent -> stage2.close());
                Button ok = new Button();
                ok.setText("Ok");
                ok.setOnAction((ActionEvent event1) -> {
                    String value = textField.getText();
                    if (value.matches("[0-9]*") && Integer.parseInt(value) <= 300 && Integer.parseInt(value) > 0) {
                        height = Integer.parseInt(value);
                        MyArtsy horizontalStripes = new MyArtsy();
                        if (image1Rotated != null && image2Rotated != null) {
                            imageResult = horizontalStripes.doHorizontalStripes(image1Rotated, image2Rotated, height);
                        }
                        else if (image1Rotated != null && image2Rotated == null) {
                            imageResult = horizontalStripes.doHorizontalStripes(image1Rotated, image2, height);
                        }
                        else if (image1Rotated == null && image2Rotated != null) {
                            imageResult = horizontalStripes.doHorizontalStripes(image1, image2Rotated, height);
                        }
                        else {
                            imageResult = horizontalStripes.doHorizontalStripes(image1, image2, height);
                        }
                        pic3.setImage(imageResult);
                        pic3.setFitWidth(300);
                        pic3.setFitHeight(300);
                        picture3.setGraphic(pic3);
                        stage2.close();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a valid height");
                        alert.show();
                    }
                });
                HBox hb = new HBox();
                hb.getChildren().addAll(cancel, ok);
                hb.setSpacing(20);
                BorderPane border2 = new BorderPane();
                border2.setStyle("-fx-background-color: #438693;");
                border2.setTop(vb);
                border2.setBottom(hb);
                Scene scene2 = new Scene(border2);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(
                        ((Node)event.getSource()).getScene().getWindow() );
                stage2.setScene(scene2);
                stage2.show();
            }
        }); //Horizontal Stripes
        
        //Vertical Stripes
        btn3.setText("Vertical Stripes");
        btn3.setOnAction((ActionEvent event) -> {
            if (image1 == defaultImage || image2 == defaultImage) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please open your images first");
                alert.show();
            }
            else {
                Stage stage2 = new Stage();
                stage2.setHeight(150);
                stage2.setWidth(450);
                stage2.setTitle("Vertical Stripes Options");
                Label label1 = new Label("Please enter the desired vertical stripe width, in pixels: ");
                TextField textField = new TextField ();
                VBox vb = new VBox();
                label1.setStyle("-fx-font-size: 12pt;");
                vb.getChildren().addAll(label1, textField);
                vb.setSpacing(20);
                Button cancel = new Button();
                cancel.setText("Cancel");
                cancel.setOnAction(actionEvent -> stage2.close());
                Button ok = new Button();
                ok.setText("Ok");
                ok.setOnAction((ActionEvent event1) -> {
                    String value = textField.getText();
                    if (value.matches("[0-9]*") && Integer.parseInt(value) <= 300 && Integer.parseInt(value) > 0) {
                        width = Integer.parseInt(value);
                        MyArtsy verticalStripes = new MyArtsy();
                        if (image1Rotated != null && image2Rotated != null) {
                            imageResult = verticalStripes.doVerticalStripes(image1Rotated, image2Rotated, width);
                        }
                        else if (image1Rotated != null && image2Rotated == null) {
                            imageResult = verticalStripes.doVerticalStripes(image1Rotated, image2, width);
                        }
                        else if (image1Rotated == null && image2Rotated != null) {
                            imageResult = verticalStripes.doVerticalStripes(image1, image2Rotated, width);
                        }
                        else {
                            imageResult = verticalStripes.doVerticalStripes(image1, image2, width);
                        }
                        pic3.setImage(imageResult);
                        pic3.setFitWidth(300);
                        pic3.setFitHeight(300);
                        picture3.setGraphic(pic3);
                        stage2.close();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a valid width");
                        alert.show();
                    }
                });
                HBox hb = new HBox();
                hb.getChildren().addAll(cancel, ok);
                hb.setSpacing(20);
                BorderPane border2 = new BorderPane();
                border2.setStyle("-fx-background-color: #438693;");
                border2.setTop(vb);
                border2.setBottom(hb);
                Scene scene2 = new Scene(border2);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(
                        ((Node)event.getSource()).getScene().getWindow() );
                stage2.setScene(scene2);
                stage2.show();
            }
        }); //Vertical Stripes
        
        //Rotate Buttons
        rotate1.setText("Rotate");
        rotate1.setOnAction((ActionEvent event) -> {
            if (image1 == defaultImage) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please open an image first");
                alert.show();
            }
            else {
                Stage stage2 = new Stage();
                stage2.setHeight(150);
                stage2.setWidth(300);
                stage2.setTitle("Rotate Image Options");
                Label label1 = new Label("Please enter an angle, in degrees: ");
                TextField textField = new TextField ();
                VBox vb = new VBox();
                label1.setStyle("-fx-font-size: 12pt;");
                vb.getChildren().addAll(label1, textField);
                vb.setSpacing(20);
                Button cancel = new Button();
                cancel.setText("Cancel");
                cancel.setOnAction(actionEvent -> stage2.close());
                Button ok = new Button();
                ok.setText("Ok");
                ok.setOnAction((ActionEvent event1) -> {
                    String value = textField.getText();
                    if (value.matches("[0-9]*")) {
                        degrees = Double.parseDouble(value);
                        MyArtsy rotate = new MyArtsy();
                        if (image1Rotated == null) {
                            image1Rotated = rotate.doRotate(image1, degrees);
                        }
                        else {
                            image1Rotated = rotate.doRotate(image1Rotated, degrees);
                        }
                        pic.setImage(image1Rotated);
                        pic.setFitWidth(300);
                        pic.setFitHeight(300);
                        picture.setGraphic(pic);
                        stage2.close();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a numerical value");
                        alert.show();
                    }
                });
                HBox hb = new HBox();
                hb.getChildren().addAll(cancel, ok);
                hb.setSpacing(20);
                BorderPane border2 = new BorderPane();
                border2.setStyle("-fx-background-color: #438693;");
                border2.setTop(vb);
                border2.setBottom(hb);
                Scene scene2 = new Scene(border2);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(
                        ((Node)event.getSource()).getScene().getWindow() );
                stage2.setScene(scene2);
                stage2.show();
            }
        }); //Rotate image one
        rotate2.setText("Rotate");
        rotate2.setOnAction((ActionEvent event) -> {
            if (image2 == defaultImage) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please open an image first");
                alert.show();
            }
            else {
                Stage stage2 = new Stage();
                stage2.setHeight(150);
                stage2.setWidth(300);
                stage2.setTitle("Rotate Image Options");
                Label label1 = new Label("Please enter an angle, in degrees: ");
                TextField textField = new TextField ();
                VBox vb = new VBox();
                label1.setStyle("-fx-font-size: 12pt;");
                vb.getChildren().addAll(label1, textField);
                vb.setSpacing(20);
                Button cancel = new Button();
                cancel.setText("Cancel");
                cancel.setOnAction(actionEvent -> stage2.close());
                Button ok = new Button();
                ok.setText("Ok");
                ok.setOnAction((ActionEvent event1) -> {
                    String value = textField.getText();
                    if (value.matches("[0-9]*")) {
                        degrees = Double.parseDouble(value);
                        MyArtsy rotate = new MyArtsy();
                        if (image2Rotated == null) {
                            image2Rotated = rotate.doRotate(image2, degrees);
                        }
                        else {
                            image2Rotated = rotate.doRotate(image2Rotated, degrees);
                        }
                        pic2.setImage(image2Rotated);
                        pic2.setFitWidth(300);
                        pic2.setFitHeight(300);
                        picture2.setGraphic(pic2);
                        stage2.close();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a numerical value");
                        alert.show();
                    }
                });
                HBox hb = new HBox();
                hb.getChildren().addAll(cancel, ok);
                hb.setSpacing(20);
                BorderPane border2 = new BorderPane();
                border2.setStyle("-fx-background-color: #438693;");
                border2.setTop(vb);
                border2.setBottom(hb);
                Scene scene2 = new Scene(border2);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(
                        ((Node)event.getSource()).getScene().getWindow() );
                stage2.setScene(scene2);
                stage2.show();
            }
        }); //Rotate image two
        rotate3.setText("Rotate");
        rotate3.setOnAction((ActionEvent event) -> {
            if (imageResult == defaultImage) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please create an image first");
                alert.show();
            }
            else {
                Stage stage2 = new Stage();
                stage2.setHeight(150);
                stage2.setWidth(300);
                stage2.setTitle("Rotate Image Options");
                Label label1 = new Label("Please enter an angle, in degrees: ");
                TextField textField = new TextField ();
                VBox vb = new VBox();
                label1.setStyle("-fx-font-size: 12pt;");
                vb.getChildren().addAll(label1, textField);
                vb.setSpacing(20);
                Button cancel = new Button();
                cancel.setText("Cancel");
                cancel.setOnAction(actionEvent -> stage2.close());
                Button ok = new Button();
                ok.setText("Ok");
                ok.setOnAction((ActionEvent event1) -> {
                    String value = textField.getText();
                    if (value.matches("[0-9]*")) {
                        degrees = Double.parseDouble(value);
                        MyArtsy rotate = new MyArtsy();
                        if (imageResultRotated == null) {
                            imageResultRotated = rotate.doRotate(imageResult, degrees);
                        }
                        else {
                            imageResultRotated = rotate.doRotate(imageResultRotated, degrees);
                        }
                        pic3.setImage(imageResultRotated);
                        pic3.setFitWidth(300);
                        pic3.setFitHeight(300);
                        picture3.setGraphic(pic3);
                        stage2.close();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a numerical value");
                        alert.show();
                    }
                });
                HBox hb = new HBox();
                hb.getChildren().addAll(cancel, ok);
                hb.setSpacing(20);
                BorderPane border2 = new BorderPane();
                border2.setStyle("-fx-background-color: #438693;");
                border2.setTop(vb);
                border2.setBottom(hb);
                Scene scene2 = new Scene(border2);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(
                        ((Node)event.getSource()).getScene().getWindow() );
                stage2.setScene(scene2);
                stage2.show();
            }
        }); //Rotate result image
        //Rotate Buttons
        
        //Reset Buttons
        reset1.setText("Reset");
        reset1.setOnAction((ActionEvent event) -> {
            pic.setImage(image1);
            pic.setFitWidth(300);
            pic.setFitHeight(300);
            picture.setGraphic(pic);
            if (image1 == defaultImage) {
                image1 = defaultImage;
            }
            image1Rotated = null;
        });
        reset2.setText("Reset");
        reset2.setOnAction((ActionEvent event) -> {
            pic2.setImage(image2);
            pic2.setFitWidth(300);
            pic2.setFitHeight(300);
            picture2.setGraphic(pic2);
            if (image2 == defaultImage) {
                image2 = defaultImage;
            }
            image2Rotated = null;
        });
        reset3.setText("Reset");
        reset3.setOnAction((ActionEvent event) -> {
            pic3.setImage(defaultImage);
            pic3.setFitWidth(300);
            pic3.setFitHeight(300);
            picture3.setGraphic(pic3);
            if (imageResult == defaultImage) {
                imageResult = defaultImage;
            }
            imageResultRotated = null;
        });
        //Reset Buttons
        
        FlowPane flow = new FlowPane();
        flow.setPrefWrapLength(1100);
        flow.setHgap(10);
        flow.setVgap(10);
        flow.getChildren().addAll(btn,btn2,btn3);
            vbox2.getChildren().add(menuBar);
            vbox.getChildren().addAll(flow);
            border.setTop(vbox2);
            border.setBottom(vbox);
            return border; //return to start method
    } //Add Hbox
    
 /**
 * This method does not accept any parameters. It sets up a space for image one to be displayed.
 * @return vbox that holds contents for user to see.
 */
    
    public VBox addVBox(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30));
        vbox.setSpacing(20);
        vbox.setStyle("-fx-background-color: #336670;");
        title1 = new Label("Image 1: " + imagePath1);
        title1.setStyle("-fx-font-size: 15pt;");
        if (image1 == defaultImage) {    
            pic.setImage(defaultImage);
            picture.setGraphic(pic);
        }
        vbox.getChildren().addAll(title1, picture);
        vbox.getChildren().addAll(rotate1, reset1);
        return vbox; //return to start method
    } //Add VBox
    
 /**
 * This method does not accept any parameters. It sets up a space for image two to be displayed.
 * @return vbox that holds contents for user to see.
 */
    public VBox addVBox1(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30, 0, 30, 30));
        vbox.setSpacing(20);      
        vbox.setStyle("-fx-background-color: #438693;");
        title2 = new Label("Image 2: " + imagePath2);
        title2.setStyle("-fx-font-size: 15pt;");
        if (image2 == defaultImage) {    
            pic2.setImage(defaultImage);
            picture2.setGraphic(pic2);
        }
        vbox.getChildren().addAll(title2, picture2);
        vbox.getChildren().addAll(rotate2, reset2);
        return vbox; //return to start method
    } //Add VBox 1
 /**
 * This method does not accept any parameters. It sets up a space for result image to be displayed.
 * @return vbox that holds contents for user to see.
 */
    
    public VBox addVBox2(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30));
        vbox.setSpacing(20);      
        vbox.setStyle("-fx-background-color: #4b96a5;");
        Label title3 = new Label("Result");
        title3.setStyle("-fx-font-size: 15pt;");
        pic3.setImage(defaultImage);
        picture3.setGraphic(pic3);
        vbox.getChildren().addAll(title3, picture3);
        vbox.getChildren().addAll(rotate3, reset3);
        return vbox; //return to start method
    } //Add VBox 2
 /**
 * This method does not return anything. It is used to open a file. 
 * @param file 
 */
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                Driver.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    } //Open File
    
} // Driver