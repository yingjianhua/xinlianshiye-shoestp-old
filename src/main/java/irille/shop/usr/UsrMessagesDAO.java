package irille.shop.usr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import irille.platform.usr.View.MessagesView;
import irille.platform.usr.View.RecipientView;
import irille.platform.usr.View.SenderAndRecipientView;
import irille.platform.usr.View.SenderView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.*;
import irille.pub.svr.Env;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.view.Page;
import irille.view.usr.UsrMessageView;

public class UsrMessagesDAO {
  public static final Log LOG = new Log(UsrMessagesDAO.class);
  private static boolean Debug = false;

  public static class select extends IduOther<select, UsrMessages> {
    public String getName(int id) {
      if (id == -1) return "系统管理员";
      if (id == -2) return "全体会员";
      FormaterSql sql = FormaterSql.build(Debug);
      sql.select(UsrPurchase.T.EMAIL).eq(UsrPurchase.T.PKEY);
      Object[] objs = BeanBase.queryOneRowIsNull(sql.buildSql(), id);
      if (objs != null && objs.length > 0) {
        String usrPurchase = String.valueOf(objs[0]);
        if (usrPurchase != null) {
          return usrPurchase;
        }
      }
      return "未能查找到该用户";
    }

    public Page<UsrMessageView> getMyInboxList(IduPage iduPage, int pubId, int type) {
      FormaterSql sql = FormaterSql.build(this);
      sql.page(iduPage);
      sql.select(UsrMessages.T.PKEY, UsrMessages.T.TITLE, UsrMessages.T.STATUS)
          .selectAs(UsrMessages.T.SEND_TIME, "sendTime")
          .eqAutoAnd(UsrMessages.T.TYPE, UsrMessages.OMessageType.USER_MESSAGE.getLine().getKey());
      if (type == 1) {
        sql.eqAutoAnd(UsrMessages.T.SENDUSER, pubId);
      } else {
        sql.eqAutoAnd(UsrMessages.T.RECIVER, pubId);
      }
      List result = new ArrayList();
      sql.castListMap(Bean.list(sql.buildSql(), sql.getParms())).stream()
          .forEach(
              map -> {
                result.add(SetBeans.set(map, UsrMessageView.class));
              });
      Page page =
          new Page<UsrMessageView>(
              result,
              iduPage.getStart(),
              iduPage.getLimit(),
              sql.castInt(Bean.queryOneRowIsNull(sql.buildCountSql(), sql.getParms())));
      page.setCurrentPage(iduPage.getStart());
      page.setLimit(8);
      return page;
    }

    /**
     * * 这里start指代前端当前页面 limtit 页面显示的条数
     *
     * @param iduPage
     * @return
     */
    public Page<UsrMessageView> sysMessageList(IduPage iduPage) {
      FormaterSql sql = FormaterSql.build(this);
      sql.page(iduPage);
      sql.select(UsrMessages.T.PKEY, UsrMessages.T.TITLE)
          .selectAs(UsrMessages.T.SEND_TIME, "sendTime")
          .eqAutoAnd(UsrMessages.T.TYPE, UsrMessages.OMessageType.SYS_MESSAGE.getLine().getKey());
      List result = new ArrayList();
      sql.castListMap(Bean.list(sql.buildSql(), sql.getParms())).stream()
          .forEach(
              map -> {
                result.add(SetBeans.set(map, UsrMessageView.class));
              });
      Page page =
          new Page<UsrMessageView>(
              result,
              iduPage.getStart(),
              iduPage.getLimit(),
              sql.castInt(Bean.queryOneRowIsNull(sql.buildCountSql(), sql.getParms())));
      page.setCurrentPage(iduPage.getStart());
      page.setLimit(8);
      return page;
    }

    public UsrMessageView getMessageById(int id) {
      UsrMessages usrMessages = UsrMessages.get(UsrMessages.class, id);
      if (usrMessages.getReadTime() == null) {
        usrMessages.setReadTime(new Date());
        FormaterSql sql = FormaterSql.build(this);
        sql.update(UsrMessages.T.READ_TIME, UsrMessages.T.STATUS).eq(UsrMessages.T.PKEY);
        Bean.executeUpdate(
            sql.buildSql(),
            usrMessages.getReadTime(),
            UsrMessages.OMessageStaus.READ.getLine().getKey(),
            id);
      }
      return SetBeans.set(usrMessages, UsrMessageView.class);
    }
  }

  public static class Ins extends IduIns<Ins, UsrMessages> {
    @Override
    public void before() {
      getB().setSendTime(Env.getSystemTime());
      //            getB().setReciver(-1);
      getB().setStatus(UsrMessages.OMessageStaus.UNREAD.getLine().getKey());
      getB().setType(UsrMessages.OMessageType.USER_MESSAGE.getLine().getKey());
      super.before();
    }

