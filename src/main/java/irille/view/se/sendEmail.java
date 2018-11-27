package irille.view.se;

import lombok.Getter;
import lombok.Setter;

public class sendEmail {
    @Getter
    private static final long serialVersionUID = 1L;
    @Setter
    @Getter
    private String receiver;
    @Setter
    @Getter
    private String subject;
    @Setter
    @Getter
    private String content;

}
