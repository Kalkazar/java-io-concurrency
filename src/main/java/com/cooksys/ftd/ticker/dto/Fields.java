package com.cooksys.ftd.ticker.dto;

import com.cooksys.ftd.ticker.dto.QuoteField;
import pl.zankowski.iextrading4j.api.stocks.Quote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

public class Fields implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5074916172344918138L;
	
	@XmlElement (name = "high")
	private BigDecimal high;
	
	@XmlElement (name = "low")
	private BigDecimal low;
	
	@XmlElement (name = "close")
	private BigDecimal close;
	
	@XmlElement (name = "open")
	private BigDecimal open;
	
	@XmlElement (name = "changePercent")
	private BigDecimal changePercent;
	
	@XmlElement (name = "change")
	private BigDecimal change;
	
	@XmlElement (name = "latestPrice")
	private BigDecimal latestPrice;
	
	public Fields() {}
	
	public Fields(Quote quote, Set<QuoteField> fields) {
		for (QuoteField field : fields) {
            switch (field) {
                case HIGH:
                    this.high = quote.getHigh();
                    break;
                case LOW:
                	this.low = quote.getLow();
                    break;
                case CLOSE:
                	this.close = quote.getClose();
                    break;
                case OPEN:
                	this.open = quote.getOpen();
                    break;
                case CHANGE_PERCENT:
                    this.changePercent = quote.getChangePercent();
                    break;
                case CHANGE:
                	this.change = quote.getChange();
                    break;
                case LATEST_PRICE:
                	this.latestPrice = quote.getLatestPrice();
                	break;
            }
		}
	}
}
