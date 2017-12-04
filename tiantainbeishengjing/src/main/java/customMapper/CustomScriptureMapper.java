package customMapper;

import java.util.List;
import java.util.Map;

import normalPo.Scripture;
import tk.mybatis.mapper.common.Mapper;
@SuppressWarnings("rawtypes")
public interface CustomScriptureMapper extends Mapper{
	int insertScripture(Map map);

	List<Scripture> searchScriptures(Map paramMap);

	int searchScripturesByDate(Map paramMap);
}
