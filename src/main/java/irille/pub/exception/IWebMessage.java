package irille.pub.exception;

public interface IWebMessage {

	public Integer getRet();

	public String getMsg();

	public Object getResult();

	public ReturnCode getCode();
}