package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.vo.MissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysMissionMapper;
import com.ruoyi.project.system.domain.SysMission;
import com.ruoyi.project.system.service.ISysMissionService;

/**
 * 任务Service业务层处理
 * 
 * @author chenjb
 * @date 2020-05-07
 */
@Service
public class SysMissionServiceImpl implements ISysMissionService 
{
    @Autowired
    private SysMissionMapper sysMissionMapper;

    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public SysMission selectSysMissionById(Long id)
    {
        return sysMissionMapper.selectSysMissionById(id);
    }

    /**
     * 查询任务列表
     * 
     * @param sysMission 任务
     * @return 任务
     */
    @Override
    public List<SysMission> selectSysMissionList(SysMission sysMission)
    {
        return sysMissionMapper.selectSysMissionList(sysMission);
    }
    /**
     * 查询任务列表Vo
     *
     * @param sysMission 任务
     * @return 任务
     */
    @Override
    public List<MissionVo> selectMissionVoList(SysMission sysMission){
        return sysMissionMapper.selectMissionVoList(sysMission);
    };
    /**
     * 新增任务
     * 
     * @param sysMission 任务
     * @return 结果
     */
    @Override
    public int insertSysMission(SysMission sysMission)
    {
        return sysMissionMapper.insertSysMission(sysMission);
    }

    /**
     * 修改任务
     * 
     * @param sysMission 任务
     * @return 结果
     */
    @Override
    public int updateSysMission(SysMission sysMission)
    {
        sysMission.setUpdateTime(DateUtils.getNowDate());
        return sysMissionMapper.updateSysMission(sysMission);
    }

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deleteSysMissionByIds(Long[] ids)
    {
        return sysMissionMapper.deleteSysMissionByIds(ids);
    }

    /**
     * 删除任务信息
     * 
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public int deleteSysMissionById(Long id)
    {
        return sysMissionMapper.deleteSysMissionById(id);
    }
}
