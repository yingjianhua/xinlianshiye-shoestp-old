package irille.view.SVS.BaseScore;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Countable {
	
	private static final Map<Class<?>, SVSHandler<?>> handerMap = new HashMap<>();
	
	static {
		handerMap.put(ExhibitionAttendedHandler.class, new ExhibitionAttendedHandler());
	}
	
	public Integer countScore() throws InstantiationException, IllegalAccessException {
		Integer totalScore = 0;
		for (Field field : this.getClass().getFields()) {
			Calulate calulate = field.getAnnotation(Calulate.class);
			if(calulate == null) 
				continue;
			SVSHandler<?> svsHandler = handerMap.get(calulate.handleClass());
			totalScore += svsHandler.getScore((Serializable)field.get(this));
		}
		return totalScore;
	}
}
