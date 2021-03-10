package com.li.springcloud.hash;

import cn.hutool.Hutool;
import cn.hutool.core.util.HashUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    
    private boolean hasVirtualNode;
    
    private TreeMap<Integer,String> nodesMap = new TreeMap<>();
    
    private int VIRTUAL_NODES;
    
    private static String CONN_STRING = "&&";
    
    public final Object NODE_LOCK = new Object();

    
    public ConsistentHashing() {
        this(false,4);
    }
    
    public ConsistentHashing(boolean hasVirtualNode) {
        this(hasVirtualNode,4);
    }
    
    public ConsistentHashing(boolean hasVirtualNode, int VIRTUAL_NODES) {
        this.hasVirtualNode = hasVirtualNode;
        this.VIRTUAL_NODES = VIRTUAL_NODES;
        initNodes();
    }
    
    protected static int hash(String Addr) {
        return HashUtil.fnvHash(Addr);
    }
    
    private String getServer(String key) {
        //如果节点map为空，返回特殊字符
        if (nodesMap.isEmpty()) {
            return CONN_STRING;
        }
        
        int hash = hash(key);
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
    
    
    public boolean isHasVirtualNode() {
        return hasVirtualNode;
    }
    
    public void setHasVirtualNode(boolean hasVirtualNode) {
        this.hasVirtualNode = hasVirtualNode;
    }
    
    protected boolean isEmpty() {
        return nodesMap.isEmpty();
    }
    
    public final void put(String value) {
        synchronized (NODE_LOCK) {
            if (hasVirtualNode) {
                for (int i = 0;i <= VIRTUAL_NODES;i++) {
                    String realValue = unPeel(value, i);
                    this.nodesMap.put(hash(realValue),realValue);
                }
                return;
            }
            int key = hash(value);
            this.nodesMap.put(key,value);
        }
    }
    
    
    public final void remove(String value) {
        synchronized (NODE_LOCK) {
            if (hasVirtualNode) {
                for (int i = 1;i <= VIRTUAL_NODES;i++) {
                    String realValue = unPeel(value, i);
                    this.nodesMap.remove(hash(realValue));
                }
                return;
            }
            int hash = hash(value);
            this.nodesMap.remove(hash);
        }
    }
}
