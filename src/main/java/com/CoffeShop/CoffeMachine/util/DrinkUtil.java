package com.CoffeShop.CoffeMachine.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import com.CoffeShop.CoffeMachine.Pojo.CoffeShop;
import com.CoffeShop.CoffeMachine.Pojo.IngredientBean;
import com.CoffeShop.CoffeMachine.Pojo.MenuBean;



public class DrinkUtil {

	PropertiestoBeansMapper properties;

	public DrinkUtil() {

		properties = new PropertiestoBeansMapper();

	}

	public Map<String, List<? extends CoffeShop>> start(Properties menuWithIngredientsProps,
			Properties IngredientsProps, Properties menuNamesProps, Properties ingredientsNameProps) {

		return properties.reboot(menuWithIngredientsProps, IngredientsProps, menuNamesProps, ingredientsNameProps);

	}

	public List<? extends CoffeShop> restock(List<? extends CoffeShop> coffeShopIngredients) {

		List<IngredientBean> ingredientBeans ;
		ingredientBeans = coffeShopIngredients.stream().map(x -> (IngredientBean) x).collect(Collectors.toList());
		ingredientBeans.stream().forEach(x -> x.setQuantity(10));
		
		return ingredientBeans;

	}

	public void display(Map<String, List<? extends CoffeShop>> coffeShop, MenuBean menuBean) {

		List<IngredientBean> ingredientBeans = new ArrayList<>();
		List<MenuBean> menuBeans = new ArrayList<>();
		LinkedHashMap<String, MenuBean> menuBeanMap = new LinkedHashMap<>();

		if (menuBean != null) {
			System.out.println("");
			DecimalFormat df = new DecimalFormat("#.##");
			String formatted = df.format(menuBean.getPrice());
			System.out.println("Dispensing :" + menuBean.getMenuName() + " $ " + formatted);
			System.out.println("");
		}
		menuBeans = coffeShop.get("menuBeans").stream().map(x -> (MenuBean) x).collect(Collectors.toList());
		menuBeans.stream().forEach(x -> menuBeanMap.put(x.getPropertyMenuName(), x));

		ingredientBeans = coffeShop.get("ingredient").stream().map(x -> (IngredientBean) x)
				.collect(Collectors.toList());

		System.out.println("----------");
		System.out.println("Inventory : ");
		System.out.println("");
		int c = 1;
		for (IngredientBean i : ingredientBeans) {

			System.out.println(c + " " + i.getIngredientName() + " : " + i.getQuantity());
			System.out.println("");
			c = c + 1;

		}

		System.out.println("");
		System.out.println("Menu : ");
		System.out.println("");
		int menuIndex = 1;

		for (MenuBean i : menuBeans) {
			
			DecimalFormat df = new DecimalFormat("#.##");
			String formatted = df.format(i.getPrice());
			System.out.println(menuIndex + " " + i.getMenuName() + " , $ " + formatted + " ," + i.isAvailability());
			System.out.println("");
			menuIndex++;

		}
		System.out.println("");
		System.out.println(" ---------- ");

	}

}
