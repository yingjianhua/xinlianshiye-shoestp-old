package irille.platform.o2o;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.Entity.O2O.O2oRegistration;
import irille.action.ActionBase;
import irille.shop.o2o.O2O_RegistrationDao;
import irille.view.ResultView;
import lombok.Data;
import org.apache.struts2.ServletActionContext;

@Data
public class O2oRegistrationAction extends ActionBase<O2oRegistration> {

  private Integer id;
  private Integer gender;
  private Integer marketing;
  private Integer buyerType;
  private Integer exhibitionCountry;
  @Inject private O2O_RegistrationDao o2O_registrationDao;

  @Override
  public Class beanClazz() {
    return O2oRegistration.class;
  }

  public void list() throws IOException {
    write(
        o2O_registrationDao.list(
            gender, marketing, buyerType, exhibitionCountry, getStart(), getLimit()));
  }

  public void info() throws IOException {
    write(o2O_registrationDao.info(id));
  }

  public void getData() throws IOException {
    ObjectMapper oMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("application/json;charset=utf-8");
    ResultView result = new ResultView();
    result.setRet(1);
    result.setMsg(null);
    result.setResult(o2O_registrationDao.getData());
    response.getWriter().print(oMapper.writeValueAsString(result));
  }
}
