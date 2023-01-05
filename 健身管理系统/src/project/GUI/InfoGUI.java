package project.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InfoGUI extends Application{
	public static String NAME = Login.NAME;
	static String PWD = Login.PASSWORD;
	static String SEX = null;
	static String AGE = null;
	static String HEIGHT = null;
	static String WEIGHT = null;
	TextField name;
	TextField pwd;
	TextField sex;
	TextField age;
	TextField height;
	TextField weight;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(10);
		Label label = new Label("修改个人信息");
		label.setTextFill(Color.CADETBLUE);
		label.setFont(Font.font("宋体", FontWeight.NORMAL, FontPosture.ITALIC, 20));
		
		HBox area1 = new HBox(20);
		Text text1 = new Text(30,50,"姓名");
		name = new TextField();
		name.setText(NAME);
		area1.getChildren().add(text1);
		area1.getChildren().add(name);
		area1.setAlignment(Pos.CENTER);
		
		HBox area2 = new HBox(20);
		Text text2 = new Text(30,50,"密码");
		pwd = new TextField();
		pwd.setText(PWD);
		area2.getChildren().add(text2);
		area2.getChildren().add(pwd);
		area2.setAlignment(Pos.CENTER);
		
		HBox area3 = new HBox(20);
		Text text3 = new Text(30,50,"性别");
		sex = new TextField();
		sex.setText(SEX);
		area3.getChildren().add(text3);
		area3.getChildren().add(sex);
		area3.setAlignment(Pos.CENTER);
		
		HBox area4 = new HBox(20);
		Text text4 = new Text(30,50,"年龄");
		age = new TextField();
		age.setText(AGE);
		area4.getChildren().add(text4);
		area4.getChildren().add(age);
		area4.setAlignment(Pos.CENTER);
		
		HBox area5 = new HBox(20);
		Text text5 = new Text(30,50,"身高");
		height = new TextField();
		height.setText(HEIGHT);
		area5.getChildren().add(text5);
		area5.getChildren().add(height);
		area5.setAlignment(Pos.CENTER);
		
		HBox area6 = new HBox(20);
		Text text6 = new Text(30,50,"体重");
		weight = new TextField();
		weight.setText(WEIGHT);
		area6.getChildren().add(text6);
		area6.getChildren().add(weight);
		area6.setAlignment(Pos.CENTER);
		
		
		HBox pane1 = new HBox();
		pane1.setSpacing(15);
		Button edit = new Button("保存修改");
		Button back = new Button("返回");
		Image imageBack = new Image(getClass().getResourceAsStream("fanhui.png"));
		back.setGraphic(new ImageView(imageBack));
		pane1.getChildren().add(edit);
		pane1.getChildren().add(back);
		pane1.setAlignment(Pos.CENTER);
		
		Text note = new Text("");
		
		pane.getChildren().add(label);
		pane.getChildren().add(area1);
		pane.getChildren().add(area2);
		pane.getChildren().add(area3);
		pane.getChildren().add(area4);
		pane.getChildren().add(area5);
		pane.getChildren().add(area6);
		pane.getChildren().add(pane1);
		pane.getChildren().add(note);
		Scene scene = new Scene(pane, 500, 400);
		primaryStage.setTitle("个人信息");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		edit.setOnAction(e->{
			String filePath = "/Users/m12j10/eclipse-workspace/JavaFinalProject/src/user.txt";
			ModifyInfo modifyFile = new ModifyInfo();
			String modifyStr = name.getText() + "," + pwd.getText() + "," + sex.getText() + "," + age.getText() + "," + height.getText() + "," + weight.getText();
			System.out.println(Login.sourceStr);
			System.out.println(modifyStr);
			if(Login.sourceStr == modifyStr) {
				note.setText("未修改信息");
			}
			else {
				modifyFile.writeFile(filePath, modifyFile.readFileContent(filePath, Login.sourceStr, modifyStr));
				Login.sourceStr = modifyStr;
				NAME = name.getText();
				PWD = pwd.getText();
				SEX = sex.getText();
				AGE = age.getText();
				HEIGHT = height.getText();
				WEIGHT = weight.getText();
				
				note.setText("修改成功！");
			}
		});
	
		back.setOnAction(e->{
			try {
				new MainGUI().start(primaryStage);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
