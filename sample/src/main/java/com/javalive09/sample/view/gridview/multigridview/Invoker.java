package com.javalive09.sample.view.gridview.multigridview;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoker extends Entry {

	public void showView() {
		LayoutInflater factory = LayoutInflater.from(getViewActivity());
		View view = factory.inflate(R.layout.multigridview, null);
		
		GridView game = (GridView) view.findViewById(R.id.game);
		
        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<10;i++)
        {
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemImage", R.drawable.card_danager_memory);//添加图像资源的ID
			map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText
        	lstImageItem.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(getViewActivity(), //没什么解释
        		                                    lstImageItem,//数据来源 
        		                                    R.layout.grid_item,//night_item的XML实现
        		                                    
        		                                    //动态数组与ImageItem对应的子项        
        		                                    new String[] {"ItemImage","ItemText"}, 
        		                                    
        		                                    //ImageItem的XML文件里面的一个ImageView,两个TextView ID
        		                                    new int[] {R.id.ItemImage,R.id.ItemText});
        
        game.setAdapter(saImageItems);
        
        
		GridView video = (GridView) view.findViewById(R.id.video);
		
        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem2 = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<10;i++)
        {
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemImage", R.drawable.card_danager_memory);//添加图像资源的ID
			map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText
			lstImageItem2.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems2 = new SimpleAdapter(getViewActivity(), //没什么解释
        		                                    lstImageItem2,//数据来源 
        		                                    R.layout.grid_item,//night_item的XML实现
        		                                    
        		                                    //动态数组与ImageItem对应的子项        
        		                                    new String[] {"ItemImage","ItemText"}, 
        		                                    
        		                                    //ImageItem的XML文件里面的一个ImageView,两个TextView ID
        		                                    new int[] {R.id.ItemImage,R.id.ItemText});
        
        video.setAdapter(saImageItems2);
        
        showView(view);
		
	}
	
	

}
