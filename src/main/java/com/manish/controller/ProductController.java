package com.manish.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.manish.Entity.Category;
import com.manish.Entity.Price;
import com.manish.Entity.Product;
import com.manish.Entity.Stock;
import com.manish.model.ProductResponse;
import com.manish.model.Productsorted;
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
		List<Product> productDB = productService.getAllProducts();

		List<ProductResponse> productLists = new ArrayList<>();

		for (Product p : productDB) {
			ProductResponse productResponse = new ProductResponse();

			productResponse.setProductName(p.getProductName());
			productResponse.setProductDescription(p.getProductDescription());
			productResponse.setProductCode(p.getProductCode());

			Category categoryDB = p.getCategory();
			if (!Objects.isNull(categoryDB))
				productResponse.setCategoryName(categoryDB.getCategoryName());

			Price priceDB = p.getPrice();
			if (!Objects.isNull(priceDB)) {
				productResponse.setCurrency(priceDB.getCurrency());
				productResponse.setPrice(priceDB.getPrice());
			}

			Stock stockDB = p.getStock();
			if (!Objects.isNull(stockDB)) {
				productResponse.setLocation(stockDB.getLocation());
				productResponse.setInventory(stockDB.getInventoryAvailable());
			}
			productLists.add(productResponse);

		}

		theModel.addAttribute("limit", 4);
		theModel.addAttribute("offset", 0);
		theModel.addAttribute("sortBy", "");
		theModel.addAttribute("count", productService.getCount());
		theModel.addAttribute("productList", productLists);
		return "product";
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute SaveProduct saveProduct) { // save Product to DB
		Product product = new Product();
		Price price = new Price();
		Stock stock = new Stock();
		Category category = new Category();

		price.setPrice(saveProduct.getProductPrice());
		price.setCurrency(saveProduct.getCurrency());

		stock.setLocation(saveProduct.getLocation());
		stock.setInventoryAvailable(saveProduct.getInvaentoryAvailable());

		category.setCategoryName(saveProduct.getCategoryName());

		product.setProductCode(saveProduct.getProductCode());
		product.setProductDescription(saveProduct.getProductDescription());
		product.setProductName(saveProduct.getProductName());
		
		product.setCategory(category);
		product.setPrice(price);
		product.setStock(stock);

		price.setProduct(product);
		stock.setProduct(product);
		
		List<Category>categories= categoryService.saveCategory(category.getCategoryName());
		if(categories!=null) {
			product.setCategory(categories.get(0));
		}
		else {
			ArrayList<Product> products=new ArrayList<Product>();
			products.add(product);
			category.setProduct(products);
		}
		productService.Save(product);
		return "redirect:/home";
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
		List<Productsorted> productDB = productService.getAllSortedProducts(sortBy, limit, offset);

		List<ProductResponse> productLists = new ArrayList<>();

		for (Productsorted p : productDB) {
			ProductResponse productResponse = new ProductResponse();

			productResponse.setProductName(p.getProduct_name());
			productResponse.setProductDescription(p.getProduct_description());
			productResponse.setProductCode(p.getProduct_code());

			productResponse.setCategoryName(p.getCategory_name());

			productResponse.setCurrency(p.getCurrency());
			productResponse.setPrice(p.getProduct_price());

			productResponse.setLocation(p.getLocation());
			productResponse.setInventory(p.getInventory_available());
			productLists.add(productResponse);

		}
		theModel.addAttribute("count", productService.getCount());
		theModel.addAttribute("limit", limit);
		theModel.addAttribute("offset", offset);
		theModel.addAttribute("sortBy", sortBy);
		theModel.addAttribute("productList", productLists);
		return "product";
	}

}
