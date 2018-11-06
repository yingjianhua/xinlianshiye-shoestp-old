package irille.shop.pdt;

import irille.homeAction.HomeAction;
import irille.homeAction.pdt.dto.PdtCommentView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduPage;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.IEnumFld;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert.TimestampToStringConvert;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtComment.T;
import irille.shop.usr.UsrMemberLevel;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;
import irille.view.pdt.CommentView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

public class PdtCommentDAO {
    public static final Log LOG = new Log(PdtCommentDAO.class);

    static {
        SetBeans.getTypeSafe().addConvertFactorys(new TimestampToStringConvert());
    }
    

    /**
     * 分页查询我的评论列表
     * @param purchase
     * @param start
     * @param limit
     */
    public static Page<CommentView> pageByPurchase(Integer purchase, Integer start, Integer limit) {
    	BeanQuery<PdtComment> q = Query.SELECT(PdtComment.class).WHERE(T.USERS_PKEY, "=?", purchase);
    	Integer totalCount = q.queryCount();
    	List<PdtComment> list = q.limit(start, limit).queryList();
    	List<CommentView> views = new ArrayList<>();
    	for(PdtComment bean:list) {
    		CommentView view = new CommentView();
    		view.setId(bean.getProduct());
    		view.setImage(bean.getImages());
    		view.setStar(bean.getProductSatisfaction());
    		view.setContent(bean.getComment());
    		view.setReply(bean.getReply());
    		view.setTime(bean.getCommentTime());
			PdtProduct pdt = translateUtil.getAutoTranslate(bean.gtProduct(), HomeAction.curLanguage());
			view.setName(pdt.getName());
    		views.add(view);
    	}
    	return new Page<CommentView>(views, start, limit, totalCount);
    }

    public static class pageSelect extends IduOther<pageSelect, PdtComment> {


        /**
         * @Description:根据商品id 查询评论列表
         * @author lijie@shoestp.cn
         * @date 2018/8/16 9:22
         */
        public List<PdtCommentView> getCommentListByProId(IduPage page, int id) {
            PdtComment pdtComment = new PdtComment();
            pdtComment.setProduct(id);
            return getCommentList(page, pdtComment);
        }

        public long getCommentCountByProId(long id) {
            FormaterSql sql = FormaterSql.build();
            sql.from(PdtComment.T.PKEY).eqAutoAnd(PdtComment.T.PRODUCT, id, x -> {
                if (x.longValue() > 0) {
                    return true;
                }
                return false;
            });
            return sql.castLong(BeanBase.queryOneRowIsNull(sql.buildCountSql(), sql.getParms()));
        }

        private List<PdtCommentView> getCommentList(IduPage page, PdtComment pdtComment) {
            FormaterSql sql = FormaterSql.build(this);
            sql
                    .select(
                            UsrPurchase.T.NAME
                    )
                    .selectAs(UsrMemberLevel.T.NAME, "usrMemberLevelName")
                    .selectAs(UsrMemberLevel.T.ICON, "usrMemberLevelIco")
                    .select(PdtComment.T.values()).from(PdtComment.T.PKEY)
                    .leftjoin(UsrPurchase.T.PKEY, PdtComment.T.USERS_PKEY)
                    .leftjoin(UsrMemberLevel.T.PKEY, UsrPurchase.T.MEMBER_LEVEL);
            List parmList = new ArrayList();
            sql.select(PdtComment.T.PKEY);
            if (pdtComment.getProduct() > 0) {
                sql.eqAutoAnd(PdtComment.T.PRODUCT);
                parmList.add(pdtComment.getProduct());
            }
            if (pdtComment.getUsersPkey() != null && pdtComment.getUsersPkey() > 0) {
                sql.eqAutoAnd(PdtComment.T.USERS_PKEY);
                parmList.add(pdtComment.getUsersPkey());
            }

            sql.page(page);
            List result = new ArrayList();
            List list = BeanBase.list(sql.buildSql(), sql.getParms(parmList));
            List<Map> l = sql.castListMap(list);
            for (Map map : l) {
                PdtCommentView pdtCommentView = SetBeans.set(map, PdtCommentView.class);
                result.add(pdtCommentView);
                System.out.println(pdtCommentView);
            }
            return result;
        }

