package com.ruoyi.project.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.SysResources;
import com.ruoyi.project.system.service.ISysResourcesService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 资源Controller
 * 
 * @author chenjb
 * @date 2020-05-07
 */
@RestController
@RequestMapping("/system/resources")
public class SysResourcesController extends BaseController
{
    @Autowired
    private ISysResourcesService sysResourcesService;

    /**
     * 查询资源列表
     */
    @PreAuthorize("@ss.hasPermi('system:resources:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysResources sysResources)
    {
        startPage();
        List<SysResources> list = sysResourcesService.selectSysResourcesList(sysResources);
        return getDataTable(list);
    }

    /**
     * 导出资源列表
     */
    @PreAuthorize("@ss.hasPermi('system:resources:export')")
    @Log(title = "资源", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysResources sysResources)
    {
        List<SysResources> list = sysResourcesService.selectSysResourcesList(sysResources);
        ExcelUtil<SysResources> util = new ExcelUtil<SysResources>(SysResources.class);
        return util.exportExcel(list, "resources");
    }

    /**
     * 获取资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:resources:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysResourcesService.selectSysResourcesById(id));
    }

    /**
     * 新增资源
     */
    @PreAuthorize("@ss.hasPermi('system:resources:add')")
    @Log(title = "资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysResources sysResources)
    {
        return toAjax(sysResourcesService.insertSysResources(sysResources));
    }

    /**
     * 修改资源
     */
    @PreAuthorize("@ss.hasPermi('system:resources:edit')")
    @Log(title = "资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysResources sysResources)
    {
        return toAjax(sysResourcesService.updateSysResources(sysResources));
    }

    /**
     * 删除资源
     */
    @PreAuthorize("@ss.hasPermi('system:resources:remove')")
    @Log(title = "资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysResourcesService.deleteSysResourcesByIds(ids));
    }
}
