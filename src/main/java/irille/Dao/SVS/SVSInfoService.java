package irille.Dao.SVS;

public interface SVSInfoService {

	/**
	 * 申请认证SVS
	 * @author GS
	 * @param research           研发能力信息
	 * @param productionCapacity 生产能力信息
	 * @param realFactory        工厂信息
	 * @param productQuality     产品质量信息
	 * @param tradeTeam          外贸团队信息
	 * @param exhibition         参加的展会信息
	 * @param partner            合作商信息
	 */
	public void applicationForCertification(String research, String productionCapacity, String realFactory,
			String productQuality, String tradeTeam, String exhibition, String partner);
}
