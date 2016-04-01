/*
 * Copyright 2016 Richard Ricciardelli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grability.coolestapps.model;

import com.google.gson.annotations.SerializedName;
import com.grability.coolestapps.util.Constants;

import java.util.List;

/**
 * Entry model
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class Entry {

    @SerializedName(Constants.IM_NAME)
    private Name name;

    @SerializedName(Constants.IM_IMAGE)
    private List<Image> image;

    private Summary summary;

    @SerializedName(Constants.IM_PRICE)
    private Price price;

    @SerializedName(Constants.IM_CONTENT_TYPE)
    private ContentType contentType;

    private Rights rights;
    private Title title;
    private Link link;
    private Id id;

    @SerializedName(Constants.IM_ARTIST)
    private Artist artist;

    private Category category;

    @SerializedName(Constants.IM_RELEASE_DATE)
    private ReleaseDate releaseDate;

    public Entry(Name name, List<Image> image, Summary summary, Price price, ContentType contentType, Rights rights, Title title, Link link, Id id, Artist artist, Category category, ReleaseDate releaseDate) {
        this.name = name;
        this.image = image;
        this.summary = summary;
        this.price = price;
        this.contentType = contentType;
        this.rights = rights;
        this.title = title;
        this.link = link;
        this.id = id;
        this.artist = artist;
        this.category = category;
        this.releaseDate = releaseDate;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "name=" + name +
                ", image=" + image +
                ", summary=" + summary +
                ", price=" + price +
                ", contentType=" + contentType +
                ", rights=" + rights +
                ", title=" + title +
                ", link=" + link +
                ", id=" + id +
                ", artist=" + artist +
                ", category=" + category +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
