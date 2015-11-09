package cn.baiing.config;


public class SystemConfig {
	
	//用来实现从配置文件获取地区ID
	private String locationId;

	private String yjUrl;
	
	private String yjGroupId;
	
	public String getYjGroupId() {
		return yjGroupId;
	}

	public void setYjGroupId(String yjGroupId) {
		this.yjGroupId = yjGroupId;
	}

	public String getYjUrl() {
		return yjUrl;
	}

	public void setYjUrl(String yjUrl) {
		this.yjUrl = yjUrl;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	
}
