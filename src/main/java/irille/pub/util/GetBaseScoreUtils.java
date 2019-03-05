package irille.pub.util;

import java.io.IOException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.view.SVS.SVSInfoView;
import irille.view.SVS.SVSInfoView.*;

public class GetBaseScoreUtils {
	@Inject
	public static ObjectMapper om;

	/**
	 * 计算基础分
	 * 
	 * @param research
	 * @param productionCapacity
	 * @param realFactory
	 * @param productQuality
	 * @param tradeTeam
	 * @param exhibition
	 * @param partner
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static int getBaseScore(research research, productionCapacity productionCapacity, realFactory realFactory,
			productQuality productQuality, tradeTeam tradeTeam, exhibitionAttended exhibition, partner partner)
			throws Exception {
		SVSInfoView view = new SVSInfoView(research, productionCapacity, realFactory, productQuality, tradeTeam,
				exhibition, partner);
		Integer score = view.countScore();
		return score;
	}
}
