package irille.view.SVS.BaseScore;

public class ResearchIsTeamHandler implements SVSHandler<Integer>{
	
	@Override
	public int getScore(Object v) {
		Integer value = (Integer)v;
		if(value==1)
			return 4;
		else
			return 0;
	}

}
