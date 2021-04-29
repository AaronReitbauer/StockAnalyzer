package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import stockanalyzer.ctrl.Controller;
import stockanalyzer.exception.YahooApiException;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		try {
			ctrl.process("NOK");
		} catch (YahooApiException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl2()  {
		try {
			ctrl.process("FB");
		} catch (YahooApiException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrl3()  {
		try {
			ctrl.process("TSLA");
		} catch (YahooApiException e) {
			e.printStackTrace();
		}

	}
	public void getDataFromCtrl4()  {
		try {
			ctrl.process("NOK2");
		} catch (YahooApiException e) {
			e.printStackTrace();
		}

	}
	public void getDataFromCtrl5()  {
		try {
			ctrl.process("FB2");
		} catch (YahooApiException e) {
			e.printStackTrace();
		}

	}
	public void getDataFromCtrl6(){
		try {
			ctrl.process("TSLA2");
		} catch (YahooApiException e) {
			e.printStackTrace();
		}
	}
	
	public void getDataForCustomInput() {
		
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Nokia", this::getDataFromCtrl1);
		menu.insert("b", "Facebook", this::getDataFromCtrl2);
		menu.insert("c", "Tesla", this::getDataFromCtrl3);
		menu.insert("d", "Nokia History", this::getDataFromCtrl4);
		menu.insert("e", "Facebook History", this::getDataFromCtrl5);
		menu.insert("f", "Tesla History", this::getDataFromCtrl6);

		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		ctrl.closeConnection();
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
