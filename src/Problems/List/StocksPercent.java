package Problems.List;

public class StocksPercent {

    public double stockPercent;
    public double Stocks;

    public double getStockPercent() {
        return stockPercent;
    }

    public void setStockPercent(double stockPercent) {
        this.stockPercent = stockPercent;
    }

    public double getStocks() {
        return Stocks;
    }

    public void setStocks(double stocks) {
        Stocks = stocks;
    }

    public StocksPercent(double stockPercent, double stocks) {
        this.stockPercent = stockPercent;
        Stocks = stocks;
    }
}
