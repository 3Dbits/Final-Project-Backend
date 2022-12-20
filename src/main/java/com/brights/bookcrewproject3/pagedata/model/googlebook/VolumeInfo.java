package com.brights.bookcrewproject3.pagedata.model.googlebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VolumeInfo {
    public String title;
    public ArrayList<String> authors;
    public String publisher;
    public String publishedDate;
    public String description;
    public ArrayList<IndustryIdentifier> industryIdentifiers;
    public ReadingModes readingModes;
    public int pageCount;
    public String printType;
    public ArrayList<String> categories;
    public String maturityRating;
    public boolean allowAnonLogging;
    public String contentVersion;
    public PanelizationSummary panelizationSummary;
    public ImageLinks imageLinks;
    public String language;
    public String previewLink;
    public String infoLink;
    public String canonicalVolumeLink;
    public String subtitle;
    public double averageRating;
    public int ratingsCount;
    public SeriesInfo seriesInfo;
    public String comicsContent;
}
