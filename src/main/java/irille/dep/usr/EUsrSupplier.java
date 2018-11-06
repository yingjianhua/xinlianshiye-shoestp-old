package irille.dep.usr;

import irille.pub.ext.Ext;
import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.html.EMCrtTrigger;
import irille.pub.html.EMForm;
import irille.pub.html.EMWin;
import irille.pub.tb.Tb;
import irille.pub.view.VFlds;
import irille.shop.usr.UsrSupplier;

public class EUsrSupplier extends UsrSupplier {
	/*
	 * 公司基础信息字段集合 
	 */
	private static VFlds baseVflds = new VFlds(T.NAME,T.CATEGORY,T.IS_AUTH,
	T.SORT,T.SEO_TITLE,T.SEO_CONTENT,T.AUTH_TIME,T.SHOW_NAME,T.ENTITY,
	T.COMPANY_TYPE,T.COMPANY_NATURE,T.CREDIT_CODE,T.COMPANY_ESTABLISH_TIME,
	T.OPERATION_TERM,T.MAIN_SALES_AREA,T.MAIN_PROD,T.PROD_PATTERN,
	T.COMPANY_ADDR,T.DES,T.CONTACTS,T.EMAIL,T.PHONE,T.TELEPHONE,
	T.FAX,T.QQ,T.CERT_PHOTO,T.ID_CARD_FRONT_PHOTO,T.ID_CARD_BACK_PHOTO,T.COOP_CERT_PHOTO);
	/**
	 * 前台页面资料字段集合 
	 */
	private static VFlds pageVflds = new VFlds(T.BUSINESS_TYP,T.LOCATION,
	T.PRODUCTION,T.DEVELOPER,T.TOTAL_EMPLOYEES,T.ANNUAL_SALES,
	T.TOP_3_MARKETS,T.MATERIALS,T.HEAD_PIC,T.DEPARTMENT,T.JOB_TITLE,
	T.WEBSITE,T.COUNTRY,T.PROVINCE,T.CITY,T.Is_Pro,T.LOGO,
	T.SIGN_BACKGD,T.AD_PHOTO,T.AD_PHOTO_MOBILE,T.AD_PHOTO_LINK,
	T.COMPANY_PHOTO,T.COMPANY_PHOTO_LINK);
	/**
	 * 个性装修字段集合 
	*/
	private static VFlds diyVflds = new VFlds(T.HOME_PAGE_DIY,T.PRODUCT_PAGE_DIY,
	T.CONTACT_PAGE_DIY,T.HOME_PAGE_DIY_MOBILE,T.PRODUCT_PAGE_DIY_MOBILE,
	T.CONTACT_PAGE_DIY_MOBILE);
	/**
	 * 营销设置
	 */
	private static VFlds marketingVflds = new VFlds(T.TRACE_CODE,T.WEB_SIZE_TITLE,
	T.WEB_SITE,T.TONGJI_URL,T.TONGJI_PWD,T.UPDATE_TIME);
	
	public static void main(String[] args) {
		new EUsrSupplier().crtExt().crtFiles();
	}
	
