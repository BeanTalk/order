package com.saituo.order.web.variable;

import com.google.common.collect.Maps;
import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.service.variable.SystemVariableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 字典类别控制器
 */
@Controller
@RequestMapping("/variable/dictionary-category")
public class DictionaryCategoryController {
    
    @Autowired
    private SystemVariableService systemVariableService;

    /**
     * 字典类别列表
     *
     * @param pageRequest 分页请求实体
     * @param filter 查询条件
     *
     * @return 响应页面:WEB-INF/page/variable/dictionary-category/list.html
     */
    @RequestMapping("list")
    public Page<Map<String, Object>> list(PageRequest pageRequest, @RequestParam Map<String, Object> filter) {

        return systemVariableService.findDictionaryCategories(pageRequest,filter);
    }

    /**
     * 判断字典类别代码是否唯一
     *
     * @param code 字典列别代码
     *
     * @return true 表示唯一，否则 false
     */
    @ResponseBody
    @RequestMapping("is-code-unique")
    public boolean isCodeUnique(String code) {
        return !systemVariableService.dictionaryCategoryCodeExists(code);
    }

    /**
     * 保存字典类别
     *
     * @param entity 字典类别实体 Map
     * @param redirectAttributes spring mvc 重定向属性
     *
     * @return 响应页面:WEB-INF/page/variable/dictionary-category/list.html
     */
    @RequestMapping("save")
    public String save(@RequestParam Map<String, Object> entity,RedirectAttributes redirectAttributes) {

        systemVariableService.saveDictionaryCategory(entity);
        redirectAttributes.addFlashAttribute("success", "保存成功");

        return "redirect:/variable/dictionary-category/list";
    }

    /**
     * 编辑字典类别,响应页面:WEB-INF/page/variable/dictionary-category/edit.html
     *
     * @param id 字典类别主键 ID
     * @param model spring mvc 的 Model 接口，主要是将 http servlet request 的属性返回到页面中
     *
     */
    @RequestMapping("edit")
    public void edit(Long id,Model model) {

        Map<String, Object> entity = Maps.newHashMap();

        if (id != null) {
            entity = systemVariableService.getDictionaryCategory(id);
        }

        model.addAttribute("entity",entity);

    }

    /**
     * 删除字典类别
     *
     * @param ids 字典类别主键 ID 集合
     * @param redirectAttributes spring mvc 重定向属性
     *
     * @return 响应页面:WEB-INF/page/variable/dictionary-category/list.html
     */
    @RequestMapping("delete")
    public String delete(@RequestParam List<Long> ids,RedirectAttributes redirectAttributes) {
        systemVariableService.deleteDictionaryCategories(ids);
        redirectAttributes.addFlashAttribute("success", "删除" + ids.size() + "条信息成功");
        return "redirect:/variable/dictionary-category/list";
    }
}
