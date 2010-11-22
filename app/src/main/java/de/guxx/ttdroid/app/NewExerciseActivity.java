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

package de.guxx.ttdroid.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import de.guxx.ttdroid.app.adapter.TTListAdapter;
import de.guxx.ttdroid.app.adapter.TTListAdapter.DataContainer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rdu
 */
public class NewExerciseActivity extends ListActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	setTheme(R.style.ListStyle);	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.newexercise);
	setListAdapter(new MyAdapter());	
    }    

    private class MyAdapter extends BaseAdapter
    {

	@Override
	public int getCount()
	{
	    return 1;
	}

	@Override
	public Object getItem(int position)
	{
	    return "text";
	}

	@Override
	public long getItemId(int position)
	{
	    return 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{	    
	    View v = View.inflate(parent.getContext(), R.layout.listitem, parent);
	    return v;
	}
	
    }
}
