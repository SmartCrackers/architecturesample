package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 
 * @author RITESH SINGH
 */
public class HelperUtility {

	/**
	 * @author RITESH SINGH
	 * @param page
	 * @param size
	 * @param sort             attributeName,ASC/DSC
	 * @param totalElements
	 * @return
	 */
	public static Pageable getPageable(Integer page, Integer size, String sort, Long totalElements){
		List<Sort.Order> orders = new ArrayList<>();
        if(sort!=null){
        	String[] propOrderSplit = sort.split(",");
            String property = propOrderSplit[0];
            if (propOrderSplit.length == 1) {
                orders.add(new Sort.Order(property));
            } else {
                Sort.Direction direction
                        = Sort.Direction.fromStringOrNull(propOrderSplit[1]);
                orders.add(new Sort.Order(direction, property));
            }
        }
        
        if(page!=null & size !=null)
        	return new PageRequest(page, size,
                orders.isEmpty() ? null : new Sort(orders));
        return new PageRequest(0, Integer.parseInt(totalElements+""),
                orders.isEmpty() ? null : new Sort(orders));
	}
	
	/**
	 * @author RITESH SINGH
	 * @param page
	 * @return
	 */
	public static <T> Map<String,Object> getPageableResponse(Page<T> page){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("totalPages", page.getTotalPages());
		data.put("totalElements", page.getTotalElements());
		data.put("nextPageable", page.nextPageable());
		data.put("previousPageable", page.previousPageable());
		data.put("sort", page.getSort());
		data.put("currentPageNumber", (page.getNumber()+1));
		
		return data;
	}
	
	/**
	 * @author RITESH SINGH
	 * @param list
	 * @param data
	 */
	public static <T> Map<String,Object> setTotalElements(List<T> list,Map<String,Object> data){
		if(data!=null)
			data.put("totalElements", list!=null ? list.size() : 0);
		return data;
	}
	
	public static String getToken(String key) {
	    String ts = String.valueOf(System.currentTimeMillis());
	    String rand = UUID.randomUUID().toString();
	    return DigestUtils.sha512Hex(ts + rand + key);
	}
}
