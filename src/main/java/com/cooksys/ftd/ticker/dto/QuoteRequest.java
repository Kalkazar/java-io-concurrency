package com.cooksys.ftd.ticker.dto;

import javax.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuoteRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4019075731310126939L;

	@XmlElementWrapper
    @XmlElement(name = "field")
    private Set<QuoteField> fields;

    @XmlElementWrapper
    @XmlElement(name = "symbol")
    private Set<String> symbols;
    
    @XmlElement(name = "interval")
    private int interval;
    
    public QuoteRequest() {}

    public QuoteRequest(Set<QuoteField> fields, Set<String> symbols, int interval) {
        this.symbols = symbols;
        this.fields = fields;
        this.interval = interval;
    }

    public Set<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(Set<String> symbols) {
        this.symbols = symbols;
    }

    public Set<QuoteField> getFields() {
        return fields;
    }

    public void setFields(Set<QuoteField> fields) {
        this.fields = fields;
    }

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
    
}

