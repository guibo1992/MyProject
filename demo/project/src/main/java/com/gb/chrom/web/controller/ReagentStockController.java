package com.gb.chrom.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ReagentService;
import com.gb.chrom.core.service.ReagentStockService;
import com.gb.chrom.model.ReagentStockIn;
import com.gb.chrom.model.ReagentStockOut;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.ReagentQuery;
import com.gb.chrom.model.query.ReagentStockQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
@Controller
@RequestMapping(value = "/reagent/stock")
public class ReagentStockController {

	@Autowired
	private ReagentService reagentService;
	@Autowired
	private ReagentStockService reagentStockService;

	@RequestMapping(value = "/list.html")
	public String reagentStockList(HttpServletRequest request) {
		return "view/reagent-stock";
	}

	@RequestMapping(value = "/in/list.html")
	public String reagentStockInList(HttpServletRequest request) {
		return "view/reagent-stock-in";
	}

	@RequestMapping(value = "/out/list.html")
	public String reagentStockOutList(HttpServletRequest request) {
		return "view/reagent-stock-out";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getReagentStockList(ReagentQuery query) {
		return PaginatorJsonResult.getPaginatorResult(reagentService.queryReagentForPagingList(query));
	}

	@RequestMapping(value = "/in/list")
	public @ResponseBody PaginatorJsonResult getReagentStockInList(ReagentStockQuery query) {
		return PaginatorJsonResult.getPaginatorResult(reagentStockService.queryReagentStockInForPagingList(query));
	}

	@RequestMapping(value = "/out/list")
	public @ResponseBody PaginatorJsonResult getReagentStockOutList(ReagentStockQuery query) {
		return PaginatorJsonResult.getPaginatorResult(reagentStockService.queryReagentStockOutForPagingList(query));
	}

	@RequestMapping(value = "/{id}/put-in")
	public @ResponseBody JsonResult reagentPutIn(@PathVariable("id") long id, ReagentStockIn stockIn) {
		stockIn.setReagentId(id);

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		stockIn.setOperatorId(user.getId());
		stockIn.setOperator(user.getName());

		return JsonResult.getJsonResult(reagentStockService.reagentPutInStorage(stockIn), "试剂入库失败!");
	}

	@RequestMapping(value = "/{id}/put-out")
	public @ResponseBody JsonResult reagentPutOut(@PathVariable("id") long id, ReagentStockOut stockOut) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);

		stockOut.setReagentId(id);
		stockOut.setOperatorId(user.getId());
		stockOut.setOperator(user.getName());

		return JsonResult.getJsonResult(reagentStockService.reagentPutOutStorage(stockOut), "试剂出库失败!");
	}

}
