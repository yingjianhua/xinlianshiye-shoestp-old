package irille.view.SVS.BaseScore;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Countable {

	private static final Map<Class<?>, SVSHandler<?>> handerMap = new HashMap<>();

	static {
		handerMap.put(ResCertificateHandler.class, new ResCertificateHandler());
		handerMap.put(ResearchIsTeamHandler.class, new ResearchIsTeamHandler());
		handerMap.put(ResNumOfShoesHandler.class, new ResNumOfShoesHandler());
		handerMap.put(RealEmployeesNumHandler.class, new RealEmployeesNumHandler());
		handerMap.put(RealIsSystemHandler.class, new RealIsSystemHandler());
		handerMap.put(RealLicenceHandler.class, new RealLicenceHandler());
		handerMap.put(ExhibitionAttendedHandler.class, new ExhibitionAttendedHandler());
		handerMap.put(NeedleCartNumHandler.class, new NeedleCartNumHandler());
		handerMap.put(PartnerHandler.class, new PartnerHandler());
		handerMap.put(ProductionExportVolumeHandler.class, new ProductionExportVolumeHandler());
		handerMap.put(ProductionLineQuantityHandler.class, new ProductionLineQuantityHandler());
		handerMap.put(ProductISOCertificateHandler.class, new ProductISOCertificateHandler());
		handerMap.put(ProductQualityCertificateHandler.class, new ProductQualityCertificateHandler());
		handerMap.put(ProductTestEquipmentHandler.class, new ProductTestEquipmentHandler());
		handerMap.put(TradeExperienceHandler.class, new TradeExperienceHandler());
		handerMap.put(TradeIsTeamHandler.class, new TradeIsTeamHandler());
		handerMap.put(TradeTeamSizeHandler.class, new TradeTeamSizeHandler());
	}

	public Integer countScore() throws InstantiationException, IllegalAccessException {
		Integer totalScore = 0;
		for (Field field : this.getClass().getDeclaredFields()) {
			Calulate calulate = field.getAnnotation(Calulate.class);
			if (calulate == null)
				continue;
			if (!field.isAccessible())
				field.setAccessible(true);
			Object attribute = field.get(this);
			if (Countable.class.isAssignableFrom(field.getType())) {
				if (attribute == null)
					return 0;
				Countable countable = (Countable) attribute;
				return countable.countScore();
			} else {
				Class<? extends SVSHandler<?>>[] handleClasses = calulate.handleClass();
				for (Class<? extends SVSHandler<?>> handleClass : handleClasses) {
					SVSHandler<?> svsHandler = handerMap.get(handleClass);
					totalScore += svsHandler.getScore(field.get(this));
				}
			}
		}
		return totalScore;
	}
}
