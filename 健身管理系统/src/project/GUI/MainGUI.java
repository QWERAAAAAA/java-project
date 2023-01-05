package project.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import project.statistics.Main;

public class MainGUI extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox pane = new VBox(20);
		pane.setAlignment(Pos.CENTER);
		Label label = new Label("欢迎您使用健身管理系统");
		label.setTextFill(Color.CADETBLUE);
		label.setFont(Font.font("宋体", FontWeight.BOLD, FontPosture.ITALIC, 20));
		
		VBox utils = new VBox(20);
		utils.setSpacing(15);
		utils.setAlignment(Pos.CENTER);
		Button Info = new Button("修改个人信息");
		Button bmi = new Button("计算BMI指数");
		Button Records = new Button("健身记录功能");
		
		utils.getChildren().add(Info);
		utils.getChildren().add(bmi);
		utils.getChildren().add(Records);
		
		pane.getChildren().add(label);
		pane.getChildren().add(utils);
		Scene scene = new Scene(pane, 400, 300);
		primaryStage.setTitle("健康管理系统");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Info.setOnAction(e->{
			try {
				new InfoGUI().start(primaryStage);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		});
		
		bmi.setOnAction(e->{
			try {
				new bmiGUI().start(primaryStage);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		});
		
		Records.setOnAction(e->{
			try {
				new Main().start(primaryStage);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
