package 备份.irille.tools;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.shop.pdt.PdtSize;

public class TestHtml {

	static ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);

	public static void main(String[] args) throws JsonProcessingException {
		PdtSize size = new PdtSize();
		size.setName("ljlj\"\'");
		System.out.println(mapper.writeValueAsString(size));
	}
}
