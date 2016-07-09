package com.pie.ie.web;

import java.util.List;
import java.util.Map;

import javax.faces.component.EditableValueHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pie.commons.BaseController;
import com.pie.commons.QueryPageBase;
import com.pie.domain.ItemDetails;
import com.pie.domain.SystemDictionaryType;
import com.pie.ie.service.IIeService;
import com.pie.ie.service.ISystemDictionaryDetailsService;
import com.pie.ie.service.ISystemDictionaryTypeService;
import com.pie.utils.FlagEnum;
import com.pie.utils.ResultDataFormat;
import com.pie.vo.ItemDetailsString;

@Controller
@RequestMapping("ie")
@SuppressWarnings("all")
public class IeController extends BaseController{
	@Autowired
	private ISystemDictionaryTypeService systemDictionaryTypeService;
	@Autowired
	private ISystemDictionaryDetailsService systemDictionaryDetailsService;
	@Autowired
	private IIeService ieService;
	
	/**
	 * 加载支出页面
	 * @param model
	 * @return
	 */
	@RequestMapping("expenses")
	public String expenses(Model model){
		ResultDataFormat all = systemDictionaryTypeService.getExpensesByType();
		model.addAttribute("allExpensesTypes", all);
		return "pages/ie/expenses";
	}
	
	/**
	 * 加载收入页面
	 * @param model
	 * @return
	 */
	@RequestMapping("income")
	public String income(Model model){
		ResultDataFormat all = systemDictionaryTypeService.getIncomeByType();
		model.addAttribute("allIncomeTypes", all);
		return "pages/ie/income";
	}
	
	/**
	 * 加载个人支出与收入明细页面
	 * @param model
	 * @return
	 */
	@RequestMapping("ieDetails")
	public String ieDetails(Model model){
		return "pages/ie/ie_details";
	}
	
	/**
	 * 保存支出详细
	 * @param itemDetailsString
	 * @param model
	 * @return
	 */
	@RequestMapping("saveExpenses")
	@ResponseBody
	public Map<String, Object> saveExpenses(ItemDetailsString itemDetailsString, Model model){
		return ieService.saveExpenses(itemDetailsString).convertResultData();
	}
	
	/**
	 * 保存收入详细
	 * @param itemDetailsString
	 * @param model
	 * @return
	 */
	@RequestMapping("saveIncome")
	@ResponseBody
	public Map<String, Object> saveIncome(ItemDetailsString itemDetailsString,Model model){
		return ieService.saveIncome(itemDetailsString).convertResultData();
	}
	
	/**
	 * 得到所有支出与收入明细
	 * @return
	 * 	暂时没有用
	 */
	@RequestMapping("getPagePIEDetails")
	public String getPagePIEDetails(Model model){
		model.addAttribute("allItemDetails", ieService.getPagePIEDetails());
		return "pages/ie/detailsTable";
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(String id,Model model){
		ResultDataFormat rf = ieService.deleteById(id);
		return rf.convertResultData();
	}
	
	/**
	 * 点击编辑时，回显数据
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(String id, Model model){
		ResultDataFormat rf = ieService.getOneById(id);
		ItemDetails items =  (ItemDetails)rf.getData();
		model.addAttribute("allTypes", systemDictionaryTypeService.getAll());
		model.addAttribute("oneObjectDetails", rf);
		model.addAttribute("allTypesDetails", systemDictionaryDetailsService.getDetailsByTypeId(items.getType().getId()));
		return "pages/ie/edit";
	}
	
	@RequestMapping("editSave")
	@ResponseBody
	public Map<String, Object> editSave(ItemDetailsString itemDetailsString){
		System.out.println("--------------------------------------------");
		return ieService.editSave(itemDetailsString).convertResultData();
	}
	
	/**
	 * 根据条件得到分页
	 * @param queryPage
	 * @param model
	 * @return
	 */
	@RequestMapping("getPageByQuery")
	public String getPageByQuery(String month,String startDate, String endDate, QueryPageBase queryPage, Model model){
		queryPage.setOrderField("payTime");
		queryPage.setOrderDerection("desc");
		ResultDataFormat rf =  ieService.getPageByQuery(month, startDate, endDate, queryPage);
		Map<String,Integer> map = super.caculatePage(Integer.valueOf(queryPage.getPageNumber()));
		model.addAttribute("retMap", rf);
		model.addAttribute("startPage", map.get("startPage"));
		model.addAttribute("endPage", map.get("endPage"));
		// 数据回显
		model.addAttribute("queryPage", queryPage);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("month", month);
		return "pages/ie/detailsTable";
	}
}
