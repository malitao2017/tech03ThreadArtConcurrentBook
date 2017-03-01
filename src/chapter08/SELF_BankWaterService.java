package chapter08;

import java.util.Map.Entry;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/**
 * �Լ���ӵ��ࣺ ���� P194
 * @author malitao
 *
 */
public class SELF_BankWaterService implements Runnable{
	//�����ִ���Լ�
	private CyclicBarrier cyc = new CyclicBarrier(4,this);
	private Executor exe = Executors.newFixedThreadPool(4);
	private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>(); 
	private void count(){
		for (int i = 0; i < 4; i++) {
			exe.execute(new Runnable() {
				@Override
				public void run() {
					map.put(Thread.currentThread().getName(), 1);
					try {//���м�����ɲ���һ������
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
		//��һ��д��
		for (Entry<String, Integer> entry: map.entrySet()) {
			count += entry.getValue();
		}
		System.out.println("��������ˮΪ��"+count);
	}
	public static void main(String[] args) {
		SELF_BankWaterService self = new SELF_BankWaterService();
		self.count();
	}
}
