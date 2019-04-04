package irille.platform.pm;

import java.io.IOException;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.plat.service.pm.IVariableService;

import irille.action.MgtAction;

public class VariableAction extends MgtAction {

  private Integer type;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @Override
  public Class beanClazz() {
    return VariableAction.class;
  }

  @Inject private IVariableService variableService;

  public void list() throws IOException {
    write(variableService.loadByTempType(type));
  }
}
