package com.example.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
// Inner objects
class Badge_counts{
    public int bronze;
    public int silver;
    public int gold;
}
// main object with attributes implementing parcelable to send data in synchronized fashion
class Item implements Parcelable {
    public Badge_counts badge_counts;
    public int reputation_change_year;
    public String location;
    public String profile_image;
    public String display_name;
    public int account_id;
    public int user_id;


    protected Item(Parcel in) {
        reputation_change_year = in.readInt();
        location = in.readString();
        profile_image = in.readString();
        display_name = in.readString();
        account_id = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(reputation_change_year);
        dest.writeString(location);
        dest.writeString(profile_image);
        dest.writeString(display_name);
        dest.writeInt(account_id);
    }
}
public class StackApiR {
    List<Item> items;
    public boolean has_more;
    public int quota_max;
    public int quota_remaining;
}
