package service;

import java.util.List;
import java.util.Map;

import exception.SysException;
import normalPo.Scripture;
import pageModel.EasyUIGridObj;
@SuppressWarnings("rawtypes")
public interface ScriptureService {
	
	
	public int insertScripture(Map map);

	public List<Scripture> searchScriptures(Map paramMap);

	public Integer searchScripturesByDate(Map paramMap);
}
