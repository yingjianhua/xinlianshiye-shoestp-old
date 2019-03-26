package irille.pub.i18n;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import irille.Filter.svr.SessionMsg;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltConfig;
import irille.shop.plt.PltConfig.Variable;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * <h5>国际化字段的JSON序列化器</h5>
 *
 * <li>在需要做国际化处理的字段上添加
 * <li>{@code @JsonSerialize(using=I18NFieldSerializer.class)}
 * <li>{@link I18NFieldSerializer}
 *
 * @author yingjianhua
 */
public class I18NFieldSerializer extends JsonSerializer<String> {
  private static final Logger logger = LoggerFactory.getLogger(I18NFieldSerializer.class);

  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    do {
      Language lang;
      try {
        HttpSession session = ServletActionContext.getRequest().getSession();
        SessionMsg sessionmsg = (SessionMsg) session.getAttribute(SessionMsg.session_key);
        lang = sessionmsg.getLang();
      } catch (Exception e) {
        gen.writeString(value);
        break;
      }
      if (value == null || "".equals(value.trim())) {
        gen.writeNull();
        break;
      }
      String content;
      try {
        JsonNode jsonNode = new ObjectMapper().readTree(value);
        content = jsonNode.path(lang.name()).asText();
        if ("".equals(content)) {
          content = jsonNode.path(PltConfig.getVariable(Variable.LanguageDefault)).asText();
          if ("".equals(content)) {
            gen.writeNull();
            break;
          }
        }
        gen.writeString(content);
        break;
      } catch (JsonParseException e) {
        logger.debug(value + ":不是json格式!");
        // if(AppConfig.dev)
        gen.writeString(value);
        //				else
        //					gen.writeNull();
        break;
      }
    } while (true);
  }
}
