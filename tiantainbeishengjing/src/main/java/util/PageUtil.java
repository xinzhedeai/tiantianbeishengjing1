package util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import pageModel.EasyUIGridObj;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import exception.SysException;
import action.UserAction;

public class PageUtil {
	public static Map _transPagging(Map paramMap) throws SysException{
		Logger logger  = Logger.getLogger(PageUtil.class);
		Map newParaMap = new HashMap();
		try {
			int start = Integer.parseInt(((String[]) paramMap.get("start"))[0]);
			int length = Integer.parseInt(((String[]) paramMap.get("length"))[0]);
			int orderCol = Integer.parseInt(((String[]) paramMap.get("order[0][column]"))[0]);//按哪里排序
			String direction = ((String[]) paramMap.get("order[0][dir]"))[0];
			newParaMap.put("start", start);
			newParaMap.put("length", length);
			newParaMap.put("orderCol", orderCol);
			newParaMap.put("direction", direction);
			/*paramMap.remove("start");
			paramMap.remove("length");*/
		} catch (Exception e) {
			logger.error(e);
			throw new SysException("页码参数有误.");
		}
		return newParaMap;
	}
	public EasyUIGridObj searchByPage(Map paramMap,String str) throws SysException {
		int pageNum = 0;
		int pageSize = 0;
		EasyUIGridObj easyUIGridObj = new EasyUIGridObj();
		try {
			pageNum = Integer.parseInt((String) paramMap.get("page"));
			pageSize = Integer.parseInt((String) paramMap.get("rows"));
		} catch (Exception e) {
			throw new SysException("分页参数格式不正确.");
		}
		
		Page page = PageHelper.startPage(pageNum, pageSize);
		/*customBlogMapper.searchMyRecBlogs(paramMap);*/
		//这句该怎么封装？？
		if(page.getTotal() > (pageNum - 1) * pageSize) {
			easyUIGridObj.setRows(page.getResult());
			easyUIGridObj.setTotal(page.getTotal());
		} else {
			easyUIGridObj.setRows(null);
			easyUIGridObj.setTotal(page.getTotal());
		}
		
		return easyUIGridObj;
	}
}