    @Override
    public void valid() {
      FormValid().validNotEmpty(UsrMessages.T.TITLE);
      if (getB().getReciver() != -1) checkUser(getB().getReciver());
      super.valid();
    }
  }

  /** * 系统消息 */
  public static class Sys_Ins extends IduIns<Sys_Ins, UsrMessages> {
    @Override
    public void before() {
      getB().setSendTime(Env.getSystemTime());
      getB().setSenduser(-1);
      getB().setReciver(-1);
      getB().setStatus(UsrMessages.OMessageStaus.UNREAD.getLine().getKey());
      getB().setType(UsrMessages.OMessageType.SYS_MESSAGE.getLine().getKey());
      super.before();
    }

    @Override
    public void valid() {
      FormValid().validNotEmpty(UsrMessages.T.TITLE);
      super.valid();
    }
  }

  private static void checkUser(int id) {
    if (BeanBase.chk(UsrPurchase.class, id) == null) {
      throw LOG.err("查不到该用户", "查不到该用户");
    }
  }

  /** * 后台回复消息(非商家 */
  public static class Upd extends IduUpd<Upd, UsrMessages> {

    @Override
    public void before() {
      FormValid().validNotEmpty(UsrMessages.T.TITLE);
      UsrMessages dbBean = loadThisBeanAndLock();
      if (dbBean.getSenduser() != -1) {
        if (dbBean.getReply() != null) {
          throw LOG.err("", "已回复");
        }
        getB().setReadTime(Env.getSystemTime());
        getB().setReplyTime(Env.getSystemTime());
        getB().setStatus(UsrMessages.OMessageStaus.READ.getLine().getKey());
        PropertyUtils.copyProperties(
            dbBean,
            getB(),
            UsrMessages.T.REPLY,
            UsrMessages.T.REPLY_TIME,
            UsrMessages.T.READ_TIME,
            UsrMessages.T.STATUS);
        setB(dbBean);
        super.before();
      } else {
        throw LOG.err("", "不能回复自己发的信件");
      }
    }
  }

  public static class Del extends IduDel<Del, UsrMessages> {}

