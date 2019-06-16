package com.example.photocropdemo.manager;


import com.example.photocropdemo.interfaces.ICropListener;

public class CropManager {
	private static volatile CropManager manager;
	private ICropListener cropListener;
	public static CropManager getInstance(){
		if (manager == null) {
			synchronized (CropManager.class) {
				if (manager == null) {
					manager = new CropManager();
				}
			}
		}
		return manager;
	}
	
	private CropManager(){
		
	}

	public ICropListener getCropListener() {
		return cropListener;
	}

	public void setCropListener(ICropListener cropListener) {
		this.cropListener = cropListener;
	}
	
	
	
	
}
