package com.liupeng.learning.zookeeper.api;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import java.util.concurrent.CountDownLatch;
import java.io.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZookeeperClient implements Watcher {  
  
    public ZooKeeper zookeeper;  
    private static int SESSION_TIME_OUT = 2000;  
    private CountDownLatch countDownLatch = new CountDownLatch(1);  
      
    /** 
     * 连接zookeeper 
     * @param host 
     * @throws IOException 
     * @throws InterruptedException 
     */  
    public void connectZookeeper(String host) throws IOException, InterruptedException{  
        zookeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);  
        countDownLatch.await();  
        System.out.println("zookeeper connect ok");  
    }  
      
    /** 
     * 实现watcher的接口方法，当连接zookeeper成功后，zookeeper会通过此方法通知watcher 
     * 此处为如果接受到连接成功的event，则countDown，让当前线程继续其他事情。 
     */  
    @Override  
    public void process(WatchedEvent event) {  
        if(event.getState() == KeeperState.SyncConnected){  
            System.out.println("111 watcher receiver event");
            countDownLatch.countDown();  
        }  
        //监听节点数据变化事件并再次绑定
        if(event.getType() == Event.EventType.NodeDataChanged){
            System.out.println("path: "+event.getPath()+" data changed !!!");
            try{
                this.zookeeper.exists(event.getPath(),true);
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
            System.out.println("path: "+event.getPath()+" children changed !!!");
            try{
                this.zookeeper.getChildren(event.getPath(),true);
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if (event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("path: " + event.getPath() + " node deleted !!!");
            try {
                this.zookeeper.exists(event.getPath(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getType() == Event.EventType.NodeCreated){
            System.out.println("path: " + event.getPath() + " node created !!!");
            try {
                this.zookeeper.exists(event.getPath(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }  
      
	/**
	 * 绑定节点监听事件
	 */
	public Stat exists(String path) throws KeeperException, InterruptedException{  
		return this.zookeeper.exists(path, true);
	}
	  
    /** 
     * 根据路径创建节点，并且设置节点数据 
     * @param path 
     * @param data 
     * @return 
     * @throws KeeperException 
     * @throws InterruptedException 
     */  
    public String createNode(String path,byte[] data) throws KeeperException, InterruptedException{  
        return this.zookeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);  
    }  
      
    /** 
     * 根据路径获取所有孩子节点  
     * @param path 
     * @return 
     * @throws KeeperException 
     * @throws InterruptedException 
     */  
    public List<String> getChildren(String path) throws KeeperException, InterruptedException{  
        return this.zookeeper.getChildren(path, false);  
    }

    public List<String> getChildren(String path, boolean watcher) throws KeeperException, InterruptedException{
        return this.zookeeper.getChildren(path, watcher);
    }

    public Stat setData(String path,byte[] data,int version) throws KeeperException, InterruptedException{  
        return this.zookeeper.setData(path, data, version);  
    }  
      
    /** 
     * 根据路径获取节点数据 
     * @param path 
     * @return 
     * @throws KeeperException 
     * @throws InterruptedException 
     */  
    public byte[] getData(String path) throws KeeperException, InterruptedException{  
        return this.zookeeper.getData(path, false, null);  
    }  
      
    /** 
     * 删除节点 
     * @param path 
     * @param version 
     * @throws InterruptedException 
     * @throws KeeperException 
     */  
    public void deleteNode(String path,int version) throws InterruptedException, KeeperException{  
        this.zookeeper.delete(path, version);  
    }  
      
    /** 
     * 关闭zookeeper连接 
     * @throws InterruptedException 
     */  
    public void closeConnect() throws InterruptedException{  
        if(null != zookeeper){  
            zookeeper.close();  
        }  
    }  
       
	private static final Logger log = LoggerFactory.getLogger(ZookeeperClient.class);  
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {  
        ZookeeperClient client = new ZookeeperClient();  
        String host = "localhost:2181";  
  
        // 连接zookeeper  
        client.connectZookeeper(host);  
        log.info("zookeeper连接成功！");  
  
        // 创建节点  
        byte[] data = { 1, 2, 3, 4, 5 };  
        String result = client.createNode("/test", data);
        log.info(result + "节点创建成功！");  
  
        // 获取某路径下所有节点  
        List<String> children = client.getChildren("/");
        for (String child : children) {  
            log.info(child);  
        }  
        log.info("成功获取child节点");  
  
        // 获取节点数据  
        byte[] nodeData = client.getData("/test");  
        log.info(Arrays.toString(nodeData));  
        log.info("成功获取节点数据！");  
  
        // 更新节点数据  
        data = "test data".getBytes();
        client.setData("/test", data, 0);
        log.info("成功更新节点数据！");  
        nodeData = client.getData("/test");

        //监听节点"/test1"上发生的事件，如节点自身修改，此节点删除事件，此节点添加
        client.exists("/test1");

        //给节点"/test1"的子节点添加事件监听，如给其添加或者删除子节点
        client.getChildren("/test1",true);

		    Thread.sleep(1000*60*5);
        client.closeConnect();  
    }  
}  
