package com.xinlianshiye.shoestp.plat.service.pm.imp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xinlianshiye.shoestp.plat.service.pm.IVariableService;
import com.xinlianshiye.shoestp.plat.view.pm.VariableView;

import irille.Config.Attribute;
import irille.Config.Variable;
import irille.Entity.pm.PM.OTempType;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.IEnumOpt;
import irille.pub.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariableServiceImp implements IVariableService{
	
	private static final String action_root_package = "irille";
	
	private static final Map<Integer,Map<String,VariableView>> map;
	
	static {
		map = initVariable();
	}
	
	//变量有中文名称/变量值/所属操作
		public static void main(String[] args) {
//			O2O_Product product = new O2O_Product();
//			product.setLowerDate(new Date());
//			product.setStatus(O2O_ProductStatus.Failed.getLine().getKey());
//			product.setRemark("郭松");
//			PdtProduct product1 = new PdtProduct();
//			product1.setName("李逸超");
//			product.stProductId(product1);
//			String template = "我是{$备注},现在是{$下架时间},商品名称是{$商品名称},haha{$商品上下架状态}";
//			VariableServiceImp service = new VariableServiceImp();
//			System.out.println(map.get(Integer.valueOf(OTempType.PROD_APPR_NOTICE.getLine().getKey())));
//			String str = service.render(map.get(Integer.valueOf(OTempType.PROD_APPR_NOTICE.getLine().getKey())), template,product,product1);
//			System.out.println(str);
//			for(Integer key:map.keySet()) {
//				System.out.println(key);
//				Map<String,VariableView> views = map.get(key);
//				for(String key2:views.keySet()) {
//					System.out.println(key2 + ":" + views.get(key2));
//				}
//			}
			System.out.println(map);
		}
	
	//根据模板类型获取对应变量
	@Override
	public List<String> loadByTempType(Integer type){
		System.err.println(map);
		Map<String,VariableView> variables = map.get(type);
		List<String> keys = new ArrayList<>();
		if(variables != null && variables.size() > 0) {
			for(String key:variables.keySet()) {
				keys.add(key);
			}
			return keys;
		}
		return null;
	}
	
	private static Map<Integer,Map<String,VariableView>> initVariable(){
		List<Class<?>> clss = Scanner.findClassByAnnotationSkipInitialize(Variable.class,action_root_package);
		Map<Integer,Map<String,VariableView>> map = new HashMap<>();
		for(Class<?> cls : clss) {
			Map<Integer,Map<String,VariableView>> initMap = initMap(cls);
			for(Integer key:initMap.keySet()) {
				if(map.containsKey(key)) {
					map.get(key).putAll(initMap.get(key));
				}else {
					map.put(key, initMap.get(key));
				}
			}
		}
		return map;
	}
	
	
	private static Map<Integer,Map<String,VariableView>> initMap(Class<?> cls){
		Map<Integer,Map<String,VariableView>> map = new HashMap<>();
		Variable valiable = cls.getAnnotation(Variable.class);
		OTempType[] tempTypes = valiable.group();
		Attribute[] attributes = valiable.attributes();
		
		for(OTempType temp:tempTypes) {
			Map<String,VariableView> attrMap = new HashMap<>();
			for(Attribute attr:attributes) {
				VariableView view = new VariableView();
				view.setField(attr.field());
				view.setClazz(valiable.clazz());
				view.setEnumType(valiable.enumType());
				view.setFieldType(attr.type());
				attrMap.put(attr.name(), view);
			}
			map.put(Integer.valueOf(temp.getLine().getKey()), attrMap);
		}
		return map;
	}
	
	
	/**
	 * 获取实体类中枚举字段中对应的实体类字段
	 */
	public static Field getDeclaredField(Class<?> clazz , Object obj) {
		try {
			Method fldMethod = obj.getClass().getMethod("getFld");
			Object fld = fldMethod.invoke(obj);
			Method codeMethod = fld.getClass().getMethod("getCode");
			Object code = codeMethod.invoke(fld);
			return clazz.getDeclaredField("_" + String.valueOf(code));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据枚举名称获取对应的枚举对象
	 */
    public static Object fromValue(Class<?> enumType, Object object) {
        try {
        	Method method = enumType.getMethod("name");
            for (Object o : enumType.getEnumConstants()) {
				if (Objects.equals(object,method.invoke(o))) {
				    return o;
				}
            }
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
        throw new IllegalArgumentException("No enum value " + object + " of " + enumType.getCanonicalName());
    }
    
    /**
     *	根据枚举值获取枚举名称
     */
    public static <T extends IEnumOpt> String fromValue1(Class<T> enumType, Object value) {
    	try {
    		Method method = enumType.getMethod("values");
    		if(value == null) {
    			return null;
    		}
            T[] values = (T[]) method.invoke(null);
            for (T x : values) {
                if (Integer.valueOf(String.valueOf(value)).equals(Integer.valueOf(x.getLine().getKey()))) {
                    return x.getLine().getName();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("can't convertToEntityAttribute" + e.getMessage());
        }
        throw new RuntimeException("unknown dbData " + value);
    }
    
    
    
    //模板填充  格式{$XXX}
    @Override
    public String render(Map<String,VariableView> variableMap,String template,Object... objects) {
    	String reg = "\\{\\$[\\S]*?\\}{1}";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(template);
		
		while (matcher.find()) {
			String str = matcher.group();
			String key = str.substring(str.indexOf("$")+1,str.lastIndexOf("}"));
			VariableView variable = variableMap.get(key);
			
			if(variable == null){
				log.info("非法参数【"+key+"】");
				throw new WebMessageException(ReturnCode.service_wrong_data,"非法参数【"+key+"】");
			}
			Class clazz = variable.getClazz();
			Field field = getDeclaredField(clazz,fromValue(variable.getEnumType(), variable.getField()));
			if(null == field) {
				continue;
			}
			Object object = null;
			for(Object obj : objects) {
				try {
					field.setAccessible(true);
					if(variable.getFieldType() == Date.class) {
						object = sdf.format((Date)field.get(obj));
					}else if(variable.getFieldType().isEnum()){
						object = fromValue1(variable.getFieldType(),field.get(obj));
					}else{
						object = field.get(obj);
					}
				} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
					continue;
				}
			}
			template = template.replaceAll(str.replace("{","\\{").replace("$", "\\$").replace("}", "\\}"), String.valueOf(object));
		}

		return template;
    }
	
    @Override
	public Map<String, VariableView> getMap(OTempType tempType) {
		return map.get(Integer.valueOf(tempType.getLine().getKey()));
	}

}
