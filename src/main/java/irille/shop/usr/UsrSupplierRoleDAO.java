package irille.shop.usr;

import irille.core.sys.SysMenu;
import irille.core.sys.SysMenuAct;
import irille.core.sys.SysMenuDAO;
import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.sellerAction.SellerAction;
import irille.shop.usr.UsrSupplierRole.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsrSupplierRoleDAO {
	public static final Log LOG = new Log(UsrSupplierRoleDAO.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		isDefault("默认商家角色不能删除"),
		isReference("已经有商家使用该角色，不能删除");
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	
	private static Map<Integer, Set<String>> role_all_act;
	public static final Set<String> ALL_ACT;
	
	static {
		ALL_ACT = initAllAct();
		role_all_act = initRoleAllAct();
	}
	
	private static Set<String> initAllAct() {
		Set<String> allAct = new LinkedHashSet<String>();
		for(UsrAccess line:UsrAccessDAO.ACCESS) {
			allAct.add(line.getPkey());
		}
		return allAct;
	}
	public static Map<Integer, Set<String>> getRoleAllAct() {
		if(role_all_act==null) {
			role_all_act = initRoleAllAct();
		}
		return role_all_act;
	}
	public static void clearRoleAllAct() {
		role_all_act = null;
	}
	
	private static Map<Integer, Set<String>> initRoleAllAct() {
		List<UsrSupplierRole> list = BeanBase.list(UsrSupplierRole.class, null, false);
		Map<Integer, Set<String>> roleAllAct = new HashMap<Integer, Set<String>>();
		for(UsrSupplierRole role:list) {
			Set<String> acts = new HashSet<String>();
			if(role.getActs()!=null && !role.getActs().trim().equals("")) {
				for(String act:role.getActs().split(",")) {
					acts.add(act);
				}
			}
			roleAllAct.put(role.getPkey(), acts);
		}
		return roleAllAct;
	}
	
	public static class  InsInit extends IduOther<InsInit, UsrSupplierRole> {
		private Map<String, String> map;

		public Map<String, String> getMap() {
			return map;
		}

		public void setMap(Map<String, String> map) {
			this.map = map;
		}

		@Override
		public void run() {
			super.run();
			boolean defaultFlag = true;
			for(String code: map.keySet()) {
				UsrSupplierRole b = new UsrSupplierRole().init();
				b.setCode(code);
				b.setName(map.get(code));
				if(defaultFlag) {
					b.stIsDefault(true);
					defaultFlag = false;
				}
				b.stUpdatedBy(getUser());
				b.setUpdatedTime(Env.getTranBeginTime());
				b.ins();
			}
		}
	}
	
	public UsrSupplierRole getDefaultRole() {
		List<UsrSupplierRole> list = UsrSupplierRole.list(UsrSupplierRole.class,UsrSupplierRole.T.IS_DEFAULT.getFld().getCodeSqlField()+"=?", false, BeanBase.booleanToByte(true));
		if(list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	/**
	 * 获取默认采购商角色
	 * @author yingjianhua
	 */
	public static UsrSupplierRole getDefault() {
		return Query.SELECT(UsrSupplierRole.class).WHERE(T.IS_DEFAULT, "=?", BeanBase.booleanToByte(true)).query();
	}
	
	/**
	 * 将该供应商角色设置为默认，其它角色设置为非默认
	 * @param pkey
	 */
	public static class SetDefault extends IduOther<SetDefault, UsrSupplierRole> {
		@Override
		public void run() {
			super.run();
			List<UsrSupplierRole> list = UsrSupplierRole.list(UsrSupplierRole.class, null, false);
			for(UsrSupplierRole line:list) {
				if(line.getPkey().equals(getB().getPkey()))
					line.stIsDefault(true);
				else 
					line.stIsDefault(false);
				line.upd();
			}
		}
	}
	
	public static class Ins extends IduIns<Ins, UsrSupplierRole> {
		@Override
		public void before() {
			super.before();
			if (UsrSupplierRole.chkUniqueCode(false, getB().getCode()) != null) {
				throw LOG.err("notFound",  "角色代码[{0}]已存在,不可重名!", getB()
						.getCode());
			}
			UsrSupplierRole bean = new UsrSupplierRole().init();
			PropertyUtils.copyProperties(bean, getB(), T.CODE, T.NAME);
			bean.stIsDefault(false);
			bean.stUpdatedBy(getUser());
			bean.setUpdatedTime(Env.getTranBeginTime());
			setB(bean);
		}

	}
	
	public static class Upd extends IduUpd<Upd, UsrSupplierRole> {

		public void before() {
			super.before();
			UsrSupplierRole dbBean = loadThisBeanAndLock();
			if (!dbBean.getCode().equals(getB().getCode())) {
				if (UsrSupplierRole.chkUniqueCode(false, getB().getCode()) != null) {
					throw LOG.err("notFound",  "角色代码[{0}]已存在,不可重名!", getB()
							.getCode());
				}
			}
			PropertyUtils.copyProperties(dbBean, getB(), UsrSupplierRole.T.NAME, UsrSupplierRole.T.CODE);
			setB(dbBean);
			getB().stUpdatedBy(getUser());
			getB().setUpdatedTime(Env.getSystemTime());
		}

	}

	public static class Del extends IduDel<Del, UsrSupplierRole> {
		@Override
		public void before() {
		  super.before();
		  if(getB().gtIsDefault()) {
			  throw LOG.err(Msgs.isDefault);
		  }
		  
		  int count = countWhere(UsrSupplierRole.T.IS_DEFAULT.getFld().getCodeSqlField()+"=?", BeanBase.booleanToByte(true));
		  if(count > 0) {
			  throw LOG.err(Msgs.isReference);
		  }
		  
		  String where = Idu.sqlString("{0}=?", UsrSupplierRoleAct.T.ROLE);
		  List<UsrSupplierRoleAct> acts = UsrSupplierRoleAct.list(UsrSupplierRoleAct.class, where, true, getB().getPkey());
		  for(UsrSupplierRoleAct line:acts) {
		  	line.del();
		  }
		  // TODO ProvCtrl.getInstance().clearAll();
		}

	}
	public static HashSet<String> listByRole(Integer pkey, String module) {
		UsrSupplierRole role = Bean.load(UsrSupplierRole.class, pkey);
		HashSet<String> result = new HashSet<String>();
		if(module != null && !module.equals("")) {
			List<UsrAccess> list = BeanBase.list(UsrAccess.class, UsrAccess.T.MODULE.getFld().getCodeSqlField()+"=?", false, module);
			Set<String> acts = new HashSet<String>();
			for(UsrAccess line:list) {
				acts.add(line.getPkey());
			}
			for(String act:role.getActs().split(",")) {
				if(acts.contains(act)) {
					result.add(act);
				}
			}
		} else {
			for(String act:role.getActs().split(",")) {
				result.add(act);
			}
		}
		return result;
	}
	
	/**
	 * 供应商角色权限的处理类
	 * @author Administrator
	 *
	 */
	public static class ProvCtrl {
		private static final Log LOG = new Log(ProvCtrl.class);
		public static final String ID_MENU = "Menu_";
		// <用户主键##菜单模块, json值>
		private static HashMap<String, String> _userMenuMap = new HashMap<String, String>();
		// <用户主键##菜单主键, acts值>---------
		private static HashMap<String, String> _umActsMap = new HashMap<String, String>();
		private static HashSet<Integer> _isActSet = new HashSet<Integer>();
		private static HashMap<Integer, String> _uTypeMap = new HashMap<Integer, String>(); // 用户主键,模块集合
		// 功能-代码缓存
		private static HashMap<Integer, SysMenuAct> _actMap = new HashMap<Integer, SysMenuAct>();
		private static HashMap<Integer, SysMenu> _menuMap = new HashMap<Integer, SysMenu>();
		private static ProvCtrl INSTANCE = new ProvCtrl();

		
		public ProvCtrl() {
			/**
			 * 初始化数据>>菜单与菜单功能
			 * 避免重复读取数据库
			 */
			List<SysMenuAct> list = BeanBase.list(SysMenuAct.class, "", false);
			for (SysMenuAct line : list)
				_actMap.put(line.getPkey(), line);
			List<SysMenu> list2 = BeanBase.list(SysMenu.class, "", false);
			for (SysMenu line : list2)
				_menuMap.put(line.getPkey(), line);
		}
		public static ProvCtrl getInstance() {
			return INSTANCE;
		}
		
		public String initActSet() {
			int supplier = SellerAction.getSupplier().getPkey();
			// 初始化权限相关数据
			if (_isActSet.contains(new Integer(supplier)) == false) {
				HashSet<Integer> actSet = UsrSupplierRoleActDAO.listByRole(SellerAction.getSupplier().getRole());
				HashSet<String> typeSet = new HashSet<String>(); // 模块SET
				for (Integer actPkey : actSet) {
					SysMenuAct ma = _actMap.get(actPkey);
					typeSet.add(_menuMap.get(ma.getMenu()).getType());
					String key = supplier + "##" + ma.getMenu();
					String acts = _umActsMap.get(key);
					if (acts == null)
						acts = "";
					acts += ma.getCode() + ",";
					_umActsMap.put(key, acts);
				}
				String types = "";
				for (String std : typeSet)
					types += "," + std;
				
//				for (String std : SysMenuAction.MENUMAP.keySet())
//					if (typeSet.contains(std))
//						types += "," + std;
				
				_uTypeMap.put(new Integer(supplier), Str.isEmpty(types) ? "" : types.substring(1));
				_isActSet.add(new Integer(supplier));
			}
			return _uTypeMap.get(new Integer(supplier));
		}
		
		// 根据类型与当前用户，取MENU的JSON
		public String getMenuByType(String type) throws JSONException {
			//当功能权限修改后，会清空大部分的缓存数据
			//由于目前菜单是异步加载，所以每次请求都检查用户的功能权限缓存中是否已经初始化了
			initActSet();
			int supplier = SellerAction.getSupplier().getPkey();
		//	int user = Env.INST.getTran().getUser().getPkey();
			String json = _userMenuMap.get(supplier + "##" + type);
			if (json != null)
				return json;
			// 取某模块下的所有MENU对象
			List<SysMenu> list = SysMenuDAO.listByType(type);
			ArrayList<ProvLine> plineAry = new ArrayList<ProvCtrl.ProvLine>();
			ProvLine pline = new ProvLine();
			plineAry.add(pline); // 第一个
			for (SysMenu menu : list) {
				ProvLine fline = pline.addMenu(menu);
				if (pline != fline) {
					plineAry.add(fline);
					pline = fline;
				}
				String acts = _umActsMap.get(supplier + "##" + menu.getPkey());
				// 原来给分类统计的菜单做，已废除
				// if (acts == null && menu.getMenuUrl() != null && menu.getMenuUp() !=
				// null)
				// acts = _umActsMap.get(user + "##" + menu.getMenuUp());
				fline.setActs(menu, acts);
			}
			JSONArray ja = new JSONArray();
			for (ProvLine line : plineAry) {
				JSONObject lj = line.toJson(type);
				if (lj != null)
					ja.put(lj);
			}
			String rtn = ja.length() > 0 ? ja.toString() : "";
			_userMenuMap.put(supplier + "##" + type, rtn);
			return rtn;
		}
		
		
		/**
		 * 更改供应商角色功能权限界面中调用
		 * @param pkey 供应商角色主键
		 * @param type 模块名字 如: sys , usr, pdt
		 * @return
		 * @throws JSONException
		 */
		public static JSONArray crtRes(Integer pkey, String type) throws JSONException {
			JSONArray ja = new JSONArray();
			
			UsrSupplierRole role = Bean.load(UsrSupplierRole.class, pkey);
			HashSet<String> roleActs = new HashSet<String>();
			if(role.getActs()!=null)
				for(String act:role.getActs().split(",")) 
					roleActs.add(act);
			
			Map<String, List<UsrAccess>> map = UsrAccessDAO.CONTROLLER_ACCESS.get(type);
			for(String controller: map.keySet()) {
				JSONObject json = new JSONObject();
				JSONArray jaact = new JSONArray();
				for(UsrAccess line:map.get(controller)) {
					JSONObject j = new JSONObject();
					j.put("mc", line.getName());
					j.put("pkey", line.getPkey());
					j.put("checked", roleActs.contains(line.getPkey()));
					jaact.put(j);
				}
				json.put("acts", jaact);
				json.put("title", controller);
				ja.put(json);
			}
			return ja;
		}
		/**
		 * 菜单对象
		 * 目前只支持到三级
		 * @author whx
		 */
		public class ProvLine {
			public SysMenu _menuOne;
			public ArrayList<SysMenu> _menuTwoList;
			public HashMap<Integer, ArrayList<SysMenu>> _menuThrMap;
			public HashMap<Integer, String> _actsMap;

			public ProvLine() {
			}

			public ProvLine addMenu(SysMenu menu) {
				// 初始化一级菜单
				if (_menuOne == null) {
					_menuOne = menu;
					return this;
				}
				// 一级菜单情况
				if (menu.getMenuUp() == null) {
					ProvLine line = new ProvLine();
					return line.addMenu(menu);
				}
				int level = validate(menu);
				// 二级菜单
				if (level == 2) {
					if (_menuTwoList == null)
						_menuTwoList = new ArrayList<SysMenu>();
					_menuTwoList.add(menu);
					return this;
				}
				// 三级菜单
				if (level == 3) {
					if (_menuThrMap == null)
						_menuThrMap = new HashMap<Integer, ArrayList<SysMenu>>();
					ArrayList<SysMenu> ary = _menuThrMap.get(menu.getMenuUp());
					if (ary == null) {
						ary = new ArrayList<SysMenu>();
						_menuThrMap.put(menu.getMenuUp(), ary);
					}
					ary.add(menu);
					return this;
				}
				throw LOG.err("errOrder", "不可超过3级菜单");
			}

			// 设置对应菜单的权限
			private void setActs(SysMenu menu, String acts) {
				if (acts == null)
					return;
				if (_actsMap == null)
					_actsMap = new HashMap<Integer, String>();
				_actsMap.put(menu.getPkey(), acts);
			}

			// 上下级菜单排序错误检查
			private int validate(SysMenu menu) {
				if (menu.getMenuUp().intValue() == _menuOne.getPkey().intValue())
					return 2;
				if (_menuTwoList != null) {
					for (SysMenu line : _menuTwoList)
						if (menu.getMenuUp().intValue() == line.getPkey().intValue())
							return 3;
				}
				throw LOG.err("errOrder", "上下级菜单排序错误[" + menu.getName() + "]");
			}

			public JSONObject toJson(String type) throws JSONException {
				if (_actsMap == null)
					return null; // 没有任务权限，无此交易
				boolean isleaf = true;
				if (_menuTwoList != null)
					isleaf = false;
				String act1 = _actsMap.get(_menuOne.getPkey());
				JSONObject json = new JSONObject();
				json.put("id", getMenuId(_menuOne)).put("text", _menuOne.getName()).put("leaf", isleaf + "")
				    .put("cls", isleaf ? "file" : "floder").put("url", _menuOne.getUrl()).put("beanType", type)
				    .put("roles", act1);
				// 下面是二级菜单的处理
				if (isleaf == false) {
					JSONArray ja2 = new JSONArray();
					for (SysMenu line2 : _menuTwoList) {
						boolean isleaf2 = true;
						if (_menuThrMap != null && _menuThrMap.containsKey(line2.getPkey()))
							isleaf2 = false;
						String acts2 = _actsMap.get(line2.getPkey());
						JSONObject json2 = new JSONObject();
						json2.put("id", getMenuId(line2)).put("text", line2.getName()).put("leaf", isleaf2 + "")
						    .put("cls", isleaf2 ? "file" : "floder").put("url", line2.getUrl()).put("beanType", type)
						    .put("roles", acts2);
						// 下面是三级菜单处理--目前系统肯定是叶节点
						if (isleaf2 == false) {
							ArrayList<SysMenu> list3 = _menuThrMap.get(line2.getPkey());
							JSONArray ja3 = new JSONArray();
							for (SysMenu line3 : list3) {
								String acts3 = _actsMap.get(line3.getPkey());
								if (acts3 == null)
									continue;
								JSONObject json3 = new JSONObject();
								json3.put("id", getMenuId(line3)).put("text", line3.getName()).put("leaf", "true").put("cls", "file")
								    .put("url", line3.getUrl()).put("beanType", type).put("roles", acts3);
								ja3.put(json3);
							}
							if (ja3.length() > 0) // 有权限的情况
								json2.put("children", ja3);
						}
						// 二级节点无权限并无下级菜单时跳过
						if (acts2 == null && json2.has("children") == false)
							continue;
						ja2.put(json2);
					}
					if (ja2.length() > 0)
						json.put("children", ja2);
				}
				// 一级节点无权限并无下级菜单时跳过
				if (act1 == null && json.has("children") == false)
					return null;
				return json;
			}

			private String getMenuId(SysMenu menu) {
				return ID_MENU + menu.getPkey();
			}
		}
	}
	
	public static class UpdCtrl extends IduOther<UpdCtrl, UsrSupplierRole> {
		public static Cn CN = new Cn("updCtrl", "权限控制");
		private String _lines;
		private String _type;

		@Override
		public void run() {
			iud(loadThisBeanAndLock(), getType(), getLines());
		}

		@Override
		public void after() {
			super.after();
			clearRoleAllAct();
		}

		/**
		 * 更新供应商角色的功能权限
		 * @param role 角色对象
		 * @param type 模块类型
		 * @param lines 功能组 eg: 
		 */
		public static void iud(UsrSupplierRole role, String type, String lines) {
			
			Set<String> roleActs = new HashSet<String>();
			if(role.getActs()!=null&&!role.getActs().trim().equals("")) {
				for(String act:role.getActs().split(",")) {
					roleActs.add(act);
				}
			}
			
			List<UsrAccess> list = UsrAccessDAO.MODULE_ACCESS.get(type);
			Set<String> allActs = new HashSet<String>();
			for(UsrAccess act:list) {
				allActs.add(act.getPkey());
				roleActs.remove(act.getPkey());
			}
			for(String act:lines.split(",")) {
				if(allActs.contains(act)){
					roleActs.add(act);
				}
			}
			
			role.setActs(roleActs.stream().collect(Collectors.joining(",")));
			role.upd();
		}
		
		public String getLines() {
			return _lines;
		}

		public void setLines(String lines) {
			_lines = lines;
		}

		public String getType() {
			return _type;
		}

		public void setType(String type) {
			_type = type;
		}
	}
	
}