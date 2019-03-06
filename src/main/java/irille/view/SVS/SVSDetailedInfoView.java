package irille.view.SVS;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.shop.usr.UsrSupplier;
import irille.view.BaseView;
import irille.view.SVS.SVSInfoView.exhibitionAttended;
import irille.view.SVS.SVSInfoView.partner;
import irille.view.SVS.SVSInfoView.productQuality;
import irille.view.SVS.SVSInfoView.productionCapacity;
import irille.view.SVS.SVSInfoView.realFactory;
import irille.view.SVS.SVSInfoView.research;
import irille.view.SVS.SVSInfoView.tradeTeam;
import lombok.Data;

@Data
public class SVSDetailedInfoView implements BaseView {

	private Integer id;

	private String factory;

	private Byte grade;

	private Integer baseScore;

	private String research;

	private String team;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date applicationTime;

	private Byte status;

	private UsrSupplier supplier;

	private String exhibition;

	private Integer dynamicScore;

	private String quality;

	private String capacity;
	
	private String partner;

}
