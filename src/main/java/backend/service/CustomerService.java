package backend.service;

import backend.model.Customer;
import backend.model.Programm;

public interface CustomerService {
	public boolean add(Customer entity, Programm programm);

}
