package com.brights.bookcrewproject3.pagedata.model.googlebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Root {
    @JsonProperty("kind")
    public String kind;
    @JsonProperty("totalItems")
    public int totalItems;
    @JsonProperty("items")
    public ArrayList<Item> items;

}
