package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysOrder;
import java.util.List;

/**
 * 预览表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
public interface SysOrderMapper 
{
    /**
     * 查询预览表
     * 
     * @param id 预览表ID
     * @return 预览表
     */
    public SysOrder selectSysOrderById(Long id);

    /**
     * 查询预览表列表
     * 
     * @param sysOrder 预览表
     * @return 预览表集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增预览表
     * 
     * @param sysOrder 预览表
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    /**
     * 修改预览表
     * 
     * @param sysOrder 预览表
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 删除预览表
     * 
     * @param id 预览表ID
     * @return 结果
     */
    public int deleteSysOrderById(Long id);

    /**
     * 批量删除预览表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysOrderByIds(Long[] ids);
}
