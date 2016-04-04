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

package com.grability.coolestapps.summary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grability.coolestapps.R;
import com.grability.coolestapps.model.Entry;
import com.grability.coolestapps.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class SummaryActivityFragment extends Fragment {

    private final String LOG_TAG = getClass().getSimpleName();

    @Bind(R.id.logo)
    ImageView logo;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.artist)
    TextView artist;

    @Bind(R.id.summary)
    TextView summary;

    @Bind(R.id.read_more)
    Button readMore;

    @Bind(R.id.collapse)
    Button collapse;

    private Entry mEntry;

    public SummaryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        mEntry = (Entry) args.getSerializable(Constants.ENTRY_KEY);
        if (mEntry != null) {
            Glide.with(this).load(mEntry.getImage().get(2).getLabel()).into(logo);
            title.setText(mEntry.getName().getLabel());
            artist.setText(mEntry.getArtist().getLabel());
            summary.setText(mEntry.getSummary().getLabel());
        } else {
            Log.e(LOG_TAG, "Entry is null");
        }
        return view;
    }

    @OnClick(R.id.read_more)
    public void expand(View view) {
        Log.d(LOG_TAG, "Expanding text");
        summary.setMaxLines(Integer.MAX_VALUE);
        summary.setEllipsize(null);
        collapse.setVisibility(View.VISIBLE);
        readMore.setVisibility(View.GONE);
    }

    @OnClick(R.id.collapse)
    public void collapse(View view) {
        Log.d(LOG_TAG, "Collapsing text");
        // TODO Get this number from preferences
        summary.setMaxLines(5);
        summary.setEllipsize(TextUtils.TruncateAt.END);
        collapse.setVisibility(View.GONE);
        readMore.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.title_card)
    public void openLink(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(mEntry.getLink().getAttributes().getHref()));
        startActivity(intent);
    }

}
