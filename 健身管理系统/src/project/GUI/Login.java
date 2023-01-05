package project.GUI;

import java.io.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

public class Login extends Application{
	TextField user;
	PasswordField pwd;
	Text text4;
	Stage primaryStage;
	static String NAME = null;
	static String PASSWORD = null;
	static String sourceStr;	// 信息的初始状态
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage = primaryStage;
		VBox pane = new VBox(20);
		pane.setAlignment(Pos.CENTER);
		HBox pane2 = new HBox(20);
		pane2.setAlignment(Pos.CENTER);
		HBox pane3 = new HBox(20);
		pane3.setAlignment(Pos.CENTER);
		
		Label text1 = new Label("健身管理系统");
		text1.setTextFill(Color.CADETBLUE);
		text1.setFont(Font.font("宋体", FontWeight.BOLD, FontPosture.ITALIC, 24));
		Button loginButton = new Button("登录");
		Text text2 = new Text(50, 50, "姓名");
		Text text3 = new Text(50, 50, "密码");
		text4 = new Text(100, 50, "");
		
		user = new TextField();
		user.setAlignment(Pos.BOTTOM_LEFT);
		pwd = new PasswordField();
		pwd.setAlignment(Pos.BOTTOM_LEFT);
		
		pane.getChildren().add(text1);
		pane2.getChildren().add(text2);
		pane2.getChildren().add(user);
		pane3.getChildren().add(text3);
		pane3.getChildren().add(pwd);
		
		pane.getChildren().add(pane2);
		pane.getChildren().add(pane3);
		pane.getChildren().add(loginButton);
		pane.getChildren().add(text4);
		
		Scene scene = new Scene(pane, 500, 400);
		pane.setBackground(new Background(new BackgroundFill(Color.AZURE, new CornerRadii(5.0), new Insets(-5.0))));
		primaryStage.setTitle("登录窗口");
		primaryStage.setScene(scene);
		primaryStage.show();
		loginButton.setOnAction(e->{
			boolean result = login(user.getText(),pwd.getText());
			if(result) {
				try {
					new MainGUI().start(primaryStage);
					NAME = user.getText();
					PASSWORD = pwd.getText();
					sourceStr = NAME + "," + PASSWORD;
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
			else
				text4.setText("登录失败");
		});
	}
	
	boolean login(String user,String pwd) {
		boolean result = false;
		File file = new File("/Users/m12j10/eclipse-workspace/JavaFinalProject/src/user.txt");
		Scanner input;
		try {
			input = new Scanner(file);
			while(input.hasNext()) {
				String line = input.nextLine();
				String[] fields = line.split(",");
				if(user.equals(fields[0]) && pwd.equals(fields[1])) {
					result = true;
					break;
				}
			}
			input.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
