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
import com.ruoyi.project.system.domain.SysOrder;
import com.ruoyi.project.system.service.ISysOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 预览表Controller
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
@RestController
@RequestMapping("/system/order")
public class SysOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;

    /**
     * 查询预览表列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder)
    {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出预览表列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "预览表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取预览表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysOrderService.selectSysOrderById(id));
    }

    /**
     * 新增预览表
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "预览表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改预览表
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "预览表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除预览表
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "预览表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysOrderService.deleteSysOrderByIds(ids));
    }
}
