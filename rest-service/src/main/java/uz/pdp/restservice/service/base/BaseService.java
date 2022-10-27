package uz.pdp.restservice.service.base;

import admin.response.ApiResponse;

public interface BaseService<T, R, R1> extends ResponseMessage {

    ApiResponse<Object> add(T t);

    ApiResponse<R> getList(int page);

    ApiResponse<R> getList(int page, T t);

    ApiResponse<R> getAllList();

    R1 getById(long id);

    ApiResponse<Object> edit(T t, Long id);

    ApiResponse<Object> delete(long id);

    boolean isNotNull(T t);
}
