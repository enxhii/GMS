package backend.service;

import java.util.List;

import backend.model.Customer;
import backend.model.Programm;

public interface CustomerService {
	public boolean add(Customer entity, List<Programm> programm);
	public List<Programm> listProgramms();

}
