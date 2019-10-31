package com.point.common.data;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * API 参数父类
 */
@Data
public class StorageParameter extends WebServiceParameter {


    private List<MultipartFile> multipartFile;

}
