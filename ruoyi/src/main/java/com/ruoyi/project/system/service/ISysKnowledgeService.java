package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysKnowledge;
import java.util.List;

/**
 * 知识点功能Service接口
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
public interface ISysKnowledgeService 
{
    /**
     * 查询知识点功能
     * 
     * @param id 知识点功能ID
     * @return 知识点功能
     */
    public SysKnowledge selectSysKnowledgeById(Long id);

    /**
     * 查询知识点功能列表
     * 
     * @param sysKnowledge 知识点功能
     * @return 知识点功能集合
     */
    public List<SysKnowledge> selectSysKnowledgeList(SysKnowledge sysKnowledge);

    /**
     * 新增知识点功能
     * 
     * @param sysKnowledge 知识点功能
     * @return 结果
     */
    public int insertSysKnowledge(SysKnowledge sysKnowledge);

    /**
     * 修改知识点功能
     * 
     * @param sysKnowledge 知识点功能
     * @return 结果
     */
    public int updateSysKnowledge(SysKnowledge sysKnowledge);

    /**
     * 批量删除知识点功能
     * 
     * @param ids 需要删除的知识点功能ID
     * @return 结果
     */
    public int deleteSysKnowledgeByIds(Long[] ids);

    /**
     * 删除知识点功能信息
     * 
     * @param id 知识点功能ID
     * @return 结果
     */
    public int deleteSysKnowledgeById(Long id);
}
