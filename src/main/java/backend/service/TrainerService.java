package backend.service;

import java.util.List;

import backend.model.Programm;
import backend.model.User;

public interface TrainerService {
	public void addProgramm(Programm programm, User user);

	public List<Programm> list(int id);

	public void updateProgram(Programm p);

	public void deleteProgramm(Integer id);

	public boolean customerExists(Integer id);

	public Programm getProgramm(Integer id);
}
