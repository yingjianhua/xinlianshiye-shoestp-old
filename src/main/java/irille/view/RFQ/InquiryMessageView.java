package irille.view.RFQ;

import java.util.Date;

import irille.view.BaseView;
import lombok.Data;

@Data
public class InquiryMessageView implements BaseView {
	private Integer pkey;
	private String title;
	private Integer read;
	private Date time;
	
}
