package irille.pub.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter
public class WebMessage implements IWebMessage{
	
	public static final WebMessage third_error = WebMessage.create(ReturnCode.tools_unknow, "服务器连接异常", "submit.faile");
	
	public static final WebMessage success = WebMessage.create(ReturnCode.success, "提交成功", "submit.success");
	
	public static final WebMessage valid_error = WebMessage.create(ReturnCode.valid_unknow, "{0}校验失败", "valid.error");
	
	public static final WebMessage timeout = WebMessage.create(ReturnCode.service_timeout, "登录超时,请重新登录", "timeout");
	
	public static final WebMessage submit_faile = WebMessage.create(ReturnCode.service_unknow, "提交失败", "submit.faile");
	
	private WebMessage(ReturnCode code) {
		this.code = code;
	}
	
	public static WebMessage create(ReturnCode code) {
		return new WebMessage(code);
	}
	
	public static WebMessage create(ReturnCode code, String message) {
		WebMessage webMessage = create(code);
		webMessage.msg = message;
		return webMessage;
	}
	
	public static WebMessage create(ReturnCode code, Object result) {
		WebMessage webMessage = create(code);
		webMessage.result = result;
		return webMessage;
	}
	
	public static WebMessage create(ReturnCode code, String message, String key) {
		WebMessage webMessage = create(code, message);
		webMessage.key = key;
		return webMessage;
	}
	
	private Object result;
	private String msg;
	@JsonIgnore
	private String key;
	@JsonIgnore
	private ReturnCode code;
	
	public ReturnCode getCode() {
		return code;
	}
	
	public String getKey() {
		return key;
	}
	
	@Override
	public Integer getRet() {
		return this.code.getCode();
	}
	@Override
	public String getMsg() {
		return this.msg;
	}
	@Override
	public Object getResult() {
		return result;
	}
	
}
