package frontend.beans;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "homeBean")
@ViewScoped
public class HomeBean {
	final static Logger logger = LogManager.getLogger(HomeBean.class);
	private List<String> images;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 16; i++) {
			images.add("fitness" + i + ".jpg");
		}
	}
	public List<String> getImages() {
		logger.debug("Getting images ");

		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}
