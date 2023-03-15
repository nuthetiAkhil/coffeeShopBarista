package com.CoffeShop.CoffeMachine.Pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.CoffeShop.CoffeMachine.exceptions.DrinkNotAvailableException;


public class MenuBean extends CoffeShop {

	private String menuName;
	private String PropertyMenuName;
	private List<IngredientBean> ingredients;
	private Map<String, Integer> recipe;
	private Float price;
	private boolean availability;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<IngredientBean> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientBean> ingredients) {
		this.ingredients = ingredients;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public boolean isAvailability() {
		boolean aviailable=true;
		for (IngredientBean ingredientBean : this.getIngredients()) {
			if (ingredientBean.getQuantity() < this.recipe.get(ingredientBean.getPropertyIngredientName().toString())) {
				aviailable=false;
				availability=aviailable;
				break;
			}else {
				availability=true;
			}
		}

		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getPropertyMenuName() {
		return PropertyMenuName;
	}

	public void setPropertyMenuName(String propertyMenuName) {
		PropertyMenuName = propertyMenuName.replaceAll(" ", "");
	}

	public static MenuBean getMenuBean(Entry<Object, Object> obj, Map<String, IngredientBean> ingredientBeanMap,
			Properties menuNames) {

		MenuBean menuBean = new MenuBean();
		menuBean.setMenuName(menuNames.getProperty(obj.getKey().toString().replaceAll(" ", "")));
		menuBean.setPropertyMenuName(obj.getKey().toString().replaceAll(" ", ""));
		Float menuPrice = 0f;
		List<IngredientBean> ingredientBeans = new ArrayList<>();
		Map<String, Integer> recipeMap = new HashMap<>();
		boolean availablity = true;
		for (String s : obj.getValue().toString().split(",")) {

			String[] ingredientRatio = s.split(":");
			menuPrice = menuPrice + (Float.parseFloat(ingredientRatio[1])
					* ingredientBeanMap.get(ingredientRatio[0].replaceAll(" ", "")).getPrice());
			availablity = ingredientBeanMap.get(ingredientRatio[0].replaceAll(" ", "")).getQuantity() > Integer
					.parseInt(ingredientRatio[1].replaceAll(" ", ""));
			ingredientBeans.add(ingredientBeanMap.get(ingredientRatio[0].replaceAll(" ", "")));
			recipeMap.put(ingredientRatio[0].replaceAll(" ", ""),
					Integer.parseInt(ingredientRatio[1].replaceAll(" ", "")));
		}
		menuBean.setPrice(menuPrice);
		menuBean.setAvailability(availablity);
		menuBean.setIngredients(ingredientBeans);
		menuBean.setRecipe(recipeMap);
		return menuBean;
	}

	@Override
	public boolean equals(Object obj) {
		return this.menuName.equals(((MenuBean) obj).getMenuName());
	}

	public Map<String, Integer> getRecipe() {
		return recipe;
	}

	public void setRecipe(Map<String, Integer> recipe) {
		this.recipe = recipe;
	}

	public MenuBean dispense() throws DrinkNotAvailableException {
		if (this.isAvailability() == false) {
			throw new DrinkNotAvailableException("Drink is Not avaiable");
		} else {
			for (IngredientBean i : ingredients) {
				i.setQuantity(i.getQuantity() - this.getRecipe().get(i.getPropertyIngredientName()));
			}
		}
		return this;
	}

}
