package com.example.iwen.factory.model.db.department;

/**
 * 部门返回列表
 *
 * @author : iwen大大怪
 * create : 12-1 001 22:36
 */
public class DepartmentRspModel {
    /**
     * id   String
     * name  String
     * total  int  //员工数量
     */

    private String id;
    private String name;
    private int total;

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
