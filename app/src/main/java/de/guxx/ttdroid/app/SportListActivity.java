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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import de.guxx.ttdroid.lib.dao.SportDao;
import de.guxx.ttdroid.lib.dao.SportDaoImpl;
import de.guxx.ttdroid.lib.entity.Sport;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 *
 * @author rdu
 */
public class SportListActivity extends ListActivity
{

    static final String[] COUNTRIES = new String[]
    {
	"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
	"Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
	"Armenia", "Aruba", "Australia", "Austria", "Azerbaijan"
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	setTheme(R.style.ListStyle);
	super.onCreate(savedInstanceState);

	SportDao sd = new SportDaoImpl();

	List<Sport> sl = sd.list();

	Spanned[] sa = new Spanned[sl.size()];

	int pos = 0;

	for (Sport s : sl)
	{
	    SpannableStringBuilder builder = new SpannableStringBuilder();
	    int lengthOfPart1 = builder.length();
	    builder.append(" ");
	    Drawable d = loadImageFromWebOperations("http://trainingstagebuch.org/static" + s.getIconImage32x32());
	    d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight()); // <---- Very important otherwise your image won't appear
	    ImageSpan myImage = new ImageSpan(d);
	    builder.setSpan(myImage, lengthOfPart1, lengthOfPart1 + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	    builder.append(" ");
	    builder.append(s.getName());
	    sa[pos++] = builder; //Html.fromHtml("<img src=\"http://trainingstagebuch.org/static/images/sports/16x16/cycling_road.png\" />" + s.getName() + "");
	}

	setListAdapter(new ArrayAdapter<Spanned>(this, R.layout.sportlist, sa));

	ListView lv = getListView();
	lv.setTextFilterEnabled(true);

	lv.setOnItemClickListener(new OnItemClickListener()
	{

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id)
	    {
		Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
			Toast.LENGTH_SHORT).show();
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
