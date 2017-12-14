package normalMapper;

import java.util.List;
import java.util.Map;

import normalPo.Scripture;
import normalPo.ScriptureExample;
import org.apache.ibatis.annotations.Param;

public interface ScriptureMapper {
    int countByExample(ScriptureExample example);

    int deleteByExample(ScriptureExample example);

    int deleteByPrimaryKey(String scriptureNo);

    int insert(Scripture record);

    int insertSelective(Scripture record);

    List<Scripture> selectByExample(ScriptureExample example);

    Scripture selectByPrimaryKey(String scriptureNo);

    int updateByExampleSelective(@Param("record") Scripture record, @Param("example") ScriptureExample example);

    int updateByExample(@Param("record") Scripture record, @Param("example") ScriptureExample example);

    int updateByPrimaryKeySelective(Scripture record);

    int updateByPrimaryKey(Scripture record);

	int modScripture(Map paramMap);
}