package com.x930073498.baseitemlib;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseHolder> implements ListAdapter {

    private class BaseOnListChangedCallback extends ObservableList.OnListChangedCallback<ObservableArrayList<BaseItem>> {

        @Override
        public void onChanged(ObservableArrayList<BaseItem> sender) {
            if (!dataObservable) return;
            if (isFromList)
                mDataObservable.notifyChanged();
            else
                notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableArrayList<BaseItem> sender, int positionStart, int itemCount) {
            if (!dataObservable) return;
            if (isFromList)
                mDataObservable.notifyChanged();
            else
                notifyItemChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(ObservableArrayList<BaseItem> sender, int positionStart, int itemCount) {
            if (!dataObservable) return;
            if (isFromList)
                mDataObservable.notifyChanged();
            else
                notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(ObservableArrayList<BaseItem> sender, int fromPosition, int toPosition, int itemCount) {
            if (!dataObservable) return;
            if (isFromList)
                mDataObservable.notifyChanged();
            else
                notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRangeRemoved(ObservableArrayList<BaseItem> sender, int positionStart, int itemCount) {
            if (!dataObservable) return;
            if (isFromList)
                mDataObservable.notifyChanged();
            else
                notifyItemRangeRemoved(positionStart, itemCount);
        }
    }


    private class DefaultOnRebindCallback extends OnRebindCallback<ViewDataBinding> {
        @Override
        public boolean onPreBind(ViewDataBinding binding) {
            return false;
        }
    }

    private boolean dataObservable = true;
    private BaseOnListChangedCallback callback = new BaseOnListChangedCallback();
    private List<? extends BaseItem> data = new ObservableArrayList<>();
    private SparseIntArray listViewTypeMap = new SparseIntArray();
    private DefaultOnRebindCallback callBack = new DefaultOnRebindCallback();
    private boolean isFromList = false;
    private DataSetObservable mDataObservable = new DataSetObservable();

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new BaseHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (data == null) return;
        for (BaseItem item : data
                ) {
            item.attachToParent(recyclerView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isFromList) return getListViewType(position);
        else return getRecyclerViewType(position);
    }


    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        BaseItem item = getItem(position);
        if (holder.getBaseItem() == null) {
            bindViewHolder(holder, item, position);
        } else {
            bindViewHolder(holder, holder.getBaseItem(), position);
        }
    }

    private void bindViewHolder(BaseHolder holder, BaseItem item, int position) {
        holder.getBinding().removeOnRebindCallback(callBack);
        if (item.getVariableId() == BaseItem.NO_ID) {
            holder.getBinding().addOnRebindCallback(callBack);
        } else {
            holder.getBinding().setVariable(item.getVariableId(), item);
            holder.getBinding().executePendingBindings();
        }
        item.onBindView(holder.getBinding(), position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean areAllItemsEnabled() {
        isFromList = true;
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        mDataObservable.registerObserver(dataSetObserver);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        mDataObservable.unregisterObserver(dataSetObserver);
    }

    @Override
    public int getCount() {
        return getItemCount();
    }

    @Override
    public BaseItem getItem(int i) {
        return data == null ? null : data.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = (BaseHolder) convertView.getTag();
        BaseItem item = data.get(position);
        item.attachToParent(parent);
        int layoutId = item.getLayoutId();
        if (holder == null) {
            holder = createListViewHolder(parent, layoutId);
            holder.itemView.setTag(holder);
        }
        holder.setBaseItem(item);
        onBindViewHolder(holder, position);
        return holder.itemView;
    }

    private BaseHolder createListViewHolder(ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new BaseHolder(view);
    }

    @Override
    public int getViewTypeCount() {
        int count = 0;
        listViewTypeMap.clear();
        for (BaseItem item : data
                ) {
            if (listViewTypeMap.indexOfKey(item.getLayoutId()) < 0) {
                listViewTypeMap.put(item.getLayoutId(), count);
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }


    public boolean isDataObservable() {
        return dataObservable;
    }

    public void setDataObservable(boolean dataObservable) {
        this.dataObservable = dataObservable;
        if (!dataObservable) {
            if (data instanceof ObservableArrayList) {
                ((ObservableArrayList) data).removeOnListChangedCallback(callback);
                return;
            }
        }
        if (data instanceof ObservableArrayList) {
            ((ObservableArrayList) data).removeOnListChangedCallback(callback);
            ((ObservableArrayList) data).addOnListChangedCallback(callback);
        } else {
            ObservableArrayList<BaseItem> temp = new ObservableArrayList<>();
            temp.addAll(data);
            temp.addOnListChangedCallback(callback);
            data = temp;
        }
    }

    public List<? extends BaseItem> getData() {
        return data;
    }

    public void setData(List<? extends BaseItem> data) {
        if (data == null) {
            setData(new ObservableArrayList<BaseItem>());
            return;
        }
        if (data instanceof ObservableArrayList) {
            ((ObservableArrayList) data).removeOnListChangedCallback(callback);
            ((ObservableArrayList) data).addOnListChangedCallback(callback);
            this.data = data;
        } else {
            ObservableArrayList<BaseItem> temp = new ObservableArrayList<>();
            temp.addAll(data);
            temp.addOnListChangedCallback(callback);
            this.data = temp;
        }
        notifyDataSetChanged();
        notifyListDataSetChange();
    }

    public final void notifyListDataSetChange() {
        mDataObservable.notifyChanged();
    }

    public final void notifyDataSetInvalidated() {
        mDataObservable.notifyInvalidated();
    }

    private int getListViewType(int position) {
        if (data == null) return -1;
        BaseItem temp = data.get(position);
        if (temp == null) return -1;
        int layoutId = temp.getLayoutId();
        return listViewTypeMap.get(layoutId);
    }

    private int getRecyclerViewType(int position) {
        if (data == null) return -1;
        BaseItem temp = data.get(position);
        if (temp == null) return -1;

        return temp.getLayoutId();
    }
}
