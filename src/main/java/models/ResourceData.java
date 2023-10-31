package models;

import lombok.Data;

import java.util.List;

@Data
public class ResourceData {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<Resource> data;
    private Support support;
}
