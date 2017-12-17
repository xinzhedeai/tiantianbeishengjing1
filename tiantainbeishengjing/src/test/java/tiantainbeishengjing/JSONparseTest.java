package tiantainbeishengjing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import util.SerialUtil;

public class JSONparseTest {

	@Test
	public void test() {
		String s = "[haha, hhhh]";
		System.out.println(SerialUtil.JsonToList(s));
	}

}
