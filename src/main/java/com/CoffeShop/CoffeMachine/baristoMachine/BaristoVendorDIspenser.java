 package com.CoffeShop.CoffeMachine.baristoMachine;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BaristoVendorDIspenser {

	public static void main(String[] args) {
		BaristoVendorDIspenser baristoMachine=new BaristoVendorDIspenser();
		String mode="working";
		baristoMachine.start(mode);
	}
	
	
	/*
	 * This method will be reading from the properties files related to the names of
	 * menuItems, Ingrediants and passing it to machine console
	 */
	
	public void start(String mode) {

		
		Properties menuWithIngredientsProps = new Properties();
		Properties IngredientsProps = new Properties();
		Properties menuNamesProps = new Properties();
		Properties ingredientsNameProps = new Properties();

		try {
			InputStream ingredientsStream = BaristoVendorDIspenser.class.getResourceAsStream("/Ingredients.properties");
			InputStream menuWithIngredientsStream = BaristoVendorDIspenser.class
					.getResourceAsStream("/MenuWithIngredients.properties");
			InputStream menuNamesStream = BaristoVendorDIspenser.class.getResourceAsStream("/MenuNames.properties");
			InputStream ingredientsNameStream = BaristoVendorDIspenser.class
					.getResourceAsStream("/IngredientsName.properties");
			menuWithIngredientsProps.load(menuWithIngredientsStream);
			menuNamesProps.load(menuNamesStream);
			IngredientsProps.load(ingredientsStream);
			ingredientsNameProps.load(ingredientsNameStream);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		MachineConsole start = new MachineConsole();
		start.start(menuWithIngredientsProps, IngredientsProps, menuNamesProps, ingredientsNameProps,mode);

	}

}
