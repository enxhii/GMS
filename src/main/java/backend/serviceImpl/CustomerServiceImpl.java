package backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.dao.CustomerDao;
import backend.dao.RoleDao;
import backend.model.Address;
import backend.model.Customer;
import backend.model.Programm;
import backend.model.Role;
import backend.model.User;
import backend.service.CustomerService;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private CustomerDao customerDao;
	final static Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

	@Override
	public boolean add(Customer entity, List<Programm> programm) {
		try {
			LOGGER.debug("User taking course");
			customerDao.chooseProgram(entity, programm);
			return true;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage() + " " + e);
		}
		return false;

	}

	@Override
	public List<Programm> listProgramms() {
		try {
			return customerDao.listProgramms();
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return null;
	}

	@Override
	public void customerReg(User user, Address address) {
		try {
			ArrayList<Role> role = new ArrayList<>();
			LOGGER.debug("Inserting user......");
			role.add(roleDao.getRoleById());
			customerDao.customerReg(user, address, role);
		} catch (Exception e) {
			LOGGER.debug(e);
			e.printStackTrace();
		}

	}

	@Override
	public Customer getCustomer(int id) {
		try {
			return customerDao.getCustomer(id);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return null;
	}
}
