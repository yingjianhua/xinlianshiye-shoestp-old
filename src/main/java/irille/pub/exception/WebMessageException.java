package irille.pub.exception;

import java.text.MessageFormat;

public class WebMessageException extends WebException {

  private static final long serialVersionUID = 1L;

  public WebMessageException(String message) {
    super(ReturnCode.failure, message);
  }

  public WebMessageException(ReturnCode code, String message) {
    super(code, message);
  }

  public WebMessageException(ReturnCode code, String message, Object... params) {
    super(code, new MessageFormat(message).format(params).toString());
  }

  public WebMessageException(IWebMessage message) {
    super(message.getCode(), message.getMsg());
  }

  public WebMessageException(IWebMessage message, Object... params) {
    super(message.getCode(), new MessageFormat(message.getMsg()).format(params).toString());
  }
}
