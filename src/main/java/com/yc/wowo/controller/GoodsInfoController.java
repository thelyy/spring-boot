package com.yc.wowo.controller;



import java.io.File;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yc.wowo.bean.GoodsInfo;
import com.yc.wowo.biz.GoodsInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.RequestParamUtil;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController{
	@Autowired
	private GoodsInfoBiz good;
	
	@Value("${web.upload-path}")
	private String uploadPath;
	
	@RequestMapping("/findByGid")
	public ResultDTO findByGid(String gid) {
		GoodsInfo goods = good.findByGid(gid);
		if(goods == null) {
			return new ResultDTO(400);
		}
		return new ResultDTO(200, goods);
	}
	@RequestMapping("/findByFirst")
	public  Map<String, Object> findByFirst(@RequestParam Map<String, Object> map){
		
		Map<String, Object> result = new HashMap<String, Object>();
		//要返回第一页的数据以及总记录数
		result.put("total", good.total());
		result.put("rows", good.finds(RequestParamUtil.findByPagetUtil(map)));
		return result;
	}
	

	@RequestMapping("/finds")
	public List<GoodsInfo> finds(@RequestParam Map<String, Object> map) {
		return good.finds(RequestParamUtil.findByPagetUtil(map));
	}
	
	@RequestMapping("/findByPage")
	public  JsonObject findByPage(@RequestParam Map<String, Object> map){
		return good.findByPage(RequestParamUtil.findByPagetUtil(map));
	}
	
	@RequestMapping("/findCondition")
	public JsonObject findCondition(@RequestParam Map<String, Object> map){
		return good.findByCondition(RequestParamUtil.findByPagetUtil(map));
	}
	
	@RequestMapping("/add")
	public ResultDTO add(GoodsInfo sf, MultipartFile good_pic, HttpServletRequest request) {
		String path = "wowo_pics";

		String savePath = "";
		File dest = null;
		if(good_pic != null && good_pic.getSize() > 0) {
			try {
				savePath = path + "/" + new Date().getTime() + "_" + good_pic.getOriginalFilename();
				System.out.println("savePath:" + savePath);
				dest = new File(uploadPath, savePath);
				good_pic.transferTo(dest);
				System.out.println("路径为" + uploadPath + savePath);
				sf.setPics("../" + savePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sf);
		int result = good.add(sf);
		System.out.println(result);
		if(result > 0) {
			return new ResultDTO(200, "成功");
		}
		return new ResultDTO(500, "失败");		
	}
	
	@RequestMapping("/update")
	public ResultDTO update(GoodsInfo tf) {
		int result = good.update(tf);
		if(result > 0) {
			return new ResultDTO(200, "成功");
		}else {
			return new ResultDTO(500, "失败");
		}	
		
	}
	
	@RequestMapping("/upload")
	public Map<String, Object> upload(MultipartFile upload, HttpServletRequest request) {
		String path = "wowo_pics";

		String savePath = "";
		File dest = null;
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		if(upload != null && upload.getSize() > 0) {
			try {
				savePath = path + "/" + new Date().getTime() + "_" + upload.getOriginalFilename();
				dest = new File(uploadPath, savePath);
				upload.transferTo(dest);
				
				result.put("filename", upload.getOriginalFilename());
				result.put("url","../../../" + savePath);
				result.put("uploaded", 1);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
}
