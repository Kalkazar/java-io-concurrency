package com.cooksys.ftd.ticker.server;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.ticker.api.StockApi;
import com.cooksys.ftd.ticker.api.StockUtils;
import com.cooksys.ftd.ticker.dto.Fields;
import com.cooksys.ftd.ticker.dto.InnerQuote;
import com.cooksys.ftd.ticker.dto.QuoteField;
import com.cooksys.ftd.ticker.dto.QuoteRequest;
import com.cooksys.ftd.ticker.dto.Quotes;

import pl.zankowski.iextrading4j.api.stocks.Quote;

public class TickerServerHandler implements Runnable {
	
	private Socket clientSocket;
	
	public TickerServerHandler(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
			QuoteRequest qRequest = (QuoteRequest) inputStream.readObject();
			while (true) {
				// Fetch quotes for each
				Set<Quote> quotes = StockApi.fetchQuotes(qRequest);
				
				Quotes quoteBean = new Quotes();
				for (Quote q : quotes) {
					quoteBean.getQuotes().add(new InnerQuote(q.getSymbol(), new Fields(q, qRequest.getFields())));
				}
				
				// Write and flush formatted string to client socket
				ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				outputStream.writeObject(quoteBean);
				outputStream.flush();
				
				Thread.sleep(qRequest.getInterval());
			}

		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}		
	}

}
