package irille.Dao.SVS.impl;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import irille.Dao.SVS.SVSInfoDao;
import irille.Dao.SVS.SVSInfoService;
import irille.Entity.SVS.SVSInfo;
import irille.Entity.SVS.Enums.SVSAuthenticationStatus;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.core.sys.SysSystem;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.GetBaseScoreUtils;
import irille.view.SVS.SVSDetailedInfoView;
import irille.view.SVS.SVSInfoView.exhibitionAttended;
import irille.view.SVS.SVSInfoView.partner;
import irille.view.SVS.SVSInfoView.productQuality;
import irille.view.SVS.SVSInfoView.productionCapacity;
import irille.view.SVS.SVSInfoView.realFactory;
import irille.view.SVS.SVSInfoView.research;
import irille.view.SVS.SVSInfoView.tradeTeam;

public class SVSInfoServiceImpl implements SVSInfoService {
	@Inject
	private SVSInfoDao SVSInfoDao;
	@Inject
	ObjectMapper om;

	/**
	 * 申请认证SVS
	 * 
	 * @author GS
	 */
	@Override
	public SVSDetailedInfoView application(SVSInfo svs) throws Exception {
		if (SVSInfoDao.findSVSInfoBySupplier(svs.getSupplier()) != null) {
			throw new WebMessageException(ReturnCode.failure, "该用户已申请认证");
		}
		SVSInfo info = SVSInfoDao.save(svs);
		SVSDetailedInfoView view = CreateView(info);
		return view;
	}

	/**
	 * 获取用户SVS认证信息
	 * 
	 * @author GS
	 */
	@Override
	public SVSDetailedInfoView getSVSInfo(Integer supplierId) throws Exception {
		SVSInfo info = SVSInfoDao.findSVSInfoBySupplier(supplierId);
		if (null == info)
			throw new WebMessageException(ReturnCode.failure, "该用户暂未申请SVS认证");
		SVSDetailedInfoView view = CreateView(info);
		return view;
	}

	/**
	 * 创建SVS视图信息
	 * 
	 * @author GS
	 * @param info
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	private SVSDetailedInfoView CreateView(SVSInfo info) throws Exception {
		SVSDetailedInfoView view = new SVSDetailedInfoView();
		view.setId(info.getPkey());
		view.setApplicationTime(info.getApplicationTime());
		view.setBaseScore(info.getBaseScore());
		view.setGrade(info.getGrade());
		view.setStatus(info.getStatus());
		// view.setSupplier(info.gtSupplier());
		view.setCapacity(om.readValue(info.getProductionCapacity(), productionCapacity.class));
		view.setExhibition(om.readValue(info.getExhibitionAttended(), exhibitionAttended.class));
		view.setFactory(om.readValue(info.getRealFactory(), realFactory.class));
		view.setQuality(om.readValue(info.getProductQuality(), productQuality.class));
		view.setResearch(om.readValue(info.getResearch(), research.class));
		view.setTeam(om.readValue(info.getForeignTradeTeam(), tradeTeam.class));
		view.setPart(om.readValue(info.getPartner(), partner.class));
		if (null != info.getAuthenticationTime() && info.gtStatus() != SVSAuthenticationStatus.ToBeAudited
				&& info.gtStatus() != SVSAuthenticationStatus.NoApplication)
			view.setAutTime(info.getAuthenticationTime());
		view.setCount(info.getApplicationCount());
		return view;

	}

	/**
	 * 修改认证信息
	 * 
	 * @author GS
	 */
	@Override
	public SVSDetailedInfoView updSVSInfo(Integer supplierId, String res, String capacity, String factory,
			String quality, String team, String exhibition, String part) throws Exception {

		SVSInfo svs = SVSInfoDao.findSVSInfoBySupplier(supplierId);
		if (svs == null)
			throw new WebMessageException(ReturnCode.failure, "商户未申请认证，无法修改");
		if (svs.gtStatus() == SVSAuthenticationStatus.SUCCESS
				&& timeDiffForDay(svs.getApplicationTime(), new Date()) <= 180)
			throw new WebMessageException(ReturnCode.failure, "商户距离上次认证成功未满半年,无法再次提交认证");
		int score = GetBaseScoreUtils.getBaseScore(res, capacity, factory, quality, team, exhibition, part);
		if (score < 30)
			throw new WebMessageException(ReturnCode.failure, "未满足银牌基础分值,无法提交认证");
		if (score >= 30 && score <= 59)
			svs.stGrade(SVSGradeType.SILVER);
		if (score >= 60)
			svs.stGrade(SVSGradeType.GOLD);
		svs.stStatus(SVSAuthenticationStatus.ToBeAudited);
		svs.setBaseScore(score);
		svs.setApplicationTime(new Date());
		svs.setResearch(res);
		svs.setProductionCapacity(capacity);
		svs.setRealFactory(factory);
		svs.setProductQuality(quality);
		svs.setForeignTradeTeam(team);
		svs.setExhibitionAttended(exhibition);
		svs.setPartner(part);
		svs.setApplicationCount(svs.getApplicationCount() + 1);
		SVSInfoDao.save(svs);
		return CreateView(SVSInfoDao.save(svs));
	}

	/**
	 * 获取两个时间的时间差（天）
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private Long timeDiffForDay(Date start, Date end) {
		long diff = end.getTime() - start.getTime();
		long nh = 1000 * 60 * 60 * 24;// 一小时的毫秒数
		long diffDay = diff / nh; // 计算差多少小时
		if (diff % nh > 0) {
			diffDay += 1;
		}
		return diffDay;
	}
}