	public EMCrt crtExt() {
//		VFlds vflds = new VFlds(TB);
		VFlds vflds = new VFlds(TB);//T.PKEY, T.NAME, T.CATEGORY, T.IS_AUTH, T.WEB_SITE, T.SORT);
		VFlds searchVflds = new VFlds(T.NAME,T.CATEGORY);
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		ext.setVfldsForm(baseVflds);
		ext.setVfldsList(new VFlds(T.PKEY,T.NAME,T.STATUS,T.CATEGORY, T.IS_AUTH, T.WEB_SITE, T.SORT));
		ext.newExts().init();
		new WinBase(TB).init().crtFile();
		new FormBase(TB, baseVflds).init().crtFile();
		new WinPage(TB).init().crtFile();
		new FormPage(TB, pageVflds).init().crtFile();
		new WinDiy(TB).init().crtFile();
		new FormDiy(TB, diyVflds).init().crtFile();
		new WinMarketing(TB).init().crtFile();
		new FormMarketing(TB, marketingVflds).init().crtFile();
		
		EMCrt extTrig = new EMCrtTrigger(TB, vflds, T.CATEGORY, new VFlds(T.NAME));
		extTrig.newExts().init().crtFiles();
		
		return ext;
	}
/** Begin onUpdBase ********
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinBase',{
				title : this.title+'>基本信息'
			});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}
*** End onUpdBase *********/

/** Begin onUpdPage ********
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinPage',{
				title : this.title+'>页面信息'
			});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}
*** End onUpdPage *********/

/** Begin onUpdDiy ********
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinDiy',{
				title : this.title+'>个性装修'
			});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}	
*** End onUpdDiy *********/

/** Begin onUpdMarketing ********
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinMarketing',{
				title : this.title+'>营销设置'
			});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}
*** End onUpdMarketing *********/
/** Begin onDoAppr ********
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '供应商['+selection.get('bean.code') + '] - 审核确认吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/usr_UsrSupplier_approve?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							var bean  = Ext.create('mvc.model.usr.UsrSupplier',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.mdMainTable.getSelectionModel().deselectAll();
							me.mdMainTable.getView().select(selection);
							Ext.example.msg(msg_title, '审核--成功');
						}else{
							Ext.MessageBox.show({
								title : msg_title, 
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
	}
 *** End onDoAppr *********/
/** Begin onUnAppr ********
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '供应商['+selection.get('bean.code') + '] - 弃审确认吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/usr_UsrSupplier_unapprove?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							var bean  = Ext.create('mvc.model.usr.UsrSupplier',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.mdMainTable.getSelectionModel().deselectAll();
							me.mdMainTable.getView().select(selection);
							Ext.example.msg(msg_title, '弃审--成功');
						}else{
							Ext.MessageBox.show({
								title : msg_title, 
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
	}
 *** End onUnAppr *********/
	
	private class FormBase extends EMForm {
		/**
		 * 公司信息
		 */
		public FormBase(Tb tb, VFlds... vflds) {
			super(tb, vflds);
		}
		@Override
		public String getFileName() {
			return Ext.getPathApp() + "view/" + getPack() + "/" + getClazz()
					+ "/FormBase.js";
		}
		@Override
		public String getDefineName() {
			return "mvc.view." + getPack() + "." + getClazz() + ".FormBase";
		}
		
		@Override
		public void initAttrs() {
			add(EXTEND, "Ext.form.Panel");
			AddDime(REQUIRES, "Ext.ux.DataTip");
			add(LAYOUT, "form");
			add(BORDER, false);
			add(FRAME, false);
			add(INS_FLAG, true);
			add(BODY_PADDING, "5 5 5 5");
			addExp(URL, Ext.url(Ext.getPag(getTb()), Ext.getClazz(getTb()), "updBase"));
			setFieldDefaultsProperies(AddList(FIELD_DEFAULTS));
			//AddList(PLUGINS).add(PTYPE, "datatip");
		}
	}
	
	/*
	 * 产生WinBase用于修改供应商基本信息
	 */
	private static class WinBase extends EMWin {

		public WinBase(Tb tb) {
			super(tb);
		}
		
		@Override
		public String getFileName() {
			return Ext.getPathApp() + "view/" + getPack() + "/" + getClazz()
					+ "/WinBase.js";
		}
		@Override
		public String getDefineName() {
			return "mvc.view." + getPack() + "." + getClazz() + ".WinBase";
		}
		@Override
		public void initForm() {
			getForm().add(ANCHOR, "100%")
			.add(PLAIN, true)
			.addExp(XTYPE,"Ext.create('mvc.view." + getPack() + "." +getClazz() + ".FormBase')");
		}
	}
	
	private class FormPage extends EMForm {
		/**
		 * 前台页面信息
		 */
		public FormPage(Tb tb, VFlds... vflds) {
			super(tb, vflds);
		}
		@Override
		public String getFileName() {
			return Ext.getPathApp() + "view/" + getPack() + "/" + getClazz()
					+ "/FormPage.js";
		}
		@Override
		public String getDefineName() {
			return "mvc.view." + getPack() + "." + getClazz() + ".FormPage";
		}
		
		@Override
		public void initAttrs() {
			add(EXTEND, "Ext.form.Panel");
			AddDime(REQUIRES, "Ext.ux.DataTip");
			add(LAYOUT, "form");
			add(BORDER, false);
			add(FRAME, false);
			add(INS_FLAG, true);
			add(BODY_PADDING, "5 5 5 5");
			addExp(URL, Ext.url(Ext.getPag(getTb()), Ext.getClazz(getTb()), "updPage"));
			setFieldDefaultsProperies(AddList(FIELD_DEFAULTS));
			//AddList(PLUGINS).add(PTYPE, "datatip");
		}
	}
	