        public boolean hasComment(Integer id, Integer userid) {
            FormaterSql sql = FormaterSql.build();
            sql.from(PdtComment.T.PKEY)
                    .eqAutoAnd(PdtComment.T.PRODUCT, id, x -> {
                        if (x.intValue() > 0) {
                            return true;
                        }
                        return false;
                    })
                    .eqAutoAnd(PdtComment.T.USERS_PKEY, userid, t -> {
                        if (t.intValue() > 0) {
                            return true;
                        }
                        return false;
                    })
            ;
            return sql.castLong(BeanBase.queryOneRowIsNull(sql.buildSql(), sql.getParms())) > 0 ? true : false;
        }
    }

    //用户对订单进行评论
    public static class Ins extends IduIns<Ins, PdtComment> {

        @Override
        public void before() {
            //判断订单状态是否为完成,是则进行以下操作
            //通过订单PKEY获取到产品PKEY并判断产品是否存在
            //为前端获取到的评论对象设置产品PKEY/订单PKEY/用户PKEY
            getB().setUsefulNumber(0);
            getB().setUnusefulNumber(0);
            //设置评论对象的评论时间
            getB().setCommentTime(Env.getSystemTime());
        }


    }

    //用户对订单评论进行修改
    public static class Upd extends IduUpd<Upd, PdtComment> {
        @Override
        public void before() {
            getB().setCommentTime(Env.getSystemTime());
            //将前端的评论对象和数据库中相应的评论对象进行比对
            PdtComment model = loadThisBeanAndLock();
            //修改修改过评论内容及评论图片
            PropertyUtils.copyPropertiesWithout(model, getB(), PdtComment.T.PRODUCT, PdtComment.T.USERS_PKEY);
            setB(model);
        }

        public int replay(PdtComment pdtComment, int supId) {
            FormaterSql sql = FormaterSql.build(true);
            if (sql.castLong(BeanBase.queryOneRow(sql.from(PdtProduct.T.SUPPLIER).eq(PdtProduct.T.SUPPLIER).buildCountSql(), 1)) > 0) {
                sql.clean().select(PdtComment.T.ROW_VERSION).eq(PdtComment.T.PKEY);
                Map map = sql.castMap(BeanBase.queryOneRow(sql.buildSql(), pdtComment.getPkey()));
                int row_version = (int) map.get(PdtComment.T.ROW_VERSION.getFld().getCodeSqlField());

                sql.clean().update(PdtComment.T.REPLY, PdtComment.T.ROW_VERSION)
                        .eqAutoAnd(PdtComment.T.ROW_VERSION)
                        .eqAutoAnd(PdtComment.T.PKEY).and()
                        .isNull(PdtComment.T.REPLY);

                return BeanBase.executeUpdate(sql.buildSql(), sql.getParms(Arrays.asList(pdtComment.getReply(), row_version + 1, row_version, pdtComment.getPkey())));
            }
            return -1;
        }

        public int unuseful(int pkey, int type) {
            if (type > -1 && type < 2) {
                FormaterSql sql = FormaterSql.build(true);
                IEnumFld iEnumFld = type == 1 ? PdtComment.T.USEFUL_NUMBER : PdtComment.T.UNUSEFUL_NUMBER;
                sql.select(iEnumFld, PdtComment.T.ROW_VERSION).eq(PdtComment.T.PKEY);
                Map map = sql.castMap(BeanBase.queryOneRow(sql.buildSql(), pkey));
                int number = (int) map.get(iEnumFld.getFld().getCodeSqlField());
                int row_version = (int) map.get(PdtComment.T.ROW_VERSION.getFld().getCodeSqlField());
                sql.clean().update(iEnumFld, PdtComment.T.ROW_VERSION).eqAutoAnd(iEnumFld).eqAutoAnd(PdtComment.T.ROW_VERSION).eqAutoAnd(PdtComment.T.PKEY);
                List parmList = Arrays.asList(number + 1, row_version + 1, number, row_version, pkey);
                return BeanBase.executeUpdate(sql.buildSql(), sql.getParms(parmList));
            }
            return -2;
        }


    }

    //后台对评论进行删除
    //before

}
