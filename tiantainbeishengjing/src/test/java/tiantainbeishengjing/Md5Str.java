package tiantainbeishengjing;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import util.DateUtil;
import util.MD5Util;
import util.MSG_CONST;

public class Md5Str {

	@Test
	public void test() {
//		System.out.println(MD5Util.toSHA256("haliluya"));
		System.out.println(MD5Util.toSHA256(DateUtil.nowTimeMilli()).length());
	}
	@Test
	public void randomTest(){
		System.out.println(Math.ceil(Math.random()*1000));
	}
	@Test
	public void timeTest(){
		System.out.println(DateUtil.nowTimeMilli());
	}
	@Test
	public void splitTest(){
		String[] a = "2&3&4".split("&");
		System.out.println(a[1]);
		System.out.println(a.toString());
	}
	@Test
	public void enumTest(){
		System.out.println(MSG_CONST.DELETEFAIL);
	}
	
}
