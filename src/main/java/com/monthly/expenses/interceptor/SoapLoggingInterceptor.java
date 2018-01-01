package com.monthly.expenses.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.transport.TransportOutputStream;

public class SoapLoggingInterceptor implements ClientInterceptor {

	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
		ByteArrayTransportOutputStream os = new ByteArrayTransportOutputStream();
		try {
			messageContext.getRequest().writeTo(os);
		} catch (IOException e) {
			throw new WebServiceIOException(e.getMessage(), e);
		}
		String request = new String(os.toByteArray()).replaceAll("\n", "");
		System.out
				.println("Soap request\n----------------------------\n" + request + "\n----------------------------\n");
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		logMessageContext(messageContext);
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		logMessageContext(messageContext);
		return true;
	}

	private void logMessageContext(MessageContext messageContext) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			messageContext.getResponse().writeTo(os);
		} catch (IOException e) {
			throw new WebServiceIOException(e.getMessage(), e);
		}
		String response = new String(os.toByteArray());
		System.out.println(
				"Soap response\n----------------------------\n" + response + "\n----------------------------\n");
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {

	}

	class ByteArrayTransportOutputStream extends TransportOutputStream {

		private ByteArrayOutputStream outputStream;

		@Override
		public void addHeader(String name, String value) throws IOException {
			createOutputStream();
			String header = name + ": " + value + "\n";
			outputStream.write(header.getBytes());
		}

		public byte[] toByteArray() {
			return outputStream.toByteArray();
		}

		@Override
		protected OutputStream createOutputStream() throws IOException {
			if (outputStream == null) {
				outputStream = new ByteArrayOutputStream();
			}
			return outputStream;
		}
	}
}