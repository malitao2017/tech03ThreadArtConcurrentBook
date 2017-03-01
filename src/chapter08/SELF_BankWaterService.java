package chapter08;

import java.util.Map.Entry;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/**
 * 自己添加的类： 书中 P194
 * @author malitao
 *
 */
public class SELF_BankWaterService implements Runnable{
	//会最后执行自己
	private CyclicBarrier cyc = new CyclicBarrier(4,this);
	private Executor exe = Executors.newFixedThreadPool(4);
	private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>(); 
	private void count(){
		for (int i = 0; i < 4; i++) {
			exe.execute(new Runnable() {
				@Override
				public void run() {
					map.put(Thread.currentThread().getName(), 1);
					try {//银行计算完成插入一个屏障
						cyc.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	@Override
	public void run() {
		int count = 0 ;
//		for (Integer num : map.values()) {
//			count += num;
//		}
		//另一种写法
		for (Entry<String, Integer> entry: map.entrySet()) {
			count += entry.getValue();
		}
		System.out.println("银行总流水为："+count);
	}
	public static void main(String[] args) {
		SELF_BankWaterService self = new SELF_BankWaterService();
		self.count();
	}
}
