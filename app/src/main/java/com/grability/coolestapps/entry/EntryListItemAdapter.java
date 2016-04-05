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

package com.grability.coolestapps.entry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grability.coolestapps.R;
import com.grability.coolestapps.model.Entry;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Renders an app logo and title on a list view.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com
 * @version 1.0
 */
public class EntryListItemAdapter extends BaseAdapter {

    private Context context;
    private List<Entry> entries;

    private LayoutInflater mLayoutInflater;

    private ViewHolder mViewHolder;

    public EntryListItemAdapter(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(entries.get(position).getId().getAttributes().getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.entry_list_item_layout, parent, false);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        Entry entry = entries.get(position);
        Glide.with(context).load(entry.getImage().get(2).getLabel()).into(mViewHolder.logo);
        mViewHolder.title.setText(entry.getName().getLabel());
        return convertView;
    }

    protected static class ViewHolder {

        @Bind(R.id.logo)
        ImageView logo;

        @Bind(R.id.title)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
