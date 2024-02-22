package com.manish.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.manish.model.ProductResponse;
import com.manish.model.SaveProduct;
import com.manish.service.CategoryService;
import com.manish.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String getProduct(Model theModel) {

		List<ProductResponse> productDB = productService.getAllProducts();

		theModel.addAttribute("limit", 4);
		theModel.addAttribute("offset", 0);
		theModel.addAttribute("sortBy", "");
		theModel.addAttribute("count", productService.getCount());
		theModel.addAttribute("productList", productDB);
		return "product";
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute SaveProduct saveProduct, Model model) { // save Product to DB

		try {
			productService.Save(saveProduct);
			model.addAttribute("successMessage", "Product saved successfully!");
		} catch (com.manish.customExceptions.DuplicateKeyException ex) {
			ex.printStackTrace();
			model.addAttribute("errorMessage", ex.getMessage());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Duplicate Product code in Database");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Some exception has occured please try again");
		}
		return "addProduct";
	}

	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String addProduct() {
		return "addProduct";
	}

	@RequestMapping(path = "/product", method = RequestMethod.GET)
	public void getProduct(Model theModel, @RequestParam("productCode") String productCode) {

		theModel.addAttribute("product", productService.getProduct(productCode));
	}

	@RequestMapping(path = "/productSort", method = RequestMethod.GET)
	public String getSortedProduct(Model theModel,
			@RequestParam(value = "sortBy", defaultValue = "", required = false) String sortBy,
			@RequestParam(value = "limit", defaultValue = "4", required = false) int limit,
			@RequestParam(value = "offset", defaultValue = "0", required = false) int offset) {

		List<ProductResponse> productLists = productService.getAllSortedProducts(sortBy, limit, offset);

		theModel.addAttribute("count", productService.getCount());
		theModel.addAttribute("limit", limit);
		theModel.addAttribute("offset", offset);
		theModel.addAttribute("sortBy", sortBy);
		theModel.addAttribute("productList", productLists);
		return "product";
	}

	@RequestMapping(path = "/delete", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("productId") Long productId) {

		productService.delete(productId);

		return "redirect:/home";
	}

}
