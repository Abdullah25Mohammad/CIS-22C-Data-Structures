/**
 * MutualFund.java
 * @author Your name
 * @author Partner's name
 * CIS 22C, Applied Lab 3
 */
import java.text.DecimalFormat;

public class MutualFund {
    private final String fundName;
    private final String ticker;
    private double pricePerShare;

    /**CONSTRUCTORS*/

    /**
     * One-argument constructor that assigns a fundName,
     * "no ticker" to the ticker and -1 to pricePerShare.
     * @param fundName the fund name
     */
    public MutualFund(String fundName) {
        this.fundName = fundName;
        this.ticker = "no ticker";
        this.pricePerShare = -1;
    }

    /**
     * Three-argument constructor.
     * @param fundName the mutual fund name
     * @param ticker the ticker symbol
     * @param pricePerShare the price per share
     */
    public MutualFund(String fundName, String ticker, double pricePerShare) {
        this.fundName = fundName;
        this.ticker = ticker;
        this.pricePerShare = pricePerShare;
    }

    /**ACCESSORS*/

    /**
     * Accesses the name of the fund
     * @return the fund name
     */
    public String getFundName() {
        return this.fundName;
    }

    /**
     * Accesses the ticker symbol
     * @return the ticker symbol
     */
    public String getTicker() {
        return this.ticker;
    }

    /**
     * Accesses the price per share
     * @return the price per share
     */
    public double getPricePerShare() {
        return this.pricePerShare;
    }

    /**MUTATORS*/

    /**
     * Updates the share price
     * @param pricePerShare the new share price
     */
    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    /**ADDITIONAL OPERATIONS*/

    /**
     * Creates a String of the mutual fund information
     * in the format:
     * <fundName>
     * <ticker>
     * Share Price: $<pricePerShare>
     * <new line>
     * @return The fund information.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");
        
        out.append(fundName + "\n");
        out.append(ticker + "\n");
        out.append("Share Price: $" + df.format(pricePerShare) + "\n");
        
        return out.toString();
    }
}
