package stockanalyzer.ctrl;

import stockanalyzer.exception.YahooApiException;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class Controller {
    YahooFinance yahooFinance = new YahooFinance();

    public void process(String ticker) throws YahooApiException {
        System.out.println("Start process");

        //TODO implement Error handling
        //TODO implement methods for
        //1) Daten laden
        //2) Daten Analyse

        Stock stock = null;

        try {
            switch (ticker) {
                case "NOK":
                    getData("NOK");
                    break;
                case "FB":
                    getData("FB");
                    break;
                case "TSLA":
                    getData("TSLA");
                    break;
                case "NOK2":
                    getData("NOK");
                    stock = yahoofinance.YahooFinance.get("NOK");
                    stock.getHistory().forEach(System.out::println);
                    break;
                case "FB2":
                    getData("FB");
                    stock = yahoofinance.YahooFinance.get("FB");
                    stock.getHistory().forEach(System.out::println);
                    break;
                case "TSLA2":
                    getData("TSLA");
                    stock = yahoofinance.YahooFinance.get("TSLA");
                    stock.getHistory().stream().forEach(System.out::println);
                    break;

            }
        } catch (YahooApiException | IOException e) {
            throw new YahooApiException("Error - possible reason: " + e.getMessage());
        }
    }


    public Object getData(String searchString) throws YahooApiException {
        List<String> ticker = Arrays.asList(searchString);
        YahooResponse response = yahooFinance.getCurrentData(ticker);
        QuoteResponse quotes = response.getQuoteResponse();
        quotes.getResult().forEach(quote -> System.out.println(quote.getAsk() + "; " + quote.getShortName() + "; " + quote.getBid() + "; " + quote.getFiftyDayAverage()));
        return null;
    }


    public void closeConnection() {

    }
}
