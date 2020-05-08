package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 任务对象 sys_mission
 * 
 * @author chenjb
 * @date 2020-05-07
 */
public class SysMission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private String missionNumber;

    /** 学校ID */
    @Excel(name = "学校ID")
    private Long schoolId;

    /** 课程名 */
    @Excel(name = "课程名")
    private String courseName;

    /** 讲师 */
    @Excel(name = "讲师")
    private Long teacherId;

    /** 课件地址 */
    @Excel(name = "课件地址")
    private String coursewareUrl;

    /** 课件类型(ppt,讲稿) */
    @Excel(name = "课件类型(ppt,讲稿)")
    private String coursewareType;

    /** 拍摄时间 */
    @Excel(name = "拍摄时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shotTime;

    /** 拍摄地点 */
    @Excel(name = "拍摄地点")
    private String shotLocation;

    /** 拍摄形式(虚拟抠像，访谈方式) */
    @Excel(name = "拍摄形式(虚拟抠像，访谈方式)")
    private String shotType;

    /** 摄像人员id */
    @Excel(name = "摄像人员id")
    private Long shotPersonnel;

    /** 后期人员id */
    @Excel(name = "后期人员id")
    private Long postPersonnel;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 审核附件地址 */
    @Excel(name = "审核附件地址")
    private String auditFileurl;

    /** 审核标签 */
    @Excel(name = "审核标签")
    private String auditLabel;

    /** 删除标志N未删除Y已删除 */
    @Excel(name = "删除标志N未删除Y已删除")
    private String isDeleted;

    /** 删除时间 */
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deletedTime;

    /** 添加时间 */
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 预约状态(0未预约，1已预约) */
    @Excel(name = "预约状态(0未预约，1已预约)")
    private String appointmentStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMissionNumber(String missionNumber) 
    {
        this.missionNumber = missionNumber;
    }

    public String getMissionNumber() 
    {
        return missionNumber;
    }
    public void setSchoolId(Long schoolId) 
    {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() 
    {
        return schoolId;
    }
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseName()
    {
        return courseName;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setCoursewareUrl(String coursewareUrl) 
    {
        this.coursewareUrl = coursewareUrl;
    }

    public String getCoursewareUrl() 
    {
        return coursewareUrl;
    }
    public void setCoursewareType(String coursewareType) 
    {
        this.coursewareType = coursewareType;
    }

    public String getCoursewareType() 
    {
        return coursewareType;
    }
    public void setShotTime(Date shotTime) 
    {
        this.shotTime = shotTime;
    }

    public Date getShotTime() 
    {
        return shotTime;
    }
    public void setShotLocation(String shotLocation) 
    {
        this.shotLocation = shotLocation;
    }

    public String getShotLocation() 
    {
        return shotLocation;
    }
    public void setShotType(String shotType) 
    {
        this.shotType = shotType;
    }

    public String getShotType() 
    {
        return shotType;
    }
    public void setShotPersonnel(Long shotPersonnel) 
    {
        this.shotPersonnel = shotPersonnel;
    }

    public Long getShotPersonnel() 
    {
        return shotPersonnel;
    }
    public void setPostPersonnel(Long postPersonnel) 
    {
        this.postPersonnel = postPersonnel;
    }

    public Long getPostPersonnel() 
    {
        return postPersonnel;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setAuditFileurl(String auditFileurl) 
    {
        this.auditFileurl = auditFileurl;
    }

    public String getAuditFileurl() 
    {
        return auditFileurl;
    }
    public void setAuditLabel(String auditLabel) 
    {
        this.auditLabel = auditLabel;
    }

    public String getAuditLabel() 
    {
        return auditLabel;
    }
    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
    {
        return isDeleted;
    }
    public void setDeletedTime(Date deletedTime) 
    {
        this.deletedTime = deletedTime;
    }

    public Date getDeletedTime() 
    {
        return deletedTime;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setAppointmentStatus(String appointmentStatus) 
    {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentStatus() 
    {
        return appointmentStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("missionNumber", getMissionNumber())
            .append("schoolId", getSchoolId())
            .append("courseName", getCourseName())
            .append("teacherId", getTeacherId())
            .append("coursewareUrl", getCoursewareUrl())
            .append("coursewareType", getCoursewareType())
            .append("shotTime", getShotTime())
            .append("shotLocation", getShotLocation())
            .append("shotType", getShotType())
            .append("shotPersonnel", getShotPersonnel())
            .append("postPersonnel", getPostPersonnel())
            .append("remarks", getRemarks())
            .append("auditFileurl", getAuditFileurl())
            .append("auditLabel", getAuditLabel())
            .append("isDeleted", getIsDeleted())
            .append("deletedTime", getDeletedTime())
            .append("submitTime", getSubmitTime())
            .append("appointmentStatus", getAppointmentStatus())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
