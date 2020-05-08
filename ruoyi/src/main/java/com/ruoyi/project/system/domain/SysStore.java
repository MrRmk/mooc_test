package com.ruoyi.project.system.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 门店自提对象 sys_store
 * 
 * @author ruoyi
 * @date 2020-02-21
 */
public class SysStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String introduction;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 省市区 */
    @Excel(name = "省市区")
    private String address;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailedAddress;

    /** 门店logo */
    @Excel(name = "门店logo")
    private String image;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 核销有效日期 */
    @Excel(name = "核销有效日期")
    private String validTime;

    /** 每日营业开关时间 */
    @Excel(name = "每日营业开关时间")
    private String dayTime;

    /** 添加时间 */
    @Excel(name = "添加时间")
    private Date addTime;

    /** 是否显示 */
    @Excel(name = "是否显示")
    private Integer isShow;
    
    @Excel(name="审核结果")
    private String rejectionResult;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDel;

    /** 店铺状态：默认为0 */
    @Excel(name = "店铺状态：默认为0")
    private Integer status;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDetailedAddress(String detailedAddress) 
    {
        this.detailedAddress = detailedAddress;
    }

    public String getDetailedAddress() 
    {
        return detailedAddress;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setValidTime(String validTime) 
    {
        this.validTime = validTime;
    }

    public String getValidTime() 
    {
        return validTime;
    }
    public void setDayTime(String dayTime) 
    {
        this.dayTime = dayTime;
    }

    public String getDayTime() 
    {
        return dayTime;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setIsShow(Integer isShow) 
    {
        this.isShow = isShow;
    }

    public Integer getIsShow() 
    {
        return isShow;
    }
    public void setIsDel(Integer isDel) 
    {
        this.isDel = isDel;
    }

    public Integer getIsDel() 
    {
        return isDel;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public String getRejectionResult() {
		return rejectionResult;
	}

	public void setRejectionResult(String rejectionResult) {
		this.rejectionResult = rejectionResult;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("introduction", getIntroduction())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("detailedAddress", getDetailedAddress())
            .append("image", getImage())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .append("validTime", getValidTime())
            .append("dayTime", getDayTime())
            .append("addTime", getAddTime())
            .append("isShow", getIsShow())
            .append("rejectionResult",getRejectionResult())
            .append("isDel", getIsDel())
            .append("status", getStatus())
            .toString();
    }
}
