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
import java.util.ArrayList;
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
	super.onCreate(savedInstanceState);

	SportDao sd = new SportDaoImpl();

	List<Sport> sl = sd.list();
	
	String[] sa = new String[sl.size()];

	int pos = 0;
	
	for (Sport s : sl)
	{
	    sa[pos++] = s.getName();
	}

	setListAdapter(new ArrayAdapter<String>(this, R.layout.sportlist, sa));

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
}
