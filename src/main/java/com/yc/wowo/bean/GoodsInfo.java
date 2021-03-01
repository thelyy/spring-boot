package com.yc.wowo.bean;

import java.io.Serializable;



import com.yc.wowo.util.StringUtil;

/**
 * 商品信息
 * company 源辰信息
 * @author navy
 * @date 2020�?10�?24�?
 * Email haijunzhou@hnit.edu.cn
 */
public class GoodsInfo implements Serializable {
	private static final long serialVersionUID = -5335873519528159804L;
	private Integer gid;
	private Integer sid;
	private String gname;
	private String ssid;
	private String ggid;
	private String pics;
	private String intro;
	private Double price;
	private Double rebate;
	private String sdate;
	private String edata;
	private String detail;
	private String license;
	private Integer status;
	private String keyword;
	private String sname;
	private String area;
	private String pic;
	private String pis;
	
	@Override
	public String toString() {
		return "GoodsInfo [gid=" + gid + ", sid=" + sid + ", gname=" + gname + ", ssid=" + ssid + ", ggid=" + ggid
				+ ", pics=" + pics + ", intro=" + intro + ", price=" + price + ", rebate=" + rebate + ", sdate=" + sdate
				+ ", edata=" + edata + ", detail=" + detail + ", license=" + license + ", status=" + status
				+ ", keyword=" + keyword + ", sname=" + sname + ", area=" + area + ", pic=" + pic + ", pis=" + pis
				+ "]";
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getGgid() {
		return ggid;
	}
	
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getSsid() {
		return ssid;
	}

	public void setGgid(String ggid) {
		this.ggid = ggid;
	}
	
	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	//goods
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		if (!StringUtil.checkNull(pic) && pic.contains(";")) { //说明有多张图�?
			this.pic = pic.split(";")[0];
		    return;
		}
		this.pic = pic;
	}
	//shop
	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		if (!StringUtil.checkNull(pis) && pis.contains(";")) { //说明有多张图�?
			this.pis = pis.split(";")[0];
		    return;
		}
		this.pis = pis;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdata() {
		return edata;
	}

	public void setEdata(String edata) {
		this.edata = edata;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		result = prime * result + ((edata == null) ? 0 : edata.hashCode());
		result = prime * result + ((ggid == null) ? 0 : ggid.hashCode());
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result + ((gname == null) ? 0 : gname.hashCode());
		result = prime * result + ((intro == null) ? 0 : intro.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((license == null) ? 0 : license.hashCode());
		result = prime * result + ((pic == null) ? 0 : pic.hashCode());
		result = prime * result + ((pics == null) ? 0 : pics.hashCode());
		result = prime * result + ((pis == null) ? 0 : pis.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((rebate == null) ? 0 : rebate.hashCode());
		result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + ((ssid == null) ? 0 : ssid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodsInfo other = (GoodsInfo) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (detail == null) {
			if (other.detail != null)
				return false;
		} else if (!detail.equals(other.detail))
			return false;
		if (edata == null) {
			if (other.edata != null)
				return false;
		} else if (!edata.equals(other.edata))
			return false;
		if (ggid == null) {
			if (other.ggid != null)
				return false;
		} else if (!ggid.equals(other.ggid))
			return false;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (gname == null) {
			if (other.gname != null)
				return false;
		} else if (!gname.equals(other.gname))
			return false;
		if (intro == null) {
			if (other.intro != null)
				return false;
		} else if (!intro.equals(other.intro))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (license == null) {
			if (other.license != null)
				return false;
		} else if (!license.equals(other.license))
			return false;
		if (pic == null) {
			if (other.pic != null)
				return false;
		} else if (!pic.equals(other.pic))
			return false;
		if (pics == null) {
			if (other.pics != null)
				return false;
		} else if (!pics.equals(other.pics))
			return false;
		if (pis == null) {
			if (other.pis != null)
				return false;
		} else if (!pis.equals(other.pis))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (rebate == null) {
			if (other.rebate != null)
				return false;
		} else if (!rebate.equals(other.rebate))
			return false;
		if (sdate == null) {
			if (other.sdate != null)
				return false;
		} else if (!sdate.equals(other.sdate))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (sname == null) {
			if (other.sname != null)
				return false;
		} else if (!sname.equals(other.sname))
			return false;
		if (ssid == null) {
			if (other.ssid != null)
				return false;
		} else if (!ssid.equals(other.ssid))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
