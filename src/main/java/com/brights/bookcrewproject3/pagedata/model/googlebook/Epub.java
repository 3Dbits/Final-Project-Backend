package com.brights.bookcrewproject3.pagedata.model.googlebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Epub {
    public boolean isAvailable;
    public String acsTokenLink;
    public String downloadLink;
}
