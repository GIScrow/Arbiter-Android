package com.lmn.Arbiter_Android.Loaders;

import java.util.ArrayList;

import com.lmn.Arbiter_Android.BaseClasses.BaseLayer;
import com.lmn.Arbiter_Android.BaseClasses.Layer;
import com.lmn.Arbiter_Android.BaseClasses.Server;
import com.lmn.Arbiter_Android.DatabaseHelpers.TableHelpers.LayersHelper;
import com.lmn.Arbiter_Android.DatabaseHelpers.TableHelpers.ServersHelper;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

public class ChooseBaseLayerLoader extends LayersListLoader {
	
	public ChooseBaseLayerLoader(Activity activity) {
		super(activity);
	}

	@Override
	public ArrayList<Layer> loadInBackground() {
		updateProjectDbHelper();
		
		SQLiteDatabase db = getProjectDbHelper().getWritableDatabase();
		
		ArrayList<Layer> layers = LayersHelper.getLayersHelper().
				getAll(db);
		
		SparseArray<Server> servers = ServersHelper.getServersHelper().
				getAll(getAppDbHelper().getWritableDatabase());
		
		layers = addServerInfoToLayers(layers, servers);
		
		layers.add(new Layer(new BaseLayer("OpenStreetMap", null, null,"OpenStreetMap", null)));
		
		return layers;
	}
}
