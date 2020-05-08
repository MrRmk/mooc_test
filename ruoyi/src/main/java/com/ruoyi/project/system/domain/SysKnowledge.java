package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 知识点功能对象 sys_knowledge
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
public class SysKnowledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 知识点名称 */
    @Excel(name = "知识点名称")
    private String name;

    /** 后期制作Id */
    @Excel(name = "后期制作Id")
    private Long postPersonnel;

    /** $column.columnComment */
    @Excel(name = "后期制作Id")
    private String fileUrl;

    /** 状态(0未审核，1审核不通过,2审核通过) */
    @Excel(name = "状态(0未审核，1审核不通过,2审核通过)")
    private String status;

    /** 任务Id */
    @Excel(name = "任务Id")
    private Long missionId;

    /** 是否删除NY */
    @Excel(name = "是否删除NY")
    private String isDeleted;

    /** $column.columnComment */
    @Excel(name = "是否删除NY", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setPostPersonnel(Long postPersonnel) 
    {
        this.postPersonnel = postPersonnel;
    }

    public Long getPostPersonnel() 
    {
        return postPersonnel;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setMissionId(Long missionId) 
    {
        this.missionId = missionId;
    }

    public Long getMissionId() 
    {
        return missionId;
    }
    public void setIsDeleted(String isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() 
    {
        return isDeleted;
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
            .append("name", getName())
            .append("postPersonnel", getPostPersonnel())
            .append("fileUrl", getFileUrl())
            .append("status", getStatus())
            .append("missionId", getMissionId())
            .append("remark", getRemark())
            .append("isDeleted", getIsDeleted())
            .append("updateTime", getUpdateTime())
            .append("submitTime", getSubmitTime())
            .toString();
    }
}
