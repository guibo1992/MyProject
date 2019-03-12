package com.gb.chrom.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.TechnicalDocumentService;
import com.gb.chrom.model.TechnicalDocument;
import com.gb.chrom.model.query.TechnicalDocumentQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * <p>
 * 
 * @author Danson
 * 
 *         Created by 2018年5月13日
 * @since
 */
@Controller
@RequestMapping(value = "/technical/document")
public class TechnicalDocumentsController {

	@Autowired
	private TechnicalDocumentService documentService;

	@RequestMapping(value = "/list.html")
	public String technicalDocuments() {
		return "view/document-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult document(TechnicalDocumentQuery query) {
		return PaginatorJsonResult.getPaginatorResult(documentService.queryTechnicalDocumentForPagingList(query));
	}

	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult documentAdd(TechnicalDocument document, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参 数提交!");
		}
		return JsonResult.getJsonResult(documentService.addTechnicalDocument(document));
	}

	@RequestMapping(value = "/{id}/remove")
	public @ResponseBody JsonResult removeTechnicalDocument(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(documentService.deleteTechnicalDocument(id));
	}

}