	/*
	 * 产生WinPage用于修改供应商前台页面信息
	 */
	private static class WinPage extends EMWin {

		public WinPage(Tb tb) {
			super(tb);
		}
		
		@Override
		public String getFileName() {
			return Ext.getPathApp() + "view/" + getPack() + "/" + getClazz()
					+ "/WinPage.js";
		}
		@Override
		public String getDefineName() {
			return "mvc.view." + getPack() + "." + getClazz() + ".WinPage";
		}
		@Override
		public void initForm() {
			getForm().add(ANCHOR, "100%")
			.add(PLAIN, true)
			.addExp(XTYPE,"Ext.create('mvc.view." + getPack() + "." +getClazz() + ".FormPage')");
		}
	}
	private class FormDiy extends EMForm {
		/**
		 * 个性装修信息
		 */
		public FormDiy(Tb tb, VFlds... vflds) {
			super(tb, vflds);
		}
		@Override
		public String getFileName() {
			return Ext.getPathApp() + "view/" + getPack() + "/" + getClazz()
					+ "/FormDiy.js";
		}
		@Override
		public String getDefineName() {
			return "mvc.view." + getPack() + "." + getClazz() + ".FormDiy";
		}
		
		@Override
		public void initAttrs() {
			add(EXTEND, "Ext.form.Panel");
			AddDime(REQUIRES, "Ext.ux.DataTip");
			add(LAYOUT, "form");
			add(BORDER, false);
			add(FRAME, false);
			add(INS_FLAG, true);
			add(BODY_PADDING, "5 5 5 5");
			addExp(URL, Ext.url(Ext.getPag(getTb()), Ext.getClazz(getTb()), "updDiy"));
			setFieldDefaultsProperies(AddList(FIELD_DEFAULTS));
			//AddList(PLUGINS).add(PTYPE, "datatip");
		}
	}
	
	/*
	 * 产生WinDiy用于修改供应商个性装修
	 */
	private static class WinDiy extends EMWin {

		public WinDiy(Tb tb) {
			super(tb);
		}
		
		@Override
		public String getFileName() {
			return Ext.getPathApp() + "view/" + getPack() + "/" + getClazz()
					+ "/WinDiy.js";
		}
		@Override
		public String getDefineName() {
			return "mvc.view." + getPack() + "." + getClazz() + ".WinDiy";
		}
		@Override
		public void initForm() {
			getForm().add(ANCHOR, "100%")
			.add(PLAIN, true)
			.addExp(XTYPE,"Ext.create('mvc.view." + getPack() + "." +getClazz() + ".FormDiy')");
		}
	}
	
	private class FormMarketing extends EMForm {
		/**
		 * 营销信息
		 */
		public FormMarketing(Tb tb, VFlds... vflds) {
			super(tb, vflds);
		}
		@Override
		public String getFileName() {
			return Ext.getPathApp() + "view/" + getPack() + "/" + getClazz()
					+ "/FormMarketing.js";
		}
		@Override
		public String getDefineName() {
			return "mvc.view." + getPack() + "." + getClazz() + ".FormMarketing";
		}
		
		@Override
		public void initAttrs() {
			add(EXTEND, "Ext.form.Panel");
			AddDime(REQUIRES, "Ext.ux.DataTip");
			add(LAYOUT, "form");
			add(BORDER, false);
			add(FRAME, false);
			add(INS_FLAG, true);
			add(BODY_PADDING, "5 5 5 5");
			addExp(URL, Ext.url(Ext.getPag(getTb()), Ext.getClazz(getTb()), "updMarketing"));
			setFieldDefaultsProperies(AddList(FIELD_DEFAULTS));
			//AddList(PLUGINS).add(PTYPE, "datatip");
		}
	}
	
	/*
	 * 产生WinMarketing用于修改供应商营销信息
	 */
	private static class WinMarketing extends EMWin {

		public WinMarketing(Tb tb) {
			super(tb);
		}
		
		@Override
		public String getFileName() {
			return Ext.getPathApp() + "view/" + getPack() + "/" + getClazz()
					+ "/WinMarketing.js";
		}
		@Override
		public String getDefineName() {
			return "mvc.view." + getPack() + "." + getClazz() + ".WinMarketing";
		}
		@Override
		public void initForm() {
			getForm().add(ANCHOR, "100%")
			.add(PLAIN, true)
			.addExp(XTYPE,"Ext.create('mvc.view." + getPack() + "." +getClazz() + ".FormMarketing')");
		}
	}
}

