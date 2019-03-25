package irille.platform.svs;

import com.google.inject.Inject;

import irille.Dao.SVS.SVSAuthenticationService;
import irille.Entity.SVS.SVSInfo;
import irille.action.ActionBase;
import irille.pub.bean.BeanBase;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrSupplier;
import lombok.Getter;
import lombok.Setter;

public class SVSAuthenticationAction extends ActionBase<SVSInfo> {
    @Inject
    private SVSAuthenticationService svsAuthenticationService;
    @Getter
    @Setter
    private Integer supplierId;

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
     * @Description 获取认证信息 @Date 2019/3/6 17:12 @Author KouHanyang
     */
    public void getAutInfo() throws Exception {
        if (null == pkey) {
            throw new WebMessageException(ReturnCode.failure, "获取认证信息的ID不能为空");
        }
        write(svsAuthenticationService.getAutInfo(pkey));
    }

    /**
     * @Description 进行认证 @Date 2019/3/6 17:13 @Author KouHanyang
     */
    public void update() throws Exception {
        if (supplierId == null) {
            throw new WebMessageException(ReturnCode.failure, "用户编号不能为空");
        }
        UsrSupplier supplier = BeanBase.load(UsrSupplier.class, supplierId);
        if (null != pkey) {
            svsAuthenticationService.Authentication(supplier, status, grade, reasons, pkey);
        } else {
            throw new WebMessageException(ReturnCode.failure, "认证的ID不能为空");
        }
        write();
    }
}
