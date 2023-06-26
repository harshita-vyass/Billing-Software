package com.bill.mvc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bill.mvc.dao.CategoryDao;
import com.bill.mvc.dao.ProductDao;
import com.bill.mvc.dto.BillViewDto;
import com.bill.mvc.dto.BillingDto;
import com.bill.mvc.entity.BillEntity;
import com.bill.mvc.entity.CategoryEntity;
import com.bill.mvc.entity.ProductEntity;
import com.bill.mvc.service.BillingServiceImpl;
import com.google.gson.Gson;

@Controller
public class HomeController {

	@Autowired
	ProductDao productDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	BillingServiceImpl billingService;

	@RequestMapping(value = "/")
	public ModelAndView fetchCategoryList(ModelMap modelMap) throws IOException {
		List<CategoryEntity> categoryList = categoryDao.view();
		modelMap.put("categoryList", categoryList);

		return new ModelAndView("home");
	}

	@ResponseBody
	@RequestMapping(value = "loadProductsByCategory/{id}", method = RequestMethod.GET)
	public String loadProductsByCategory(@PathVariable("id") int categoryId) {

		List<ProductEntity> productList = productDao.getProductListByCategoryId(categoryId);
		Gson gson = new Gson();

		return gson.toJson(productList);
	}

	@ResponseBody
	@RequestMapping(value = "getProductById/{id}", method = RequestMethod.GET)
	public String getProductById(@PathVariable("id") int id) {

		ProductEntity product = productDao.getProductById(id);
		Gson gson = new Gson();

		return gson.toJson(product);
	}

	@ResponseBody
	@RequestMapping(value = "/generateBill", method = RequestMethod.POST)
	public int generateBill(@RequestBody List<BillingDto> bill) {

		return billingService.generateBill(bill);
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	public ModelAndView getInvoices(ModelAndView model) {

		List<BillEntity> invoiceList = billingService.getAllBills();
		model.addObject("invoiceList", invoiceList);
		model.setViewName("invoices");
		return model;
	}

	@RequestMapping(value = "/invoices/{id}")
	public ModelAndView viewBill(@PathVariable("id") int id, ModelAndView model) throws IOException {

		BillViewDto billViewDto = billingService.viewBill(id);
		model.addObject("bill", billViewDto);
		model.setViewName("bill");

		return model;
	}

	@RequestMapping(value = "/inventory")
	public ModelAndView views(ModelAndView model) throws IOException {

		List<ProductEntity> listProduct = productDao.view();
		model.addObject("listProduct", listProduct);
		model.setViewName("inventory");

		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/inventory/update", method = RequestMethod.PUT)
	public void updateStock(@RequestParam("product_id") int productId, @RequestParam("quantity") int quantity) {
		productDao.updateProductQuantity(productId, quantity);
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView getReportss() {
		return new ModelAndView("error");
	}

}
