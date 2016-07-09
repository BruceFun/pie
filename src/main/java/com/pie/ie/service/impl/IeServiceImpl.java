package com.pie.ie.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pie.commons.QueryPageBase;
import com.pie.domain.ItemDetails;
import com.pie.ie.service.IIeService;
import com.pie.ie.service.ISystemDictionaryDetailsService;
import com.pie.ie.service.ISystemDictionaryTypeService;
import com.pie.repositories.IeRepository;
import com.pie.userCenter.service.IUserService;
import com.pie.utils.AppUtils;
import com.pie.utils.FlagEnum;
import com.pie.utils.PageUtils;
import com.pie.utils.ResultDataFormat;
import com.pie.vo.ItemDetailsString;

@Service("ieService")
@Transactional
public class IeServiceImpl implements IIeService {
	@Autowired
	private IeRepository ieRepository;
	@Autowired
	private ISystemDictionaryTypeService systemDictionaryTypeService;
	@Autowired
	private ISystemDictionaryDetailsService systemDictionaryDetailsService;
	@Autowired
	private IUserService userService;
	
	/**
	 * 保存支出详细
	 */
	@Override
	public ResultDataFormat saveExpenses(ItemDetailsString itemDetailsString) {
		System.out.println("保存开始……");
		String userId = "402882f15405e346015405e355bd0000";
		ResultDataFormat rf = new ResultDataFormat("保存成功", FlagEnum.SUCCESS.value());
		ItemDetails itemDetails = new ItemDetails();
		// 添加数据
		itemDetails.setId(AppUtils.getUUID());
		itemDetails.setItem(itemDetailsString.getItem());
		itemDetails.setMoney(new BigDecimal(itemDetailsString.getMoney()));
		itemDetails.setPayTime(AppUtils.formatStringToDate(itemDetailsString.getPayTime()));
		itemDetails.setSign("1");
		itemDetails.setExtraContent(itemDetailsString.getExtraContent());
		itemDetails.setTime(new Date());
		itemDetails.setType(systemDictionaryTypeService.getOneById(itemDetailsString.getTypeId()));
		itemDetails.setTypeDetails(systemDictionaryDetailsService.getOneById(itemDetailsString.getTypeDetailsId()));
		itemDetails.setUser(userService.getOneById(userId));
		itemDetails = this.setYearMonthDayValue(itemDetailsString.getPayTime(), itemDetails);
		// 保存
		ieRepository.save(itemDetails);
		// 减少总金额
		userService.updateMoneyReduces(userId, itemDetails.getMoney());
		
		System.out.println("保存结束……");
		return rf;
	}

	/**
	 * 保存收入详细
	 */
	@Override
	public ResultDataFormat saveIncome(ItemDetailsString itemDetailsString) {
		String userId = "402882f15405e346015405e355bd0000";
		ResultDataFormat rf = new ResultDataFormat("保存成功", FlagEnum.SUCCESS.value());
		ItemDetails itemDetails = new ItemDetails();
		// 添加数据
		itemDetails.setId(AppUtils.getUUID());
		itemDetails.setItem(itemDetailsString.getItem());
		itemDetails.setMoney(new BigDecimal(itemDetailsString.getMoney()));
		itemDetails.setPayTime(AppUtils.formatStringToDate(itemDetailsString.getPayTime()));
		itemDetails.setSign("2");
		itemDetails.setExtraContent(itemDetailsString.getExtraContent());
		itemDetails.setTime(new Date());
		itemDetails.setType(systemDictionaryTypeService.getOneById(itemDetailsString.getTypeId()));
		itemDetails.setTypeDetails(systemDictionaryDetailsService.getOneById(itemDetailsString.getTypeDetailsId()));
		itemDetails.setUser(userService.getOneById(userId));
		itemDetails = this.setYearMonthDayValue(itemDetailsString.getPayTime(), itemDetails);
		// 保存
		ieRepository.save(itemDetails);
		// 增加总金额
		userService.updateMoneyIncrease(userId, itemDetails.getMoney());
		
		return rf;
	}
	
	/**
	 * 编辑后保存
	 */
	@Override
	public ResultDataFormat editSave(ItemDetailsString itemDetailsString) {
		String userId = "402882f15405e346015405e355bd0000";
		ResultDataFormat rf = new ResultDataFormat("修改成功", FlagEnum.SUCCESS.value());
		ItemDetails itemDetails = new ItemDetails();
		// 构建数据
		itemDetails.setId(itemDetailsString.getId());
		itemDetails.setItem(itemDetailsString.getItem());
		itemDetails.setMoney(new BigDecimal(itemDetailsString.getMoney()));
		itemDetails.setPayTime(AppUtils.formatStringToDate(itemDetailsString.getPayTime()));
		itemDetails.setExtraContent(itemDetailsString.getExtraContent());
		itemDetails.setSign(itemDetailsString.getSign());
		itemDetails.setTime(AppUtils.formatStringToDate(itemDetailsString.getTime()));
		itemDetails.setType(systemDictionaryTypeService.getOneById(itemDetailsString.getTypeId()));
		itemDetails.setTypeDetails(systemDictionaryDetailsService.getOneById(itemDetailsString.getTypeDetailsId()));
		itemDetails.setUser(userService.getOneById(userId));
		itemDetails = this.setYearMonthDayValue(itemDetailsString.getPayTime(), itemDetails);
		
		// 得到原来的金额，以对应的调整总金额（调整金额）
		ItemDetails one = this.getOneId(itemDetails.getId());
		BigDecimal money = one.getMoney();
		BigDecimal subValue = money.subtract(itemDetails.getMoney());
		if(one.getSign().equals("1")){ // 表示支出
			if(money.compareTo(itemDetails.getMoney())== -1){  // 原来的金额少于现在的金额（多支出）
				userService.updateMoneyReduces(userId, subValue.abs());
			}else if(money.compareTo(itemDetails.getMoney()) == 1){  // 原来的金额多于现在的金额
				userService.updateMoneyIncrease(userId, subValue.abs());
			}
		}else if(one.getSign().equals("2")){
			if(money.compareTo(itemDetails.getMoney())== -1){  // 原来的金额少于现在的金额（多收入）
				userService.updateMoneyIncrease(userId, subValue.abs());
			}else if(money.compareTo(itemDetails.getMoney()) == 1){  // 原来的金额多于现在的金额
				userService.updateMoneyReduces(userId, subValue.abs());
			}
		}
		
		// 保存
		ieRepository.save(itemDetails);
		
		return rf;
	}

