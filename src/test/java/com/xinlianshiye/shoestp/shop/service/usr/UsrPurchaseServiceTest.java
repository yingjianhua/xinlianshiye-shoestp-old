package com.xinlianshiye.shoestp.shop.service.usr;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.BaseTest;
import com.xinlianshiye.shoestp.shop.view.usr.PurchaseView;

import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQPurchaseContact;
import irille.pub.bean.Query;
import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrPurchase;

public class UsrPurchaseServiceTest extends BaseTest {

  @Inject private UsrPurchaseService service;
  @Inject private ObjectMapper om;
  
  @BeforeClass
  public static void initBean() { 
	  RFQConsultRelation.TB.getCode();
	  RFQPurchaseContact.TB.getCode();
  }

  @Test(expected = WebMessageException.class)
  @Ignore
  public void testEditAccount() {
    UsrPurchase purchase = UsrPurchase.load(UsrPurchase.class, 1261);

    PurchaseView accountSetting = new PurchaseView();
    accountSetting.setPhone("13555548668");
    accountSetting.setSurname("Ying");
    accountSetting.setGender((byte) 2);
    //		accountSetting.setFirstName("Jianhua");
    accountSetting.setCompany("有限公司");

    service.editAccount(purchase, accountSetting);
  }

  @Test
  public void getProfile() throws JsonProcessingException {
    UsrPurchase purchase = Query.SELECT(UsrPurchase.class, 3787);
    PurchaseView profile = service.getProfile(purchase);
    System.out.println(om.writeValueAsString(profile));
  }
}
