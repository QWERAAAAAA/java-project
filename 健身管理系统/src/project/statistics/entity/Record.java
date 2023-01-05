package project.statistics.entity;

public class Record implements Cloneable {
	private boolean selected;
	private String date;
	private String type;
	private String time;
	private String spent;

    public Record() {
    }

    public Record(Record record) {
    	this.date = record.date;
    	this.type = record.type;
    	this.time = record.time;
    	this.spent = record.spent;
        this.selected = record.selected;
    }

    public Record(String date, String type, String time, String spent) {
    	this.date = date;
    	this.type = type;
    	this.time = time;
    	this.spent = spent;
    }

    public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSpent() {
		return spent;
	}
	public void setSpent(String spent) {
		this.spent = spent;
	}

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public Record clone() throws CloneNotSupportedException {
        return (Record) super.clone();
    }

    @Override
    public String toString() {
        return date+","+type+","+time+","+spent+"\n";
    }
}
