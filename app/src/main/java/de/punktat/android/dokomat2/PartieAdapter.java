/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.punktat.android.dokomat2;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import de.punktat.android.dokomat2.data.Partie;
import de.punktat.android.dokomat2.databinding.PartieListitemBinding;

import java.util.List;
import java.util.Objects;


public class PartieAdapter extends RecyclerView.Adapter<PartieAdapter.PartieViewHolder> {

    List<? extends Partie> mPartieList;

    @Nullable
    private final PartieClickCallback mPartieClickCallback;

    public PartieAdapter(@Nullable PartieClickCallback clickCallback) {
        mPartieClickCallback = clickCallback;
    }

    public void setPartieList(final List<? extends Partie> PartieList) {
        if (mPartieList == null) {
            mPartieList = PartieList;
            notifyItemRangeInserted(0, PartieList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPartieList.size();
                }

                @Override
                public int getNewListSize() {
                    return PartieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mPartieList.get(oldItemPosition).getId() ==
                            PartieList.get(newItemPosition).getId();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Partie newPartie = PartieList.get(newItemPosition);
                    Partie oldPartie = mPartieList.get(oldItemPosition);
                    return newPartie.getId() == oldPartie.getId()
                            && Objects.equals(newPartie.getLocation(), oldPartie.getLocation())
                            && Objects.equals(newPartie.getEndTime(), oldPartie.getEndTime())
                            && newPartie.getFinished() == oldPartie.getFinished();
                }
            });
            mPartieList = PartieList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public PartieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PartieListitemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.partie_listitem,
                        parent, false);
        binding.setCallback(mPartieClickCallback);
        return new PartieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PartieViewHolder holder, int position) {
        holder.binding.setPartie(mPartieList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mPartieList == null ? 0 : mPartieList.size();
    }

    static class PartieViewHolder extends RecyclerView.ViewHolder {

        final PartieListitemBinding binding;

        public PartieViewHolder(PartieListitemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
