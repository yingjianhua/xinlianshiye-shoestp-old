package irille.shop.cnt;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.util.AppConfig;
import irille.shop.cnt.CntAdLine.T;
import irille.view.cnt.AdLineView;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CntAdLineDAO {

	/**
	 * 获取指定广告管理下的所有明细信息
	 * @param main 广告管理pkey
	 * @author yingjianhua
	 */
	public static List<AdLineView> listViewByMain(Long main, Integer start, Integer limit) {
		List<AdLineView> views = new ArrayList<>();
		Query
		.SELECT(T.IMAGE, T.URL, T.MAIN_IMG, T.NAME, T.BRIEF)
		.FROM(CntAdLine.class)
		.WHERE(T.MAIN, "=?", main)
		.WHERE(T.ENABLED, "=?", OEnabled.TRUE)
		.limit(start, limit)
		.ORDER_BY(T.SORT, "asc")
		.queryList()
		.forEach(bean->{
			views.add(new AdLineView(){{
				setPrimary(bean.gtMainImg());
				setName(bean.getName());
				setSummary(bean.getBrief());
				setImage(bean.getImage());
				setUrl(bean.getUrl());
			}});
		});
		return views;
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		BeanQuery.config(true);
		CntAdLine.TB.getCode();
		String s = AppConfig.objectMapper.writeValueAsString(listViewByMain(6L, 0, 3));
		System.out.println(s);
		AppConfig.dbpool_release();
	}
}
