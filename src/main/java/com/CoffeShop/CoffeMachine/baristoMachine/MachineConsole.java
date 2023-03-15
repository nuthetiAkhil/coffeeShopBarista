package com.CoffeShop.CoffeMachine.baristoMachine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import com.CoffeShop.CoffeMachine.Pojo.CoffeShop;
import com.CoffeShop.CoffeMachine.Pojo.MenuBean;
import com.CoffeShop.CoffeMachine.exceptions.DrinkNotAvailableException;
import com.CoffeShop.CoffeMachine.util.DrinkUtil;

public class MachineConsole {

	public static Map<String, List<? extends CoffeShop>> coffeShopTest;
 
	public void start(Properties menuWithIngredientsProps, Properties IngredientsProps, Properties menuNamesProps,
			Properties ingredientsNameProps, String mode) {

		DrinkUtil drinkUtil = new DrinkUtil();
		LinkedHashMap<String, MenuBean> menuBeanMap = new LinkedHashMap<>();

		Map<String, List<? extends CoffeShop>> coffeShop = drinkUtil.start(menuWithIngredientsProps, IngredientsProps,
				menuNamesProps, ingredientsNameProps);

		drinkUtil.display(coffeShop, null);

		List<MenuBean> menuBeans = coffeShop.get("menuBeans").stream().map(x -> (MenuBean) x)
				.collect(Collectors.toList());

		boolean close = false;
		if (mode.equals("working")) {
			while (!close) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				try {
					String name = reader.readLine();
					if (name.equalsIgnoreCase("q")) {
						System.out.println("Machine down");
						close = true;
					} else if (name.equalsIgnoreCase("r")) {
						System.out.println("Machine reloaded");
						drinkUtil.restock(coffeShop.get("ingredient"));
						drinkUtil.display(coffeShop, null);
					} else if (isNumeric(name)) {
						if (Integer.parseInt(name) > 0
								&& coffeShop.get("menuBeans").size() + 1 > Integer.parseInt(name)) {
							MenuBean menuBean = menuBeans.get(Integer.parseInt(name) - 1).dispense();
							drinkUtil.display(coffeShop, menuBean);

						} else {
							throw new DrinkNotAvailableException("Please select from the menu");
						}
					} else {
						System.out.println("Command not recognized");
					}
				} catch (DrinkNotAvailableException e) {
					if (e.getMessage().equals("Drink is Not avaiable")) {
						System.out.println("Drink is Not avaiable");
					} else if (e.getMessage().equals("Please select from the menu")) {
						System.out.println("Please select from the menu");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		coffeShopTest = coffeShop;
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
