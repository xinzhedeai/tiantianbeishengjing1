package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import customMapper.CustomScriptureMapper;
import exception.SysException;
import normalPo.Scripture;
import pageModel.EasyUIGridObj;
import service.ScriptureService;

public class ScriptureServiceImpl implements ScriptureService {
	@Autowired
	private CustomScriptureMapper scriptureMapper;

	public int insertScripture(Map map) {
		return scriptureMapper.insertScripture(map);
	}

	@Override
	public List<Scripture> searchScriptures(Map paramMap) {
		return scriptureMapper.searchScriptures(paramMap);
	}

	@Override
	public Integer searchScripturesByDate(Map paramMap) {
		if(scriptureMapper.searchScripturesByDate(paramMap) != null 
						&& !"".equals(scriptureMapper.searchScripturesByDate(paramMap))){
			return scriptureMapper.searchScripturesByDate(paramMap);
		}else{
			return -1;
		}
	}
}
