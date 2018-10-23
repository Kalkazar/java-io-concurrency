package com.cooksys.ftd.ticker.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Quotes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 191263628883294792L;
	
	@XmlElement (name = "quote")
	private Set<InnerQuote> quotes;
	
	public Quotes() {
		quotes = new HashSet<>();
	}

	public Set<InnerQuote> getQuotes() {
		return quotes;
	}

	public void setQuotes(Set<InnerQuote> quotes) {
		this.quotes = quotes;
	}
	
}
