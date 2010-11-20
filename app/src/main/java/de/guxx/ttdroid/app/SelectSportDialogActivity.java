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
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import de.guxx.ttdroid.app.adapter.TTListAdapter;
import de.guxx.ttdroid.app.adapter.TTListAdapter.DataContainer;
import de.guxx.ttdroid.lib.dao.SportDao;
import de.guxx.ttdroid.lib.dao.SportDaoImpl;
import de.guxx.ttdroid.lib.entity.Sport;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rdu
 */
public class SelectSportDialogActivity extends ListActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	setTheme(R.style.ListStyle);
	super.onCreate(savedInstanceState);

	SportDao sd = new SportDaoImpl();
	List<Sport> sl = sd.list();
	List<DataContainer> dcl = new ArrayList<DataContainer>();

	for (Sport s : sl)
	{
	    SpannableStringBuilder builder = new SpannableStringBuilder();
	    int lengthOfPart1 = builder.length();
	    builder.append(" ");
	    Drawable d = loadImageFromWebOperations("http://trainingstagebuch.org/static" + s.getIconImage32x32());
	    d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
	    ImageSpan myImage = new ImageSpan(d);
	    builder.setSpan(myImage, lengthOfPart1, lengthOfPart1 + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	    builder.append(" ");
	    builder.append(s.getName());
	    DataContainer dc = new DataContainer();
	    dc.setId(s.getId());
	    dc.setContent(builder);
	    dcl.add(dc);
	}

	setListAdapter(new TTListAdapter(dcl, R.layout.sportlist));

	ListView lv = getListView();
	lv.setTextFilterEnabled(true);

	lv.setOnItemClickListener(new OnItemClickListener()
	{

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id)
	    {
		Bundle map = new Bundle();
		map.putLong("id", id);
		setResult(RESULT_OK, new Intent().putExtras(map));
		finish();
	    }
	});
    }

    private Drawable loadImageFromWebOperations(String url)
    {
	try
	{
	    InputStream is = (InputStream) new URL(url).getContent();
	    Drawable d = Drawable.createFromStream(is, "src name");
	    return d;
	}
	catch (Exception e)
	{
	    return null;
	}
    }
}
