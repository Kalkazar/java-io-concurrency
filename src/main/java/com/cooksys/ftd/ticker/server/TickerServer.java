package com.cooksys.ftd.ticker.server;

import com.cooksys.ftd.ticker.dto.QuoteField;
import com.cooksys.ftd.ticker.dto.QuoteRequest;
import com.cooksys.ftd.ticker.api.StockApi;
import com.cooksys.ftd.ticker.api.StockUtils;
import pl.zankowski.iextrading4j.api.stocks.Quote;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class TickerServer {
	
	public static void main(String[] args) {

		try {
			ServerSocket server = new ServerSocket(3000);
			while (true) {
				new Thread(new TickerServerHandler(server.accept())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}