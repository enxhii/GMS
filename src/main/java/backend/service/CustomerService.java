package backend.service;

import java.util.List;

import backend.model.Address;
import backend.model.Customer;
import backend.model.Programm;
import backend.model.User;

public interface CustomerService {
	public boolean add(Customer entity, List<Programm> programm);

	public List<Programm> listProgramms();

	public void customerReg(User user, Address address);

	public Customer getCustomer(int id);

}
