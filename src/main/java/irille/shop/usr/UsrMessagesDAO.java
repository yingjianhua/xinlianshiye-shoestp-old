package irille.shop.usr;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.*;
import irille.pub.svr.Env;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.view.Page;
import irille.view.usr.UsrMessageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            if(objs != null && objs.length > 0){
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
            sql.select(
                    UsrMessages.T.PKEY,
                    UsrMessages.T.TITLE
                    , UsrMessages.T.STATUS
            ).selectAs(UsrMessages.T.SEND_TIME, "sendTime").eqAutoAnd(UsrMessages.T.TYPE, UsrMessages.OMessageType.USER_MESSAGE.getLine().getKey());
            if (type == 1) {
                sql.eqAutoAnd(UsrMessages.T.SENDUSER, pubId);
            } else {
                sql.eqAutoAnd(UsrMessages.T.RECIVER, pubId);
            }
            List result = new ArrayList();
            sql.castListMap(Bean.list(sql.buildSql(), sql.getParms())).stream().forEach(map -> {
                result.add(SetBeans.set(map, UsrMessageView.class));
            });
            Page page = new Page<UsrMessageView>(result, iduPage.getStart(), iduPage.getLimit(), sql.castInt(Bean.queryOneRowIsNull(sql.buildCountSql(), sql.getParms())));
            page.setCurrentPage(iduPage.getStart());
            page.setLimit(8);
            return page;
        }


        /***
         * 这里start指代前端当前页面
         * limtit  页面显示的条数
         * @param iduPage
         * @return
         */
        public Page<UsrMessageView> sysMessageList(IduPage iduPage) {
            FormaterSql sql = FormaterSql.build(this);
            sql.page(iduPage);
            sql.select(
                    UsrMessages.T.PKEY,
                    UsrMessages.T.TITLE
            ).selectAs(UsrMessages.T.SEND_TIME, "sendTime").eqAutoAnd(UsrMessages.T.TYPE, UsrMessages.OMessageType.SYS_MESSAGE.getLine().getKey());
            List result = new ArrayList();
            sql.castListMap(Bean.list(sql.buildSql(), sql.getParms())).stream().forEach(map -> {
                result.add(SetBeans.set(map, UsrMessageView.class));
            });
            Page page = new Page<UsrMessageView>(result, iduPage.getStart(), iduPage.getLimit(), sql.castInt(Bean.queryOneRowIsNull(sql.buildCountSql(), sql.getParms())));
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
                Bean.executeUpdate(sql.buildSql(), usrMessages.getReadTime(), UsrMessages.OMessageStaus.READ.getLine().getKey(), id);
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
            if (getB().getReciver() != -1)
                checkUser(getB().getReciver());
            super.valid();
        }
    }

    /***
     * 系统消息
     */
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

    /***
     * 后台回复消息(非商家
     */
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
                PropertyUtils.copyProperties(dbBean, getB(),
                        UsrMessages.T.REPLY,
                        UsrMessages.T.REPLY_TIME,
                        UsrMessages.T.READ_TIME,
                        UsrMessages.T.STATUS
                );
                setB(dbBean);
                super.before();
            } else {
                throw LOG.err("", "不能回复自己发的信件");
            }
        }
    }


    public static class Del extends IduDel<Del, UsrMessages> {

    }
}