	/**
	 * 原生分页查询，无条件的
	 */
	@Override
	public ResultDataFormat getPagePIEDetails() {
		ResultDataFormat rf = new ResultDataFormat("查询成功", FlagEnum.SUCCESS.value());
		PageRequest pageable = new PageRequest(1, 10, Direction.DESC, "time");
		Page<ItemDetails> page = ieRepository.getPagePIEDetails(pageable);
		rf.setData(page.getContent());
		return rf;
	}

	/**
	 * 根据ID，删除一条记录
	 */
	@Override
	public ResultDataFormat deleteById(String id) {
		ResultDataFormat rf = new ResultDataFormat("删除成功", FlagEnum.SUCCESS.value());
		ItemDetails itemDetails = ieRepository.findOne(id);
		if(itemDetails == null){
			rf.setMsg("删除失败");
			rf.setFlag(FlagEnum.ERROR.value());
			return rf;
		}
		ieRepository.delete(id);
		return rf;
	}

	/**
	 * 根据条件得到分页
	 */
	@Override
	public ResultDataFormat getPageByQuery(final String month, final String startDate, final String endDate, final QueryPageBase queryPage) {
		final String userId = "402882f15405e346015405e355bd0000";
		ResultDataFormat rf = new ResultDataFormat("查询成功", FlagEnum.SUCCESS.value());
		PageRequest pageable = PageUtils.buildPageRequest(Integer.valueOf(queryPage.getPageNumber()), 
				Integer.valueOf(queryPage.getPageSize()), queryPage.getOrderField(), queryPage.getOrderDerection());
		Page<ItemDetails> pages = ieRepository.findAll(new Specification<ItemDetails>(){

			@Override
			public Predicate toPredicate(Root<ItemDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> condition = new ArrayList<Predicate>();
				
				Path<String> p_date = root.get("payTime");
				Path<String> p_month = root.get("month");
				Path<String> p_item = root.get("item");
				Path<String> p_userId = root.get("user").get("id");
				
				if(StringUtils.isNotBlank(queryPage.getKeyWords())){
					condition.add(cb.like(p_item.as(String.class), "%" + queryPage.getKeyWords() + "%"));
				}
				if(StringUtils.isNotBlank(month)){
					condition.add(cb.equal(p_month.as(String.class), month));
				}
				if(StringUtils.isNotBlank(startDate)){
					condition.add(cb.greaterThanOrEqualTo(p_date.as(String.class), startDate));
				}
				if(StringUtils.isNotBlank(endDate)){
					condition.add(cb.lessThanOrEqualTo(p_date.as(String.class), endDate));
				}
				condition.add(cb.equal(p_userId.as(String.class), userId));
				
				query.where(condition.toArray(new Predicate[condition.size()]));
				return query.getRestriction();
			}
		}, pageable);
		
		rf.setData(pages.getContent());
		rf.setPageNumber(Long.valueOf(queryPage.getPageNumber()));
		rf.setPageSize(Long.valueOf(queryPage.getPageSize()));
		rf.setTotalElements(pages.getTotalElements());
		rf.setTotalPages(Long.valueOf(String.valueOf(pages.getTotalPages())));
		
		return rf;
	}

	/**
	 * itemDetails
	 */
	@Override
	public ItemDetails setYearMonthDayValue(String date, ItemDetails itemDetails) {
		String date1 = date.split(" ")[0].trim();
		String[] dates = date1.split("-");
		
		String y = dates[0].trim();
		String m = dates[1].trim();
		String d = dates[2].trim();
		if(m.charAt(0)=='0'){
			m = String.valueOf(m.charAt(1));
		}
		if(d.charAt(0)=='0'){
			d = String.valueOf(d.charAt(1));
		}
		
		itemDetails.setYear(y);
		itemDetails.setMonth(m);
		itemDetails.setDay(d);
		return itemDetails;
	}

	/**
	 * 根据ID，得到一个对象
	 */
	@Override
	public ItemDetails getOneId(String id) {
		return ieRepository.getOne(id);
	}

	/**
	 * 根据ID,得到一个对象，返回ResultDataFormat
	 */
	@Override
	public ResultDataFormat getOneById(String id) {
		ResultDataFormat rf = new ResultDataFormat("查询成功", FlagEnum.SUCCESS.value());
		ItemDetails one = this.getOneId(id);
		rf.setData(one);
		return rf;
	}
}
