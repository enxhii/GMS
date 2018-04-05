package backend.service;

import java.util.List;

import backend.model.Programm;
import backend.model.User;

public interface TrainerService {
	public void addProgramm(Programm programm ,User user);
	public List<Programm> list();

}
