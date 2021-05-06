package application;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.effect.DropShadow;
public class CostSummaryBox {

	public static void display(String title, int shipping_costs, int employee_costs, int overhead_costs, int total_cost) {
		Stage window = new Stage();
		DropShadow shadow = new DropShadow();
		Scene container;
		GridPane box_layout = new GridPane();
		VBox list = new VBox(10);


		Label Total_Costs = new Label("Total Cost: $" + total_cost);
		Total_Costs.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		Label Employee_Costs = new Label("Employee Costs: $" + employee_costs);
		Label Overhead_Costs = new Label("Overhead Costs: $" + overhead_costs);
		Label Shipping_Costs = new Label("Shipping Costs: $" + shipping_costs);
		Button exit_button = new Button("Exit");
		exit_button.setOnAction(e ->  window.close());
		exit_button.setPrefWidth(120);
		exit_button.setPrefHeight(36);
		exit_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
		exit_button.setOnMouseEntered(e -> {
			exit_button.setEffect(shadow);
			exit_button.setStyle("-fx-background-color: #f2980d; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
		});
		exit_button.setOnMouseExited(e -> {
			exit_button.setEffect(null);
			exit_button.setStyle("-fx-background-color: #156cb8; -fx-border-width: 0; -fx-text-fill: #FFFFFF; -fx-border-radius: 20px; -fx-background-radius: 20px;");
		});
		list.getChildren().addAll(Total_Costs, Shipping_Costs, Employee_Costs, Overhead_Costs);

		VBox whole = new VBox(50);
		whole.getChildren().addAll(list, exit_button);

		box_layout.getChildren().add(whole);
		box_layout.setAlignment(Pos.CENTER);

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		container = new Scene(box_layout, 500, 300);

		window.setScene(container);
		window.show();
	}
}