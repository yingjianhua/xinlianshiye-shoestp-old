import irille.Dao.SVS.impl.SVSAuthenticationServiceImpl;
import irille.view.SVS.SVSAuthenticationView;

public class Test {
    @org.junit.Test
    public void test() throws Exception{
        SVSAuthenticationServiceImpl svsAuthenticationService=new SVSAuthenticationServiceImpl();
        SVSAuthenticationView s = svsAuthenticationService.getAutInfo(1);
        System.out.println(s.toString());
    }
    @org.junit.Test
    public void tes1() throws Exception{
        SVSAuthenticationServiceImpl svsAuthenticationService=new SVSAuthenticationServiceImpl();
        svsAuthenticationService.Authentication(1,1,null,1);
    }
}
