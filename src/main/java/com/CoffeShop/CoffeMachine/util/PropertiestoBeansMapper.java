package com.CoffeShop.CoffeMachine.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.CoffeShop.CoffeMachine.Pojo.CoffeShop;
import com.CoffeShop.CoffeMachine.Pojo.IngredientBean;
import com.CoffeShop.CoffeMachine.Pojo.MenuBean;


public class PropertiestoBeansMapper {

	public Map<String, List<? extends CoffeShop>> reboot(Properties menuWithIngredientsProps,Properties IngredientsProps,Properties menuNamesProps,Properties ingredientsNameProps) {

		return populateMenuAndIngredientBean(menuWithIngredientsProps, IngredientsProps, menuNamesProps,
				ingredientsNameProps);

	}

	public Map<String, List<? extends CoffeShop>> populateMenuAndIngredientBean(Properties menuWithIngredientsProps,
			Properties ingredientsProps, Properties menuNamesProps, Properties ingredientsNameStream) {

		Map<String, List<? extends CoffeShop>> coffeShop = new HashMap<>();
		Map<String, IngredientBean> ingredientBeanMap = new HashMap<>();
		List<IngredientBean> ingredientBeans =new ArrayList<>();
		
		ingredientBeans = ingredientsProps.entrySet().stream()
				.map(x -> IngredientBean.getIngredientBean(x, ingredientsNameStream.get(x.getKey()).toString()))
				.collect(Collectors.toList());

		ingredientBeans.stream().forEach(x -> ingredientBeanMap.put(x.getPropertyIngredientName().replaceAll(" ", ""), x));
		List<MenuBean> menuBeans = new ArrayList<MenuBean>();

		menuBeans = menuWithIngredientsProps.entrySet().stream()
				.map(x -> MenuBean.getMenuBean(x, ingredientBeanMap, menuNamesProps)).collect(Collectors.toList());
		
		
		
		coffeShop.put("ingredient", ingredientBeans);
		coffeShop.put("menuBeans", menuBeans);
		
		return coffeShop;

	}

}
