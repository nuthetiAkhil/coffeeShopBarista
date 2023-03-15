package com.CoffeShop.coffeeShop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.CoffeShop.CoffeMachine.Pojo.CoffeShop;
import com.CoffeShop.CoffeMachine.Pojo.IngredientBean;
import com.CoffeShop.CoffeMachine.Pojo.MenuBean;
import com.CoffeShop.CoffeMachine.baristoMachine.BaristoVendorDIspenser;
import com.CoffeShop.CoffeMachine.baristoMachine.MachineConsole;
import com.CoffeShop.CoffeMachine.exceptions.DrinkNotAvailableException;
import com.CoffeShop.CoffeMachine.util.DrinkUtil;



@SpringBootTest
class CoffeeShopApplicationTests {

	@Test
	public void displayTest() {
		BaristoVendorDIspenser baristoMachine = new BaristoVendorDIspenser();
		String mode="test";
		baristoMachine.start(mode);
		Map<String, List<? extends CoffeShop>> coffeShop = MachineConsole.coffeShopTest;

		assertEquals(9, coffeShop.get("ingredient").size());
		assertEquals(6, coffeShop.get("menuBeans").size());

	}

	@Test
	public void dispenseTest() {

		BaristoVendorDIspenser baristoMachine = new BaristoVendorDIspenser();
		String mode="test";
		baristoMachine.start(mode);
		Map<String, List<? extends CoffeShop>> coffeShop = MachineConsole.coffeShopTest;

		List<IngredientBean> ingredientbeans = coffeShop.get("ingredient").stream().map(x -> (IngredientBean) x)
				.collect(Collectors.toList());
		List<MenuBean> menuBeans = coffeShop.get("menuBeans").stream().map(x -> (MenuBean) x)
				.collect(Collectors.toList());
		
		
		
		assertEquals(10, ingredientbeans.get(0).getQuantity());
		try {
			menuBeans.get(0).dispense();
		} catch (DrinkNotAvailableException e) {
			e.printStackTrace();
		}
		assertEquals(9, ingredientbeans.get(0).getQuantity());
		
		assertEquals(8, ingredientbeans.get(3).getQuantity());
		assertEquals(9, ingredientbeans.get(8).getQuantity());
	}

	@Test
	public void reStockTest() {

		BaristoVendorDIspenser baristoMachine = new BaristoVendorDIspenser();
		String mode="test";
		baristoMachine.start(mode);
		Map<String, List<? extends CoffeShop>> coffeShop = MachineConsole.coffeShopTest;
		List<IngredientBean> ingredientbeans = coffeShop.get("ingredient").stream().map(x -> (IngredientBean) x)
				.collect(Collectors.toList());
		List<MenuBean> menuBeans = coffeShop.get("menuBeans").stream().map(x -> (MenuBean) x)
				.collect(Collectors.toList());
		
		
		assertEquals(10, ingredientbeans.get(0).getQuantity());
		try {
			menuBeans.get(0).dispense();
			
		} catch (DrinkNotAvailableException e) {
			e.printStackTrace();
		}
		assertEquals(9, ingredientbeans.get(0).getQuantity());
		assertEquals(8, ingredientbeans.get(3).getQuantity());
	
			DrinkUtil drinkUtil=new DrinkUtil();
			drinkUtil.restock(ingredientbeans);
			assertEquals(10, ingredientbeans.get(0).getQuantity());
			assertEquals(10, ingredientbeans.get(3).getQuantity());
			assertEquals(10, ingredientbeans.get(4).getQuantity());
	}

	@Test
	public void rebootTest() {
		
		BaristoVendorDIspenser baristoMachine = new BaristoVendorDIspenser();
		String mode="test";
		baristoMachine.start(mode);
		Map<String, List<? extends CoffeShop>> coffeShop = MachineConsole.coffeShopTest;
		List<IngredientBean> ingredientbeans = coffeShop.get("ingredient").stream().map(x -> (IngredientBean) x)
				.collect(Collectors.toList());
		List<MenuBean> menuBeans = coffeShop.get("menuBeans").stream().map(x -> (MenuBean) x)
				.collect(Collectors.toList());
		
			assertEquals(10, ingredientbeans.get(0).getQuantity());
			assertEquals(10, ingredientbeans.get(1).getQuantity());
			assertEquals(10, ingredientbeans.get(2).getQuantity());
			assertEquals(10, ingredientbeans.get(3).getQuantity());
			assertEquals(10, ingredientbeans.get(4).getQuantity());
			assertEquals(10, ingredientbeans.get(5).getQuantity());
			assertEquals(10, ingredientbeans.get(6).getQuantity());
			assertEquals(10, ingredientbeans.get(7).getQuantity());
		
	}

	@Test
	public void populatePriceTest() {
		BaristoVendorDIspenser baristoMachine = new BaristoVendorDIspenser();
		String mode="test";
		baristoMachine.start(mode);
		Map<String, List<? extends CoffeShop>> coffeShop = MachineConsole.coffeShopTest;
		List<IngredientBean> ingredientbeans = coffeShop.get("ingredient").stream().map(x -> (IngredientBean) x)
				.collect(Collectors.toList());
		List<MenuBean> menuBeans = coffeShop.get("menuBeans").stream().map(x -> (MenuBean) x)
				.collect(Collectors.toList());
		
		assertEquals(4.45f, menuBeans.get(0).getPrice());
		assertEquals(2.55f, menuBeans.get(1).getPrice());
		assertEquals(2.75f, menuBeans.get(2).getPrice());
		assertEquals(2.75f, menuBeans.get(3).getPrice());
		assertEquals(3.3000002f, menuBeans.get(4).getPrice());
		assertEquals(2.8999999f, menuBeans.get(5).getPrice());

	}

	@Test
	public void populateDrinksTest() {
	BaristoVendorDIspenser baristoMachine = new BaristoVendorDIspenser();
	String mode="test";
	baristoMachine.start(mode);
	Map<String, List<? extends CoffeShop>> coffeShop = MachineConsole.coffeShopTest;
	List<IngredientBean> ingredientbeans = coffeShop.get("ingredient").stream().map(x -> (IngredientBean) x)
			.collect(Collectors.toList());
	List<MenuBean> menuBeans = coffeShop.get("menuBeans").stream().map(x -> (MenuBean) x)
			.collect(Collectors.toList());			
			
	assertEquals("CaffeMocha", menuBeans.get(0).getPropertyMenuName());
	assertEquals("CaffeLatte", menuBeans.get(1).getPropertyMenuName());
	assertEquals("Coffee", menuBeans.get(2).getPropertyMenuName());
	assertEquals("DecafCoffee", menuBeans.get(3).getPropertyMenuName());
	assertEquals("CaffeAmericano", menuBeans.get(4).getPropertyMenuName());
	assertEquals("Cappuccino", menuBeans.get(5).getPropertyMenuName());
	
	}

}
