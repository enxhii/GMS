package backend.serviceImpl;
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
	public boolean add(Customer entity,Programm programm) {
		try {
			logger.debug("User taking course");
			customerDao.chooseProgram(entity,programm);
			logger.debug("Programm added succesfully ");

			return true;

		}catch (Exception e) {
logger.debug(e.getMessage() + " " +  e);		}
		return false;
		
	}


	
	
}
