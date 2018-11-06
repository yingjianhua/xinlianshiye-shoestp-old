package irille.shop.prm;
/**
 * 用于获取前台传来的收货信息
 * @author liyichao
 *
 */
public class Info {
	private String address;
	private String name;
	private String phone;
	private String code;
	public Info(String address, String name, String phone, String code) {
		super();
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
