package io.alectra.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Test;

import io.alectra.main.StringUtils;

class StringUtilsTest {

	@Test
	void testEqualStringsTrue() {
		StringUtils stringUtils = new StringUtils();
		assertTrue(stringUtils.equalStrings("test", "test"), "should be true");
	}

	@Test
	void testEqualStringsFalse() {
		StringUtils stringUtils = new StringUtils();
		assertFalse(stringUtils.equalStrings("test", "testt"), "should be false");
	}
	
	@Test
	void testEqualStringsNull() {
		StringUtils stringUtils = new StringUtils();
		assertFalse(stringUtils.equalStrings(null, null), "null is false");
	}
	
	@Test
	void testEqualStringsEmpty() {
		StringUtils stringUtils = new StringUtils();
		assertTrue(stringUtils.equalStrings("", ""), "both empty true");
	}
	
	@Test
	void testEqualStringsOneNull() {
		StringUtils stringUtils = new StringUtils();
		assertFalse(stringUtils.equalStrings("asd", null), "one null false");
	}

}
