package irille.Service.Manage.O2O.Imp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.inject.Inject;

import irille.Dao.PdtCatDao;
import irille.Dao.O2O.O2OActivityDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Service.Manage.O2O.O2OActivityService;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.shop.pdt.PdtCat;
import irille.view.Page;
import irille.view.O2O.O2OActivityView;

public class O2OActivityServiceImpl implements O2OActivityService {

	@Inject
	private O2OActivityDao o2OActivityDao;
	@Inject
	private O2OProductDao o2OProductDao;
	@Inject
	private PdtCatDao pdtCatDao;
	
	@Override
	public Page<O2OActivityView> list(Integer start, Integer limit, O2OActivityView condition) {
		Page<O2OActivityView> page = o2OActivityDao.pageView(start, limit, condition);
		
		Map<Integer, String> cat_pkey_name_map = new HashMap<>();
		page.getItems().stream().forEach(view->{
			String activityCat = activitCat_pkey2Name(view.getActivityCat(), cat_pkey_name_map);
			view.setActivityCat(activityCat);
		});
		return page;
	}
	/**
	 * 把以逗号分隔的产品分类pkey,转换成以分类名称分隔的字符串
	 * 
	 * @param activityCat 以逗号分隔的分类的pkey
	 * @param cat_pkey_name_map pkey和分类名称键值对, 若不存在相应数据 则从数据库中查询后 放入map
	 * @return 以逗号分隔的分类名称
	 * @author Jianhua Ying
	 */
	private String activitCat_pkey2Name(String activityCat, Map<Integer, String> cat_pkey_name_map) {
		
		if(activityCat == null || activityCat.trim().equals("")) {
			return "{\"zh_CN\":\"通用\"}";
		} else {
			activityCat = Stream.of(activityCat.split(",")).map(spkey->{
				Integer pkey = Integer.parseInt(spkey);
				if(cat_pkey_name_map != null && cat_pkey_name_map.containsKey(pkey)) {
					return cat_pkey_name_map.get(pkey);
				} else {
					PdtCat cat = pdtCatDao.findById(pkey);
					if(cat_pkey_name_map != null)
						cat_pkey_name_map.put(pkey, cat.getName());
					return cat.getName();
				}
			}).collect(Collectors.joining(","));
			return activityCat;
		}
	}

	@Override
	public void cancel(O2OActivityView view) {
		if(view == null || view.getPkey() == null) {
			throw new WebMessageException(ReturnCode.valid_notnull, "请选中活动");
		}
		O2O_Activity bean = o2OActivityDao.findById(view.getPkey());
		if(bean == null) {
			throw new WebMessageException(ReturnCode.service_gone, "活动不存在");
		}
		if(!bean.gtStatus().equals(O2O_ActivityStatus.TOBEGIN)) {
			throw new WebMessageException(ReturnCode.service_state_error, "活动已经开始或者已经完成,不能关闭");
		}
		if(o2OProductDao.countByActivity(view.getPkey()) > 0) {
			throw new WebMessageException(ReturnCode.service_unknow, "已有商品报名参加活动,不能关闭");
		}
		bean.stStatus(O2O_ActivityStatus.END);
		bean.upd();
	}

	@Override
	public O2OActivityView deploy(O2OActivityView view) {
		O2O_Activity bean = new O2O_Activity();
		valid(view);
		//TODO 地址信息需要转换成包含经纬度的json格式保存到数据库
		bean.setName(view.getName());
		bean.setActivityCat(view.getActivityCat());
		bean.ins();
		view = O2OActivityView.toView(bean);
		view.setActivityCat(activitCat_pkey2Name(bean.getActivityCat(), null));
		return view;
	}

	@Override
	public O2OActivityView edit(O2OActivityView view) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 活动新增和编辑时进行字段的校验
	 * 
	 * @param view 页面传值
	 * @author Jianhua Ying
	 */
	private void valid(O2OActivityView view) {
		//活动名称
		if(view.getName() == null || view.getName().trim().equals("")) {
			throw new WebMessageException(ReturnCode.valid_notblank, "请填写活动名称");
		}
		if(view.getName().length() > 20) {
			throw new WebMessageException(ReturnCode.valid_toolong, "活动名称不能超过20个字符");
		}
		
		//活动分类
		if(view.getActivityCat() == null || view.getActivityCat().trim().equals("")) {
			view.setActivityCat(null);
		} else {
			view.setActivityCat(Stream.of(view.getActivityCat().split(",")).map(spkey->{
				return Integer.parseInt(spkey);
			}).collect(Collectors.toSet()).stream().map(pkey->{
				//没有校验产品分类是否存在, 平台段应该没有那么多幺蛾子
				return String.valueOf(pkey);
			}).collect(Collectors.joining(",")));
		}
		
		//活动起始时间
		if(view.getStartDate() == null) {
			throw new WebMessageException(ReturnCode.valid_notnull, "请填写起始时间");
		} else {
			//清除时分秒
			LocalDate startDate = view.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			view.setStartDate(java.sql.Date.valueOf(startDate));
		}
		
		//活动国家/地区
		if(view.getAddress() == null || view.getAddress().trim().equals("")) {
			throw new WebMessageException(ReturnCode.valid_notnull, "请填写活动国家/地区");
		}
		
		//活动截止时间
		if(view.getEndDate() == null) {
			throw new WebMessageException(ReturnCode.valid_notnull, "请填写截止时间");
		} else {
			//清除时分秒
			LocalDate endDate  = view.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
			view.setEndDate(java.sql.Date.valueOf(endDate));
		}
		//活动规则
		if(view.getRules() == null || view.getRules().trim().equals("")) {
			throw new WebMessageException(ReturnCode.valid_notblank, "请填写活动规则");
		}
		if(view.getRules().length() > 800) {
			//暂时不限定字数
		}
	}
}
