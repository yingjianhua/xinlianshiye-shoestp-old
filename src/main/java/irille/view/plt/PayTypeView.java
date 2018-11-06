package irille.view.plt;

import irille.shop.plt.PltPay.OPay_Mode;
import irille.view.BaseView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayTypeView implements BaseView{

	private Integer id;
	private Byte mode;
	private String name;
	private Map<String, String> setting;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Byte getMode() {
		return mode;
	}
	public void setMode(OPay_Mode mode) {
		this.mode = mode.getLine().getKey();
	}
	public String getName() {
		return name;
	}
	public void setName(OPay_Mode mode) {
		this.name = mode.getLine().getName();
	}
	public Map<String, String> getSetting() {
		return setting;
	}
	public void setSetting(String setting) throws JsonParseException, JsonMappingException, IOException {
		this.setting = new ObjectMapper().readValue(setting, HashMap.class);
	}
	
}
