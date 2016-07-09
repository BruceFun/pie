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
	 * ����֧����ϸ
	 */
	@Override
	public ResultDataFormat saveExpenses(ItemDetailsString itemDetailsString) {
		System.out.println("���濪ʼ����");
		String userId = "402882f15405e346015405e355bd0000";
		ResultDataFormat rf = new ResultDataFormat("����ɹ�", FlagEnum.SUCCESS.value());
		ItemDetails itemDetails = new ItemDetails();
		// �������
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
		// ����
		ieRepository.save(itemDetails);
		// �����ܽ��
		userService.updateMoneyReduces(userId, itemDetails.getMoney());
		
		System.out.println("�����������");
		return rf;
	}

	/**
	 * ����������ϸ
	 */
	@Override
	public ResultDataFormat saveIncome(ItemDetailsString itemDetailsString) {
		String userId = "402882f15405e346015405e355bd0000";
		ResultDataFormat rf = new ResultDataFormat("����ɹ�", FlagEnum.SUCCESS.value());
		ItemDetails itemDetails = new ItemDetails();
		// �������
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
		// ����
		ieRepository.save(itemDetails);
		// �����ܽ��
		userService.updateMoneyIncrease(userId, itemDetails.getMoney());
		
		return rf;
	}
	
	/**
	 * �༭�󱣴�
	 */
	@Override
	public ResultDataFormat editSave(ItemDetailsString itemDetailsString) {
		String userId = "402882f15405e346015405e355bd0000";
		ResultDataFormat rf = new ResultDataFormat("�޸ĳɹ�", FlagEnum.SUCCESS.value());
		ItemDetails itemDetails = new ItemDetails();
		// ��������
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
		
		// �õ�ԭ���Ľ��Զ�Ӧ�ĵ����ܽ�������
		ItemDetails one = this.getOneId(itemDetails.getId());
		BigDecimal money = one.getMoney();
		BigDecimal subValue = money.subtract(itemDetails.getMoney());
		if(one.getSign().equals("1")){ // ��ʾ֧��
			if(money.compareTo(itemDetails.getMoney())== -1){  // ԭ���Ľ���������ڵĽ���֧����
				userService.updateMoneyReduces(userId, subValue.abs());
			}else if(money.compareTo(itemDetails.getMoney()) == 1){  // ԭ���Ľ��������ڵĽ��
				userService.updateMoneyIncrease(userId, subValue.abs());
			}
		}else if(one.getSign().equals("2")){
			if(money.compareTo(itemDetails.getMoney())== -1){  // ԭ���Ľ���������ڵĽ������룩
				userService.updateMoneyIncrease(userId, subValue.abs());
			}else if(money.compareTo(itemDetails.getMoney()) == 1){  // ԭ���Ľ��������ڵĽ��
				userService.updateMoneyReduces(userId, subValue.abs());
			}
		}
		
		// ����
		ieRepository.save(itemDetails);
		
		return rf;
	}

	/**
	 * ԭ����ҳ��ѯ����������
	 */
	@Override
	public ResultDataFormat getPagePIEDetails() {
		ResultDataFormat rf = new ResultDataFormat("��ѯ�ɹ�", FlagEnum.SUCCESS.value());
		PageRequest pageable = new PageRequest(1, 10, Direction.DESC, "time");
		Page<ItemDetails> page = ieRepository.getPagePIEDetails(pageable);
		rf.setData(page.getContent());
		return rf;
	}

	/**
	 * ����ID��ɾ��һ����¼
	 */
	@Override
	public ResultDataFormat deleteById(String id) {
		ResultDataFormat rf = new ResultDataFormat("ɾ���ɹ�", FlagEnum.SUCCESS.value());
		ItemDetails itemDetails = ieRepository.findOne(id);
		if(itemDetails == null){
			rf.setMsg("ɾ��ʧ��");
			rf.setFlag(FlagEnum.ERROR.value());
			return rf;
		}
		ieRepository.delete(id);
		return rf;
	}

	/**
	 * ���������õ���ҳ
	 */
	@Override
	public ResultDataFormat getPageByQuery(final String month, final String startDate, final String endDate, final QueryPageBase queryPage) {
		final String userId = "402882f15405e346015405e355bd0000";
		ResultDataFormat rf = new ResultDataFormat("��ѯ�ɹ�", FlagEnum.SUCCESS.value());
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
	 * ����ID���õ�һ������
	 */
	@Override
	public ItemDetails getOneId(String id) {
		return ieRepository.getOne(id);
	}

	/**
	 * ����ID,�õ�һ�����󣬷���ResultDataFormat
	 */
	@Override
	public ResultDataFormat getOneById(String id) {
		ResultDataFormat rf = new ResultDataFormat("��ѯ�ɹ�", FlagEnum.SUCCESS.value());
		ItemDetails one = this.getOneId(id);
		rf.setData(one);
		return rf;
	}
}
