package com;

public class Exchange {
    private int id;
    private int itemId1;
    private int itemId2;
    private int user1Id;
    private String status;
    private String item1Name;
    private String item2Name;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId1() {
        return itemId1;
    }

    public void setItemId1(int itemId1) {
        this.itemId1 = itemId1;
    }

    public int getItemId2() {
        return itemId2;
    }

    public void setItemId2(int itemId2) {
        this.itemId2 = itemId2;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItem1Name() {
        return item1Name;
    }

    public void setItem1Name(String item1Name) {
        this.item1Name = item1Name;
    }

    public String getItem2Name() {
        return item2Name;
    }

    public void setItem2Name(String item2Name) {
        this.item2Name = item2Name;
    }
}
