package irille.tools;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.pub.bean.Query;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSpec;

public class TestColorOrSizeExists {
	
	static {
		PdtSize.TB.getCode();
		PdtColor.TB.getCode();
		PdtProduct.TB.getCode();
		PdtSpec.TB.getCode();
		PdtAttrLine.TB.getCode();
	}

	static ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
	
	static Set<Integer> allSize = Query.SELECT(PdtSize.T.PKEY).FROM(PdtSize.class).queryList(Integer.class).stream().collect(Collectors.toSet());
	
	static Set<Integer> allColor = Query.SELECT(PdtColor.T.PKEY).FROM(PdtColor.class).queryList(Integer.class).stream().collect(Collectors.toSet());
	
	static Set<Integer> allAttrLine = Query.SELECT(PdtAttrLine.T.PKEY).FROM(PdtAttrLine.class).queryList(Integer.class).stream().collect(Collectors.toSet());
	
//	@Test
	public void testSpec() {
		System.out.println("-----------------------------------------校验规格表-------------------------------------------------");
		Set<Integer> noSize = new HashSet<>();
		Set<Integer> noColor = new HashSet<>();
		List<PdtSpec> list = Query.SELECT(PdtSpec.T.PKEY, PdtSpec.T.SIZE, PdtSpec.T.COLOR).FROM(PdtSpec.class).queryList();
		for (PdtSpec line : list) {
			Integer pkey = line.getPkey();
			Integer size = line.getSize();
			Integer color = line.getColor();
			if(!allSize.contains(size)) {
				System.out.println("规格["+pkey+"]的尺寸["+size+"]不存在");
				noSize.add(size);
			}
			if(!allColor.contains(color)) {
				System.out.println("规格["+pkey+"]的颜色["+color+"]不存在");
				noColor.add(color);
			}
		}
		System.out.println("汇总:");
		System.out.println("下面这些尺寸不存在");
		toConsole(noSize);
		System.out.println("下面这些颜色不存在");
		toConsole(noColor);
	}
	
//	@Test
	public void testProduct() {
		System.out.println("-----------------------------------------校验产品表-------------------------------------------------");
		Set<Integer> noSize = new HashSet<>();
		Set<String> illeageSize = new HashSet<>();
		Set<Integer> noColor = new HashSet<>();
		Set<String> illeageColor = new HashSet<>();
		Set<Integer> noAttrLine = new HashSet<>();
		Set<String> illeageAttrLine = new HashSet<>();
		List<PdtProduct> list = Query.SELECT(PdtProduct.T.PKEY, PdtProduct.T.SIZE_ATTR, PdtProduct.T.NORM_ATTR, PdtProduct.T.COLOR_ATTR).FROM(PdtProduct.class).queryList();
		for (PdtProduct line : list) {
			Integer pkey = line.getPkey();
			String size = line.getSizeAttr();
			String color = line.getColorAttr();
			String attrLine = line.getNormAttr();
			if(size==null||size.trim().equals(""))  {
				System.out.println("产品["+toStr(line)+"]没有尺寸");
				continue;
			}
			if(color==null||color.trim().equals("")) {
				System.out.println("产品["+toStr(line)+"]没有颜色");
				continue;
			}
			if(attrLine==null||attrLine.trim().equals("")) {
				System.out.println("产品["+toStr(line)+"]没有属性明细");
				continue;
			}
			for (String s : size.split(",")) {
				try {
					Integer ss = Integer.valueOf(s);
					if(!allSize.contains(ss)) {
						System.out.println("产品"+toStr(line)+"的尺寸["+ss+"]不存在");
						noSize.add(ss);
					}
				} catch (NumberFormatException e) {
					System.out.println("产品"+toStr(line)+"的尺寸["+s+"]异常");
					illeageSize.add(s);
				}
			}
			for (String s : color.split(",")) {
				try {
					Integer ss = Integer.valueOf(s);
					if(!allColor.contains(ss)) {
						System.out.println("产品"+toStr(line)+"的颜色["+ss+"]不存在");
						noColor.add(ss);
					}
				} catch (NumberFormatException e) {
					System.out.println("产品"+toStr(line)+"的颜色["+s+"]异常");
					illeageColor.add(s);
				}
			}
			for (String s : attrLine.split(",")) {
				try {
					Integer ss = Integer.valueOf(s);
					if(!allAttrLine.contains(ss)) {
						System.out.println("产品"+toStr(line)+"的属性明细["+ss+"]不存在");
						noAttrLine.add(ss);
					}
				} catch (NumberFormatException e) {
					System.out.println("产品"+toStr(line)+"的属性明细["+s+"]异常");
					illeageAttrLine.add(s);
				}
			}
		}
		System.out.println("汇总:");
		System.out.println("下面这些尺寸不存在");
		toConsole(noSize);
		System.out.println("下面这些尺寸异常");
		toConsole(illeageSize);
		System.out.println("下面这些颜色不存在");
		toConsole(noColor);
		System.out.println("下面这些颜色异常");
		toConsole(illeageColor);
		System.out.println("下面这些属性明细不存在");
		toConsole(noAttrLine);
		System.out.println("下面这些属性明细异常");
		toConsole(illeageAttrLine);
	}
	
	public static String toStr(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return "";
		}
	}
	
	public static void toConsole(Object o) {
		System.out.println(toStr(o));
	}
	
}
