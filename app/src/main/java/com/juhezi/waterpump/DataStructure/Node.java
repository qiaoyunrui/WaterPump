package com.juhezi.waterpump.DataStructure;

/**
 * Node
 *
 * @author: 乔云瑞
 * @time: 2016/4/18 15:30
 * <p/>
 * 绘制在SurfaceView上的节点
 */
public class Node {

    private double value;   //代表的数值
    private int second; //所处的秒段
    private boolean state = false;  //该节点所代表的状态 true表示未达到预警值 false表示达到预警值
    private int standard = 100;   //警界值

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

    public boolean isState() {
        return value < standard;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
