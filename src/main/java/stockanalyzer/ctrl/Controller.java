package stockanalyzer.ctrl;

import stockanalyzer.exception.YahooApiException;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.spi.CalendarDataProvider;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    YahooFinance yahooFinance = new YahooFinance();

    public void process(String ticker) throws YahooApiException {
        System.out.println("Start process");

        //TODO implement Error handling
        //TODO implement methods for
        //1) Daten laden
        //2) Daten Analyse

        Stock stock = null;
        long count = 0;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH,-2);

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
                    stock.getHistory(cal, Interval.DAILY).forEach(System.out::println);
                    amountData(stock);
                    System.out.println("Anzahl der Datensätze: " +  amountData(stock));
                    System.out.println("Höchster Wert: " +  max(stock));
                    System.out.println("Tiefster Wert: " +  min(stock));
                    System.out.println("Durchschnitt: " +  average(stock));
                    break;
                case "FB2":
                    getData("FB");
                    stock = yahoofinance.YahooFinance.get("FB");
                    stock.getHistory(cal, Interval.DAILY).forEach(System.out::println);
                    amountData(stock);
                    System.out.println("Anzahl der Datensätze: " +  amountData(stock));
                    System.out.println("Höchster Wert: " +  max(stock));
                    System.out.println("Tiefster Wert: " +  min(stock));
                    System.out.println("Durchschnitt: " +  average(stock));
                    break;
                case "TSLA2":
                    getData("TSLA");
                    stock = yahoofinance.YahooFinance.get("TSLA");
                    stock.getHistory(cal, Interval.DAILY).forEach(System.out::println);
                    System.out.println("Anzahl der Datensätze: " +  amountData(stock));
                    System.out.println("Höchster Wert: " +  max(stock));
                    System.out.println("Tiefster Wert: " +  min(stock));
                    System.out.println("Durchschnitt: " +  average(stock));
                    break;
                case "GOOG":
                    getData("GOOG");
                    stock = yahoofinance.YahooFinance.get("GOOG",true);
                    stock.getHistory(cal, Interval.DAILY).forEach(System.out::println);
                    System.out.println("Anzahl der Datensätze: " +  amountData(stock));
                    System.out.println("Höchster Wert: " +  max(stock));
                    System.out.println("Tiefster Wert: " +  min(stock));
                    System.out.println("Durchschnitt: " +  average(stock));
                    break;

            }
        } catch (YahooApiException | IOException | NullPointerException e) {
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

    public long amountData(Stock stock) throws IOException, YahooApiException {
        return stock.getHistory().size();

    }
    public double average(Stock stock) throws IOException {
        return stock.getHistory().stream().mapToDouble(q->q.getClose().doubleValue()).average().orElse(0.0);

    }

    public double min(Stock stock) throws IOException {
        return stock.getHistory().stream().mapToDouble(q->q.getClose().doubleValue()).min().orElse(0.0);

    }
    public double max(Stock stock) throws IOException {
        return stock.getHistory().stream().mapToDouble(q->q.getClose().doubleValue()).max().orElse(0.0);

    }
    /*public void minMaxValue (Stock stock) throws IOException, YahooApiException {
        System.out.println("Max: "+stock.getHistory().stream().max(Comparator.comparing(HistoricalQuote::getHigh)));
        System.out.println("Min: "+stock.getHistory().stream().min(Comparator.comparing(HistoricalQuote::getLow)));

    }*/
    public void closeConnection() {

    }
}
