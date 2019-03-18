package irille.shop.usr;

import java.util.ArrayList;
import java.util.List;

import irille.pub.Log;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
import irille.shop.usr.UsrConsultMessage.T;
import irille.view.usr.ConsultMessageView;
import irille.view.usr.ConsultRelationView;

public class UsrConsultMessageDAO {
  public static final Log LOG = new Log(UsrConsultMessageDAO.class);

  /**
   * 获取该询盘中与每个供应商的所有留言记录
   *
   * @param pkey
   * @return
   * @author yingjianhua
   */
  public static List<ConsultRelationView> listViewByConsult(Integer pkey) {
    List<UsrConsultRelation> relations = UsrConsultRelationDAO.listByConsult(pkey);
    List<ConsultRelationView> views = new ArrayList<>();
    for (UsrConsultRelation relation : relations) {
      List<UsrConsultMessage> messages = listByRelation(relation.getPkey());
      ConsultRelationView rv = new ConsultRelationView();
      rv.setId(relation.getPkey());
      rv.setSupplierName(relation.gtSupplier().getName());
      rv.setHaveNewMsg(relation.gtSToPNewMsg());
      rv.setMsgs(new ArrayList<>());
      for (UsrConsultMessage message : messages) {
        ConsultMessageView mv = new ConsultMessageView();
        mv.setContent(message.getContent());
        mv.setSendTime(message.getSendTime());
        mv.setP2S(message.gtP2S());
        rv.getMsgs().add(mv);
      }
      views.add(rv);
      if (relation.gtSToPNewMsg()) {
        relation.stSToPNewMsg(false);
        relation.upd();
      }
    }
    return views;
  }

  public static List<UsrConsultMessage> listByRelation(Integer relation) {
    return BeanBase.list(UsrConsultMessage.class, T.RELATION + "=?", false, relation);
  }

  /**
   * 留言
   *
   * @author yingjianhua
   */
  public static class Send extends IduIns<Send, UsrConsultMessage> {
    private boolean p2s;
    private int relationId;
    private int sender;
    private String content;
    UsrConsultRelation relation;

    public Send(boolean p2s, int relation, int sender, String content) {
      this.p2s = p2s;
      this.relationId = relation;
      this.sender = sender;
      this.content = content;
    }

    @Override
    public void before() {
      relation = UsrConsultRelation.load(UsrConsultRelation.class, relationId);
      if (p2s) { // 采购商给供应商留言
        if (!relation.gtConsult().getPurchase().equals(sender)) {
          throw LOG.err("senderErr", "wrong sender");
        }
      } else {
        if (!relation.getSupplier().equals(sender)) {
          throw LOG.err("senderErr", "wrong sender");
        }
      }
      UsrConsultMessage message = new UsrConsultMessage();
      message.setRelation(relationId);
      message.stP2S(p2s);
      message.setContent(content);
      message.setSendTime(Env.getTranBeginTime());
      message.setRowVersion((short) 0);
      setB(message);
    }

    @Override
    public void after() {
      if (p2s) relation.stPToSNewMsg(true);
      else relation.stSToPNewMsg(true);
      relation.upd();
      UsrConsult consult = relation.gtConsult();
      consult.stHaveNewMsg(true);
      consult.upd();
    }
  }

  public static void deleteByConsult(Integer consult) {
    //		delete from usr_consult_message where relation in (select pkey from usr_consult_relation
    // where consult=?)|params:1
    SQL subSql =
        new SQL() {
          {
            SELECT(UsrConsultRelation.T.PKEY);
            FROM(UsrConsultRelation.class);
            WHERE(UsrConsultRelation.T.CONSULT, "=?");
          }
        };
    SQL sql =
        new SQL() {
          {
            DELETE_FROM(UsrConsultMessage.class);
            WHERE(UsrConsultMessage.T.RELATION, "in (" + subSql.toString() + ")", consult);
          }
        };
    Query.sql(sql).executeUpdate();
  }
}
