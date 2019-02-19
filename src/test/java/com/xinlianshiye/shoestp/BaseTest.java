package com.xinlianshiye.shoestp;

import java.lang.reflect.Field;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class BaseTest {
	
	private Injector in = Guice.createInjector((binder)->{});
	
	@Before
	public void testBefore() throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(field.getAnnotation(Inject.class) != null) {
				field.setAccessible(true);
				field.set(this, in.getInstance(field.getType()));
			}
		}
	}

}
