package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysMission;
import com.ruoyi.project.system.domain.vo.MissionVo;

import java.util.List;

/**
 * 任务Mapper接口
 * 
 * @author chenjb
 * @date 2020-05-07
 */
public interface SysMissionMapper 
{
    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    public SysMission selectSysMissionById(Long id);

    /**
     * 查询任务列表
     * 
     * @param sysMission 任务
     * @return 任务集合
     */
    public List<SysMission> selectSysMissionList(SysMission sysMission);
    /**
     * 查询任务列表Vo
     *
     * @param sysMission 任务
     * @return 任务集合
     */
    public List<MissionVo> selectMissionVoList(SysMission sysMission);
    /**
     * 新增任务
     * 
     * @param sysMission 任务
     * @return 结果
     */
    public int insertSysMission(SysMission sysMission);

    /**
     * 修改任务
     * 
     * @param sysMission 任务
     * @return 结果
     */
    public int updateSysMission(SysMission sysMission);

    /**
     * 删除任务
     * 
     * @param id 任务ID
     * @return 结果
     */
    public int deleteSysMissionById(Long id);

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysMissionByIds(Long[] ids);
}
