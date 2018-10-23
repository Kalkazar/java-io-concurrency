package com.cooksys.ftd.ticker.dto;

import java.io.Serializable;
import java.util.*;

import javax.xml.bind.annotation.*;

import pl.zankowski.iextrading4j.api.stocks.Quote;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InnerQuote implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6872266443693967520L;
	
	@XmlElement (name = "symbol")
	private String symbol;
	
	@XmlElement (name = "fields")
	private Fields fields;
	
	public InnerQuote() {}
	
	public InnerQuote(String symbol, Fields field) {
		this.symbol = symbol;
		this.fields = field;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}
	
}
