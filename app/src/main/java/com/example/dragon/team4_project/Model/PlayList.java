package com.example.dragon.team4_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable {

@SerializedName("Listid")
@Expose
private String listid;
@SerializedName("Listname")
@Expose
private String listname;
@SerializedName("Picturelink")
@Expose
private String picturelink;

public String getListid() {
return listid;
}

public void setListid(String listid) {
this.listid = listid;
}

public String getListname() {
return listname;
}

public void setListname(String listname) {
this.listname = listname;
}

public String getPicturelink() {
return picturelink;
}

public void setPicturelink(String picturelink) {
this.picturelink = picturelink;
}

}