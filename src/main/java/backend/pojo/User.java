package backend.pojo;
import java.util.ArrayList;
public class User {
private Integer id;
private String name;
private String surname;
private String username;
private String password ;
private Role role;
private Address address;
private String email;
private String phone;
private ArrayList<Programm> programms;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public ArrayList<Programm> getProgramms() {
	return programms;
}
public void setProgramms(ArrayList<Programm> programms) {
	this.programms = programms;
}
	
	
}
