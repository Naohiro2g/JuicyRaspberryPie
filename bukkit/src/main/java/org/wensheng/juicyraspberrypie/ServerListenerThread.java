package org.wensheng.juicyraspberrypie;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class ServerListenerThread implements Runnable {
	private static final Logger logger = Logger.getLogger("MCR_Server"); // Logger for logging messages

	ServerSocket serverSocket;
	boolean running = true;
	private JuicyRaspberryPie plugin;

	ServerListenerThread(JuicyRaspberryPie plugin, SocketAddress bindAddress) throws IOException {
		this.plugin = plugin;
		serverSocket = new ServerSocket();
		serverSocket.setReuseAddress(true);
		serverSocket.bind(bindAddress);
	}

	public void run() {
		while (running) {
			try {
				Socket newConnection = serverSocket.accept();
				if (!running) return;
				plugin.handleConnection(new RemoteSession(plugin, newConnection));
			} catch (Exception e) {
				// if the server thread is still running raise an error
				if (running) {
					logger.warning("Error creating new connection");
					e.printStackTrace();
				}
			}
		}
		try {
			serverSocket.close();
		} catch (Exception e) {
			logger.warning("Error closing server socket");
			e.printStackTrace();
		}
	}
}
