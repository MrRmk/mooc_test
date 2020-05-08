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
import com.ruoyi.project.system.domain.SysKnowledge;
import com.ruoyi.project.system.service.ISysKnowledgeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 知识点功能Controller
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
@RestController
@RequestMapping("/system/knowledge")
public class SysKnowledgeController extends BaseController
{
    @Autowired
    private ISysKnowledgeService sysKnowledgeService;

    /**
     * 查询知识点功能列表
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysKnowledge sysKnowledge)
    {
        startPage();
        List<SysKnowledge> list = sysKnowledgeService.selectSysKnowledgeList(sysKnowledge);
        return getDataTable(list);
    }

    /**
     * 导出知识点功能列表
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:export')")
    @Log(title = "知识点功能", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysKnowledge sysKnowledge)
    {
        List<SysKnowledge> list = sysKnowledgeService.selectSysKnowledgeList(sysKnowledge);
        ExcelUtil<SysKnowledge> util = new ExcelUtil<SysKnowledge>(SysKnowledge.class);
        return util.exportExcel(list, "knowledge");
    }

    /**
     * 获取知识点功能详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysKnowledgeService.selectSysKnowledgeById(id));
    }

    /**
     * 新增知识点功能
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:add')")
    @Log(title = "知识点功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysKnowledge sysKnowledge)
    {
        return toAjax(sysKnowledgeService.insertSysKnowledge(sysKnowledge));
    }

    /**
     * 修改知识点功能
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:edit')")
    @Log(title = "知识点功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysKnowledge sysKnowledge)
    {
        return toAjax(sysKnowledgeService.updateSysKnowledge(sysKnowledge));
    }

    /**
     * 删除知识点功能
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:remove')")
    @Log(title = "知识点功能", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysKnowledgeService.deleteSysKnowledgeByIds(ids));
    }
}
