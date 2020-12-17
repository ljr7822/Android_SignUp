package com.example.iwen.factory.model.api.department;

/**
 * 部门请求模块
 *
 * @author : iwen大大怪
 * create : 12-1 001 22:32
 */
public class DepartmentModel {
    /**
     * id   String
     * name  String
     * total  int  //员工数量
     */

    private String id;
    private String name;
    private int total;

    public DepartmentModel() {
    }

    public DepartmentModel(String id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
