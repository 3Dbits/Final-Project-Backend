package com.brights.bookcrewproject3.pagedata.model.googlebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pdf {
    public boolean isAvailable;
    public String acsTokenLink;
    public String downloadLink;
}
