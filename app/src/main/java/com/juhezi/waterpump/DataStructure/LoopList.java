package com.juhezi.waterpump.DataStructure;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * LoopList
 *
 * @author: 乔云瑞
 * @time: 2016/4/18 15:12
 * <p/>
 * 循环线性表
 */
public class LoopList<T> {

    private static final String TAG = "LoopList";
    private int length;
    private List<T> list = new ArrayList<>();

    public LoopList(int length) {
        this.length = length;
    }

    public void push(T object) {
        synchronized (list) {
            if (list.size() == length) {    //线性表中已满
                list.remove(0);
                list.add(object);
            } else {    //线性表没有满
                list.add(object);
            }
        }
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "LoopList{" +
                "length=" + length +
                ", list=" + list.toString() +
                '}';
    }

    public T get(int i) {
        if (i >= length) {
            i = 0;
        }
        return list.get(i);
    }
}
