package com.cooksys.ftd.ticker.client;

import com.cooksys.ftd.ticker.dto.QuoteField;
import com.cooksys.ftd.ticker.dto.QuoteRequest;
import com.cooksys.ftd.ticker.dto.Quotes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TickerClient {

	public static void main(String[] args) {

		// List of fields that the client is requesting on a quote
		Set<QuoteField> fields = new HashSet<>(Collections.singletonList((QuoteField.LATEST_PRICE)));

		// Stock ticker symbols client is requesting
		Set<String> symbols = new HashSet<>(Arrays.asList("NIO", "TWTR"));

		// Time it will take to wait.
		int interval = 2000;

		// Encapsulating request object
		QuoteRequest request = new QuoteRequest(fields, symbols, interval);

		try {
			Socket socket = new Socket("localhost", 3000);
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(request);
			outputStream.flush();

			while (socket.isConnected()) {
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				Quotes quotes = (Quotes) inputStream.readObject();
				JAXBContext context = JAXBContext.newInstance(Quotes.class);
				Marshaller marshaller = context.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				
				marshaller.marshal(quotes, new FileOutputStream("xmlFile.xml"));
				marshaller.marshal(quotes, System.out);
			}

		} catch (JAXBException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
