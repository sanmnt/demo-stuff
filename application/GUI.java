package application;
// Peer Coded By Alson Kharel, Ariel Wu, Kelsey Coen
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Integer;
import java.lang.NumberFormatException;
import java.io.*;


import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.*;
import javafx.scene.web.*;
import javafx.scene.image.*;
import javafx.scene.Group;
import javafx.scene.text.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;



public class GUI extends Application {
    public class Row {
        //Assume each Row have 6 elements, all String
        private SimpleStringProperty f1, f2, f3, f4, f5, f6, f7, f8, f9;
        public String getF1() {
            return f1.get();
        }
        public String getF2() {
            return f2.get();
        }
        public String getF3() {
            return f3.get();
        }
        public String getF4() {
            return f4.get();
        }
        public String getF5() {
            return f5.get();
        }
        public String getF6() {
            return f6.get();
        }
        public String getF7() {
            return f7.get();
        }
        public String getF8() {
            return f8.get();
        }
        public String getF9() {
            return f9.get();
        }
        Row(String f1, String f2, String f3, String f4,
            String f5, String f6, String f7, String f8, String f9) {
            this.f1 = new SimpleStringProperty(f1);
            this.f2 = new SimpleStringProperty(f2);
            this.f3 = new SimpleStringProperty(f3);
            this.f4 = new SimpleStringProperty(f4);
            this.f5 = new SimpleStringProperty(f5);
            this.f6 = new SimpleStringProperty(f6);
            this.f7 = new SimpleStringProperty(f7);
            this.f8 = new SimpleStringProperty(f8);
            this.f9 = new SimpleStringProperty(f9);
        }
    }
    Stage window;
    Scene main, newOrder, order, data, overhead, employee, shipping, show, search;
    private int employee_column, manuf_column, ship_column, order_column; // For check functions
    ComboBox<String> shipCompany, findOrder;
    RadioButton yes, no, yes1, no1;
    String order_number;
    int employee_costs, shipping_costs, overhead_costs, total_cost;
    DropShadow shadow = new DropShadow();

