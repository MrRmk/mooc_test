package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysKnowledgeMapper;
import com.ruoyi.project.system.domain.SysKnowledge;
import com.ruoyi.project.system.service.ISysKnowledgeService;

/**
 * 知识点功能Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
@Service
public class SysKnowledgeServiceImpl implements ISysKnowledgeService 
{
    @Autowired
    private SysKnowledgeMapper sysKnowledgeMapper;

    /**
     * 查询知识点功能
     * 
     * @param id 知识点功能ID
     * @return 知识点功能
     */
    @Override
    public SysKnowledge selectSysKnowledgeById(Long id)
    {
        return sysKnowledgeMapper.selectSysKnowledgeById(id);
    }

    /**
     * 查询知识点功能列表
     * 
     * @param sysKnowledge 知识点功能
     * @return 知识点功能
     */
    @Override
    public List<SysKnowledge> selectSysKnowledgeList(SysKnowledge sysKnowledge)
    {
        return sysKnowledgeMapper.selectSysKnowledgeList(sysKnowledge);
    }

    /**
     * 新增知识点功能
     * 
     * @param sysKnowledge 知识点功能
     * @return 结果
     */
    @Override
    public int insertSysKnowledge(SysKnowledge sysKnowledge)
    {
        return sysKnowledgeMapper.insertSysKnowledge(sysKnowledge);
    }

    /**
     * 修改知识点功能
     * 
     * @param sysKnowledge 知识点功能
     * @return 结果
     */
    @Override
    public int updateSysKnowledge(SysKnowledge sysKnowledge)
    {
        sysKnowledge.setUpdateTime(DateUtils.getNowDate());
        return sysKnowledgeMapper.updateSysKnowledge(sysKnowledge);
    }

    /**
     * 批量删除知识点功能
     * 
     * @param ids 需要删除的知识点功能ID
     * @return 结果
     */
    @Override
    public int deleteSysKnowledgeByIds(Long[] ids)
    {
        return sysKnowledgeMapper.deleteSysKnowledgeByIds(ids);
    }

    /**
     * 删除知识点功能信息
     * 
     * @param id 知识点功能ID
     * @return 结果
     */
    @Override
    public int deleteSysKnowledgeById(Long id)
    {
        return sysKnowledgeMapper.deleteSysKnowledgeById(id);
    }
}
