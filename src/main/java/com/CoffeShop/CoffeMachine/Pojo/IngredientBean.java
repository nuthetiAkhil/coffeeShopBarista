package com.CoffeShop.CoffeMachine.Pojo;

import java.util.Map.Entry;

public class IngredientBean extends CoffeShop {

	private String IngredientName;
	private String propertyIngredientName;
	private Integer quantity;
	private Float price;

	public String getIngredientName() {
		return IngredientName;
	}

	public void setIngredientName(String ingredientName) {
		IngredientName = ingredientName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPropertyIngredientName() {
		return propertyIngredientName;
	}

	public void setPropertyIngredientName(String propertyIngredientName) {
		this.propertyIngredientName = propertyIngredientName.replaceAll(" ", "");
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public static IngredientBean getIngredientBean(Entry<Object, Object> obj, String ingredientName) {
		IngredientBean ingredientBean = new IngredientBean();
		ingredientBean.setPropertyIngredientName(obj.getKey().toString().replaceAll(" ", ""));
		ingredientBean.setQuantity(10);
		ingredientBean.setPrice(Float.parseFloat(obj.getValue().toString()));
		ingredientBean.setIngredientName(ingredientName);
		return ingredientBean;
	}

}
