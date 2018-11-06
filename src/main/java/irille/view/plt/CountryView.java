package irille.view.plt;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CountryView implements BaseView {

	private int id;
	@JsonSerialize(using=I18NFieldSerializer.class)
	private String name;
	private String shortName;
	private String flag;
	private boolean isDefault;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
}
