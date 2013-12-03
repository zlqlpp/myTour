package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Page;
import com.model.SearchPojo;
import com.model.WqInfo;
import com.service.WqInfoService;

@Controller
@RequestMapping("/wq")
public class WqController {
	private final	Log logger =LogFactory.getLog(WqController.class);
	
	@Autowired
	private WqInfoService wqinfoService;
	@RequestMapping("/add.do")
    public  String sayHello(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("sdfsdfs");
		System.out.println("sdfsdfsdf");
		try {
			request.setCharacterEncoding("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(request.getParameter("onee"));
		logger.info(request.getParameter("twoo"));
		logger.info(request.getParameter("threee"));
		logger.info(request.getParameter("fourr"));
		logger.info(request.getParameter("fivee"));
		logger.info(request.getParameter("sixx"));
		logger.info(request.getParameter("sevenn"));
		logger.info(request.getParameter("eightt"));
		logger.info(request.getParameter("ninee"));
		
		WqInfo wqinfo = new WqInfo();
		
		//wqinfo.setId(id);
		wqinfo.setWangwang(request.getParameter("onee"));
		
/*		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		wqinfo.setCreateStoreTime(request.getParameter("twoo"));
		wqinfo.setStroeURL(request.getParameter("threee").toString());
		wqinfo.setAddress(request.getParameter("fourr").toString());
		wqinfo.setStoreLevel(request.getParameter("fivee").toString());
		wqinfo.setName(request.getParameter("sixx").toString());
		wqinfo.setImgURL(request.getParameter("sevenn").toString());
		wqinfo.setSellCount(request.getParameter("eightt").toString());
		wqinfo.setPrice(request.getParameter("ninee").toString());
		wqinfo.setInsertTime(new Date());
		
		wqinfoService.saveWqInfo(wqinfo);
		
		List<WqInfo> list = wqinfoService.findAll();
		System.out.println(list.size());
		return "saveSuccess";
	}
	
	
	@RequestMapping("/showAll.do")
    public  String showAll(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("sdfsdfs");
		List<?> list = wqinfoService.findAll();
		String json="{\"total\" : \"4\", \"rows\": ";
		try {
			//response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONArray jsonArray2 = JSONArray.fromObject( list );
			System.out.println(jsonArray2.toString());
			
			out.print(json+="}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/listPage.do")
    public  String listPage(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("sdfsdfs");
		
		return "showAll";
	}
	@RequestMapping("/downLoadByDate.do")
    public  String downLoadByDate(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("sdfsdfs");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		SearchPojo sp = new SearchPojo();
		sp.setStartTime(startTime);
		sp.setEndTime(endTime);
		
		Page page = new Page();
		List list = wqinfoService.downLoadByDate(sp, page);
		
		try {
			//PrintWriter out = response.getWriter();
			String title="旺旺id,实体店地址,创店时间,商品名称,图片地址,商品价格,销售量,店铺级别,店铺地址\n";
			String contents="";
			WqInfo wqinfo = new WqInfo();
			for(int i=0;i<list.size();i++){
				wqinfo = (WqInfo) list.get(i);
				contents+=wqinfo.getWangwang()+","+wqinfo.getAddress().toString()+","+wqinfo.getCreateStoreTime().toString()+
						","+wqinfo.getName().substring(35, wqinfo.getName().length())+","+wqinfo.getId()+","+wqinfo.getPrice()+","+wqinfo.getSellCount()+
						","+wqinfo.getStoreLevel()+","+wqinfo.getStroeURL()+"\n";
			}
			
			OutputStream o = null;
			 response.setContentType("application/download;charset=UTF-8");
             response.setContentType("Content-type:application/vnd.ms-excel;charset=UTF-8");
             response.setHeader("Content-disposition","attachment;filename=\"WQinfo" + startTime+"--"+endTime + ".csv\"");
             o = response.getOutputStream();
             o.write((title+contents).toString().getBytes("GBK"));
			//out.print(title+title2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
