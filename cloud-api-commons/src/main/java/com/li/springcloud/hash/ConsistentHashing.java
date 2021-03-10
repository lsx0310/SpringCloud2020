package com.li.springcloud.hash;

import cn.hutool.core.lang.ConsistentHash;
import cn.hutool.core.util.HashUtil;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @ClassName ConsistentHashing
 * 一致性hash算法：
 *  一致性hash算法通过一个叫作一致性hash环的数据结构实现。这个环的起点是0，终点是2^32 - 1，并且起点与终点连接，
 *  环的中间的整数按逆时针分布，故这个环的整数分布范围是[0, 2^32-1]
 * @link https://blog.csdn.net/suifeng629/article/details/81567777
 * @Description: TODO
 * @Author: li
 * @Date: 2021/3/10 17:19
 * @Version v1.0
 **/
public abstract class ConsistentHashing {
    
    /**
     * 是否有虚拟节点
     */
    private boolean hasVirtualNode;
    
    /**
     * 存储节点的TreeMap
     */
    private TreeMap<Integer,String> nodesMap = new TreeMap<>();
    
    /**
     * 虚拟节点的个数
     */
    private int virtualNodes;
    
    /**
     * 生成虚拟节点的连接字符
     */
    private static String CONN_STRING = "&&";
    
    private ReentrantReadWriteLock.ReadLock readLock;
    
    private ReentrantReadWriteLock.WriteLock writeLock;
    

    
    public ConsistentHashing() {
        this(false,0);
    }
    
    public ConsistentHashing(boolean hasVirtualNode) {
        this(hasVirtualNode,0);
    }
    
    public ConsistentHashing(boolean hasVirtualNode, int nodes) {
        if (hasVirtualNode) {
            this.virtualNodes = nodes;
        }
        this.hasVirtualNode = hasVirtualNode;
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
        initNodes();
    }
    
    /**
     * 可以继承该方法自己实现hash算法
     * @param addr
     * @return
     */
    public int hash(String addr) {
        return HashUtil.fnvHash(addr);
    }
    
    private String getServer(String key) {
        //如果节点map为空，返回特殊字符
        if (nodesMap.isEmpty()) {
            return CONN_STRING;
        }
        
        int hash = hash(key);
        readLock.lock();
        try {
            //得到该hash值的所有map
            SortedMap<Integer, String> tailMap = nodesMap.tailMap(hash);
            //如果没有比该key的hash值大的，从第一个node开始
            if (tailMap.isEmpty()) {
                Integer firstKey = nodesMap.firstKey();
                return nodesMap.get(firstKey);
            }else {
                //第一个key就是顺时针过去离node最近的那个结点
                Integer firstKey = tailMap.firstKey();
                return tailMap.get(firstKey);
            }
        } finally {
            readLock.unlock();
        }
    }
    
    public String realServer(String key) {
        if (hasVirtualNode) {
            return peel(getServer(key));
        }
        return getServer(key);
    }
    
    private String peel(String virtual) {
        int index = virtual.indexOf(CONN_STRING);
        return virtual.substring(0,index);
    }
    
    
    private String unPeel(String real,int index) {
        return real + CONN_STRING + index;
    }
    
    
    /**
     * 初始化nodeMap 可以自己实现从不同渠道获取
     */
    protected abstract void initNodes();
    
    
    public final void put(String value) {
        //对nodesMap任何操作都需要加锁
        writeLock.lock();
        try {
           if (hasVirtualNode) {
               for (int i = 0;i <= virtualNodes;i++) {
                   String realValue = unPeel(value, i);
                   this.nodesMap.put(hash(realValue),realValue);
               }
               return;
           }
           int key = hash(value);
           this.nodesMap.put(key,value);
        } finally {
            writeLock.unlock();
        }
    }
    
    public final void remove(String value) {
        writeLock.lock();
        try {
            if (hasVirtualNode) {
                for (int i = 1;i <= virtualNodes;i++) {
                    String realValue = unPeel(value, i);
                    this.nodesMap.remove(hash(realValue));
                }
                return;
            }
            int hash = hash(value);
            this.nodesMap.remove(hash);
        } finally {
            writeLock.unlock();
        }
    }
}
