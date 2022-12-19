package com.brights.bookcrewproject3.pagedata.model.googlebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeriesInfo {
    private String kind;
    private String shortSeriesBookTitle;
    private String bookDisplayNumber;
    private ArrayList<VolumeSeries> volumeSeries;
}
