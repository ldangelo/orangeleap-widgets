package com.orangeleap.webtools.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.orangeleap.client.CustomTableRow;
import com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap;

public class CustomTableRowComparator implements Comparator<CustomTableRow> {

	private String fieldNameToCompareBy;
	private Class<?> classTypeToCompare;
	
	public CustomTableRowComparator(String fieldNameToCompareBy, Class<?> classTypeToCompare) {
		this.fieldNameToCompareBy = fieldNameToCompareBy;
		this.classTypeToCompare = classTypeToCompare;
		if (classTypeToCompare != Integer.class  && classTypeToCompare != Long.class 
				&& classTypeToCompare != String.class && classTypeToCompare != Date.class) {
			throw new IllegalArgumentException("Only Integer, Long, String and Date classes are currently supported.");
		}
	}

	@Override
	public int compare(CustomTableRow rowA, CustomTableRow rowB) {
		if (getCustomFieldMapValue(rowA.getCustomFieldMap(), fieldNameToCompareBy) != null && getCustomFieldMapValue(rowB.getCustomFieldMap(), fieldNameToCompareBy) != null) {
			if (classTypeToCompare.equals(Integer.class)) {
				Integer rowAId = Integer.parseInt(getCustomFieldMapValue(rowA.getCustomFieldMap(), fieldNameToCompareBy));
				Integer rowBId = Integer.parseInt(getCustomFieldMapValue(rowB.getCustomFieldMap(), fieldNameToCompareBy));
				return rowAId.compareTo(rowBId);
			} else if (classTypeToCompare.equals(Long.class)) {
				Long rowAId = Long.parseLong(getCustomFieldMapValue(rowA.getCustomFieldMap(), fieldNameToCompareBy));
				Long rowBId = Long.parseLong(getCustomFieldMapValue(rowB.getCustomFieldMap(), fieldNameToCompareBy));
				return rowAId.compareTo(rowBId);
			} else if (classTypeToCompare.equals(String.class)) {
				String rowAId = getCustomFieldMapValue(rowA.getCustomFieldMap(), fieldNameToCompareBy);
				String rowBId = getCustomFieldMapValue(rowB.getCustomFieldMap(), fieldNameToCompareBy);
				return rowAId.compareTo(rowBId);
			} else if (classTypeToCompare.equals(Date.class)) {
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					Date rowAId = formatter.parse(getCustomFieldMapValue(rowA.getCustomFieldMap(), fieldNameToCompareBy));
					Date rowBId = formatter.parse(getCustomFieldMapValue(rowB.getCustomFieldMap(), fieldNameToCompareBy));
					return rowAId.compareTo(rowBId);
				} catch (ParseException e) {
					return 0;
				}
			}
		} else if (getCustomFieldMapValue(rowA.getCustomFieldMap(), fieldNameToCompareBy) == null && getCustomFieldMapValue(rowB.getCustomFieldMap(), fieldNameToCompareBy) != null) {
			return -1;
		} else if (getCustomFieldMapValue(rowA.getCustomFieldMap(), fieldNameToCompareBy) != null && getCustomFieldMapValue(rowB.getCustomFieldMap(), fieldNameToCompareBy) == null) {
			return 1;
		} else {
			return 0;
		}
		return 0;
	}
	
	private String getCustomFieldMapValue(CustomFieldMap cfMap,String index) {
		List<com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry> list = cfMap.getEntry();
		Iterator<com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry> it = list.iterator();
		
		while (it.hasNext()) {
			com.orangeleap.client.AbstractCustomizableEntity.CustomFieldMap.Entry entry = it.next();
			if (entry.getKey().equals(index)) {
				return entry.getValue().getValue();
			}
		}
		return "";
	}
}
