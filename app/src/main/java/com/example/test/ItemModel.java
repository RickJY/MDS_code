package com.example.test;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemModel implements Parcelable {
    private final int imageResId; // Resource ID of the item's image
    private final String itemName; // Name of the item
    private final int originalIndex; // Original index of the item in the list

    // Constructor to initialize the ItemModel object
    public ItemModel(int imageResId, String itemName, int originalIndex) {
        this.imageResId = imageResId;
        this.itemName = itemName;
        this.originalIndex = originalIndex;
    }

    // Getter method to retrieve the image resource ID
    public int getImageResId() {
        return imageResId;
    }

    // Getter method to retrieve the item name
    public String getItemName() {
        return itemName;
    }

    // Getter method to retrieve the original index of the item
    public int getOriginalIndex() {
        return originalIndex;
    }

    // Constructor to create an ItemModel object from a Parcel
    protected ItemModel(Parcel in) {
        imageResId = in.readInt();
        itemName = in.readString();
        originalIndex = in.readInt();
    }

    // Creator constant to create ItemModel objects from Parcel
    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0; // Indicates no special kinds of objects will be in the Parcel
    }

    // Method to write the ItemModel object to a Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResId);
        dest.writeString(itemName);
        dest.writeInt(originalIndex);
    }
}