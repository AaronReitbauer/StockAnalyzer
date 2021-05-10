package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import stockanalyzer.ctrl.Controller;
import stockanalyzer.download.ParallelDownloader;
import stockanalyzer.download.SequentialDownloader;
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
	public void getDataFromCtrl7(){
		try {
			ctrl.process("GOOG");
		} catch (YahooApiException e) {
			e.printStackTrace();
		}
	}

	public void getDataFromCtrlSequentialDownloader(){
		long start, end;
			var list = Arrays.asList("OMV.VI",
					"EBS.VI","DOC.VI","SBO.VI","RBI.VI","VIG.VI","TKA.VI","VOE.VI","FACC.VI","ANDR.VI","VER.VI",
					"WIE.VI","CAI.VI","BG.VI","POST.VI","LNZ.VI","UQA.VI","SPI.VI","ATS.VI","IIA.VI");
			SequentialDownloader sq = new SequentialDownloader();
		start = System.currentTimeMillis();
		ctrl.downloadTickers(list,sq);
		end =System.currentTimeMillis();
		System.out.println("Time for Parallel Download: "+ (end-start) + "ms");
	}
	public void getDataFromCtrlParallelDownloader(){
	long start, end;
		var list = Arrays.asList("OMV.VI",
				"EBS.VI","DOC.VI","SBO.VI","RBI.VI","VIG.VI","TKA.VI","VOE.VI","FACC.VI","ANDR.VI","VER.VI",
				"WIE.VI","CAI.VI","BG.VI","POST.VI","LNZ.VI","UQA.VI","SPI.VI","ATS.VI","IIA.VI");
		ParallelDownloader pq = new ParallelDownloader();
		start = System.currentTimeMillis();
		ctrl.downloadTickers(list,pq);
		end =System.currentTimeMillis();
		System.out.println("Time for Parallel Download: "+ (end-start)+ "ms");
	}
	public void getDataFromCtrlParallelSequentialDownloader(){
		long start, end;
		var list = Arrays.asList("OMV.VI",
				"EBS.VI","DOC.VI","SBO.VI","RBI.VI","VIG.VI","TKA.VI","VOE.VI","FACC.VI","ANDR.VI","VER.VI",
				"WIE.VI","CAI.VI","BG.VI","POST.VI","LNZ.VI","UQA.VI","SPI.VI","ATS.VI","IIA.VI");
		SequentialDownloader sq = new SequentialDownloader();
		ParallelDownloader pq = new ParallelDownloader();
		start = System.currentTimeMillis();
		ctrl.downloadTickers(list,sq);
		end =System.currentTimeMillis();
		System.out.println("Time for Sequential Download: "+ (end-start)+ "ms");
		long sequTime= end-start;
		start = System.currentTimeMillis();
		ctrl.downloadTickers(list,pq);
		end = System.currentTimeMillis();
		System.out.println("Time for Parallel Download: "+ (end-start)+ "ms");
		long parrTime=end-start;
		System.out.println("Time Difference: "+ (sequTime-parrTime)+ "ms");
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
		menu.insert("g", "Google History", this::getDataFromCtrl7);
		menu.insert("h", "Download Sequential Tickers", this::getDataFromCtrlSequentialDownloader);
		menu.insert("i", "Download Parallel Tickers", this::getDataFromCtrlParallelDownloader);
		menu.insert("j", "Download Parallel & Sequential Tickers", this::getDataFromCtrlParallelSequentialDownloader);

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
