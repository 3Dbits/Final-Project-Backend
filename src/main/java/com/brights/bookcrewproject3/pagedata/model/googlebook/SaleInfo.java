package com.brights.bookcrewproject3.pagedata.model.googlebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleInfo {
    public String country;
    public String saleability;
    public boolean isEbook;
    public String buyLink;
}
