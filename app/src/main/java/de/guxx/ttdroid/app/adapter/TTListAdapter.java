/**
 * Copyright (C) 2010-2010 rdu <ich@ronny-dudeck.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program at http://www.gnu.org/licenses/licence/gpl_v3.txt.
 *
 * If not, see <http://www.gnu.org/licenses/>.
 */
package de.guxx.ttdroid.app.adapter;

import android.content.Context;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.List;

/**
 *
 * @author rdu
 */
public class TTListAdapter extends BaseAdapter implements ListAdapter
{

    public static class DataContainer
    {

	private Integer id;
	private Spannable content;

	public Spannable getContent()
	{
	    return content;
	}

	public void setContent(Spannable content)
	{
	    this.content = content;
	}

	public Integer getId()
	{
	    return id;
	}

	public void setId(Integer id)
	{
	    this.id = id;
	}
    }
    private List<DataContainer> list;
    private Integer resource;

    public TTListAdapter(List<DataContainer> list, Integer resource)
    {
	this.list = list;
	this.resource = resource;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
	return true;
    }

    @Override
    public boolean isEnabled(int position)
    {
	return true;
    }

    @Override
    public int getCount()
    {
	return list.size();
    }

    @Override
    public Object getItem(int position)
    {
	return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
	return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
	LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View view = inflater.inflate(resource, parent, false);
	TextView text = (TextView)view;
	text.setText((CharSequence) list.get(position).getContent());
	return view;
    }

    @Override
    public boolean isEmpty()
    {
	return list.isEmpty();
    }
}
