package com.xinlianshiye.shoestp.shop.view.usr;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import irille.view.BaseView;
import lombok.Data;
@Data
public class PurchaseAccountSettingView implements BaseView {

	@NotNull
	private Byte gender;
	@NotBlank
	private String firstName;
	@NotBlank
	private String surname;
	@NotBlank
	private String phone;
	@NotBlank
	private String company;
	
	private String address;
}
