package backend.serviceImpl;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.dao.CustomerDao;
import backend.model.Customer;
import backend.model.Programm;
import backend.service.CustomerService;


@Transactional
@Service
public class CustomerServiceImpl  implements CustomerService{
	
@Autowired
private CustomerDao customerDao;
final static Logger logger = LogManager.getLogger(CustomerServiceImpl.class);


	@Override
	public boolean add(Customer entity,List<Programm> programm) {
		try {
			logger.debug("User taking course");
			customerDao.chooseProgram(entity,programm);
			logger.debug("Programm added succesfully ");

			return true;

		}catch (Exception e) {
logger.debug(e.getMessage() + " " +  e);		}
		return false;
		
	}


	@Override
	public List<Programm> listProgramms() {
try {
	return customerDao.listProgramms();
	}catch (Exception e) {
logger.debug(e);	}
return null;
}


	
	
}
