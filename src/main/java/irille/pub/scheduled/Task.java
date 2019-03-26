package irille.pub.scheduled;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
  private Class clazz;
  private Integer time;
  private String name;
  private String cron;
}
