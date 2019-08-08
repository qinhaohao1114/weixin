package com.weixin.publicnation.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("列表返回")
public class ListResponse<T> extends SimpleResponse {

	public static final int DEFAULT_PAGE_SIZE = 50;

	@ApiModelProperty(value = "总数", example = "1")
	private Long total; // 总数

	@ApiModelProperty(value = "页大小", example = "20")
	private Integer pageSize; // 页大小

	@ApiModelProperty(value = "总页数", example = "1")
	private Integer totalPage; // 总页数

	@ApiModelProperty(value = "当前页", example = "1")
	private Integer page; // 当前页

	@ApiModelProperty(value = "当前偏移量", example = "20")
	private Integer offset; // 当前偏移量

	public static <T> ListResponse<T> success(List<T> data, long totalCount, int page) {
		return success(data, totalCount, page, 20);
	}

	public static <T> ListResponse<T> success(List<T> data, long totalCount, int page, int pageSize) {
		ListResponse response = new ListResponse();
		response.setData(data);
		response.setStatus(SUCCESS_STATUS);
		response.setMessage(SUCCESS_MESSAGE);

		if (pageSize == 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}

		int totalPage = (int) Math.ceil((double)totalCount / pageSize);

		if (page > totalPage) {
			page = totalPage;
		}
		if (page <= 0) {
			page = 1;
		}

		int offset = pageSize * (page - 1);
		response.setTotal(totalCount);
		response.setPageSize(pageSize);
		response.setTotalPage(totalPage);
		response.setPage(page);
		response.setOffset(offset);
		return response;
	}


}
