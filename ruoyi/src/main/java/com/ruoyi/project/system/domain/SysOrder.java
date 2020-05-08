package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 预览表对象 sys_order
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
public class SysOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 老师Id */
    @Excel(name = "老师Id")
    private Long teacherId;

    /** 摄像人员id */
    @Excel(name = "摄像人员id")
    private Long shotPersonnel;

    /** 任务Id */
    @Excel(name = "任务Id")
    private Long missionId;

    /** 拍摄开始时间 */
    @Excel(name = "拍摄开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shotStartTime;

    /** 拍摄结束时间 */
    @Excel(name = "拍摄结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shotEndTime;

    /** 0:已预约,1:已拍摄 */
    @Excel(name = "0:已预约,1:已拍摄")
    private String status;

    /** 提交时间 */
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setShotPersonnel(Long shotPersonnel) 
    {
        this.shotPersonnel = shotPersonnel;
    }

    public Long getShotPersonnel() 
    {
        return shotPersonnel;
    }
    public void setMissionId(Long missionId) 
    {
        this.missionId = missionId;
    }

    public Long getMissionId() 
    {
        return missionId;
    }
    public void setShotStartTime(Date shotStartTime) 
    {
        this.shotStartTime = shotStartTime;
    }

    public Date getShotStartTime() 
    {
        return shotStartTime;
    }
    public void setShotEndTime(Date shotEndTime) 
    {
        this.shotEndTime = shotEndTime;
    }

    public Date getShotEndTime() 
    {
        return shotEndTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teacherId", getTeacherId())
            .append("shotPersonnel", getShotPersonnel())
            .append("missionId", getMissionId())
            .append("shotStartTime", getShotStartTime())
            .append("shotEndTime", getShotEndTime())
            .append("status", getStatus())
            .append("submitTime", getSubmitTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
