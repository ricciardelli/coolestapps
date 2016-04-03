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

package com.grability.coolestapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grability.coolestapps.R;
import com.grability.coolestapps.model.Entry;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final String LOG_TAG = getClass().getSimpleName();

    private Context context;

    private List<Entry> entries;

    public CardAdapter(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(entries.get(position).getImage().get(2).getLabel()).into(holder.logo);
        holder.title.setText(entries.get(position).getName().getLabel());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private final String LOG_TAG = getClass().getSimpleName();

        @Bind(R.id.logo)
        ImageView logo;

        @Bind(R.id.title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card)
        public void onClick(View view) {
            Log.d(LOG_TAG, "Clicked on card");
        }

        @OnLongClick(R.id.card)
        public boolean onLongClick(View view) {
            Log.d(LOG_TAG, "Long clicked on card");
            return false;
        }
    }
}
