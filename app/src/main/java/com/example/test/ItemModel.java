package com.example.test;


//define our recycle elements
public class ItemModel {  // 这里修改为大写 I
    private final int imageResId;
    private final String itemName;
    private  final int originalIndex;
    public ItemModel(int imageResId, String itemName, int originalIndex) {  // 现在类名与构造方法一致
        this.imageResId = imageResId;
        this.itemName = itemName;
        this.originalIndex = originalIndex;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getItemName() {
        return itemName;
    }

    public  int getOriginalIndex(){
        return originalIndex;
    }
}