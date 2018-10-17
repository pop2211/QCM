package fr.eni.tp.qcm.utils;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private HashMap<Integer, Float> hm = new HashMap<>();
	private Float total;
	
	public Result(HashMap<Integer, Float> hm) {
		this.hm = hm;
		float sum = 0.0f;
		for (float f : hm.values()) {
		    sum += f;
		}
		this.total = sum * 100 / hm.size();
	}

	public Map<Integer, Float> getHm() {
		return hm;
	}

	public void setHm(HashMap<Integer, Float> hm) {
		this.hm = hm;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
}
	
