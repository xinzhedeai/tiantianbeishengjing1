package customMapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import pageModel.User;

@SuppressWarnings("rawtypes")
public interface CustomUserMapper {
	
	List<Map> selectUser(Map map);
	
	List<Map> getAdminListByPage(Map map);
	
	List<Map> getUserListByPage(Map map);

	int addReceit(String scripture_user_id);

	int addScriptureUser(String scripture_user_name);

	int delScriptureUser(List scripture_user_ids);
}
