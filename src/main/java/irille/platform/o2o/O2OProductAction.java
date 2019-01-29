package irille.platform.o2o;

import java.io.IOException;

import javax.inject.Inject;

import irille.Entity.O2O.O2O_Product;
import irille.Service.Manage.O2O.O2OProductService;
import irille.action.ActionBase;
import irille.view.O2O.O2OProductView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O2OProductAction extends ActionBase<O2O_Product> implements IO2OProductAction {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private O2OProductService o2OProductService;
	
	private O2OProductView product;
	
    @Override
    public Class<O2O_Product> beanClazz() {
        return O2O_Product.class;
    }
    
    @Override
	public void list() throws IOException  {
    	write(o2OProductService.list(getStart(), getLimit(), product));
	}

	@Override
	public void approve() throws IOException  {
		write(o2OProductService.approve(product));;
	}

}
