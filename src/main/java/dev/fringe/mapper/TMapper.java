package dev.fringe.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Select;

public interface TMapper {

	@Select("""
			SELECT 
				* 
			  FROM T
			""")
	List<Map<Object, Object>> select();
}
