package com.juhezi.waterpump.DataStructure;

import android.util.Log;

import com.juhezi.waterpump.Other.Config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Node
 *
 * @author: 乔云瑞
 * @time: 2016/4/18 15:30
 * <p>
 * 绘制在SurfaceView上的节点
 */
public class Node implements Serializable {

    private static final String TAG = "Node";

    private double value;   //代表的数值
    private int second; //所处的秒段

    private List<Double> values = new ArrayList<>();

    public Node(int second) {
        this.second = second;
    }

    public Node(int second, double value) {
        this(second);
        this.value = value;
    }

    public Node(Node node, int start, int end) {
        if (node == null) {
            return;
        }
        this.second = node.getSecond();

        for (int i = 0; i < (end - start); i++) {
            this.values.add(node.getValues().get(start + i));
        }
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    /**
     * 添加value
     *
     * @param value
     */
    public void addValue(double value) {
        values.add(value);
    }

    /**
     * 获取values List
     *
     * @return
     */
    public List<Double> getValues() {
        return values;
    }

    public void deleteX(int flag) {
        int max = flag < values.size() ? flag : values.size();
        for (int i = 0; i < (flag < values.size() ? flag : values.size()); i++) {
            this.values.remove(0);   //移除前面的数据
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", second=" + second +
                ", values=" + values +
                '}';
    }
}
