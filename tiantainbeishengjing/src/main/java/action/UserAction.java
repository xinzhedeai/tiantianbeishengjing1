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

import customMapper.CustomUserMapper;
import exception.SysException;
import pageModel.EasyUIGridObj;
import pageModel.JsonResult;
import pageModel.User;
import service.UserService;
import util.MSG_CONST;
import util.PageUtil;
import util.SerialUtil;
import util.SpringUtils;
@Controller
@RequestMapping("/userAction")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserAction extends BaseAction{
	public static Logger logger  = Logger.getLogger(UserAction.class);
	
	@Autowired
	private CustomUserMapper customUserMapper;
	@ResponseBody
	@RequestMapping("/login")
	public JsonResult loginSubmit(HttpServletRequest req)throws Exception{
		System.out.println("进到servlet里面了。");
		Map reqMap = SpringUtils.getParameterMap(req);
		List<Map> userInfoList = customUserMapper.selectUser(reqMap);
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
	
	/*后台信息管理接口*/
	@ResponseBody
	@RequestMapping("/getAdminListByPage")
	public JsonResult getAdminListByPage(HttpServletRequest req) throws Exception{
//		Thread.sleep(2000000000);
		Map reqMap = SpringUtils.getParameterMap(req);
		JsonResult j = new JsonResult();
		
		EasyUIGridObj easyUIGridObj = null;
		try {
			easyUIGridObj = PageUtil.searchByPage(customUserMapper, reqMap, "getAdminListByPage");
		} catch (SysException e) {
			e.printStackTrace();
		}
		if(easyUIGridObj != null){
			j.setSuccess(true);
			j.setMsg(MSG_CONST.READSUCCESS);
			j.setResult(easyUIGridObj);
		}else{
			j.setSuccess(false);
			j.setMsg(MSG_CONST.READFAIL);
		}
		return j;
	}
	//新人背经情况数据 获取
	@ResponseBody
	@RequestMapping("/getUserListByPage")
	public JsonResult getUserListByPage(HttpServletRequest req) throws Exception{
		Map reqMap = SpringUtils.getParameterMap(req);
		JsonResult j = new JsonResult();
		
		EasyUIGridObj easyUIGridObj = null;
		try {
			easyUIGridObj = PageUtil.searchByPage(customUserMapper, reqMap, "getUserListByPage");
		} catch (SysException e) {
			e.printStackTrace();
		}
		if(easyUIGridObj != null){
			j.setSuccess(true);
			j.setMsg(MSG_CONST.READSUCCESS);
			j.setResult(easyUIGridObj);
		}else{
			j.setSuccess(false);
			j.setMsg(MSG_CONST.READFAIL);
		}
		return j;
	}
	//新人背经情况数据 获取
	@ResponseBody
	@RequestMapping("/addReceit")
	public JsonResult addReceit(String scripture_user_id) throws Exception{
		JsonResult j = new JsonResult();
		if(customUserMapper.addReceit(scripture_user_id) > 0){
			j.setSuccess(true);
			j.setMsg(MSG_CONST.ADDSUCCESS);
		}else{
			j.setSuccess(false);
			j.setMsg(MSG_CONST.ADDFAIL);
		}
		return j;
	}
	//新人添加
	@ResponseBody
	@RequestMapping("/addScriptureUser")
	public JsonResult addScriptureUser(String scripture_user_name) throws Exception{
		JsonResult j = new JsonResult();
		if(customUserMapper.addScriptureUser(scripture_user_name) > 0){
			j.setSuccess(true);
			j.setMsg(MSG_CONST.ADDSUCCESS);
		}else{
			j.setSuccess(false);
			j.setMsg(MSG_CONST.ADDFAIL);
		}
		return j;
	}
	//新人刪除
	@ResponseBody
	@RequestMapping("/delScriptureUser")
	public JsonResult delScriptureUser(String scripture_user_ids) throws Exception{
		JsonResult j = new JsonResult();
		List userList = SerialUtil.JsonToList(scripture_user_ids);
		if(customUserMapper.delScriptureUser(scripture_user_ids) > 0){
			j.setSuccess(true);
			j.setMsg(MSG_CONST.DELETESUCCESS);
		}else{
			j.setSuccess(false);
			j.setMsg(MSG_CONST.DELETEFAIL);
		}
		return j;
	}
}
