package action;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import normalMapper.UserMapper;
import normalPo.User;
import normalPo.UserExample;
import pageModel.JsonResult;
import service.UserService;
import util.SpringUtils;
@Controller
@RequestMapping("/userAction")
public class UserAction extends BaseAction{
	
	public static Logger logger  = Logger.getLogger(UserAction.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userServiceImpl;
/*	@RequestMapping("/login")
	public String login()throws Exception{
		return "login";
	}*/
	@ResponseBody
	@RequestMapping("/loginSubmit")
	public JsonResult loginSubmit(HttpSession session, String userName, String userPwd)throws Exception{
		System.out.println("进到servlet里面了。");
		UserExample userExp = new UserExample();
		UserExample.Criteria criteria = userExp.createCriteria();
		criteria.andUserNameEqualTo(userName).andUserPwdEqualTo(userPwd);
		List<User> userInfoList = userMapper.selectByExample(userExp);
		JsonResult j = new JsonResult();
		if(userInfoList.size() == 1){
			j.setSuccess(true);
			j.setMsg("登录成功！");
			j.setResult(userInfoList.get(0));
		}else{
			j.setSuccess(false);
			j.setMsg("账号或密码错误！");
		}
		return j;
	}
	@ResponseBody
	@RequestMapping("/loginSubmit1")
	public JsonResult loginSubmit1(HttpServletRequest req)throws Exception{
		System.out.println("进到login里面了。");
		Map reqMap = SpringUtils.getParameterMap(req);
		JsonResult j = new JsonResult();
		if(userServiceImpl.checkUser(reqMap) == 1){
			j.setSuccess(true);
			j.setMsg("登录成功！");
		}else{
			j.setSuccess(false);
			j.setMsg("账号或密码错误！");
		}
		return j;
	}
}
