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

import java.io.Serializable;
import java.util.List;

/**
 * Feed model
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class Feed implements Serializable {

    private Author author;
    private List<Entry> entry;
    private Updated updated;
    private Rights rights;
    private Title title;
    private Icon icon;
    private List<Link> link;
    private Id id;

    public Feed(Author author, List<Entry> entry, Updated updated, Rights rights, Title title, Icon icon, List<Link> link, Id id) {
        this.author = author;
        this.entry = entry;
        this.updated = updated;
        this.rights = rights;
        this.title = title;
        this.icon = icon;
        this.link = link;
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public Updated getUpdated() {
        return updated;
    }

    public void setUpdated(Updated updated) {
        this.updated = updated;
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

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "author=" + author +
                ", entry=" + entry +
                ", updated=" + updated +
                ", rights=" + rights +
                ", title=" + title +
                ", icon=" + icon +
                ", link=" + link +
                ", id=" + id +
                '}';
    }
}
