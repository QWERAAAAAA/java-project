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

public class bmiGUI extends Application{
	TextField height;
	TextField weight;
	Text text;
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(10);
		Label label = new Label("计算BMI指数");
		label.setTextFill(Color.CADETBLUE);
		label.setFont(Font.font("宋体", FontWeight.NORMAL, FontPosture.ITALIC, 20));
		HBox tap = new HBox();
		tap.setSpacing(20);
		tap.setAlignment(Pos.CENTER);
		Button bmi = new Button("计算BMI");
		Button range = new Button("查看BMI范围");
		text = new Text(100, 50, "");
		Button back = new Button("返回");
		Image imageBack = new Image(getClass().getResourceAsStream("fanhui.png"));
		back.setGraphic(new ImageView(imageBack));

		
		// 限制只能输入数字
		height = new TextField() {
		  @Override
		  public void replaceText(int start, int end, String text) {
		      if (!text.matches("[a-z]")) {
		        super.replaceText(start, end, text);
		      }
		   }
		   @Override
		   public void replaceSelection(String text) {
		      if (!text.matches("[a-z]")) {
		        super.replaceSelection(text);
		      }
		    }
		};
		height.setPromptText("请输入身高（单位cm）");
		
		weight = new TextField(){
			  @Override
			  public void replaceText(int start, int end, String text) {
			      if (!text.matches("[a-z]")) {
			        super.replaceText(start, end, text);
			      }
			   }
			   @Override
			   public void replaceSelection(String text) {
			      if (!text.matches("[a-z]")) {
			        super.replaceSelection(text);
			      }
			    }
		};
		weight.setPromptText("请输入体重（单位kg）");
		height.setMaxSize(150, 100);
		weight.setMaxSize(150, 100);
		
		
		
		pane.getChildren().add(label);
		pane.getChildren().add(height);
		pane.getChildren().add(weight);
		tap.getChildren().add(bmi);
		tap.getChildren().add(range);
		pane.getChildren().add(tap);
		pane.getChildren().add(text);
		pane.getChildren().add(back);
		
		Scene scene = new Scene(pane, 400, 300);
		primaryStage.setTitle("BMI指数");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		bmi.setOnAction(e->{
			try {
				bmiIndex(Double.parseDouble(height.getText()),Double.parseDouble(weight.getText()));
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		});
		
		range.setOnAction(e->{
			try {
				bmiRange(Double.parseDouble(height.getText()),Double.parseDouble(weight.getText()));
			}catch(Exception e1) {
				e1.printStackTrace();
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
	
	void bmiIndex(double h, double w){
		double bmi = w / (h * h * (1e-4));
		String BMI = String.format("%.2f",bmi);
		text.setText("你的BMI指数为："+ BMI);
	}
	
	void bmiRange(double h, double w) {
		double bmi = w / (h * h * (1e-4));
		if(bmi < 18.5)
			text.setText("你的身材偏瘦");
		else if(bmi < 23.9)
			text.setText("你的身材正常");
		else if(bmi < 26.9)
			text.setText("你的身材偏胖");
		else if(bmi < 26.9)
			text.setText("你的身材肥胖");
		else
			text.setText("你的身材重度肥胖！");
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
