package com.great.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.great.bean.AuditsDetail;
import com.great.service.DrugService;
import com.great.service.DrugTypeService;
import com.great.service.FactoryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/stock")
public class AuditsManagement {
	
	
	
	
	public ArrayList<Map> orders=new ArrayList<>();
	@Resource // 
	
	
	
	
	@Autowired
	private DrugService drugService;
	@Resource
	private DrugTypeService drugTypeService;
	@Resource
	private FactoryService factoryService;
//	@Log(thing = "添加采购审计表")
	@RequestMapping(value = "/purchase.action",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	 public ModelAndView toHomepage(HttpServletRequest request) {
		List<Map<String, Object>> secondType = drugTypeService.secondType();
		request.setAttribute("secondType", secondType);
		List<Map<String, Object>> factoris = factoryService.factoris();
		request.setAttribute("factoris", factoris);
		List<Map<String, Object>>  drugList = drugService.queryAll();
		/*String jsonStr = JSONArray.fromObject(drugList).toString();
		JSONArray ja = JSONArray.fromObject(jsonStr); 
		System.out.println(ja);*/
		request.setAttribute("drugList", drugList);
		
	 	ModelAndView mav = new ModelAndView();
	 	mav.setViewName("manage/storge_add");
		return mav;
	}
	@RequestMapping(value = "/add_storge.action",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	 public ModelAndView add_storge(HttpServletRequest request,HttpSession session,AuditsDetail detail) {
		
		
		
	 	ModelAndView mav = new ModelAndView();
	 	mav.setViewName("manage/storge_add");
		return mav;
	}
	
	@RequestMapping(value = "/addOrder.action",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String addOrder(@RequestBody String order) throws UnsupportedEncodingException {
		//AuditsDetail
		order=URLDecoder.decode(order, "UTF-8");
		order = order.substring(0,order.length() - 1);
		System.out.println("order="+order);
		String[] temp=order.split(",");
		JSONArray jsonArray=JSONArray.fromObject(temp);
		
		JSONObject  myJson = JSONObject.fromObject(order);

		Map m=myJson;
		orders.add(m);
		System.out.println("orders.size()="+orders.size());
		return null;
	}
	
	
	
}


