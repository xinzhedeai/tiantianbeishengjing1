package tiantainbeishengjing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class jiyiquxiansuanfa {

	@Test
	public void test() {
		String[] type = {"A", "B", "C", "D", "E"};//经文类型
		int scriptureNoNew = 111;//新输入的经文编号
		int[] memoPeriodArr = {1, 2, 4, 7, 15, 30, 90, 180};//记忆曲线周期
		List readSriptureNoArr = new ArrayList();//查询经文所需编号
		readSriptureNoArr.add(0, type[2] + scriptureNoNew);
		for(int i = 0; i < memoPeriodArr.length; i++){
			int tempNo = scriptureNoNew - memoPeriodArr[i];
			if(tempNo > 0){
				readSriptureNoArr.add(i + 1, type[2] + tempNo);
			}else{
				break;
			}
			/*System.out.println(memoPeriodArr[i]);
			System.out.println("查询条件经文编号："+ readSriptureNoArr.get(i));*/
		}
		for (Object item : readSriptureNoArr) {
			System.out.println("查询条件经文编号："+ item);
		}
	
	}
	@Test
	public void splitTest() {
		String a = "A20002";
		System.out.println(a.substring(0, 1));
		System.out.println(a.substring(1));
	}
	

}
