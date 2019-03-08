package irille.platform.svs;
import com.google.inject.Inject;
import irille.Dao.SVS.SVSAuthenticationService;
import irille.Entity.SVS.SVSInfo;
import irille.action.ActionBase;
import lombok.Getter;
import lombok.Setter;




public class SVSAuthenticationAction extends ActionBase<SVSInfo> {
    @Inject
    private SVSAuthenticationService svsAuthenticationService;

    @Getter
    @Setter
    private Integer pkey;
    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private Integer grade;
    @Getter
    @Setter
    private String reasons;

    @Override
    public Class beanClazz() {
        return null;
    }
    
    /**
     * @Description  获取认证信息
     * @Date  2019/3/6 17:12
     * @Author KouHanyang
     **/
    public void getAutInfo()throws Exception{
        write(svsAuthenticationService.getAutInfo(pkey));
    }
    /**
     * @Description  进行认证
     * @Date  2019/3/6 17:13
     * @Author KouHanyang
     **/
    public void upd() throws  Exception {
        svsAuthenticationService.Authentication(status,  grade,  reasons,  pkey);
        write();
    }

}