    private final TableView<Row> tableView = new TableView<>();
    private final ObservableList<Row> dataList
            = FXCollections.observableArrayList();
    private void readCSV() {
        String CsvFile = "C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\order.csv";
        String FieldDelimiter = ",";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                Row Row = new Row(fields[0], fields[1], fields[2],
                        fields[3], fields[4], fields[5], fields[6], fields[7], fields[8]);
                dataList.add(Row);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public  void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Shamwow - Billing and Payment System");
        Label welcome_title = new Label("Welcome to Shamwow Billing and Payment System!");
        Label welcomeMsg = new Label("\nYou may make new order, look up or update cost information of materials, manufacturers, shippings, \noverheads and employees."
                + "\n\nClick on the buttons on the left to proceed.");
        welcome_title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25));

		/* -----------------------------------------------------------------------------
		 Main Page
		 ----------------------------------------------------------------------------- */
        // list of buttons in the main page
        Button order_button = new Button("Make New Order");
        order_button.setOnAction(e -> window.setScene(newOrder));
        order_button.setPrefWidth(150);
        order_button.setPrefHeight(40);
        order_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        order_button.setOnMouseEntered(e -> {
            order_button.setEffect(shadow);
            order_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        order_button.setOnMouseExited(e -> {
            order_button.setEffect(null);
            order_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        Button data_button = new Button("Enter Data");
        data_button.setOnAction(e -> window.setScene(data));
        data_button.setPrefWidth(150);
        data_button.setPrefHeight(40);
        data_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        data_button.setOnMouseEntered(e -> {
            data_button.setEffect(shadow);
            data_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        data_button.setOnMouseExited(e -> {
            data_button.setEffect(null);
            data_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        Button exit_button = new Button("Exit");
        exit_button.setOnAction(e -> System.exit(0));
        exit_button.setPrefWidth(150);
        exit_button.setPrefHeight(40);
        exit_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        exit_button.setOnMouseEntered(e -> {
            exit_button.setEffect(shadow);
            exit_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        exit_button.setOnMouseExited(e -> {
            exit_button.setEffect(null);
            exit_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        Button report_button = new Button("Check Overall Costs");
        report_button.setOnAction(e -> {
            shipping_costs = general.shippingCosts();
            employee_costs = general.laborCost();
            overhead_costs = general.overheadCost();
            total_cost = general.totalCost();
            CostSummaryBox.display("Cost Summary", shipping_costs, employee_costs, overhead_costs, total_cost);
        });
        report_button.setPrefWidth(150);
        report_button.setPrefHeight(40);
        report_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        report_button.setOnMouseEntered(e -> {
            report_button.setEffect(shadow);
            report_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        report_button.setOnMouseExited(e -> {
            report_button.setEffect(null);
            report_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        Button show_button = new Button("Show Order History");
        show_button.setOnAction(e -> window.setScene(show));
        show_button.setPrefWidth(150);
        show_button.setPrefHeight(40);
        show_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        show_button.setOnMouseEntered(e -> {
            show_button.setEffect(shadow);
            show_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        show_button.setOnMouseExited(e -> {
            show_button.setEffect(null);
            show_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        Button search_button = new Button("Find Orders");
        search_button.setOnAction(e -> window.setScene(search));
        search_button.setPrefWidth(150);
        search_button.setPrefHeight(40);
        search_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        search_button.setOnMouseEntered(e -> {
            search_button.setEffect(shadow);
            search_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        search_button.setOnMouseExited(e -> {
            search_button.setEffect(null);
            search_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });


        VBox button_list = new VBox(10);
        button_list.setPrefWidth(200);
        button_list.getChildren().addAll(order_button, data_button, report_button, show_button, search_button, exit_button);
        button_list.setPadding(new Insets(30, 0, 0, 10));
        VBox main_content = new VBox();
        main_content.getChildren().addAll(welcome_title, welcomeMsg);
        main_content.setPadding(new Insets(30, 0, 0, 0));
        // make layout for main page
        HBox main_layout = new HBox(45);
        // try {
        Image image = new Image("https://cdn.discordapp.com/attachments/551214454587654155/838517821742252143/6e8e5fc722a47865e3d299a7b8090d5b.w973.h765.jpg");
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        // create Background
        Background background = new Background(backgroundimage);
        // set background
        main_layout.setBackground(background);
        main_layout.getChildren().addAll(button_list, main_content);
        // } catch (FileNotFoundException  ex) {
        // join buttons and welcomeMsg
        // main_layout.getChildren().addAll(button_list, welcomeMsg);
        // }
        main = new Scene(main_layout, 900, 600);
        /* -----------------------------------------------------------------------------
		 Data Page
		 ----------------------------------------------------------------------------- */
        Label dataPageMsg = new Label("Please choose the data you would like to add/update/remove."
                + "\n\nClick on the buttons on the left to proceed.");
        Button goBack_button = new Button("<< Go back");
        goBack_button.setOnAction(e -> window.setScene(main));
        goBack_button.setPrefWidth(150);
        goBack_button.setPrefHeight(30);
        goBack_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        goBack_button.setOnMouseEntered(e -> {
            goBack_button.setEffect(shadow);
            goBack_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        goBack_button.setOnMouseExited(e -> {
            goBack_button.setEffect(null);
            goBack_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        Button shipping_button = new Button("Shipping");
        shipping_button.setOnAction(e -> window.setScene(shipping));
        shipping_button.setPrefWidth(150);
        shipping_button.setPrefHeight(30);
        shipping_button.setStyle("-fx-background-color: #000000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        shipping_button.setOnMouseEntered(e -> {
            shipping_button.setEffect(shadow);
            shipping_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        shipping_button.setOnMouseExited(e -> {
            shipping_button.setEffect(null);
            shipping_button.setStyle("-fx-background-color: #000000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        Button overhead_button = new Button("Overhead");
        overhead_button.setOnAction(e -> window.setScene(overhead));
        overhead_button.setPrefWidth(150);
        overhead_button.setPrefHeight(30);
        overhead_button.setStyle("-fx-background-color: #000000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        overhead_button.setOnMouseEntered(e -> {
            overhead_button.setEffect(shadow);
            overhead_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        overhead_button.setOnMouseExited(e -> {
            overhead_button.setEffect(null);
            overhead_button.setStyle("-fx-background-color: #000000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        Button employee_button = new Button("Employee");
        employee_button.setOnAction(e -> window.setScene(employee));
        employee_button.setPrefWidth(150);
        employee_button.setPrefHeight(30);
        employee_button.setStyle("-fx-background-color: #000000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        employee_button.setOnMouseEntered(e -> {
            employee_button.setEffect(shadow);
            employee_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        employee_button.setOnMouseExited(e -> {
            employee_button.setEffect(null);
            employee_button.setStyle("-fx-background-color: #000000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        Button order_button1 = new Button("Order");
        order_button1.setOnAction(e -> window.setScene(order));
        order_button1.setPrefWidth(150);
        order_button1.setPrefHeight(30);
        order_button1.setStyle("-fx-background-color: #000000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        order_button1.setOnMouseEntered(e -> {
            order_button1.setEffect(shadow);
            order_button1.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        order_button1.setOnMouseExited(e -> {
            order_button1.setEffect(null);
            order_button1.setStyle("-fx-background-color: #000000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        Button exit_button1 = new Button("Exit");
        exit_button1.setOnAction(e -> System.exit(0));
        exit_button1.setPrefWidth(150);
        exit_button1.setPrefHeight(30);
        exit_button1.setStyle("-fx-background-color: #800000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        exit_button1.setOnMouseEntered(e -> {
            exit_button1.setEffect(shadow);
            exit_button1.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        exit_button1.setOnMouseExited(e -> {
            exit_button1.setEffect(null);
            exit_button1.setStyle("-fx-background-color: #800000; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        VBox data_buttonlist = new VBox(10);
        data_buttonlist.setPrefWidth(200);
        data_buttonlist.getChildren().addAll(goBack_button,  shipping_button, overhead_button, employee_button, order_button1);
        HBox data_layout = new HBox(50);
        data_layout.setPadding(new Insets(10, 0, 0, 10));
        data_layout.getChildren().addAll(data_buttonlist, dataPageMsg);
        data = new Scene(data_layout, 900, 600);

		/* -----------------------------------------------------------------------------
		 Make New Order Page
		 ----------------------------------------------------------------------------- */
        Label newOrder_label = new Label("");
        Button goBack_button1 = new Button("<< Go back");
        goBack_button1.setOnAction(e -> {
            window.setScene(main);
            newOrder_label.setText("");
        });
        goBack_button1.setPrefWidth(150);
        goBack_button1.setPrefHeight(30);
        goBack_button1.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        goBack_button1.setOnMouseEntered(e -> {
            goBack_button1.setEffect(shadow);
            goBack_button1.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        goBack_button1.setOnMouseExited(e -> {
            goBack_button1.setEffect(null);
            goBack_button1.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        HBox newOrder_layout = new HBox(20);
        Label order_step1 = new Label("Enter Number of Units Wanted:");
        TextField units =  new TextField();
        Label order_step2 = new Label("Choose Shipping Company:");
        shipCompany = new ComboBox<>();
        shipCompany.setEditable(false);
        //String[] ids = DataAccess.returnColumn("D:\\shipping.csv", 0, 5);
        String[] ids = DataAccess.returnColumn("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\shipping.csv", 0, 5);
        shipCompany.getItems().addAll(ids);

        Label paidLabel = new Label("Paid?");
        yes = new RadioButton("Yes");
        no = new RadioButton("No");
        ToggleGroup paid = new ToggleGroup();
        yes.setToggleGroup(paid);
        no.setToggleGroup(paid);
        HBox toggleButtons = new HBox(20);
        toggleButtons.getChildren().addAll(yes, no);

        //TextField paidOrNo =  new TextField();
        //paidOrNo.setPromptText("Enter yes or no");
        Label buttonNotPressed = new Label("Please check whether item is paid or not.");
        buttonNotPressed.setVisible(false);


        Button submit_order = new Button("Submit");
        submit_order.setOnAction(e -> {
            if(paid.getSelectedToggle() != null)
            {
                System.out.println("YES");
                String units_input, shipping_ID;
                order_number = Order.generateOrderNumber();
                units_input = units.getText();
                shipping_ID = shipCompany.getValue();
                //System.out.println("error here");
                String paidStatus = paid.getSelectedToggle().toString();
                String paidStatus2 = paidStatus.substring(paidStatus.indexOf("\'"), paidStatus.lastIndexOf("\'"));
                //System.out.println(paidStatus2);
                //String paidStatus = paidOrNo.getText();
                Order.newOrder(order_number, units_input, shipping_ID, paidStatus2);
                newOrder_label.setText("Order created. Your order number is " + order_number);

                units.clear();
                shipCompany.setValue(null);
            }
            else
            {
                System.out.println("NO");
                buttonNotPressed.setVisible(true);
            }

        });

        VBox order_steps = new VBox(10);
        order_steps.setPrefWidth(360);
        order_steps.getChildren().addAll(order_step1, units, order_step2, shipCompany, paidLabel, toggleButtons, submit_order, buttonNotPressed, newOrder_label);
        newOrder_layout.getChildren().addAll(goBack_button1, order_steps);
        newOrder_layout.setPadding(new Insets(10, 0, 0, 10));
        newOrder = new Scene(newOrder_layout, 900, 600);

        /* -----------------------------------------------------------------------------
		 Order Page
		 ----------------------------------------------------------------------------- */
        Label order_label1 = new Label("");
        Label order_label2 = new Label("");
        Button goBack_button6 = new Button("<< Go back");
        goBack_button6.setOnAction(e -> window.setScene(data));
        HBox order_layout = new HBox(30);
        /*
        // New Order
        String orderID1, units1, shippingID1;
        Label newOrder_title = new Label("Make a new order");
        TextField text_orderID1 = new TextField();
        text_orderID1.setPromptText("Order ID");
        orderID1 = text_orderID1.getText();
        TextField text_units1 = new TextField();
        text_units1.setPromptText("# of units wanted");
        units1 = text_units1.getText();
        TextField text_shippingID1 = new TextField();
        text_shippingID1.setPromptText("Shipping company ID");
        shippingID1 = text_shippingID1.getText();
        Button submit_order = new Button("Submit");
        submit_order.setOnAction(e -> {
            //Order.newOrder(orderID1, units1, shippingID1);
        	order_label1.setText("New order has been created");
            System.out.println("New order has been created");
            text_orderID1.clear();
            text_units1.clear();
            text_shippingID1.clear();
        });
        VBox newOrder_texts = new VBox(10);
        newOrder_texts.setPrefWidth(200);
        newOrder_texts.getChildren().addAll(newOrder_title, text_orderID1, text_units1, text_shippingID1, submit_order, order_label1);*/

        // Update Order
        Label updateOrder_title = new Label("Update order info");

        TextField text_orderID2 = new TextField();
        text_orderID2.setPromptText("Enter existing order ID");

        TextField text_units2 = new TextField();
        text_units2.setPromptText("# of units wanted");

        TextField text_shippingID2 = new TextField();
        text_shippingID2.setPromptText("Shipping company ID");

        Label paidLabel1 = new Label("Paid?");
        yes1 = new RadioButton("Yes");
        no1 = new RadioButton("No");
        ToggleGroup paid1 = new ToggleGroup();
        yes1.setToggleGroup(paid1);
        no1.setToggleGroup(paid1);
        HBox toggleButtons1 = new HBox(20);
        toggleButtons1.getChildren().addAll(yes1, no1);

        Label buttonNotPressed1 = new Label("Please check whether item is paid.");
        buttonNotPressed1.setVisible(false);

        //TextField text_paid2 = new TextField();
        //text_paid2.setPromptText("Paid or not (Enter yes or no)");

        Button update_order = new Button("Update");
        update_order.setOnAction(e -> {
            if(paid1.getSelectedToggle() != null)
            {
                String orderID2, units2, shippingID2, paid2;
                orderID2 = text_orderID2.getText();
                units2 = text_units2.getText();
                shippingID2 = text_shippingID2.getText();
                paid2 = paid1.getSelectedToggle().toString();
                String paid3 = paid2.substring(paid2.indexOf("\'"), paid2.lastIndexOf("\'"));

                Order.updateOrder(orderID2, units2, shippingID2, paid3);
                order_label1.setText("Order data has been updated");
                System.out.println("Order data has been updated");

                text_orderID2.clear();
                text_units2.clear();
                text_shippingID2.clear();
                //text_paid2.clear();
            }
            else
            {
                buttonNotPressed1.setVisible(true);
            }

        });

        VBox updateOrder_texts = new VBox(10);
        updateOrder_texts.setPrefWidth(200);
        updateOrder_texts.getChildren().addAll(updateOrder_title, text_orderID2, text_units2, text_shippingID2, paidLabel1, toggleButtons1, update_order,buttonNotPressed1, order_label2);


        // Delete Order
        Label deleteOrder_title = new Label("Choose order number to delete");
        ComboBox<String> orderId = new ComboBox<>();
        orderId.setEditable(false);
        //String[] order_ids = DataAccess.returnColumn("D:\\order.csv", 0, 9);
        String[] order_ids = DataAccess.returnColumn("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\order.csv", 0, 9);
        orderId.getItems().addAll(order_ids);

        Button deleteOrder_button = new Button("Delete");

        deleteOrder_button.setOnAction(e -> {
            String order_ID;
            order_ID = orderId.getValue();
            Order.deleteOrder(order_ID);
            order_label2.setText("Your order has been deleted");
        });

        VBox deleteOrder_texts = new VBox(10);
        deleteOrder_texts.setPrefWidth(200);
        deleteOrder_texts.getChildren().addAll(deleteOrder_title, orderId, deleteOrder_button, order_label2);

        order_layout.getChildren().addAll(goBack_button6, updateOrder_texts, deleteOrder_texts);
        order_layout.setPadding(new Insets(10, 0, 0, 10));
        order = new Scene(order_layout, 900, 600);

        // Check Order
        /*
        String orderID3;
        Label checkOrder_title = new Label("Check Order");
        TextField text_orderID3 = new TextField();
        text_orderID3.setPromptText("Enter existing order ID");
        orderID3 = text_orderID3.getText();
        TextField text_orderColumn = new TextField();
        text_orderColumn.setPromptText("Enter column #");
        try {
            order_column = Integer.parseInt(text_orderColumn.getText());
        } catch (NumberFormatException ex) { }
        Label order_label3 = new Label("");
        Button check_order = new Button("Check");
        check_order.setOnAction(e -> {
        	String search_order = Order.checkOrder(orderID3, order_column);
        	order_label3.setText(search_order);
        	text_orderID3.clear();
        	text_orderColumn.clear();
        });
        VBox checkOrder_texts = new VBox(10);
        checkOrder_texts.setPrefWidth(200);
        checkOrder_texts.getChildren().addAll(checkOrder_title, text_orderID3, text_orderColumn, check_order, order_label3);
        */

		/* -----------------------------------------------------------------------------
		 Show Order History Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button5 = new Button("<< Go back");
        goBack_button5.setOnAction(e -> window.setScene(main));
        goBack_button5.setPrefWidth(150);
        goBack_button5.setPrefHeight(30);
        goBack_button5.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        goBack_button5.setOnMouseEntered(e -> {
            goBack_button5.setEffect(shadow);
            goBack_button5.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        goBack_button5.setOnMouseExited(e -> {
            goBack_button5.setEffect(null);
            goBack_button5.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        Label history_Label = new Label("Order History:");
        history_Label.setAlignment(Pos.TOP_CENTER);

        history_Label.setFont(new Font("Calibre", 45));
        Group root = new Group();
        TableColumn columnF1 = new TableColumn("Order ID");
        columnF1.setMinWidth(75);
        columnF1.setCellValueFactory(
                new PropertyValueFactory<>("f1"));
        TableColumn columnF2 = new TableColumn("Units");
        columnF2.setMinWidth(50);
        columnF2.setCellValueFactory(
                new PropertyValueFactory<>("f2"));
        TableColumn columnF3 = new TableColumn("Shipping Company ID");
        columnF3.setMinWidth(175);
        columnF3.setCellValueFactory(
                new PropertyValueFactory<>("f3"));
        TableColumn columnF4 = new TableColumn("Shipping Cost");
        columnF4.setMinWidth(100);
        columnF4.setCellValueFactory(
                new PropertyValueFactory<>("f4"));
        TableColumn columnF5 = new TableColumn("Material Cost");
        columnF5.setMinWidth(100);
        columnF5.setCellValueFactory(
                new PropertyValueFactory<>("f5"));
        TableColumn columnF6 = new TableColumn("Overhead Cost");
        columnF6.setMinWidth(100);
        columnF6.setCellValueFactory(
                new PropertyValueFactory<>("f7"));
        TableColumn columnF7 = new TableColumn("Labor Cost");
        columnF7.setMinWidth(100);
        columnF7.setCellValueFactory(
                new PropertyValueFactory<>("f7"));
        TableColumn columnF8 = new TableColumn("Price");
        columnF8.setMinWidth(80);
        columnF8.setCellValueFactory(
                new PropertyValueFactory<>("f8"));
        TableColumn columnF9 = new TableColumn("Paid?");
        columnF9.setMinWidth(75);
        columnF9.setCellValueFactory(
                new PropertyValueFactory<>("f9"));
        tableView.setItems(dataList);
        tableView.getColumns().addAll(
                columnF1, columnF2, columnF3, columnF4, columnF5, columnF6, columnF7, columnF8, columnF9);
        VBox test = new VBox(10);
        test.setSpacing(10);
        test.getChildren().addAll(goBack_button5, history_Label, tableView);
        test.setPadding(new Insets(10, 0, 0, 20));
        root.getChildren().add(test);
        readCSV();
        show = new Scene(root, 900, 600);


        /* -----------------------------------------------------------------------------
		 Find Order Page
		 ----------------------------------------------------------------------------- */
        Button goBack_button7 = new Button("<< Go back");
        goBack_button7.setOnAction(e -> window.setScene(main));
        goBack_button7.setPrefWidth(150);
        goBack_button7.setPrefHeight(30);
        goBack_button7.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        goBack_button7.setOnMouseEntered(e -> {
            goBack_button7.setEffect(shadow);
            goBack_button7.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        goBack_button7.setOnMouseExited(e -> {
            goBack_button7.setEffect(null);
            goBack_button7.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        VBox search_layout = new VBox(20);
        VBox search_layout1 = new VBox(20);
        BorderPane borderPane = new BorderPane();
        findOrder = new ComboBox<>();
        findOrder.setEditable(true);
        findOrder.setPrefSize(500, 25);
        Label searchLabel = new Label("Search for Orders");
        Label nullLabel = new Label("Please enter an order ID.");
        nullLabel.setVisible(false);
        String[] findIDArray = DataAccess.returnColumn("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\order.csv", 0, 9);
        findOrder.getItems().addAll(findIDArray);

        VBox returnedSearch = new VBox(20);
        Label search1 = new Label("");
        Label search2 = new Label("");
        Label search3 = new Label("");
        Label search4 = new Label("");
        Label search5 = new Label("");
        Label search6 = new Label("");
        Label search7 = new Label("");
        Label search8 = new Label("");
        Label errorLabel = new Label("Order ID not found. Please try another ID");

        search1.setVisible(false);
        search2.setVisible(false);
        search3.setVisible(false);
        search4.setVisible(false);
        search5.setVisible(false);
        search6.setVisible(false);
        search7.setVisible(false);
        search8.setVisible(false);
        errorLabel.setVisible(false);
        returnedSearch.getChildren().addAll(search1,search2,search3,search4,search5,search6,search7,search8, errorLabel);

        Button orderSearchButton = new Button("Search");
        orderSearchButton.setOnAction(e -> {

            if(findOrder.getValue() != null)
            {
                String searchID;
                int searchField, searchColumn;
                searchID = findOrder.getValue();
                searchField = 9;
                String[] searchReturn = new String[8];

                for(searchColumn = 1; searchColumn < searchField; searchColumn++)
                {
                    searchReturn[searchColumn-1] = Order.checkOrder(searchID, searchColumn);
                    if(searchReturn[searchColumn-1].equals("Not Found") || searchReturn[searchColumn-1].equals("Error"))
                    {
                        errorLabel.setVisible(true);
                        search1.setVisible(false);
                        search2.setVisible(false);
                        search3.setVisible(false);
                        search4.setVisible(false);
                        search5.setVisible(false);
                        search6.setVisible(false);
                        search7.setVisible(false);
                        search8.setVisible(false);
                    }
                    else
                    {
                        errorLabel.setVisible(false);
                    }
                }

                //String searchReturn = DataAccess.query(orderFilePath,searchID,searchColumn, searchField);
                searchColumn = 1;
                System.out.println("\n" + searchReturn[searchColumn]);
                if(!errorLabel.isVisible())
                {
                    search1.setVisible(true);
                    search2.setVisible(true);
                    search3.setVisible(true);
                    search4.setVisible(true);
                    search5.setVisible(true);
                    search6.setVisible(true);
                    search7.setVisible(true);
                    search8.setVisible(true);
                    errorLabel.setVisible(false);

                    search1.setText("Units: " + searchReturn[0]);
                    search2.setText("Shipping Company ID: " + searchReturn[1]);
                    search3.setText("Shipping Costs: " + searchReturn[2]);
                    search4.setText("Material Costs: " + searchReturn[3]);
                    search5.setText("Overhead Costs: " + searchReturn[4]);
                    search6.setText("Labor Cost: " + searchReturn[5]);
                    search7.setText("Price: " + searchReturn[6]);
                    search8.setText("Paid (Yes or No): " + searchReturn[7]);

                }

            }
            else
            {
                nullLabel.setVisible(true);
            }

        });

        search_layout.getChildren().addAll(goBack_button7);
        search_layout.setPadding(new Insets(10, 0, 0, 20));
        search_layout1.getChildren().addAll(searchLabel, findOrder, orderSearchButton, nullLabel);
        search_layout1.setAlignment(Pos.CENTER);
        borderPane.setCenter(returnedSearch);
        returnedSearch.setAlignment(Pos.CENTER);
        borderPane.setTop(search_layout);
        borderPane.setBottom(search_layout1);

        search = new Scene(borderPane, 900, 600);

		/* -----------------------------------------------------------------------------
		 Overhead Page
		 ----------------------------------------------------------------------------- */
        Label overhead_label1 = new Label("");
        Label overhead_label2 = new Label("");
        Label overhead_label4 = new Label("");
        Button goBack_button2 = new Button("<< Go back");
        goBack_button2.setOnAction(e -> {
            window.setScene(data);
            overhead_label1.setText("");
            overhead_label2.setText("");
        });
        goBack_button2.setPrefWidth(150);
        goBack_button2.setPrefHeight(30);
        goBack_button2.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        goBack_button2.setOnMouseEntered(e -> {
            goBack_button2.setEffect(shadow);
            goBack_button2.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        goBack_button2.setOnMouseExited(e -> {
            goBack_button2.setEffect(null);
            goBack_button2.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        HBox overhead_layout = new HBox(20);
        // New Overhead
        Label newOverhead_title = new Label("Add Manufacturer to the Database");
        TextField text_manufacturer1 = new TextField();
        text_manufacturer1.setPromptText("Manufacturer");
        text_manufacturer1.setPrefColumnCount(10);
        TextField text_rent1 = new TextField();
        text_rent1.setPromptText("Rent");
        TextField text_utilities1 = new TextField();
        text_utilities1.setPromptText("Utilities");
        TextField text_insurance1 = new TextField();
        text_insurance1.setPromptText("Insurance");
        TextField text_machinery1 = new TextField();
        text_machinery1.setPromptText("Machinery");

        Button submit_manufacturer = new Button("Submit");
        submit_manufacturer.setOnAction(e -> {
            String manufacturer1, rent1, utilities1, insurance1, machinery1;
            manufacturer1 = text_manufacturer1.getText();
            rent1 = text_rent1.getText();
            utilities1 = text_utilities1.getText();
            insurance1 = text_insurance1.getText();
            machinery1 = text_machinery1.getText();
            Overhead.newManufacturer(manufacturer1, rent1, utilities1, insurance1, machinery1);
            overhead_label1.setText("Manufacturer data has been added");
            System.out.println("Manufacturer data has been added");
            text_manufacturer1.clear();
            text_rent1.clear();
            text_utilities1.clear();
            text_insurance1.clear();
            text_machinery1.clear();
        });
        VBox newManuf_texts = new VBox(10);
        newManuf_texts.setPrefWidth(200);
        newManuf_texts.getChildren().addAll(newOverhead_title, text_manufacturer1, text_rent1, text_utilities1, text_insurance1, text_machinery1, submit_manufacturer, overhead_label1);

        // Update Manufacturer
        Label updateOverhead_title = new Label("Update Existing Manufacturer");
        TextField text_manufacturer2 = new TextField();
        text_manufacturer2.setPromptText("Manufacturer");
        text_manufacturer2.setPrefColumnCount(10);
        TextField text_rent2 = new TextField();
        text_rent2.setPromptText("Rent");
        TextField text_utilities2 = new TextField();
        text_utilities2.setPromptText("Utilities");
        TextField text_insurance2 = new TextField();
        text_insurance2.setPromptText("Insurance");
        TextField text_machinery2 = new TextField();
        text_machinery2.setPromptText("Machinery");
        Button update_manufacturer = new Button("Submit");
        update_manufacturer.setOnAction(e -> {
            String manufacturer2, rent2, utilities2, insurance2, machinery2;
            manufacturer2 = text_manufacturer2.getText();
            rent2 = text_rent2.getText();
            utilities2 = text_utilities2.getText();
            insurance2 = text_insurance2.getText();
            machinery2 = text_machinery2.getText();
            Overhead.updateManufacturer(manufacturer2, rent2, utilities2, insurance2, machinery2);
            overhead_label2.setText("Manufacturer data has been updated");
            System.out.println("Manufacturer data has been updated");
            text_manufacturer2.clear();
            text_rent2.clear();
            text_utilities2.clear();
            text_insurance2.clear();
            text_machinery2.clear();
        });
        VBox updateManuf_texts = new VBox(10);
        updateManuf_texts.setPrefWidth(200);
        updateManuf_texts.getChildren().addAll(updateOverhead_title, text_manufacturer2, text_rent2, text_utilities2, text_insurance2, text_machinery2, update_manufacturer, overhead_label2);

        // Delete Manufacturer
        Label deleteManuf_title = new Label("Choose manufacturer ID to delete");
        ComboBox<String> manufDropdown = new ComboBox<>();
        manufDropdown.setEditable(false);
        //String[] manuf_ids = DataAccess.returnColumn("manufacturer.csv", 0, 5);
        String[] manuf_ids = DataAccess.returnColumn("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\manufacturer.csv", 0, 5);
        manufDropdown.getItems().addAll(manuf_ids);

        Button deleteManuf_button = new Button("Delete");

        deleteManuf_button.setOnAction(e -> {
            String manuf_ID;
            manuf_ID = manufDropdown.getValue();
            Overhead.deleteManufacturer(manuf_ID);
            overhead_label4.setText("Manufacturer data has been deleted");
        });


        VBox deleteManuf_texts = new VBox(10);
        deleteManuf_texts.setPrefWidth(200);
        deleteManuf_texts.getChildren().addAll(deleteManuf_title, manufDropdown, deleteManuf_button, overhead_label4);

        // Check Manufacturer (no need)
        String manufacturer3;
        Label checkManuf_title = new Label("Check Manufacturer");
        TextField text_manufacturer3 = new TextField();
        text_manufacturer3.setPromptText("Enter existing manufacturer ID");
        manufacturer3 = text_manufacturer3.getText();
        TextField text_manufColumn = new TextField();
        text_manufColumn.setPromptText("Enter column #");
        try {
            manuf_column = Integer.parseInt(text_manufColumn.getText());
        } catch (NumberFormatException ex) { }
        Label overhead_label3 = new Label("");
        Button check_manufacturer = new Button("Check");
        check_manufacturer.setOnAction(e -> {
            String search_overhead = Overhead.check(manufacturer3, manuf_column);
            overhead_label3.setText(search_overhead);
            text_manufacturer3.clear();
            text_manufColumn.clear();
        });
        VBox checkManuf_texts = new VBox(10);
        //checkManuf_texts.setPrefWidth(200);
        checkManuf_texts.getChildren().addAll(checkManuf_title, text_manufacturer3, text_manufColumn, check_manufacturer, overhead_label3);
        overhead_layout.getChildren().addAll(goBack_button2, newManuf_texts, updateManuf_texts, deleteManuf_texts);
        overhead_layout.setPadding(new Insets(10, 0, 0, 10));
        overhead = new Scene(overhead_layout, 900, 600);

        /* -----------------------------------------------------------------------------
		 Employee Page
		 ----------------------------------------------------------------------------- */
        Label employee_label1 = new Label("");
        Label employee_label2 = new Label("");
        Label employee_label4 = new Label("");
        Button goBack_button3 = new Button("<< Go back");
        goBack_button3.setOnAction(e -> {
            window.setScene(data);
            employee_label1.setText("");
            employee_label2.setText("");
        });
        goBack_button3.setPrefWidth(150);
        goBack_button3.setPrefHeight(30);
        goBack_button3.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        goBack_button3.setOnMouseEntered(e -> {
            goBack_button3.setEffect(shadow);
            goBack_button3.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        goBack_button3.setOnMouseExited(e -> {
            goBack_button3.setEffect(null);
            goBack_button3.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        HBox employee_layout = new HBox(20);
        // New Employee
        Label newEmpl_title = new Label("Add Employee to the Database");
        TextField text_ID1 = new TextField();
        text_ID1.setPromptText("Employee ID");
        text_ID1.setPrefColumnCount(10);
        TextField text_fname1 = new TextField();
        text_fname1.setPromptText("First name");
        TextField text_lname1 = new TextField();
        text_lname1.setPromptText("Last name");
        TextField text_pay1 = new TextField();
        text_pay1.setPromptText("Paycheck");
        TextField text_position1 = new TextField();
        text_position1.setPromptText("Position");

        Button submit_employee = new Button("Submit");
        submit_employee.setOnAction(e -> {
            String id1, fname1, lname1, pay1, position1;
            id1 = text_ID1.getText();
            fname1 = text_fname1.getText();
            lname1 = text_lname1.getText();
            pay1 = text_pay1.getText();
            position1 = text_position1.getText();
            Employees.newEmployee(id1, fname1, lname1, pay1, position1);
            employee_label1.setText("Employee data has been added");
            System.out.println("Employee data has been added");
            text_ID1.clear();
            text_fname1.clear();
            text_lname1.clear();
            text_pay1.clear();
            text_position1.clear();
        });
        VBox text_fields1 = new VBox(10);
        text_fields1.setPrefWidth(200);
        text_fields1.getChildren().addAll(newEmpl_title, text_ID1, text_fname1, text_lname1, text_pay1, text_position1, submit_employee, employee_label1);

        // Update Employee
        Label updateEmpl_title = new Label("Update Employee Info");
        TextField text_ID2 = new TextField();
        text_ID2.setPromptText("Enter existing employee ID");
        text_ID2.setPrefColumnCount(10);
        TextField text_fname2 = new TextField();
        text_fname2.setPromptText("First name");
        TextField text_lname2 = new TextField();
        text_lname2.setPromptText("Last name");
        TextField text_pay2 = new TextField();
        text_pay2.setPromptText("Paycheck");
        TextField text_position2 = new TextField();
        text_position2.setPromptText("Position");
        Button update_employee = new Button("Update");
        update_employee.setOnAction(e -> {
            String id2, fname2, lname2, pay2, position2;
            id2 = text_ID2.getText();
            fname2 = text_fname2.getText();
            lname2 = text_lname2.getText();
            pay2 = text_pay2.getText();
            position2 = text_position2.getText();
            Employees.updateEmployee(id2, fname2, lname2, pay2, position2);
            employee_label2.setText("Employee info has been updated");
            System.out.println("Employee info has been updated");
            text_ID2.clear();
            text_fname2.clear();
            text_lname2.clear();
            text_pay2.clear();
            text_position2.clear();
        });
        VBox text_fields2 = new VBox(10);
        text_fields2.setPrefWidth(200);
        text_fields2.getChildren().addAll(updateEmpl_title, text_ID2, text_fname2, text_lname2, text_pay2, text_position2, update_employee, employee_label2);

        // Delete Employee
        Label deleteEmployee_title = new Label("Choose employee ID to delete");
        ComboBox<String> employeeDropdown = new ComboBox<>();
        employeeDropdown.setEditable(false);
        //String[] employee_ids = DataAccess.returnColumn("employees.csv", 0, 5);
        String[] employee_ids = DataAccess.returnColumn("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\employees.csv", 0, 5);
        employeeDropdown.getItems().addAll(employee_ids);

        Button deleteEmployee_button = new Button("Delete");

        deleteEmployee_button.setOnAction(e -> {
            String empl_ID;
            empl_ID = employeeDropdown.getValue();
            System.out.println("employeeDropdown.getValue();");
            Employees.deleteEmployee(empl_ID);
            employee_label4.setText("Employee data has been deleted");
        });


        VBox deleteEmployee_texts = new VBox(10);
        deleteEmployee_texts.setPrefWidth(200);
        deleteEmployee_texts.getChildren().addAll(deleteEmployee_title, employeeDropdown, deleteEmployee_button, employee_label4);


        // check Employee (no need)
        String id3;
        Label checkEmpl_title = new Label("Check Employee");
        TextField text_ID3 = new TextField();
        text_ID3.setPromptText("Enter existing employee ID");
        id3 = text_ID3.getText();
        TextField text_column = new TextField();
        text_column.setPromptText("Enter column #");
        try {
            employee_column = Integer.parseInt(text_column.getText());
        } catch (NumberFormatException ex) { }
        Label employee_label3 = new Label("");
        Button check_employee = new Button("Check");
        check_employee.setOnAction(e -> {
            String search_employee = Employees.check(id3, employee_column);
            employee_label3.setText(search_employee);
        });
        VBox text_fields3 = new VBox(10);
        text_fields3.setPrefWidth(200);
        text_fields3.getChildren().addAll(checkEmpl_title, text_ID3, text_column, check_employee, employee_label3);
        employee_layout.getChildren().addAll(goBack_button3, text_fields1, text_fields2, deleteEmployee_texts);
        employee_layout.setPadding(new Insets(10, 0, 0, 10));
        employee = new Scene(employee_layout, 900, 600);

        /* -----------------------------------------------------------------------------
		 Shipping Page
		 ----------------------------------------------------------------------------- */
        Label shipping_label1 = new Label("");
        Label shipping_label2 = new Label("");
        Label shipping_label4 = new Label("");
        Button goBack_button4 = new Button("<< Go back");
        goBack_button4.setOnAction(e -> {
            window.setScene(data);
            shipping_label1.setText("");
            shipping_label2.setText("");
        });
        goBack_button4.setPrefWidth(150);
        goBack_button4.setPrefHeight(30);
        goBack_button4.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        goBack_button4.setOnMouseEntered(e -> {
            goBack_button4.setEffect(shadow);
            goBack_button4.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });
        goBack_button4.setOnMouseExited(e -> {
            goBack_button4.setEffect(null);
            goBack_button4.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        });

        // New Shipping
        Label shipping_title = new Label("New Shipping Information");
        TextField text_ShipID = new TextField();
        text_ShipID.setPromptText("Shipping ID");
        text_ShipID.setPrefColumnCount(10);
        TextField text_shipName = new TextField();
        text_shipName.setPromptText("Full Name");
        TextField text_shipAddy = new TextField();
        text_shipAddy.setPromptText("Address");
        TextField text_shipPhone = new TextField();
        text_shipPhone.setPromptText("Phone Number");
        TextField text_shipCost = new TextField();
        text_shipCost.setPromptText("Cost");
        Button complete_shipDetails = new Button("Complete");
        complete_shipDetails.setOnAction(e -> {
            String shipID, shipName, shipAddress, shipPhone, shipCost;
            shipID = text_ShipID.getText();
            shipName = text_shipName.getText();
            shipAddress = text_shipAddy.getText();
            shipPhone = text_shipPhone.getText();
            shipCost = text_shipCost.getText();
            Shipping.newShipping(shipID, shipName, shipAddress, shipPhone, shipCost);
            shipping_label1.setText("Shipping data has been added");
            System.out.println("Shipping data has been added");
            text_ShipID.clear();
            text_shipName.clear();
            text_shipAddy.clear();
            text_shipPhone.clear();
            text_shipCost.clear();
        });
        VBox vBoxShip = new VBox(10);
        vBoxShip.setPrefWidth(200);
        vBoxShip.getChildren().addAll(shipping_title, text_ShipID, text_shipName, text_shipAddy, text_shipPhone, text_shipCost, complete_shipDetails, shipping_label1);
        // Update Shipping
        Label shipping_title1 = new Label("Update Shipping Information");
        TextField text_ShipID1 = new TextField();
        text_ShipID1.setPromptText("Shipping ID");
        text_ShipID1.setPrefColumnCount(10);
        TextField text_shipName1 = new TextField();
        text_shipName1.setPromptText("Full Name");
        TextField text_shipAddy1 = new TextField();
        text_shipAddy1.setPromptText("Address");
        TextField text_shipPhone1 = new TextField();
        text_shipPhone1.setPromptText("Phone Number");
        TextField text_shipCost1 = new TextField();
        text_shipCost1.setPromptText("Cost");
        Button update_shipDetails1 = new Button("Update");
        update_shipDetails1.setOnAction(e -> {
            String shipID1, shipName1, shipAddress1, shipPhone1, shipCost1;
            shipID1 = text_ShipID1.getText();
            shipName1 = text_shipName1.getText();
            shipAddress1 = text_shipAddy1.getText();
            shipPhone1 = text_shipPhone1.getText();
            shipCost1 = text_shipCost1.getText();
            Shipping.updateShipping(shipID1, shipName1, shipAddress1, shipPhone1, shipCost1);
            shipping_label2.setText("Shipping data has been updated");
            System.out.println("Shipping data has been updated");
            text_ShipID1.clear();
            text_shipName1.clear();
            text_shipAddy1.clear();
            text_shipPhone1.clear();
            text_shipCost1.clear();
        });
        VBox vBoxShip1 = new VBox(10);
        vBoxShip1.setPrefWidth(200);
        vBoxShip1.getChildren().addAll(shipping_title1, text_ShipID1, text_shipName1, text_shipAddy1, text_shipPhone1, text_shipCost1, update_shipDetails1, shipping_label2);


        // Delete Shipping
        Label deleteShipping_title = new Label("Choose shipping company ID to delete");
        ComboBox<String> shippingDropdown= new ComboBox<>();
        shippingDropdown.setEditable(false);
        //String[] shipping_ids = DataAccess.returnColumn("shipping.csv", 0, 5);
        String[] shipping_ids = DataAccess.returnColumn("C:\\Users\\Administrator\\IdeaProjects\\Shamwow_Payment_Billing_System\\src\\application\\shipping.csv", 0, 5);
        shippingDropdown.getItems().addAll(shipping_ids);

        Button deleteShipping_button = new Button("Delete");

        deleteShipping_button.setOnAction(e -> {
            String shipping_ID;
            shipping_ID = shippingDropdown.getValue();
            Shipping.deleteShipping(shipping_ID);
            shipping_label4.setText("Shipping data has been deleted");
        });


        VBox deleteShipping_texts = new VBox(10);
        deleteShipping_texts.setPrefWidth(200);
        deleteShipping_texts.getChildren().addAll(deleteShipping_title, shippingDropdown, deleteShipping_button, shipping_label4);

        // Check Shipping (no need)
        String shipID3;
        Label checkShip_title = new Label("Check Shipping Information");
        TextField text_shipID3 = new TextField();
        text_shipID3.setPromptText("Enter existing Shipping ID");
        shipID3 = text_shipID3.getText();
        TextField text_shipColumn = new TextField();
        text_shipColumn.setPromptText("Enter column #");
        try {
            ship_column = Integer.parseInt(text_shipColumn.getText());
        } catch (NumberFormatException ex) { }
        Label shipping_label3 = new Label("");
        Button check_shipping = new Button("Check");
        check_shipping.setOnAction(e -> {
            String search_shipping = Shipping.check(shipID3, ship_column);
            shipping_label3.setText(search_shipping);
            text_shipID3.clear();
            text_shipColumn.clear();
        });
        VBox vboxShipCheck = new VBox(10);
        vboxShipCheck.setPrefWidth(200);
        vboxShipCheck.getChildren().addAll(checkShip_title, text_shipID3, text_shipColumn, check_shipping, shipping_label3);
        HBox hboxShipping = new HBox(10);
        hboxShipping.getChildren().addAll(goBack_button4, vBoxShip, vBoxShip1, deleteShipping_texts);
        hboxShipping.setPadding(new Insets(10, 0, 0, 10));
        shipping = new Scene(hboxShipping, 900, 600);
        window.setScene(main);
        window.show();
    }
}