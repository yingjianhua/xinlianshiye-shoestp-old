package irille.view.SVS.BaseScore;

public class ProductISOCertificateHandler implements SVSHandler<String> {

	@Override
	public int getScore(Object v) {
		String value=(String)v;
		if(value!=null&&"".equals(value))
		return 5;
		else
		return 0;
	}

}
