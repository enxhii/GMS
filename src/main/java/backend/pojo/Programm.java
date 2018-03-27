package backend.pojo;

import java.util.ArrayList;

public class Programm {
private Integer id;
private String header;
private String name;
private ArrayList<Schedule> schedules;
private Integer duration;
private String level;
private String description;
private Double price ;
private int burnedCalory;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getHeader() {
	return header;
}
public void setHeader(String header) {
	this.header = header;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ArrayList<Schedule> getSchedules() {
	return schedules;
}
public void setSchedules(ArrayList<Schedule> schedules) {
	this.schedules = schedules;
}
public Integer getDuration() {
	return duration;
}
public void setDuration(Integer duration) {
	this.duration = duration;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public int getBurnedCalory() {
	return burnedCalory;
}
public void setBurnedCalory(int burnedCalory) {
	this.burnedCalory = burnedCalory;
}
}
