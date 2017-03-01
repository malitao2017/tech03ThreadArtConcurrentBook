package chapter04.SimpleHttpServer_TEST;

import chapter04.SimpleHttpServer;

/**
 * 自己增加的内容
 * 测试多线程服务器端的线程处理
 */
public class SimpleHttpServer_TEST {
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		SimpleHttpServer shs = new SimpleHttpServer();
		shs.start();
	}
}
