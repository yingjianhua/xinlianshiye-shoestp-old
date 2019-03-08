package com.xinlianshiye.shoestp.plat.service.pm.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.xinlianshiye.shoestp.plat.dao.pm.IPMTemplateDao;
import com.xinlianshiye.shoestp.plat.dao.pm.imp.PMTemplateDaoImp;
import com.xinlianshiye.shoestp.plat.service.pm.IPMTemplateService;
import com.xinlianshiye.shoestp.plat.view.pm.TemplateView;

import irille.Entity.pm.PM.OTempType;
import irille.Entity.pm.PMTemplate;
import irille.core.sys.Sys.OYn;
import irille.pub.svr.DbPool;

public class PMTemplateServiceImp implements IPMTemplateService{
	
	private static Map<Integer,PMTemplate> templateMap = null;
	
	@Override
	public Map<Integer, PMTemplate> getTemplateMap() {
		if(null == templateMap) {
			templateMap = new HashMap<>();
		}
		return templateMap;
	}
	public void setTemplateMap(Map<Integer, PMTemplate> templateMap) {
		this.templateMap = templateMap;
	}
//	@Inject
	private IPMTemplateDao templateDao = new PMTemplateDaoImp();
	
	//模板列表
	@Override
	public List<TemplateView> list() {
		return templateDao.list().stream().map(bean->{
			TemplateView view = new TemplateView();
			view.setPkey(bean.getPkey());
			view.setType(bean.gtType().getLine().getName());
			view.setTypeId(Integer.valueOf(bean.gtType().getLine().getKey()));
			view.setMailEnable(bean.getEmailStatus());
			view.setPmEnable(bean.getPmStatus());
			view.setRec(bean.gtRcvrType().getLine().getName());
			view.setMailContent(bean.getMailContent());
			view.setPmContent(bean.getPmContent());
			view.setRecType(Integer.valueOf(bean.getRcvrType()));
			return view;
		}).collect(Collectors.toList());
		
	}
	
	//模板修改
	@Override
	public void upd(TemplateView view) {
		PMTemplate template = templateDao.load(view.getPkey());
		template.setMailContent(view.getMailContent());
		template.setPmContent(view.getPmContent());
		template.setMailTitle(view.getMailTitle());
//		SQL sql = new SQL();
//		sql.UPDATE(PMTemplate.class)
//			.SET(PMTemplate.T.MAIL_CONTENT, view.getMailContent())
//			.SET(PMTemplate.T.PM_CONTENT, view.getPmContent())
//			.WHERE(PMTemplate.T.PKEY, " =? ", view.getPkey());
		template.upd();
//		Query.sql(sql).executeUpdate();
		loadTempList();
	}
	
	//模板启用停用
	@Override
	public void enable(TemplateView view) {
		PMTemplate template = templateDao.load(view.getPkey());
		template.setEmailStatus(view.getMailEnable());
		template.setPmStatus(view.getPmEnable());
		template.upd();
		loadTempList();
	}
	
	public static void main(String[] args) {
		PMTemplate.TB.getClass();
		TemplateView template = new TemplateView();
		IPMTemplateService service = new PMTemplateServiceImp();
		template.setPkey(106);
		template.setMailEnable((byte)3);
		template.setPmEnable((byte)3);
		service.enable(template);
		try {
			DbPool.getInstance().getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//初始化模板列表
	@Override
	public void initTemp() {
		OTempType[] temps = OTempType.values();
		if(null == templateDao) {
			templateDao = new PMTemplateDaoImp();
		}
		Map<Integer,PMTemplate> beans = list2Map(templateDao.list());
		
		List<PMTemplate> addBeans = new ArrayList<>();
		for(OTempType temp:temps) {
			if(!beans.containsKey(Integer.valueOf(temp.getLine().getKey()))) {
				if(temp.getLine().getKey() != (byte)-1) {
					addBeans.add(initBean(temp));
				}
			}else {
				beans.remove(Integer.valueOf(temp.getLine().getKey()));
			}
		}
		if(beans.size() > 0) {
			//删除
			List<PMTemplate> delBeans = new ArrayList<>();
			for(Integer key:beans.keySet()) {
				delBeans.add(beans.get(key));
			}
			if(delBeans.size()>0) {
				templateDao.del(delBeans);
			}
		}
		if(addBeans.size() > 0) {
			templateDao.add(addBeans);
		}
		loadTempList();
	}
	
	//加载模板列表
	public void loadTempList() {
		setTemplateMap(list2Map(templateDao.list()));
	}
	
	private PMTemplate initBean(OTempType temp) {
		PMTemplate template = new PMTemplate();
		template.setTitle(temp.getLine().getName());
		template.setRcvrType(temp.getRcvrType().getLine().getKey());
		template.setType(temp.getLine().getKey());
		template.setEmailStatus(OYn.NO.getLine().getKey());
		template.setPmStatus(OYn.NO.getLine().getKey());
		template.setRowVersion((short)0);
		return template;
	}
	
	public Map<Integer,PMTemplate> list2Map(List<PMTemplate> lists){
		Map<Integer,PMTemplate> map = new HashMap<Integer,PMTemplate>();
		lists.forEach(l->{
			map.put(Integer.valueOf(l.getType()), l);
		});
		return map;
	}
}
