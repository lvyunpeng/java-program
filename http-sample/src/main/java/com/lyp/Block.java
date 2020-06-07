package com.lyp;

import com.lyp.sample.JsonUtils;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

public class Block {

    private int charm;

    private String meta;

    private int positionx;

    private int positiony;

    private int positionz;

    private int price;

    private String temp_id;

    public Block(){}

    public Block(int charm, String meta, int positionx, int positiony, int positionz, int price, String temp_id){
        this.charm = charm;
        this.meta = meta;
        this.positionx = positionx;
        this.positiony = positiony;
        this.positionz = positionz;
        this.price = price;
        this.temp_id = temp_id;
    }

    public String toString(){
        return this.charm + "," + this.meta + "," + this.positionx + "," + this.positiony + "," + this.positionz + "," + this.price + "," + this.temp_id;
    }

    public static void main(String[] args){
        List<Block> list = new ArrayList<Block>();
        for(int i=0; i<10000; i++){
            Block block2 = new Block(12, "235454544", 32434, 34343, 45545465, 334342322, "545342232");
            list.add(block2);
        }

//        System.out.println(JsonUtils.toJson(list));
        System.out.println(JsonUtils.toJson(list).length());
        long startTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        for(Block block : list){
            stringBuilder.append(block.toString()).append(";");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(stringBuilder.toString());
        System.out.println(stringBuilder.toString().length());
    }

}
