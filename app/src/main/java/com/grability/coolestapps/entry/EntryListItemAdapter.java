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
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grability.coolestapps.R;
import com.grability.coolestapps.model.Entry;
import com.grability.coolestapps.summary.SummaryActivity;
import com.grability.coolestapps.util.AnimationUtils;
import com.grability.coolestapps.util.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Renders an app logo and title on a list view.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class EntryListItemAdapter extends RecyclerView.Adapter<EntryListItemAdapter.ViewHolder> implements OnEntryClickListener {

    private final String LOG_TAG = getClass().getSimpleName();

    private Context context;

    private List<Entry> entries;

    public EntryListItemAdapter(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.entry_list_item_layout, parent, false);
        Animation animation = AnimationUtils.getAnimation(context);
        if (animation != null) {
            view.setAnimation(animation);
        }
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Entry entry = entries.get(position);
        Glide.with(context).load(entry.getImage().get(2).getLabel()).into(holder.logo);
        holder.title.setText(entry.getName().getLabel());
        holder.bind(entry);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    @Override
    public void onClick(String id) {
        Log.d(LOG_TAG, "On entry click :: id :: " + id);
        Intent intent = new Intent(context, SummaryActivity.class);
        Entry selectedEntry = null;
        for (Entry entry : entries) {
            if (entry.getId().getAttributes().getId().equalsIgnoreCase(String.valueOf(id))) {
                selectedEntry = entry;
                break;
            }
        }
        intent.putExtra(Constants.ENTRY_KEY, selectedEntry);
        context.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnEntryClickListener onEntryClickListener;

        @Bind(R.id.logo)
        ImageView logo;

        @Bind(R.id.title)
        TextView title;

        public ViewHolder(View itemView, OnEntryClickListener onEntryClickListener) {
            super(itemView);
            this.onEntryClickListener = onEntryClickListener;
            ButterKnife.bind(this, itemView);
        }

        public void bind(Entry entry) {
            itemView.setId(Integer.parseInt(entry.getId().getAttributes().getId()));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onEntryClickListener != null) {
                onEntryClickListener.onClick(String.valueOf(view.getId()));
            }
        }
    }
}
