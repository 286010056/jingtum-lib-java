package com.blink.jtblc.connection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Connection {
	private static WebSocket webSocket;
	
	public Connection(WebSocket webSocket) {
		this.webSocket = webSocket;
	}
	
	/**
	 *  发送请求
	 * 
	 * @param params 参数
	 * @return string
	 */
	public static String submit(Map<String, Object> params) {
		String result = "";
		try {
			Future<String> future = ExecutorPool.getExecutorPool().submit(new HandleProcessTask(params, webSocket));
			try {
				while (!future.isDone()) {
					result = future.get();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("处理请求失败");
		}
		return result;
	}
	
	/**
	 *  发送请求
	 * 
	 * @param params 参数
	 * @return map
	 */
	public static Map<String, String> send(Map<String, Object> params) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Future<String> future = ExecutorPool.getExecutorPool().submit(new HandleProcessTask(params, webSocket));
			try {
				while (!future.isDone()) {
					ObjectMapper mapper = new ObjectMapper();
					String result = future.get();
					map = mapper.readValue(result, Map.class);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("处理请求失败");
		}
		return map;
	}
}