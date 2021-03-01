package com.yc.wowo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yc.wowo.bean.ShopInfo;
import com.yc.wowo.biz.ShopInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.RequestParamUtil;

@RestController
@RequestMapping("/shop")
public class ShopInfoController {
	@Autowired
	private ShopInfoBiz shop;
	
	@RequestMapping("/finds")
	public List<ShopInfo> finds(){
		return shop.finds();
	}
	
	@RequestMapping("/findBySid")
	public ResultDTO findBySid(String sid) {
		ShopInfo shopinfo = shop.findBySid(sid);
		if(shopinfo == null) {
			return new ResultDTO(400);
		}
		return new ResultDTO(200, shopinfo);
	}
	
	@RequestMapping("/findCondition")
	public JsonObject findCondition(@RequestParam Map<String, Object> map){
		return shop.findByCondition(RequestParamUtil.findByPagetUtil(map));
	}
	
	@RequestMapping("/add")
	public ResultDTO add(ShopInfo sf, MultipartFile license_pic, MultipartFile[] shop_pic, HttpServletRequest request) {
		String path = request.getServletContext().getInitParameter("upliadPath");
		System.out.println(path);
		String basePath = request.getServletContext().getRealPath("");
		System.out.println(basePath);
		String savePath = "";
		File dest = null;
		if(license_pic != null && license_pic.getSize() > 0) {
			try {
				savePath = path + "/" + new Date().getTime() + "_" + license_pic.getOriginalFilename();
				dest = new File(new File(basePath).getParentFile(), savePath);
				license_pic.transferTo(dest);
				sf.setLicense("../" + savePath);
				System.out.println(savePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(shop_pic != null && shop_pic.length > 0 && shop_pic[0].getSize() > 0) {
			String picStr = "";
			try {
				for(MultipartFile pic : shop_pic) {
					savePath = path + "/" + new Date().getTime() + "_" + pic.getOriginalFilename();
					dest = new File(new File(basePath).getParentFile(), savePath);
					pic.transferTo(dest);
					if("".equals(picStr)) {
						picStr += "../" + savePath;
					}else{
						picStr += ";../" + savePath;
					}
				}
				sf.setPics(picStr);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sf);
		int result = shop.add(sf);
		System.out.println(result);
		if(result > 0) {
			return new ResultDTO(200, "成功");
		}
		return new ResultDTO(500, "失败");		
	}
	
	@RequestMapping("/findByPage")
	public JsonObject findByPage(@RequestParam Map<String, Object> map){
		return shop.findByPage(RequestParamUtil.findByPagetUtil(map));
	}
}
