package project.statistics;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import project.statistics.entity.Record;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制层
 */
public class Controller {
	
	@FXML
    private TableColumn<Record, String> timeColumn;

    @FXML
    private TableColumn<Record, String> spentColumn;

    @FXML
    private TableColumn<Record, Boolean> selectedColumn;

    @FXML
    private TextField AddDate;

    @FXML
    private TableView<Record> tableView;

    @FXML
    private TextField AddType;

    @FXML
    private TableColumn<Record, String> dateColumn;

    @FXML
    private TextField AddSpent;

    @FXML
    private TableColumn<Record, String> typeColumn;

    @FXML
    private TextField AddTime;
    
    @FXML
    private TextField dateSearch;

    @FXML
    private TextField typeSearch;
    
    @FXML
    public PieChart pieChart;
    
    @FXML
    private Label caption;
    
    @FXML
    private Button refresh;


    //表格内容list
    private final ObservableList<Record> data = FXCollections.observableArrayList();

    //文件目录
    private final Path path = Paths.get("src/test.txt");

    // 与表格内容同步的数据list
    private final List<Record> recordList = new ArrayList<>();
    private final List<Record> tmp = new ArrayList<>();

    /**
     * list转byte[]数组，用于存入文件中
     */
    public byte[] listToBytes(List<Record> recordList) {
        StringBuffer sb = new StringBuffer();
        for (Record record : recordList) {
            sb.append(record.toString());
        }
        return sb.toString().getBytes();
    }

    /**
     *  添加记录功能
     */
    @FXML
    void add(ActionEvent event) throws IOException {
        if (AddDate.getText() != null
                && AddType.getText() != null
                && AddTime.getText() != null
                && AddSpent.getText() != null) {
            //获得文本框的输入
            Record record = new Record(
            		AddDate.getText(),
            		AddType.getText(),
            		AddTime.getText(),
            		AddSpent.getText());
            //添加到表格中
            data.add(record);
            //同步添加到list中
            recordList.add(record);
            
            //将list写入到文件中
            Files.write(path, listToBytes(recordList));

            //清空文本输入框
            AddDate.clear();
            AddType.clear();
            AddTime.clear();
            AddSpent.clear();
        }
    }

    /**
     *  删除记录功能
     */
    @FXML
    void delete(ActionEvent event) throws IOException {
        deleteRecord();
    }

    private boolean deleteRecord() throws IOException {
        int size = data.size();
        if (size <= 0) {
            return false;
        }
        //遍历所选选项
        for (int i = size - 1; i >= 0; i--) {
            Record p = data.get(i);
            if (p.isSelected()) {
                //从list中移除
                recordList.remove(p);
                //将list写入到文件中
                Files.write(path, listToBytes(recordList));
                //从表格中移除
                data.remove(p);
            }
        }
        return true;
    }
    
    /**
     * 	查找记录功能
     */
    @FXML
    void search(ActionEvent event) throws CloneNotSupportedException {
        for (Record p : recordList) {
            tmp.add(p.clone());
        }
        //先清空表格所有数据
        data.removeAll(recordList);

        String date = dateSearch.getText();
        String type = typeSearch.getText();

        //若为空时，则添加回所有数据
        if (date.equals("") && type.equals("")) {
            data.addAll(recordList);
        }

        //遍历list，若有与搜索条件相同的数据，则添加到表格中
        for (Record p : recordList) {
            if (p.getDate().equals(date) || p.getType().equals(type)) {
                data.add(p);
            }
        }
        dateSearch.clear();
        typeSearch.clear();
    }
    

    /**
     * 刷新图表功能
     */
    @FXML
    void onRefresh(ActionEvent event) throws IOException{
    	pieChart.getData().clear();
        for (Record p : recordList) {
        	int count = 1;
        	System.out.println(p.getType());
        	boolean matchFound = false;
            Record tmp = new Record(p.getDate(), p.getType(), p.getTime(), p.getSpent());
            for(PieChart.Data w:pieChart.getData()) {
        		if(w.getName().equals(p.getType())) {
        			matchFound = true;
        			count ++;
        			pieChart.getData().remove(w);
        			PieChart.Data upDate = new PieChart.Data(tmp.getType(), count);
            		pieChart.getData().add(upDate);
        		}
        	}
            if(!matchFound) {
            	PieChart.Data newSlice = new PieChart.Data(tmp.getType(), 1);
        		pieChart.getData().add(newSlice);
            }
        }
        	
        	caption.setTextFill(Color.BLACK);
        	caption.setStyle("-fx-font: 20 arial;");
        	
        	for (final PieChart.Data data : pieChart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override public void handle(MouseEvent e) {

                                caption.setTranslateX(e.getSceneX()-540);
                                caption.setTranslateY(e.getSceneY()-260);
                                caption.setText(String.valueOf(data.getPieValue()) + "次");

                            }
                        });
            }
        }


    
    /**
     * 返回
     */
    @FXML
    void back(ActionEvent event) throws Exception {
    	
    }
    
    
    /**
     *  初始化
     */
    
    @FXML
    private void initialize() throws IOException {
    	
        //绑定每一列
    	dateColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("date"));
        selectedColumn.setCellValueFactory(new PropertyValueFactory<Record, Boolean>("selected"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("type"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("time"));
        spentColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("spent"));
        //设定数据list
        tableView.setItems(data);
        tableView.setEditable(true);

        //按行读取文件，存入list中
        List<String> lines = Files.readAllLines(path);
        //根据分隔符拆分文件每一行数据，存入对象中，并添加到fx表格中
        for (String line : lines) {
            String[] tmp = line.split(",");
            recordList.add(new Record(tmp[0], tmp[1], tmp[2], tmp[3]));
            //不能通过匿名对象创建，会导致无法remove掉元素
            data.add(recordList.get(recordList.size() - 1));
        }

        //初始化多选框，默认全为false，当为true则被选中
        selectedColumn.setCellFactory(
                CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(Integer index) {
                        final Record person = tableView.getItems().get(index);
                        ObservableValue<Boolean> ret =
                                new SimpleBooleanProperty(person, "selected", person.isSelected());
                        ret.addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {
                                person.setSelected(newValue);
                            }
                        });
                        return ret;
                    }
                }));


    }
}