  /** ———————————————————分割(新平台端)——————————————————————— */
  public static Page getMessagesList(
      Integer sender, Integer recipient, Integer start, Integer limit) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(UsrMessages.class).FROM(UsrMessages.class);
            if (sender != null) {
              WHERE(UsrMessages.T.SENDUSER, "=?", sender);
            }
            if (recipient != null) {
              WHERE(UsrMessages.T.RECIVER, "=?", recipient);
            }
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<MessagesView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new MessagesView() {
                      {
                        setId(
                            Integer.valueOf(
                                String.valueOf(
                                    o.get(UsrMessages.T.PKEY.getFld().getCodeSqlField()))));
                        if (Integer.valueOf(
                                String.valueOf(
                                    o.get(UsrMessages.T.SENDUSER.getFld().getCodeSqlField())))
                            != -1) {
                          setSender(
                              BeanBase.load(
                                      UsrPurchase.class,
                                      Integer.valueOf(
                                          String.valueOf(
                                              o.get(
                                                  UsrMessages.T
                                                      .SENDUSER
                                                      .getFld()
                                                      .getCodeSqlField()))))
                                  .getLoginName());
                        } else {
                          setSender("系统管理员");
                        }
                        if (Integer.valueOf(
                                String.valueOf(
                                    o.get(UsrMessages.T.RECIVER.getFld().getCodeSqlField())))
                            != -1) {
                          setRecipient(
                              BeanBase.load(
                                      UsrPurchase.class,
                                      Integer.valueOf(
                                          String.valueOf(
                                              o.get(
                                                  UsrMessages.T
                                                      .RECIVER
                                                      .getFld()
                                                      .getCodeSqlField()))))
                                  .getLoginName());
                        } else {
                          setRecipient("系统管理员");
                        }
                        setTitle(
                            String.valueOf(o.get(UsrMessages.T.TITLE.getFld().getCodeSqlField())));
                        setContent(
                            String.valueOf(
                                o.get(UsrMessages.T.CONTENT.getFld().getCodeSqlField())));
                        if (String.valueOf(o.get(UsrMessages.T.REPLY.getFld().getCodeSqlField()))
                            != "null") {
                          setReply(
                              String.valueOf(
                                  o.get(UsrMessages.T.REPLY.getFld().getCodeSqlField())));
                        } else {
                          setReply("");
                        }
                        setStatus(
                            Integer.valueOf(
                                String.valueOf(
                                    o.get(UsrMessages.T.STATUS.getFld().getCodeSqlField()))));
                        setType(
                            Integer.valueOf(
                                String.valueOf(
                                    o.get(UsrMessages.T.TYPE.getFld().getCodeSqlField()))));
                        setSendTime(
                            (Date) (o.get(UsrMessages.T.SEND_TIME.getFld().getCodeSqlField())));
                        setReadTime(
                            (Date) (o.get(UsrMessages.T.READ_TIME.getFld().getCodeSqlField())));
                        setReplyTime(
                            (Date) (o.get(UsrMessages.T.REPLY_TIME.getFld().getCodeSqlField())));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  public static SenderAndRecipientView getSenderAndRecipients() {
    SenderAndRecipientView view = new SenderAndRecipientView();
    SQL sender =
        new SQL() {
          {
            SELECT(UsrMessages.T.SENDUSER).FROM(UsrMessages.class).GROUP_BY(UsrMessages.T.SENDUSER);
          }
        };
    SQL recipients =
        new SQL() {
          {
            SELECT(UsrMessages.T.RECIVER).FROM(UsrMessages.class).GROUP_BY(UsrMessages.T.RECIVER);
          }
        };
    List<SenderView> senderViews =
        Query.sql(sender).queryMaps().stream()
            .map(
                o ->
                    new SenderView() {
                      {
                        setSenderid(
                            (Integer) o.get(UsrMessages.T.SENDUSER.getFld().getCodeSqlField()));
                        if ((Integer) o.get(UsrMessages.T.SENDUSER.getFld().getCodeSqlField())
                            != -1) {
                          setSender(
                              BeanBase.load(
                                      UsrPurchase.class,
                                      Integer.valueOf(
                                          String.valueOf(
                                              o.get(
                                                  UsrMessages.T
                                                      .SENDUSER
                                                      .getFld()
                                                      .getCodeSqlField()))))
                                  .getLoginName());
                        } else {
                          setSender("系统管理员");
                        }
                      }
                    })
            .collect(Collectors.toList());

    List<RecipientView> recipientViews =
        Query.sql(recipients).queryMaps().stream()
            .map(
                o ->
                    new RecipientView() {
                      {
                        setRecipientid(
                            (Integer) o.get(UsrMessages.T.RECIVER.getFld().getCodeSqlField()));
                        if ((Integer) o.get(UsrMessages.T.RECIVER.getFld().getCodeSqlField())
                            != -1) {
                          setRecipient(
                              BeanBase.load(
                                      UsrPurchase.class,
                                      Integer.valueOf(
                                          String.valueOf(
                                              o.get(
                                                  UsrMessages.T
                                                      .RECIVER
                                                      .getFld()
                                                      .getCodeSqlField()))))
                                  .getLoginName());
                        } else {
                          setRecipient("系统管理员");
                        }
                      }
                    })
            .collect(Collectors.toList());
    view.setSenders(senderViews);
    view.setRecipients(recipientViews);
    return view;
  }

  public static class deletes extends IduDel<deletes, UsrMessages> {
    @Override
    public void before() {
      super.before();
    }
  }

  public static Page getPurchases(Integer start, Integer limit) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    List<RecipientView> recipientViews = new ArrayList<>();
    SQL sql =
        new SQL() {
          {
            SELECT(UsrPurchase.class).FROM(UsrPurchase.class);
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<UsrPurchase> list = Query.sql(sql.LIMIT(start, limit)).queryList(UsrPurchase.class);
    for (UsrPurchase usrPurchase : list) {
      RecipientView recipientView = new RecipientView();
      recipientView.setRecipientid(usrPurchase.getPkey());
      recipientView.setRecipient(usrPurchase.getLoginName());
      recipientViews.add(recipientView);
    }
    return new Page(recipientViews, start, limit, count);
  }

  public static void ins(Integer reciver, String title, String content) {
    UsrMessages usrMessages = new UsrMessages();
    usrMessages.setSenduser(-1);
    if (reciver != null) {
      usrMessages.setReciver(reciver);
    } else {
      usrMessages.setReciver(-1);
    }
    usrMessages.setTitle(title);
    usrMessages.setContent(content);
    usrMessages.setStatus(UsrMessages.OMessageStaus.UNREAD.getLine().getKey());
    usrMessages.setType(UsrMessages.OMessageType.USER_MESSAGE.getLine().getKey());
    usrMessages.setSendTime(Env.getTranBeginTime());
    usrMessages.ins();
  }

  public static class reply extends IduOther<reply, UsrMessages> {
    @Override
    public void before() {
      getB().setReadTime(Env.getTranBeginTime());
    }

    @Override
    public void valid() {}

    @Override
    public void run() {
      UsrMessages dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), UsrMessages.T.REPLY, UsrMessages.T.READ_TIME);
      dbBean.upd();
      super.run();
    }
  }

  /** ———————————————————分割(新平台端)END——————————————————————— */
}
