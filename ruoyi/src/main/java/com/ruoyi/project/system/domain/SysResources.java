package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 资源对象 sys_resources
 * 
 * @author chenjb
 * @date 2020-05-07
 */
public class SysResources extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 素材名称 */
    @Excel(name = "素材名称")
    private String name;

    /** 摄像人员Id */
    @Excel(name = "摄像人员Id")
    private Long shotPersonnel;

    /** $column.columnComment */
    @Excel(name = "摄像人员Id")
    private String fileUrl;

    /** 状态(0未制作，1已制作) */
    @Excel(name = "状态(0未制作，1已制作)")
    private String status;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long missionId;

    /** 知识点ID */
    @Excel(name = "知识点ID")
    private Long knowledgeId;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

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
    public void setShotPersonnel(Long shotPersonnel) 
    {
        this.shotPersonnel = shotPersonnel;
    }

    public Long getShotPersonnel() 
    {
        return shotPersonnel;
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
    public void setKnowledgeId(Long knowledgeId) 
    {
        this.knowledgeId = knowledgeId;
    }

    public Long getKnowledgeId() 
    {
        return knowledgeId;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("shotPersonnel", getShotPersonnel())
            .append("fileUrl", getFileUrl())
            .append("status", getStatus())
            .append("missionId", getMissionId())
            .append("knowledgeId", getKnowledgeId())
            .append("remarks", getRemarks())
            .append("isDeleted", getIsDeleted())
            .append("updateTime", getUpdateTime())
            .append("submitTime", getSubmitTime())
            .toString();
    }
}
